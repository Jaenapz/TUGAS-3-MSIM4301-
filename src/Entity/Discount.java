package Entity;

public class Discount extends MenuItem{

    private double discount;

    public Discount(String name, double price, String category, double discount) {
        super(name, price, category);
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public void showMenu() {
        System.out.println(getName() + " - Diskon: " + discount + "%");
    }

}
