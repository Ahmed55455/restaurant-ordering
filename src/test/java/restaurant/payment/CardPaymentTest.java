package restaurant.payment;

import org.junit.jupiter.api.Test;
import restaurant.exception.InvalidPaymentException;

import static org.junit.jupiter.api.Assertions.*;

class CardPaymentTest {

    private static final String VALID_CARD = "1234567812345678";

    @Test
    void pay_withValidCard_addsTwoPercentFee() {
        PaymentResult result = new CardPayment(VALID_CARD).pay(100.0);

        assertEquals("CARD", result.getMethod());
        assertEquals(102.0, result.getAmountCharged(), 0.001);
    }

    @Test
    void pay_withValidCard_givesNoChange() {
        PaymentResult result = new CardPayment(VALID_CARD).pay(50.0);

        assertEquals(0.0, result.getChange());
    }

    @Test
    void pay_withZeroTotal_throwsInvalidPaymentException() {
        CardPayment card = new CardPayment(VALID_CARD);

        assertThrows(InvalidPaymentException.class, () -> card.pay(0));
    }

    @Test
    void createCardPayment_withShortNumber_throwsInvalidPaymentException() {
        assertThrows(InvalidPaymentException.class, () -> new CardPayment("12345"));
    }

    @Test
    void createCardPayment_withLettersInNumber_throwsInvalidPaymentException() {
        assertThrows(InvalidPaymentException.class,
                () -> new CardPayment("1234abcd12345678"));
    }

    @Test
    void createCardPayment_withNullNumber_throwsInvalidPaymentException() {
        assertThrows(InvalidPaymentException.class, () -> new CardPayment(null));
    }
}