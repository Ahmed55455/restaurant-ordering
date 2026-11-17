package restaurant.payment;

public interface PaymentStrategy {
    String getMethodName();

    PaymentResult pay(double orderTotal);
}