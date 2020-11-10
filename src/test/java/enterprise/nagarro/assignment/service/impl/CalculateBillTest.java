package enterprise.nagarro.assignment.service.impl;


import enterprise.nagarro.assignment.model.Affiliate;
import enterprise.nagarro.assignment.model.Customer;
import enterprise.nagarro.assignment.model.Employee;
import enterprise.nagarro.assignment.model.Item;
import enterprise.nagarro.assignment.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class CalculateBillTest {
    @InjectMocks
    private CalculateBill calculateBill;
    private User affiliate;
    private User employee;
    private User customer;

    @Before
    public void setUp() {
        affiliate = new Affiliate();
        employee = new Employee();
        customer = new Customer();
        customer.setRelationshipPeriod(1);
    }

    @Test
    public void testCalculateBillForGroceryItems() {
        BigDecimal amountPayable = calculateBill.calculateBill(employee,
                Arrays.asList(Item.builder().quantity(20).price(BigDecimal.TEN).itemType(Item.ItemType.GROCERY).build()));
        Assert.assertEquals(BigDecimal.valueOf(135), amountPayable);
    }

    @Test
    public void testCalculateBillForNonGroceryItems() {
        BigDecimal amountPayable = calculateBill.calculateBill(affiliate,
                Arrays.asList(Item.builder().quantity(20).price(BigDecimal.TEN).itemType(Item.ItemType.NON_GROCERY).build()));
        Assert.assertEquals(BigDecimal.valueOf(175), amountPayable);
    }

    @Test
    public void testCalculateBillForNonGroceryItemsNoUserDiscount() {
        BigDecimal amountPayable = calculateBill.calculateBill(customer,
                Arrays.asList(Item.builder().quantity(19).price(BigDecimal.TEN).itemType(Item.ItemType.NON_GROCERY).build()));
        Assert.assertEquals(BigDecimal.valueOf(185), amountPayable);
    }

}
