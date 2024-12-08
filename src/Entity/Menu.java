package Entity;

import java.io.*;
import java.util.ArrayList;

public class Menu {

    private  ArrayList<MenuItem> menuItem;


    public Menu(ArrayList<MenuItem> menuItem) {
        this.menuItem = menuItem;
    }

    public Menu() {
        menuItem = new ArrayList<>();
    }

    public ArrayList<MenuItem> getMenuMakanan() {
        return menuItem;
    }

    public void showingMenu(){
        System.out.println("=======Selamat datang di restoran IMPHEN =========");
        for (MenuItem menu : menuItem){
            menu.showMenu();
        }
    }

    public MenuItem searchMenu(String name) throws Exception{
        for (MenuItem menu : menuItem){
            if (menu.getName().equalsIgnoreCase(name)){
                return menu;
            }
        }
        throw new Exception("Menu tidak ditemukan");
    }

    public MenuItem searchDiscount(double price) throws Exception{
        for (MenuItem menu : menuItem){
            if (price >= menu.getPrice() && menu instanceof Discount){
                return menu;
            }
        }
        throw new Exception("Diskon tidak ditemukan");
        }

    public void addMenu(MenuItem menusItem){
        menuItem.add(menusItem);
    }


    public void LoadMenuFromFile(String filename) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 4) {
                    System.out.println("Baris tidak valid: " + line);
                    continue;
                }

                String name = parts[0];
                double price = Double.parseDouble(parts[1]);
                String category = parts[2];

                if (category.equalsIgnoreCase("Makanan")) {
                    addMenu(new Makanan(name, price, category, parts[3]));
                } else if (category.equalsIgnoreCase("Minuman")) {
                    addMenu(new Minuman(name, price, category, parts[3]));
                } else if (category.equalsIgnoreCase("Diskon")) {
                    double discount = Double.parseDouble(parts[3]);
                    addMenu(new Discount(name, price, category, discount));
                }
            }
            System.out.println("Menu berhasil dimuat dari file: " + filename);
        } catch (IOException e) {
            System.out.println("Menu tidak dapat dimuat: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Kesalahan format angka pada file: " + e.getMessage());
        }
    }
    public void saveMenuToFile(String filename){
        File folder = new File("File-Tugas-3");
        if (!folder.exists()){
            folder.mkdir();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (MenuItem menu : menuItem) {
                String data = menu.getName() + "," + menu.getPrice() + "," + menu.getCategory();
                if (menu instanceof Makanan) {
                    data += "," + ((Makanan) menu).getJenisMakanan();
                } else if (menu instanceof Minuman) {
                    data += "," + ((Minuman) menu).getJenisMinuman();
                } if (menu instanceof Discount) {
                    data += "," + ((Discount) menu).getDiscount();
                }

                writer.write(data);
                writer.newLine();
            }
            System.out.println("Menu berhasil disimpan di file: " + filename);
        } catch (IOException e) {
            System.out.println("Menu gagal disimpan: " + e.getMessage());
        }
    }
}
