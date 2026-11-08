package restaurant.exception;

public class OrderAlreadyPaidException extends RestaurantException {
    public OrderAlreadyPaidException(String message) {
        super(message);
    }
}