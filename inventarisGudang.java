import java.util.Scanner;

/**
 * inventarisGudang
 */
public class inventarisGudang {
    public static void main(String[] args) {
        // Declaration
        Scanner input = new Scanner(System.in);
        String x;
        int y, z;
        char pilihMenu;
        int pilihGudang;
        String user, pass, userData = "admin", userPass = "test";
        boolean login = false, running = true;
        String[] namaObat = { "Pfizer", "Promag", "Paracetamol" };
        int[][] stok = {
                { 0, 100, 3 },
                { 1, 50, 1 },
                { 2, 73, 2 },
                { 1, 59, 0 },
        };
        String[] gudang = { "Malang", "Jakarta", "Kediri", "Surabaya" };

        // Main Program
        while (running) {
            if (login != true) {
                short i = 1, j = 3;
                while (i <= 4) {
                    // Halaman Login //
                    System.out.print(
                            "\n".repeat(15)+"============== GUDANG ==============\n");
                    System.out.print("Masukan Username : ");
                    user = input.next();
                    System.out.print("Masukan Password : ");
                    pass = input.next();

                    if (userData.equals(user) && userPass.equals(pass)) {
                        login = true;
                        break;
                    } else if (i == 4) {
                        running = false;
                        System.out.println("Akses Ditolak!");
                    } else {
                        System.out.println("============== NOTIFIKASI ==============");
                        System.out.printf(
                                "Username atau Password yang Anda Masukan Salah!\nPercobaan yang tersisa adalah %d \n",
                                j);
                    }
                    j--;
                    i++;
                }
            } else {
                // Notification if login valid
                System.out.printf("\n".repeat(15)+"Selamat Datang Kembali %s\n",
                        userData);

                // menu page
                System.out.print("1.Lihat Stok\n2.Tambah Stok\n3.Ambil Stok\n4.Logout\n5.Keluar\nPilih Menu : ");
                pilihMenu = input.next().charAt(0);

                switch (pilihMenu) {

                    case '1':
                        // Pilih Gudang
                        System.out.print(
                                "\n".repeat(15)+"============== Gudang ==============\n");
                        for (int i = 0; i < gudang.length; i++) {
                            System.out.println((i + 1) + "." + gudang[i] + " ");
                        }
                        System.out.print("Pilih Gudang :");
                        pilihGudang = input.nextInt();
                        // Lihat Stok Section
                        System.out.printf("\n".repeat(15)+"============== Gudang %s ==============\n",gudang[pilihGudang-1]);
                        for (int i = 0; i < stok.length; i++) {
                            if (stok[i][2] == pilihGudang - 1) {
                                System.out.println(namaObat[stok[i][0]] + ": " + stok[i][1]);
                            }
                        }
                        System.out.println("Masukkan apapun untuk kembali ke menu");
                        x = input.next();
                        break;

                    case '2':
                        // Tambah stok Section
                        System.out.print(
                                "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n============== Tambah Obat ==============\n");

                        for (int i = 0; i < namaObat.length; i++) {
                            System.out.println((1 + i) + ". " + namaObat[i]);
                        }

                        System.out.print("Masukkan nomor obat: ");
                        y = input.nextInt();

                        if (0 < y && y <= namaObat.length) {
                            System.out.println("Jumlah stok obat " + namaObat[y - 1] + " = " + stok[y - 1][1]);
                            System.out.print("Masukkan jumlah tambah obat " + namaObat[y - 1] + ": ");
                            z = input.nextInt();

                            stok[y - 1][1] += z;

                            System.out.println(
                                    "Jumlah stok obat " + namaObat[y - 1] + " saat ini adalah " + stok[y - 1][1]);

                            System.out.println("Masukkan apapun untuk kembali ke menu");
                            x = input.next();
                            break;

                        } else {
                            System.out.println("ERROR! Masukkan Nomor obat yang benar!");
                            System.out.println("Masukkan apapun untuk kembali ke menu");
                            x = input.next();
                            break;
                        }

                    case '3':
                        // Ambil stok Section
                        System.out.print(
                                "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n============== Ambil Obat ==============\n");
                        for (int i = 0; i < namaObat.length; i++) {
                            System.out.println((i + 1) + ". " + namaObat[i]);
                        }

                        System.out.print("Masukkan nomor obat: ");
                        y = input.nextInt();
                        if (0 < y && y <= namaObat.length) {
                            System.out.println("Jumlah stok obat " + namaObat[y - 1] + " = " + stok[y - 1][1]);

                            System.out.print("Masukkan jumlah ambil obat " + namaObat[y - 1] + ": ");
                            z = input.nextInt();

                            stok[y - 1][1] -= z;

                            System.out.println(
                                    "Jumlah stok obat " + namaObat[y - 1] + " saat ini adalah " + stok[y - 1][1]);

                            System.out.println("Masukkan apapun untuk kembali ke menu");
                            x = input.next();
                            break;

                        } else {
                            System.out.println("ERROR! Masukkan Nomor obat yang benar!");
                            System.out.println("Masukkan apapun untuk kembali ke menu");
                            x = input.next();
                            break;
                        }

                    case '4':
                        // Exit Menu
                        login = false;
                        break;
                    case '5':
                        // Exit Program Menu
                        running = false;
                        break;
                    default:
                        System.out.println("Error: Invalid User Input!");
                        System.out.println("Masukkan apapun untuk kembali ke menu");
                        x = input.next();
                        break;
                }
            }
        }
    }
}