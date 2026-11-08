package restaurant.menu;

import restaurant.exception.RestaurantException;

public abstract class MenuItem {
    private final String name;
    private final double price;

    protected MenuItem(String name, double price) {
        if (name == null || name.isBlank()) {
            throw new RestaurantException("Item name must not be empty");
        }
        if (price <= 0) {
            throw new RestaurantException("Item price must be positive: " + price);
        }
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public abstract String getCategory();
}