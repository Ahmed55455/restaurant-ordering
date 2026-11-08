package restaurant.exception;

public class OrderCancelledException extends RestaurantException {
    public OrderCancelledException(String message) {
        super(message);
    }
}