package enterprise.org.assignment.service.impl;

import enterprise.org.assignment.config.DiscountConfigurationProperties;
import enterprise.org.assignment.exception.ServiceException;
import enterprise.org.assignment.model.User;
import enterprise.org.assignment.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService implements IUserService {

    @Autowired
    private DiscountConfigurationProperties configurationProperties;

    @Override
    public Double getUserDiscountFromUserType(User user) {
        if (user.getUserType() == null) {
            throw new ServiceException("Invalid user type");
        }
        String userApplicableDiscount = configurationProperties.getUserPercentage().get(user.getUserType().toString());
        double userDiscount = Double.parseDouble(userApplicableDiscount);
        if (user.getUserType().equals(User.UserType.CUSTOMER) && user.getRelationshipPeriod() < 2) {
            userDiscount = 0d;
        }
        log.debug("UserService:: discount {}", userDiscount);
        return userDiscount;
    }
}
