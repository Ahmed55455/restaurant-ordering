package restaurant.menu;

import org.junit.jupiter.api.Test;
import restaurant.exception.RestaurantException;

import static org.junit.jupiter.api.Assertions.*;

class MenuItemTest {

    @Test
    void createFood_withValidData_setsNamePriceAndCategory() {
        MenuItem item = new Food("Burger", 12.5);

        assertEquals("Burger", item.getName());
        assertEquals(12.5, item.getPrice());
        assertEquals("FOOD", item.getCategory());
    }

    @Test
    void createDrink_withValidData_hasDrinkCategory() {
        MenuItem item = new Drink("Cola", 3.0);

        assertEquals("DRINK", item.getCategory());
    }

    @Test
    void createDessert_withValidData_hasDessertCategory() {
        MenuItem item = new Dessert("Cake", 6.0);

        assertEquals("DESSERT", item.getCategory());
    }

    @Test
    void createItem_withEmptyName_throwsRestaurantException() {
        assertThrows(RestaurantException.class, () -> new Food("  ", 10.0));
    }

    @Test
    void createItem_withNullName_throwsRestaurantException() {
        assertThrows(RestaurantException.class, () -> new Drink(null, 5.0));
    }

    @Test
    void createItem_withZeroPrice_throwsRestaurantException() {
        assertThrows(RestaurantException.class, () -> new Dessert("Cake", 0));
    }

    @Test
    void createItem_withNegativePrice_throwsRestaurantException() {
        assertThrows(RestaurantException.class, () -> new Food("Burger", -4.0));
    }
}