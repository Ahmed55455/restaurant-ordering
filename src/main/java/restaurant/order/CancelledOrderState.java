package restaurant.order;

import restaurant.exception.OrderCancelledException;
import restaurant.menu.MenuItem;

class CancelledOrderState implements OrderState {

    @Override
    public String getName() {
        return "CANCELLED";
    }

    @Override
    public void addItem(Order order, MenuItem menuItem, int quantity) {
        throw new OrderCancelledException("Cannot add items to a cancelled order: " + order.getId());
    }

    @Override
    public void removeItem(Order order, String itemName) {
        throw new OrderCancelledException("Cannot remove items from a cancelled order: " + order.getId());
    }

    @Override
    public void markPaid(Order order) {
        throw new OrderCancelledException("Cannot pay a cancelled order: " + order.getId());
    }

    @Override
    public void cancel(Order order) {
        throw new OrderCancelledException("Order is already cancelled: " + order.getId());
    }
}