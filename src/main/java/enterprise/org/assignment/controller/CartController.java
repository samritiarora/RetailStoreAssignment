package enterprise.org.assignment.controller;

import enterprise.org.assignment.model.ShoppingCart;
import enterprise.org.assignment.model.User;
import enterprise.org.assignment.service.ICalculateBillService;
import enterprise.org.assignment.service.IUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;

@Slf4j
@RequestMapping(path = "/api/retailstore")
@RestController
public class CartController {

    @Autowired
    private ICalculateBillService calculateBill;

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "Calculate cart value",
            notes = "Calculate cart values based on a shopping caty entity that contains the user as well as items selected by the user",
            response = ShoppingCart.class)
    @PostMapping("calculate-cart")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Valid value of cart"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "User object not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal calculateCartValue(@Valid @RequestBody ShoppingCart shoppingCart) {
        log.trace("Received request to validate cart object");
        return calculateBill.calculateBill(shoppingCart);
    }

}
