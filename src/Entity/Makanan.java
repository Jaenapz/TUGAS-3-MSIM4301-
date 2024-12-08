package Entity;

public class Makanan extends MenuItem {

    private String jenisMakanan;

    public Makanan(String name, double price, String category, String jenisMakanan) {
        super(name, price, category);
        this.jenisMakanan = jenisMakanan;
    }

    @Override
    public void showMenu() {
        System.out.println("- " + getName() + " = Rp." + getPrice()+ "," + getCategory() +
                " (" + jenisMakanan +")" );
    }

    public String getJenisMakanan() {
        return jenisMakanan;
    }
}
