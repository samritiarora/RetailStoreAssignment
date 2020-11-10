package enterprise.org.assignment.service;

import enterprise.org.assignment.model.Item;
import enterprise.org.assignment.model.ShoppingCart;
import enterprise.org.assignment.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface ICalculateBillService {
    BigDecimal calculateBill(final ShoppingCart shoppingCart);
}
