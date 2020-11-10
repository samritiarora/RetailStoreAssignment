package enterprise.org.assignment.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Builder
public class User {
    private int relationshipPeriod;
    private UserType userType;

    public enum UserType {
        EMPLOYEE, AFFILIATE, CUSTOMER
    }
}
