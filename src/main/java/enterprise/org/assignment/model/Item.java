package enterprise.org.assignment.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class Item {
    private String itemDescription;
    private ItemType itemType;
    private BigDecimal price;
    private int quantity;

    public enum ItemType {
        GROCERY, UNCATEGORIZED
    }
}
