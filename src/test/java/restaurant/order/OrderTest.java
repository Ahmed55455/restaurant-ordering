package restaurant.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.customer.Customer;
import restaurant.exception.InvalidQuantityException;
import restaurant.exception.ItemNotFoundException;
import restaurant.exception.RestaurantException;
import restaurant.menu.Drink;
import restaurant.menu.Food;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Customer customer;
    private Order order;

    @BeforeEach
    void setUp() {
        customer = new Customer("C1", "Ahmed");
        order = new Order("O1", customer);
    }

    @Test
    void createOrder_withValidData_setsIdAndCustomer() {
        assertEquals("O1", order.getId());
        assertEquals(customer, order.getCustomer());
        assertTrue(order.getItems().isEmpty());
    }

    @Test
    void createOrder_withEmptyId_throwsRestaurantException() {
        assertThrows(RestaurantException.class, () -> new Order(" ", customer));
    }

    @Test
    void createOrder_withNullCustomer_throwsRestaurantException() {
        assertThrows(RestaurantException.class, () -> new Order("O1", null));
    }

    @Test
    void addItem_withValidItem_addsOneLine() {
        order.addItem(new Food("Burger", 12.5), 2);

        assertEquals(1, order.getItems().size());
        assertEquals(2, order.getItems().get(0).getQuantity());
    }

    @Test
    void addItem_withSameItemTwice_mergesQuantities() {
        order.addItem(new Food("Burger", 12.5), 2);
        order.addItem(new Food("burger", 12.5), 3);

        assertEquals(1, order.getItems().size());
        assertEquals(5, order.getItems().get(0).getQuantity());
    }

    @Test
    void addItem_withZeroQuantity_throwsInvalidQuantityException() {
        assertThrows(InvalidQuantityException.class,
                () -> order.addItem(new Food("Burger", 12.5), 0));
    }

    @Test
    void addItem_withNullItem_throwsRestaurantException() {
        assertThrows(RestaurantException.class, () -> order.addItem(null, 1));
    }

    @Test
    void removeItem_withExistingName_removesTheLine() {
        order.addItem(new Food("Burger", 12.5), 1);
        order.addItem(new Drink("Cola", 3.0), 1);

        order.removeItem("burger");

        assertEquals(1, order.getItems().size());
        assertEquals("Cola", order.getItems().get(0).getMenuItem().getName());
    }

    @Test
    void removeItem_withUnknownName_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> order.removeItem("Pizza"));
    }

    @Test
    void getItems_returnedList_cannotBeModified() {
        order.addItem(new Food("Burger", 12.5), 1);

        assertThrows(UnsupportedOperationException.class,
                () -> order.getItems().clear());
    }

    @Test
    void getTotal_withMultipleItems_returnsSumOfSubtotals() {
        order.addItem(new Food("Burger", 12.5), 2);
        order.addItem(new Drink("Cola", 3.0), 1);

        assertEquals(28.0, order.getTotal());
    }

    @Test
    void getTotal_withEmptyOrder_returnsZero() {
        assertEquals(0.0, order.getTotal());
    }

    @Test
    void getTotal_afterRemovingItem_excludesRemovedItem() {
        order.addItem(new Food("Burger", 12.5), 2);
        order.addItem(new Drink("Cola", 3.0), 1);

        order.removeItem("Cola");

        assertEquals(25.0, order.getTotal());
    }
}