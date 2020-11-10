package enterprise.org.assignment.service.impl;


import enterprise.org.assignment.config.DiscountConfigurationProperties;
import enterprise.org.assignment.model.Item;
import enterprise.org.assignment.model.ShoppingCart;
import enterprise.org.assignment.model.User;
import enterprise.org.assignment.service.IUserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.any;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = {"classpath:application.properties"},
        properties = "org.cash.flat.discount=5")
public class CalculateBillServiceTest {
    @Mock
    private IUserService userService;

    @Mock
    private DiscountConfigurationProperties discountConfigurationProperties;

    @InjectMocks
    private CalculateBillService calculateBillService;

    @Before
    public void setUp() {
        Mockito.when(discountConfigurationProperties.getBase()).thenReturn(100d);
        Mockito.when(discountConfigurationProperties.getFlat()).thenReturn(5d);
    }

    @Test
    public void testCalculateBillForGroceryItems() {
        Mockito.when(userService.getUserDiscountFromUserType(any())).thenReturn(30d);

        BigDecimal amountPayable =
                calculateBillService.calculateBill(
                        ShoppingCart.builder()
                                .items(Arrays.asList(Item.builder().quantity(20).price(BigDecimal.TEN).itemType(Item.ItemType.GROCERY).build()))
                                .user(User.builder().userType(User.UserType.EMPLOYEE).relationshipPeriod(1).build())
                                .build());
        Assert.assertEquals(BigDecimal.valueOf(190.0), amountPayable);
    }

    @Test
    public void testCalculateBillForNonGroceryItems() {
        Mockito.when(userService.getUserDiscountFromUserType(any())).thenReturn(10d);
        BigDecimal amountPayable = calculateBillService.calculateBill(
                ShoppingCart.builder()
                        .items(Arrays.asList(Item.builder().quantity(20).price(BigDecimal.TEN).itemType(Item.ItemType.UNCATEGORIZED).build()))
                        .user(User.builder().userType(User.UserType.AFFILIATE).relationshipPeriod(1).build())
                        .build());
        Assert.assertEquals(BigDecimal.valueOf(175.0), amountPayable);
    }

    @Test
    public void testCalculateBillForNonGroceryItemsNoUserDiscount() {
        Mockito.when(userService.getUserDiscountFromUserType(any())).thenReturn(0d);
        BigDecimal amountPayable = calculateBillService.calculateBill(ShoppingCart.builder()
                        .items(Arrays.asList(Item.builder().quantity(19).price(BigDecimal.TEN).itemType(Item.ItemType.UNCATEGORIZED).build()))
                        .user(User.builder().userType(User.UserType.CUSTOMER).relationshipPeriod(1).build())
                        .build());
        Assert.assertEquals(BigDecimal.valueOf(185.0), amountPayable);
    }

}
