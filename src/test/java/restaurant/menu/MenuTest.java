package restaurant.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.exception.ItemNotFoundException;
import restaurant.exception.RestaurantException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    private Menu menu;

    @BeforeEach
    void setUp() {
        menu = new Menu();
    }

    @Test
    void addItem_withValidItem_addsItToMenu() {
        menu.addItem(new Food("Burger", 12.5));

        assertEquals(1, menu.getItems().size());
    }

    @Test
    void addItem_withNullItem_throwsRestaurantException() {
        assertThrows(RestaurantException.class, () -> menu.addItem(null));
    }

    @Test
    void addItem_withDuplicateName_throwsRestaurantException() {
        menu.addItem(new Food("Burger", 12.5));

        assertThrows(RestaurantException.class,
                () -> menu.addItem(new Food("burger", 10.0)));
    }

    @Test
    void getItems_returnedList_cannotBeModified() {
        menu.addItem(new Food("Burger", 12.5));

        assertThrows(UnsupportedOperationException.class,
                () -> menu.getItems().add(new Drink("Cola", 3.0)));
    }

    @Test
    void findItem_withExistingName_returnsItem() {
        menu.addItem(new Drink("Cola", 3.0));

        MenuItem found = menu.findItem("cola");

        assertEquals("Cola", found.getName());
    }

    @Test
    void findItem_withUnknownName_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> menu.findItem("Pizza"));
    }

    @Test
    void getItemsByCategory_withMatchingCategory_returnsOnlyThoseItems() {
        menu.addItem(new Food("Burger", 12.5));
        menu.addItem(new Drink("Cola", 3.0));
        menu.addItem(new Drink("Tea", 2.0));

        List<MenuItem> drinks = menu.getItemsByCategory("DRINK");

        assertEquals(2, drinks.size());
    }

    @Test
    void getItemsByCategory_withNoMatch_returnsEmptyList() {
        menu.addItem(new Food("Burger", 12.5));

        assertTrue(menu.getItemsByCategory("DESSERT").isEmpty());
    }
}