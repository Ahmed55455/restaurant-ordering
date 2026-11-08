package restaurant.customer;

import restaurant.exception.RestaurantException;

public class Customer {
    private final String id;
    private final String name;

    public Customer(String id, String name) {
        if (id == null || id.isBlank()) {
            throw new RestaurantException("Customer id must not be empty");
        }
        if (name == null || name.isBlank()) {
            throw new RestaurantException("Customer name must not be empty");
        }
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}