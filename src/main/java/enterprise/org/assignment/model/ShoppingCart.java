package enterprise.org.assignment.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ShoppingCart {
    private User user;
    private List<Item> items;
}
