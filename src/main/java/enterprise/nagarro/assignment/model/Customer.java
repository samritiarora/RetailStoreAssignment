package enterprise.nagarro.assignment.model;

import enterprise.nagarro.assignment.service.impl.CalculateBill;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Customer extends User {

    public static final double USER_APPLICABLE_DISCOUNT = 5d;

    @Override
    public BigDecimal applyUserApplicableDiscount(BigDecimal subTotal) {
        if (getRelationshipPeriod() >= 2) {
            return subTotal.multiply(BigDecimal.ONE.subtract(BigDecimal.valueOf(USER_APPLICABLE_DISCOUNT).divide(CalculateBill.PERCENT)));
        } else {
            return subTotal;
        }
    }
}
