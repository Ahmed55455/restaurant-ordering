package restaurant.order;

import restaurant.exception.EmptyOrderException;
import restaurant.menu.MenuItem;

class NewOrderState implements OrderState {

    @Override
    public String getName() {
        return "NEW";
    }

    @Override
    public void addItem(Order order, MenuItem menuItem, int quantity) {
        order.applyAddItem(menuItem, quantity);
    }

    @Override
    public void removeItem(Order order, String itemName) {
        order.applyRemoveItem(itemName);
    }

    @Override
    public void markPaid(Order order) {
        if (order.getItems().isEmpty()) {
            throw new EmptyOrderException("Cannot pay an empty order: " + order.getId());
        }
        order.setState(new PaidOrderState());
    }

    @Override
    public void cancel(Order order) {
        order.setState(new CancelledOrderState());
    }
}