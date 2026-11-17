package restaurant.payment;

public class PaymentResult {
    private final String method;
    private final double amountCharged;
    private final double change;

    public PaymentResult(String method, double amountCharged, double change) {
        this.method = method;
        this.amountCharged = amountCharged;
        this.change = change;
    }

    public String getMethod() {
        return method;
    }

    public double getAmountCharged() {
        return amountCharged;
    }

    public double getChange() {
        return change;
    }
}