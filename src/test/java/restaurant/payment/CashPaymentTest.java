package restaurant.payment;

import org.junit.jupiter.api.Test;
import restaurant.exception.InvalidPaymentException;

import static org.junit.jupiter.api.Assertions.*;

class CashPaymentTest {

    @Test
    void pay_withExactAmount_chargesTotalAndGivesNoChange() {
        PaymentResult result = new CashPayment(30.0).pay(30.0);

        assertEquals("CASH", result.getMethod());
        assertEquals(30.0, result.getAmountCharged());
        assertEquals(0.0, result.getChange());
    }

    @Test
    void pay_withLargerAmount_returnsChange() {
        PaymentResult result = new CashPayment(50.0).pay(30.0);

        assertEquals(30.0, result.getAmountCharged());
        assertEquals(20.0, result.getChange());
    }

    @Test
    void pay_withLessThanTotal_throwsInvalidPaymentException() {
        CashPayment cash = new CashPayment(10.0);

        assertThrows(InvalidPaymentException.class, () -> cash.pay(30.0));
    }

    @Test
    void createCashPayment_withZeroAmount_throwsInvalidPaymentException() {
        assertThrows(InvalidPaymentException.class, () -> new CashPayment(0));
    }

    @Test
    void createCashPayment_withNegativeAmount_throwsInvalidPaymentException() {
        assertThrows(InvalidPaymentException.class, () -> new CashPayment(-5.0));
    }
}