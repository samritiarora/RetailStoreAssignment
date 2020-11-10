package enterprise.org.assignment.controller;

import enterprise.org.assignment.model.ShoppingCart;
import enterprise.org.assignment.service.ICalculateBillService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class CartControllerTest {
    @InjectMocks
    private CartController cartController;

    @Mock
    private ICalculateBillService calculateBillService;

    @Test
    public void calculateCartValue() {
        Mockito.when(calculateBillService.calculateBill(any(ShoppingCart.class))).thenReturn(BigDecimal.TEN);
        Assert.assertEquals(BigDecimal.TEN, cartController.calculateCartValue(ShoppingCart.builder().build()));
    }
}
