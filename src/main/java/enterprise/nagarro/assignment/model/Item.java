package enterprise.nagarro.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private String itemDescription;
    private ItemType itemType;
    private BigDecimal price;
    private int quantity;

    public enum ItemType {
        GROCERY, NON_GROCERY
    }
}
