package enterprise.org.assignment.config;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@org.springframework.boot.context.properties.ConfigurationProperties(prefix = "discount")
@Getter
@Setter
public class DiscountConfigurationProperties {
    private Map<String, String> userPercentage;
    private double flat;
    private double base;
}
