package restaurant.payment;

import restaurant.exception.InvalidPaymentException;

public class CashPayment implements PaymentStrategy {
    private final double amountGiven;

    public CashPayment(double amountGiven) {
        if (amountGiven <= 0) {
            throw new InvalidPaymentException("Cash amount must be positive: " + amountGiven);
        }
        this.amountGiven = amountGiven;
    }

    @Override
    public String getMethodName() {
        return "CASH";
    }

    @Override
    public PaymentResult pay(double orderTotal) {
        if (amountGiven < orderTotal) {
            throw new InvalidPaymentException(
                    "Cash given (" + amountGiven + ") is less than the total (" + orderTotal + ")");
        }
        return new PaymentResult(getMethodName(), orderTotal, amountGiven - orderTotal);
    }
}