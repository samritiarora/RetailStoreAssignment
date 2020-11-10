package enterprise.nagarro.assignment;

import enterprise.nagarro.assignment.model.Employee;
import enterprise.nagarro.assignment.model.Item;
import enterprise.nagarro.assignment.service.ICalculateBill;
import enterprise.nagarro.assignment.service.impl.CalculateBill;

import java.math.BigDecimal;
import java.util.Arrays;

public class RetailStoreApplication {
    public static void main(String[] args) {
        ICalculateBill calculateBillService = new CalculateBill();
        calculateBillService.calculateBill(Employee.builder().build(),
                Arrays.asList(Item.builder().quantity(20).price(BigDecimal.TEN).itemType(Item.ItemType.GROCERY).build()));
    }
}
