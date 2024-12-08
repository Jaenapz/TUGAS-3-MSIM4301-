package Entity;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Order {

    private ArrayList<MenuItem> orderItem;

    public Order() {
        orderItem = new ArrayList<>();
    }

    public void addOrder(MenuItem order) {
        if (order instanceof Discount) {
            boolean foundDiscount = false;
            for (MenuItem orders : orderItem) {
                if (orders instanceof Discount) {
                    foundDiscount = true;
                    break;
                }
            }
            if (foundDiscount) {
                System.out.println("Diskon sudah diterapkan. Tidak dapat menambahkan diskon lagi.");
                return;
            }
        }
        orderItem.add(order);
        System.out.println(order.getName() + ", Ditambahkan ke pesanan");
    }

    public void showOrder() {
        System.out.println("===== Daftar Pesanan =====");
        for (MenuItem order : orderItem) {
            order.showMenu();
        }
        System.out.println("==========================");
    }

    public double countTotal() {
        double total = 0;
        double discountValue = 0;
        for (MenuItem order : orderItem) {
            if (!(order instanceof Discount)) {
                total += order.getPrice();
            }
        }
        for (MenuItem order : orderItem) {
            if (order instanceof Discount) {
                discountValue += ((Discount) order).getDiscount();
            }
        }
        if (total > 300000) {
            System.out.println("Pembelian lebih dari 300 ribu! Diskon tambahan diterapkan.");
            discountValue += 10.0;
        }
        double totalAfterDiscount = total - (total * (discountValue / 100));
        return totalAfterDiscount;
    }


    public void saveStrukToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("=======Struk Pesanan==========");
            writer.newLine();

            ArrayList<MenuItem> orderWithDiscount = new ArrayList<>();
            ArrayList<MenuItem> orderWithOutDiscount = new ArrayList<>();

            for (MenuItem order : orderItem){
                if (order instanceof Discount){
                    orderWithDiscount.add(order);
                }else {
                    orderWithOutDiscount.add(order);
                }
            }
            for (MenuItem order : orderWithDiscount){
                writer.write(" - " + order.getName() + " |Harga setelah discount = Rp." + order.getPrice());
                writer.newLine();
            }
            for (MenuItem order : orderWithOutDiscount){
                writer.write(" - " + order.getName() + " Rp." + order.getPrice());
                writer.newLine();
            }
            writer.write("Total Pesanan : Rp." + countTotal());
            writer.newLine();
            writer.write("=========================");
            writer.newLine();

            System.out.println("Struk Pesanan Berhasil disimpan dalam Fle :" + fileName);
        } catch (IOException e) {
            System.out.println("Struk Gagal Disimpan di dalam File : " + fileName);
        }
    }
}
