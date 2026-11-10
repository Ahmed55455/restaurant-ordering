package restaurant.order;

import restaurant.menu.MenuItem;

interface OrderState {
    String getName();

    void addItem(Order order, MenuItem menuItem, int quantity);

    void removeItem(Order order, String itemName);

    void markPaid(Order order);

    void cancel(Order order);
}