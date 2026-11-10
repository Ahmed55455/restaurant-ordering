package restaurant.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.customer.Customer;
import restaurant.exception.EmptyOrderException;
import restaurant.exception.OrderAlreadyPaidException;
import restaurant.exception.OrderCancelledException;
import restaurant.menu.Food;

import static org.junit.jupiter.api.Assertions.*;

class OrderStateTest {

    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order("O1", new Customer("C1", "Ahmed"));
    }

    private void fillOrder() {
        order.addItem(new Food("Burger", 12.5), 1);
    }

    @Test
    void newOrder_hasStatusNew() {
        assertEquals("NEW", order.getStatus());
    }

    @Test
    void cancel_newOrder_setsStatusCancelled() {
        order.cancel();

        assertEquals("CANCELLED", order.getStatus());
    }

    @Test
    void addItem_afterCancel_throwsOrderCancelledException() {
        order.cancel();

        assertThrows(OrderCancelledException.class,
                () -> order.addItem(new Food("Burger", 12.5), 1));
    }

    @Test
    void removeItem_afterCancel_throwsOrderCancelledException() {
        fillOrder();
        order.cancel();

        assertThrows(OrderCancelledException.class, () -> order.removeItem("Burger"));
    }

    @Test
    void cancel_twice_throwsOrderCancelledException() {
        order.cancel();

        assertThrows(OrderCancelledException.class, () -> order.cancel());
    }

    @Test
    void markPaid_afterCancel_throwsOrderCancelledException() {
        fillOrder();
        order.cancel();

        assertThrows(OrderCancelledException.class, () -> order.markPaid());
    }

    @Test
    void markPaid_withItems_setsStatusPaid() {
        fillOrder();

        order.markPaid();

        assertEquals("PAID", order.getStatus());
    }

    @Test
    void markPaid_withEmptyOrder_throwsEmptyOrderException() {
        assertThrows(EmptyOrderException.class, () -> order.markPaid());
    }

    @Test
    void addItem_afterPaid_throwsOrderAlreadyPaidException() {
        fillOrder();
        order.markPaid();

        assertThrows(OrderAlreadyPaidException.class,
                () -> order.addItem(new Food("Pizza", 15.0), 1));
    }

    @Test
    void removeItem_afterPaid_throwsOrderAlreadyPaidException() {
        fillOrder();
        order.markPaid();

        assertThrows(OrderAlreadyPaidException.class, () -> order.removeItem("Burger"));
    }

    @Test
    void cancel_afterPaid_throwsOrderAlreadyPaidException() {
        fillOrder();
        order.markPaid();

        assertThrows(OrderAlreadyPaidException.class, () -> order.cancel());
    }

    @Test
    void markPaid_twice_throwsOrderAlreadyPaidException() {
        fillOrder();
        order.markPaid();

        assertThrows(OrderAlreadyPaidException.class, () -> order.markPaid());
    }
}