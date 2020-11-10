package enterprise.nagarro.assignment.model;

import enterprise.nagarro.assignment.service.impl.CalculateBill;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
public class Affiliate extends User {
    public static final double USER_APPLICABLE_DISCOUNT = 10d;

    @Override
    public BigDecimal applyUserApplicableDiscount(BigDecimal subTotal) {
        return subTotal.multiply(BigDecimal.ONE.subtract(BigDecimal.valueOf(USER_APPLICABLE_DISCOUNT).divide(CalculateBill.PERCENT)));
    }
}
