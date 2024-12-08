package Entity;

public class Minuman extends MenuItem{

    private String jenisMinuman;

    public Minuman(String name, double price, String category, String jenisMinuman) {
        super(name, price, category);
        this.jenisMinuman = jenisMinuman;
    }

    @Override
    public void showMenu() {
        System.out.println("- " + getName() + " = Rp." + getPrice() + "," + getCategory()
                +" (" + jenisMinuman +")" );
    }

    public String getJenisMinuman() {
        return jenisMinuman;
    }
}
