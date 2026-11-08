package restaurant.order;

import restaurant.exception.InvalidQuantityException;
import restaurant.menu.MenuItem;

public class OrderItem {
    private final MenuItem menuItem;
    private final int quantity;

    public OrderItem(MenuItem menuItem, int quantity) {
        if (quantity <= 0) {
            throw new InvalidQuantityException("Quantity must be positive: " + quantity);
        }
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSubtotal() {
        return menuItem.getPrice() * quantity;
    }
}