package restaurant.exception;

public class ItemNotFoundException extends RestaurantException {
    public ItemNotFoundException(String message) {
        super(message);
    }
}