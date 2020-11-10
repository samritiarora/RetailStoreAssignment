package enterprise.org.assignment;

import enterprise.org.assignment.config.DiscountConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(DiscountConfigurationProperties.class)
public class RetailStoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(RetailStoreApplication.class, args);
    }
}
