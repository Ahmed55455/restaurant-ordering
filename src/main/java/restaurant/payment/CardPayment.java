package restaurant.payment;

import restaurant.exception.InvalidPaymentException;

public class CardPayment implements PaymentStrategy {
    public static final double FEE_RATE = 0.02;

    public CardPayment(String cardNumber) {
        if (cardNumber == null || !cardNumber.matches("\\d{16}")) {
            throw new InvalidPaymentException("Card number must be exactly 16 digits");
        }
    }

    @Override
    public String getMethodName() {
        return "CARD";
    }

    @Override
    public PaymentResult pay(double orderTotal) {
        if (orderTotal <= 0) {
            throw new InvalidPaymentException("Order total must be positive: " + orderTotal);
        }
        double charged = orderTotal * (1 + FEE_RATE);
        return new PaymentResult(getMethodName(), charged, 0.0);
    }
}