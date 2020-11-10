package enterprise.org.assignment.service.impl;

import enterprise.org.assignment.model.User;
import enterprise.org.assignment.service.IUserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
@Getter
@Setter
@ConfigurationProperties(prefix = "${org.user.percentage}")
public class UserService implements IUserService {
//    @Value("${org.user.percentage.discount}")
    private Map<User.UserType, Double> discount;

    @Override
    public Double getUserDiscountFromUserType(User user) {
        Double userApplicableDiscount = discount.get(user.getUserType());
        if (user.getUserType().equals(User.UserType.CUSTOMER) && user.getRelationshipPeriod() < 2) {
            userApplicableDiscount = 0d;
        }
        return userApplicableDiscount;
    }
}
