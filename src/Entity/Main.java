package Entity;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Menu menu = new Menu();
        Order order = new Order();

        menu.LoadMenuFromFile("D:\\Tugas Pemograman Berbasis Desktop\\TUGAS3\\File-Tugas-3\\Menu.txt");

        while (true) {
            System.out.println("=======Menu Utama Aplikasi Restaurant=========");
            System.out.println("1.Tambah Menu");
            System.out.println("2.Tampilkan Menu");
            System.out.println("3.Terima Pesanan");
            System.out.println("4.Hitung Total Pesanan");
            System.out.println("5.Simpan Menu ke Dalam File");
            System.out.println("6.Muat Menu Dari File");
            System.out.println("7.Simpan Struk Ke File");
            System.out.println("0. Exit");
            System.out.print("Pilih Opsi :");
            int choose = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choose) {
                    case 1:
                        System.out.println("Silahkan Pilih Layanan Di Bawah :");
                        System.out.println("1. Makanan");
                        System.out.println("2. Minuman");
                        System.out.println("3. Diskon");
                        System.out.print("Silahkan Masukan Pilihan : ");
                        int service = scanner.nextInt();
                        scanner.nextLine();

                        if (service == 1) {
                            System.out.print("Masukan Nama Makanan : ");
                            String name = scanner.nextLine();
                            System.out.print("Masukan Harga :");
                            double price = Double.parseDouble(scanner.nextLine());
                            System.out.print("Masukan Jenis Makanannya :");
                            String jenisMakanan = scanner.nextLine();
                            menu.addMenu(new Makanan(name, price, "Makanan", jenisMakanan));
                        } else if (service == 2) {
                            System.out.print("Masukan Nama Minuman : ");
                            String name = scanner.nextLine();
                            System.out.print("Masukan Harga :");
                            double price = Double.parseDouble(scanner.nextLine());
                            System.out.println("Masukan Jenis Minuman :");
                            String jenisMinuman = scanner.nextLine();
                            menu.addMenu(new Makanan(name, price, "Minuman", jenisMinuman));
                        } else if (service == 3) {
                            System.out.print("Masukan Nama Diskon : ");
                            String name = scanner.nextLine();
                            System.out.print("Masukan Harga Dikon :");
                            double price = Double.parseDouble(scanner.nextLine());
                            System.out.println("Diskon (%):");
                            double discount = scanner.nextDouble();
                            menu.addMenu(new Discount(name,price,"Dicount",discount));
                        }
                        break;

                case 2:
                    menu.showingMenu();
                    break;
                case 3:
                    while (true) {
                        System.out.print("Silahkan masukan Pesanan: ");
                        String menuNames = scanner.nextLine();

                        try {
                            MenuItem menuItem = menu.searchMenu(menuNames);
                            order.addOrder(menuItem);
                            if (menuItem instanceof Discount) {
                                MenuItem itemDiskon = menu.searchDiscount(menuItem.getPrice());
                                if (itemDiskon != null) {
                                    order.addOrder(itemDiskon);
                                }
                            }
                            System.out.println("Pesanan berhasil ditambahkan.");
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        System.out.print("Apakah Anda ingin menambah pesanan lagi? (Y/N): ");
                        String continueChoice = scanner.nextLine().toUpperCase();
                        if (continueChoice.equalsIgnoreCase("N")) {
                            break;
                        }
                    }
                    break;
                case 4:
                    order.showOrder();
                    System.out.println("Total Pesanan : " + order.countTotal());
                    break;
                case 5:
                    menu.saveMenuToFile("D:\\Tugas Pemograman Berbasis Desktop\\TUGAS3\\File-Tugas-3\\Menu.txt");
                    break;
                case 6:
                    menu.LoadMenuFromFile("D:\\Tugas Pemograman Berbasis Desktop\\TUGAS3\\File-Tugas-3\\Menu.txt");
                    break;
                case 7:
                    order.saveStrukToFile("D:\\Tugas Pemograman Berbasis Desktop\\TUGAS3\\File-Tugas-3\\Struk.txt");
                    break;
                case 0:
                    System.out.println("Terima Kasih,Sampai Jumpa!");
                    return;
                default:
                    System.out.println("Angka tidak valid,coba masukan lagi!");
            }


        }catch(Exception e){
            System.out.println("Something wrong : " + e.getMessage());
            }
        }
    }
    }


