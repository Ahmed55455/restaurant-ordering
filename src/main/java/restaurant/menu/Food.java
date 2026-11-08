package restaurant.menu;

public class Food extends MenuItem {

    public Food(String name, double price) {
        super(name, price);
    }

    @Override
    public String getCategory() {
        return "FOOD";
    }
}