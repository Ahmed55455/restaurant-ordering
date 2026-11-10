package restaurant.order;

import restaurant.customer.Customer;
import restaurant.exception.InvalidQuantityException;
import restaurant.exception.ItemNotFoundException;
import restaurant.exception.RestaurantException;
import restaurant.menu.MenuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private final String id;
    private final Customer customer;
    private final List<OrderItem> items = new ArrayList<>();

    public Order(String id, Customer customer) {
        if (id == null || id.isBlank()) {
            throw new RestaurantException("Order id must not be empty");
        }
        if (customer == null) {
            throw new RestaurantException("Order must have a customer");
        }
        this.id = id;
        this.customer = customer;
    }

    public String getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addItem(MenuItem menuItem, int quantity) {
        if (menuItem == null) {
            throw new RestaurantException("Cannot add a null item to the order");
        }
        if (quantity <= 0) {
            throw new InvalidQuantityException("Quantity must be positive: " + quantity);
        }
        for (int i = 0; i < items.size(); i++) {
            OrderItem existing = items.get(i);
            if (existing.getMenuItem().getName().equalsIgnoreCase(menuItem.getName())) {
                items.set(i, new OrderItem(existing.getMenuItem(), existing.getQuantity() + quantity));
                return;
            }
        }
        items.add(new OrderItem(menuItem, quantity));
    }

    public void removeItem(String itemName) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getMenuItem().getName().equalsIgnoreCase(itemName)) {
                items.remove(i);
                return;
            }
        }
        throw new ItemNotFoundException("Item not in the order: " + itemName);
    }
}