package enterprise.nagarro.assignment.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public abstract class User {
    private int relationshipPeriod;
    public abstract BigDecimal applyUserApplicableDiscount(BigDecimal subTotal);
}
