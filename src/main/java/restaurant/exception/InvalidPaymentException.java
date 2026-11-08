package restaurant.exception;

public class InvalidPaymentException extends RestaurantException {
    public InvalidPaymentException(String message) {
        super(message);
    }
}