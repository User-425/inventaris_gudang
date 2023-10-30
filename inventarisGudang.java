import java.util.Scanner;

/**
 * inventarisGudang
 */
public class inventarisGudang {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int obat1 = 100, obat2 = 100;
        int jml_tambah1, jml_tambah2, jml_ambil1, jml_ambil2;
        char pilihMenu;
        String user, pass, userData = "admin", userPass = "test";
        boolean login = false, running = true;
        String[] namaObat = { "Pfizer", "Promag" };
        while (running) {
            if (login != true) {
                // login page
                System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n============== GUDANG ==============\n");
                System.out.print("Masukan Username : ");
                user = input.next();
                System.out.print("Masukan Password : ");
                pass = input.next();

                if (userData.equals(user) && userPass.equals(pass)) {
                    login = true;
                } else {
                    // Notification if login invalid
                    System.out.println("Username atau Password yang Anda Masukan Salah");
                }
            } else {
                // Notification if login valid
                System.out.printf("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nSelamat Datang Kembali %s\n", userData);

                // menu page
                System.out.println("1.Lihat Stok\n2.Tambah Stok\n3.Ambil Stok\n4.Logout\n5.Keluar");
                pilihMenu = input.next().charAt(0);

                switch (pilihMenu) {
                    case '1':
                        // Lihat stok Section
                        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n============== Data Obat ==============\n");
                        for (int i = 0; i < namaObat.length; i++) {
                            System.out.println((i + 1) + ". " + namaObat[i]);
                        }
                        System.out.println("Masukkan apapun untuk kembali ke menu");
                        String x = input.next();
                        break;

                    case '2':
                        // Tambah stok Section
                        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n============== Tambah Obat ==============\n");
                        // obat1
                        System.out.print("Tambah Stok Obat 1 : ");
                        jml_tambah1 = input.nextInt();
                        obat1 += jml_tambah1;
                        System.out.printf("Sukses Menambahkan Obat Sebanyak %d\n", jml_tambah1);

                        // obat2
                        System.out.print("Tambah Stok Obat 2 : ");
                        jml_tambah2 = input.nextInt();
                        obat2 += jml_tambah2;
                        System.out.printf("Sukses Menambahkan Obat Sebanyak %d\n", jml_tambah2);

                        // Stok
                        System.out.println("Jumlah Stok Akhir Obat 1: " + obat1);
                        System.out.println("Jumlah Stok Akhir Obat 2: " + obat2);
                        System.out.println("Masukkan apapun untuk kembali ke menu");
                         x = input.next();
                        break;

                    case '3':
                        // Ambil stok Section
                        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n============== Ambil Obat ==============\n");
                        // Obat1
                        System.out.println("Ambil stok obat1 : ");
                        jml_ambil1 = input.nextInt();
                        obat1 -= jml_ambil1;

                        // Obat2
                        System.out.println("Ambil stok obat2  : ");
                        jml_ambil2 = input.nextInt();
                        obat2 -= jml_ambil2;

                        // Stok
                        System.out.println("Stok obat1 setelah diambil: " + obat1);
                        System.out.println("Stok obat2 setelah diambil: " + obat2);

                        System.out.println("Masukkan apapun untuk kembali ke menu");
                        x = input.next();
                        break;

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