package restaurant.menu;

import restaurant.exception.ItemNotFoundException;
import restaurant.exception.RestaurantException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Menu {
    private final List<MenuItem> items = new ArrayList<>();

    public void addItem(MenuItem item) {
        if (item == null) {
            throw new RestaurantException("Cannot add a null item to the menu");
        }
        if (containsName(item.getName())) {
            throw new RestaurantException("Item already exists in the menu: " + item.getName());
        }
        items.add(item);
    }

    public List<MenuItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public MenuItem findItem(String name) {
        for (MenuItem item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        throw new ItemNotFoundException("Item not found in the menu: " + name);
    }

    public List<MenuItem> getItemsByCategory(String category) {
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem item : items) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                result.add(item);
            }
        }
        return result;
    }

    private boolean containsName(String name) {
        for (MenuItem item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}