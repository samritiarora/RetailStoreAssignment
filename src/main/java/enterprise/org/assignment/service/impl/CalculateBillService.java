package enterprise.org.assignment.service.impl;

import enterprise.org.assignment.commons.Constants;
import enterprise.org.assignment.config.DiscountConfigurationProperties;
import enterprise.org.assignment.model.Item;
import enterprise.org.assignment.model.ShoppingCart;
import enterprise.org.assignment.service.ICalculateBillService;
import enterprise.org.assignment.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service class to calculate bill of the retail store user
 */
@Service
@Slf4j
public class CalculateBillService implements ICalculateBillService {

    private IUserService userService;
    private DiscountConfigurationProperties discountConfigurationProperties;

    @Autowired
    public CalculateBillService(IUserService userService, DiscountConfigurationProperties discountConfigurationProperties) {
        this.userService = userService;
        this.discountConfigurationProperties = discountConfigurationProperties;
    }

    /**
     * Calculates the net payable amount after applying discounts.
     * There can be two types of discounts:
     * - User specific: applicable only on non grocery items
     * - Flat discount: for every $100, there is a $5 discount
     *
     * @param shoppingCart the user for which we are calculating the bill and  the list of items that the user
     *                     has purchased
     * @return Returns the net payable amount
     */
    public BigDecimal calculateBill(final ShoppingCart shoppingCart) {

        Map<Boolean, BigDecimal> subTotals =
                shoppingCart.getItems().stream()
                        .collect(Collectors.groupingBy(selectedItem -> selectedItem.getItemType().equals(Item.ItemType.GROCERY),
                                Collectors.reducing(BigDecimal.ZERO, item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())), BigDecimal::add)));

        BigDecimal grocerySubTotal = subTotals.get(Boolean.TRUE) == null ? BigDecimal.ZERO :
                subTotals.get(Boolean.TRUE);
        BigDecimal nonGrocerySubTotal = subTotals.get(Boolean.FALSE) == null ? BigDecimal.ZERO :
                subTotals.get(Boolean.FALSE);

        BigDecimal subTotal = grocerySubTotal.add(nonGrocerySubTotal);
        // apply user discount
        Double userDiscount = userService.getUserDiscountFromUserType(shoppingCart.getUser());
        BigDecimal userDiscountValue =
                nonGrocerySubTotal.multiply(BigDecimal.valueOf(userDiscount)).divide(Constants.PERCENT);
        subTotal = subTotal.subtract(userDiscountValue);

        //  every $100 on the bill, there would be a $ 5 discount
        BigDecimal modResult = subTotal.divide(BigDecimal.valueOf(discountConfigurationProperties.getBase()), 0,
                RoundingMode.FLOOR);
        BigDecimal flatDiscountValue =
                modResult.multiply(BigDecimal.valueOf(discountConfigurationProperties.getFlat()));
        log.debug("CalculateBillService:: discount {}", subTotal);
        return subTotal.subtract(flatDiscountValue);
    }

}
