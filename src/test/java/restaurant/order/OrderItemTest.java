package restaurant.order;

import org.junit.jupiter.api.Test;
import restaurant.exception.InvalidQuantityException;
import restaurant.menu.Food;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemTest {

    @Test
    void createOrderItem_withValidQuantity_setsItemAndQuantity() {
        Food burger = new Food("Burger", 12.5);

        OrderItem line = new OrderItem(burger, 2);

        assertEquals(burger, line.getMenuItem());
        assertEquals(2, line.getQuantity());
    }

    @Test
    void getSubtotal_withQuantityTwo_returnsPriceTimesQuantity() {
        OrderItem line = new OrderItem(new Food("Burger", 12.5), 2);

        assertEquals(25.0, line.getSubtotal());
    }

    @Test
    void createOrderItem_withZeroQuantity_throwsInvalidQuantityException() {
        assertThrows(InvalidQuantityException.class,
                () -> new OrderItem(new Food("Burger", 12.5), 0));
    }

    @Test
    void createOrderItem_withNegativeQuantity_throwsInvalidQuantityException() {
        assertThrows(InvalidQuantityException.class,
                () -> new OrderItem(new Food("Burger", 12.5), -1));
    }
}