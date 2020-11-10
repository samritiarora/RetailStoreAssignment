package enterprise.org.assignment.service;

import enterprise.org.assignment.model.ShoppingCart;

import java.math.BigDecimal;

public interface ICalculateBillService {
    BigDecimal calculateBill(final ShoppingCart shoppingCart);
}
