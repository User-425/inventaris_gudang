import java.util.Scanner;

/**
 * inventarisGudang
 */
public class inventarisGudang {

    public static void main(String[] args) {
        String x;
        int y,z;
        Scanner input = new Scanner(System.in);
        char pilihMenu;
        String user, pass, userData = "admin", userPass = "test";
        boolean login = false, running = true;
        String[] namaObat = { "Pfizer", "Promag" };
        int[] jumlahStok = {120,100};
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
                            System.out.println((i + 1) + ". " + namaObat[i]+" ("+jumlahStok[i]+')');
                        }
                        System.out.println("Masukkan apapun untuk kembali ke menu");
                        x = input.next();
                        break;

                    case '2':
                        // Tambah stok Section
                        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n============== Tambah Obat ==============\n");
                        
                        for (int i = 0; i < namaObat.length; i++) {
                            System.out.println((i + 1) + ". " + namaObat[i]);
                        }

                        System.out.println("Masukkan nomor obat:");
                        y = input.nextInt();

                        
                        if (0 < y && y <= namaObat.length) {
                        System.out.println("Jumlah stok obat "+ namaObat[y-1] + " = "+jumlahStok[y-1]);
                        System.out.println("Masukkan jumlah tambah obat " + namaObat[y-1]+": ");
                        z = input.nextInt();

                        jumlahStok[y-1] += z; 

                        System.out.println("Jumlah stok obat "+ namaObat[y-1] + " saat ini adalah "+jumlahStok[y-1]);

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
                        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n============== Ambil Obat ==============\n");
                        for (int i = 0; i < namaObat.length; i++) {
                            System.out.println((i + 1) + ". " + namaObat[i]);
                        }

                        System.out.println("Masukkan nomor obat:");
                        y = input.nextInt();
                        if (0 < y && y <= namaObat.length) {
                        System.out.println("Jumlah stok obat "+ namaObat[y-1] + " = "+jumlahStok[y-1]);
                        
                        System.out.println("Masukkan jumlah ambil obat " + namaObat[y-1]+": ");
                        z = input.nextInt();

                        jumlahStok[y-1] -= z; 

                        System.out.println("Jumlah stok obat "+ namaObat[y-1] + " saat ini adalah "+jumlahStok[y-1]);

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