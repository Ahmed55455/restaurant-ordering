package restaurant.menu;

public class Drink extends MenuItem {

    public Drink(String name, double price) {
        super(name, price);
    }

    @Override
    public String getCategory() {
        return "DRINK";
    }
}