package enterprise.org.assignment;

import enterprise.org.assignment.model.Item;
import enterprise.org.assignment.service.ICalculateBillService;
import enterprise.org.assignment.service.impl.CalculateBillService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Arrays;

@SpringBootApplication
public class RetailStoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(RetailStoreApplication.class, args);
    }
}
