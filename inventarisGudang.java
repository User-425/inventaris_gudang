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
        boolean login = false;

        while (login != true) {
            // login page
            System.out.print("============== GUDANG ==============\n");
            System.out.print("Masukan Username : ");
            user = input.nextLine();
            System.out.print("Masukan Password : ");
            pass = input.nextLine();

            if (userData.equals(user) && userPass.equals(pass)) {
                break;
            } else {
                // Notification if login invalid
                System.out.println("Username atau Password yang Anda Masukan Salah");
            }
        }
        // Notification if login valid
        System.out.printf("Selamat Datang Kembali %s\n", userData);

        // menu page
        System.out.println("1.Lihat Stok\n2.Tambah Stok\n3.Ambil Stok\n4.Keluar");
        pilihMenu = input.next().charAt(0);

        switch (pilihMenu) {
            case '1':
                // Lihat stok Section
                
                System.out.println("Jumlah stok obat1: " + obat1);
                System.out.println("Jumlah stok obat2: " + obat2);
                break;

            case '2':
                // Tambah stok Section

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
                System.out.println("Jumlah Stok Akhir Obat 1 " + obat1);
                System.out.println("Jumlah Stok Akhir Obat 2 " + obat2);
                break;

            case '3':
                // Ambil stok Section

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
                break;

            case '4':
                // Exit Menu

                login = true;
                break;
            default:
                System.out.println("Error");
                break;
        }

    }
}