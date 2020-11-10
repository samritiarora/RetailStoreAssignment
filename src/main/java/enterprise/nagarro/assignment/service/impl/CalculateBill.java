package enterprise.nagarro.assignment.service.impl;

import enterprise.nagarro.assignment.model.Item;
import enterprise.nagarro.assignment.model.User;
import enterprise.nagarro.assignment.service.ICalculateBill;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service class to calculate bill of the retail store user
 */
public class CalculateBill implements ICalculateBill {
    public static final BigDecimal PERCENT = BigDecimal.valueOf(100);
    public static final BigDecimal FIVE = BigDecimal.valueOf(5);

    /**
     * Calculates the net payable amount after applying discounts.
     * There can be two types of discounts:
     * - User specific: applicable only on non grocery items
     * - Flat discount: for every $100, there is a $5 discount
     *
     * @param user          the user for which we are calculating the bill
     * @param selectedItems the list of items that the user has purchased
     * @return Returns the net payable amount
     */
    public BigDecimal calculateBill(final User user, final List<? extends Item> selectedItems) {
        Map<Boolean, BigDecimal> subTotals =
                selectedItems.stream()
                        .collect(Collectors.groupingBy(selectedItem -> selectedItem.getItemType().equals(Item.ItemType.GROCERY),
                                Collectors.reducing(BigDecimal.ZERO, item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())), BigDecimal::add)));

        BigDecimal grocerySubTotal = subTotals.get(Boolean.TRUE) == null ? BigDecimal.ZERO :
                subTotals.get(Boolean.TRUE);
        BigDecimal nonGrocerySubTotal = subTotals.get(Boolean.FALSE) == null ? BigDecimal.ZERO :
                subTotals.get(Boolean.FALSE);

        BigDecimal subTotal = grocerySubTotal.add(nonGrocerySubTotal);
        // apply user discount
        subTotal = user.applyUserApplicableDiscount(subTotal);
        //TODO: Fix me
        subTotal = new BigDecimal(subTotal.doubleValue());
        //  every $100 on the bill, there would be a $ 5 discount
        BigDecimal flatDiscount = subTotal.divide(PERCENT, RoundingMode.FLOOR).multiply(FIVE);

        return subTotal.subtract(flatDiscount);
    }

}
