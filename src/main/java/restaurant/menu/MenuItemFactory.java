package restaurant.menu;

import restaurant.exception.RestaurantException;

public class MenuItemFactory {

    private MenuItemFactory() {
    }

    public static MenuItem create(String type, String name, double price) {
        if (type == null) {
            throw new RestaurantException("Item type must not be null");
        }
        switch (type.trim().toUpperCase()) {
            case "FOOD":
                return new Food(name, price);
            case "DRINK":
                return new Drink(name, price);
            case "DESSERT":
                return new Dessert(name, price);
            default:
                throw new RestaurantException("Unknown item type: " + type);
        }
    }
}