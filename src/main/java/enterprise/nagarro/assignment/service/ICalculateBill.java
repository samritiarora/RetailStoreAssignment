package enterprise.nagarro.assignment.service;

import enterprise.nagarro.assignment.model.Item;
import enterprise.nagarro.assignment.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface ICalculateBill {
    BigDecimal calculateBill(final User user, final List<? extends Item> selectedItems);
}
