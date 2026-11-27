package restaurant.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.customer.Customer;
import restaurant.exception.EmptyOrderException;
import restaurant.exception.InvalidPaymentException;
import restaurant.exception.OrderAlreadyPaidException;
import restaurant.exception.OrderCancelledException;
import restaurant.exception.RestaurantException;
import restaurant.menu.Food;
import restaurant.payment.CardPayment;
import restaurant.payment.CashPayment;
import restaurant.payment.PaymentResult;

import static org.junit.jupiter.api.Assertions.*;

class OrderPayTest {

    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order("O1", new Customer("C1", "Ahmed"));
        order.addItem(new Food("Burger", 10.0), 2);
    }

    @Test
    void pay_withEnoughCash_marksOrderPaidAndReturnsChange() {
        PaymentResult result = order.pay(new CashPayment(50.0));

        assertEquals("PAID", order.getStatus());
        assertEquals(20.0, result.getAmountCharged());
        assertEquals(30.0, result.getChange());
    }

    @Test
    void pay_withCard_chargesTotalPlusFee() {
        PaymentResult result = order.pay(new CardPayment("1234567812345678"));

        assertEquals(20.4, result.getAmountCharged(), 0.001);
    }

    @Test
    void pay_withInsufficientCash_throwsAndKeepsOrderNew() {
        assertThrows(InvalidPaymentException.class, () -> order.pay(new CashPayment(5.0)));

        assertEquals("NEW", order.getStatus());
    }

    @Test
    void pay_withEmptyOrder_throwsEmptyOrderException() {
        Order empty = new Order("O2", new Customer("C1", "Ahmed"));

        assertThrows(EmptyOrderException.class, () -> empty.pay(new CashPayment(50.0)));
    }

    @Test
    void pay_whenAlreadyPaid_throwsOrderAlreadyPaidException() {
        order.pay(new CashPayment(50.0));

        assertThrows(OrderAlreadyPaidException.class, () -> order.pay(new CashPayment(50.0)));
    }

    @Test
    void pay_whenCancelled_throwsOrderCancelledException() {
        order.cancel();

        assertThrows(OrderCancelledException.class, () -> order.pay(new CashPayment(50.0)));
    }

    @Test
    void pay_withNullStrategy_throwsRestaurantException() {
        assertThrows(RestaurantException.class, () -> order.pay(null));
    }
}