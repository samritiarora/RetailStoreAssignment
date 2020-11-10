package enterprise.org.assignment.model;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
public class User {
    private int relationshipPeriod;
    @NotNull
    private UserType userType;

    public enum UserType {
        EMPLOYEE, AFFILIATE, CUSTOMER
    }
}
