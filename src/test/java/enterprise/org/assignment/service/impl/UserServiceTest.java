package enterprise.org.assignment.service.impl;

import enterprise.org.assignment.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {
    private UserService userService;
    @Before
    public void setUp() {
        ReflectionTestUtils.setField(userService, "discount", new HashMap());
    }

    @Test
    public void testCalculateBillForGroceryItems() {
        Mockito.when(userService.getUserDiscountFromUserType(any())).thenReturn(30d);
        Double amountPayable =
                userService.getUserDiscountFromUserType(User.builder().userType(User.UserType.EMPLOYEE).relationshipPeriod(1).build());
        Assert.assertEquals(BigDecimal.valueOf(190.0), amountPayable);
    }
}
