package restaurant.order;

import restaurant.exception.OrderAlreadyPaidException;
import restaurant.menu.MenuItem;

class PaidOrderState implements OrderState {

    @Override
    public String getName() {
        return "PAID";
    }

    @Override
    public void addItem(Order order, MenuItem menuItem, int quantity) {
        throw new OrderAlreadyPaidException("Cannot add items to a paid order: " + order.getId());
    }

    @Override
    public void removeItem(Order order, String itemName) {
        throw new OrderAlreadyPaidException("Cannot remove items from a paid order: " + order.getId());
    }

    @Override
    public void validatePayable(Order order) {
        throw new OrderAlreadyPaidException("Order is already paid: " + order.getId());
    }

    @Override
    public void markPaid(Order order) {
        validatePayable(order);
    }

    @Override
    public void cancel(Order order) {
        throw new OrderAlreadyPaidException("Cannot cancel a paid order: " + order.getId());
    }
}