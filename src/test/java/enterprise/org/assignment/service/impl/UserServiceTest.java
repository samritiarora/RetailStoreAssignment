package enterprise.org.assignment.service.impl;

import enterprise.org.assignment.config.DiscountConfigurationProperties;
import enterprise.org.assignment.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private DiscountConfigurationProperties discountConfigurationProperties;
    @Before
    public void setUp() {
        Map<String, String> discount = new HashMap();
        discount.put("EMPLOYEE", "30");
        discount.put("CUSTOMER", "5");
        Mockito.when(discountConfigurationProperties.getUserPercentage()).thenReturn(discount);
    }

    @Test
    public void testDiscountValueForEmployee() {
        Double amountPayable =
                userService.getUserDiscountFromUserType(User.builder().userType(User.UserType.EMPLOYEE).relationshipPeriod(1).build());
        Assert.assertEquals(Double.valueOf(30d), amountPayable);
    }

    @Test
    public void testDiscountValueForCustomer() {
        Double amountPayable =
                userService.getUserDiscountFromUserType(User.builder().userType(User.UserType.CUSTOMER).relationshipPeriod(1).build());
        Assert.assertEquals(Double.valueOf(0d), amountPayable);
    }
}
