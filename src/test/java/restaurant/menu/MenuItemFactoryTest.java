package restaurant.menu;

import org.junit.jupiter.api.Test;
import restaurant.exception.RestaurantException;

import static org.junit.jupiter.api.Assertions.*;

class MenuItemFactoryTest {

    @Test
    void create_withFoodType_returnsFood() {
        MenuItem item = MenuItemFactory.create("FOOD", "Burger", 10.0);

        assertInstanceOf(Food.class, item);
    }

    @Test
    void create_withDrinkType_returnsDrink() {
        assertInstanceOf(Drink.class, MenuItemFactory.create("DRINK", "Cola", 3.0));
    }

    @Test
    void create_withDessertType_returnsDessert() {
        assertInstanceOf(Dessert.class, MenuItemFactory.create("DESSERT", "Cake", 6.0));
    }

    @Test
    void create_withLowercaseType_stillWorks() {
        assertEquals("FOOD", MenuItemFactory.create("food", "Burger", 10.0).getCategory());
    }

    @Test
    void create_withUnknownType_throwsRestaurantException() {
        assertThrows(RestaurantException.class, () -> MenuItemFactory.create("SOUP", "Soup", 5.0));
    }

    @Test
    void create_withNullType_throwsRestaurantException() {
        assertThrows(RestaurantException.class, () -> MenuItemFactory.create(null, "Soup", 5.0));
    }
}