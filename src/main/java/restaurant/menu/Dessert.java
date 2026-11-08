package restaurant.menu;

public class Dessert extends MenuItem {

    public Dessert(String name, double price) {
        super(name, price);
    }

    @Override
    public String getCategory() {
        return "DESSERT";
    }
}