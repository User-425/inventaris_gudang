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
        char pilihMenu, pilihMenuKeluar;
        int pilihGudang, pilihStok, ambilStok;
        String user, pass, userData = "admin", userPass = "test";
        boolean login = false, running = true;
        String[] namaObat = { "Pfizer", "Promag", "Paracetamol", "Amoxicillin", "Decolgen" };
        int[][] stok = {
                { 0, 113, 0 },
                { 1, 57, 1 },
                { 2, 73, 2 },
                { 3, 59, 1 },
                { 4, 34, 3 },
                { 0, 82, 1 },
                { 2, 141, 3 },
                { 3, 42, 0 },
                { 4, 29, 2 },
                { 1, 65, 2 }
        };
        String[] gudang = { "Malang", "Jakarta", "Kediri", "Surabaya" };

        // Main Program
        while (running) {
            if (login != true) {
                short i = 1, j = 3;
                while (i <= 4) {
                    // Halaman Login //
                    System.out.print(
                            "\n".repeat(15) + "============== GUDANG ==============\n");
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
                System.out.printf("\n".repeat(10) + "Selamat Datang Kembali %s\n",
                        userData);

                // menu page
                System.out.println("Menu:");
                System.out.println("1. Lihat Stok");
                System.out.println("2. Tambah Stok");
                System.out.println("3. Ambil Stok");
                System.out.println("4. Data Keseluruhan");
                System.out.println("5. Keluar");

                System.out.print("Pilih Menu : ");
                pilihMenu = input.next().charAt(0);

                switch (pilihMenu) {

                    // Pilih Gudang
                    case '1':
                        System.out.print(
                                "\n".repeat(15) + "============== Gudang ==============\n");
                        for (int i = 0; i < gudang.length; i++) {
                            System.out.println((i + 1) + "." + gudang[i] + " ");
                        }
                        System.out.print("Pilih Gudang :");
                        pilihGudang = input.nextInt();
                        // Lihat Stok Section
                        System.out.printf("\n".repeat(15) + "============== Gudang %s ==============\n",
                                gudang[pilihGudang - 1]);
                        for (int i = 0; i < stok.length; i++) {
                            if (stok[i][2] == pilihGudang - 1) {
                                System.out.println(namaObat[stok[i][0]] + ": " + stok[i][1]);
                            }
                        }
                        System.out.println("Masukkan apapun untuk kembali ke menu");
                        x = input.next();
                        break;

                    // Pilih Gudang
                    case '2':
                        System.out.print(
                                "\n".repeat(15) + "============== Gudang ==============\n");
                        for (int i = 0; i < gudang.length; i++) {
                            System.out.println((i + 1) + "." + gudang[i] + " ");
                        }
                        System.out.print("Pilih Gudang :");
                        pilihGudang = input.nextInt() - 1;
                        // Tambah Stok Section
                        System.out.printf("\n".repeat(15) + "============== Gudang %s ==============\n",
                                gudang[pilihGudang]);
                        for (int i = 0; i < stok.length; i++) {
                            if (stok[i][2] == pilihGudang) {
                                System.out.println("(" + i + ") " + namaObat[stok[i][0]] + ": " + stok[i][1]);
                            }
                        }
                        System.out.print("Masukan ID Obat : ");
                        pilihStok = input.nextInt();

                        if (stok[pilihStok][2] == pilihGudang) {
                            System.out.print("Masukan Jumlah Tambah Stok Obat : ");
                            stok[pilihStok][1] += input.nextInt();

                            System.out.println(
                                    "Jumlah stok obat " + namaObat[stok[pilihStok][0]] + " saat ini adalah "
                                            + stok[pilihStok][1]);
                        } else {
                            System.out.println("ERROR! Masukkan Nomor ID obat yang benar!");
                        }

                        System.out.print("Masukkan apapun untuk kembali ke menu : ");
                        x = input.next();
                        break;

                    // Pilih Gudang
                    case '3':
                        System.out.print(
                                "\n".repeat(15) + "============== Gudang ==============\n");
                        for (int i = 0; i < gudang.length; i++) {
                            System.out.println((i + 1) + "." + gudang[i] + " ");
                        }
                        System.out.print("Pilih Gudang : ");
                        pilihGudang = input.nextInt();
                        // Ambil Stok Section
                        System.out.printf("\n".repeat(15) + "============== Gudang %s ==============\n",
                                gudang[pilihGudang - 1]);
                        for (int i = 0; i < stok.length; i++) {
                            if (stok[i][2] == pilihGudang - 1) {
                                System.out.println("(" + i + ") " + namaObat[stok[i][0]] + ": " + stok[i][1]);
                            }
                        }
                        System.out.print("Masukan ID Obat : ");
                        pilihStok = input.nextInt();

                        if (stok[pilihStok][2] == pilihGudang - 1) {
                            System.out.print("Masukan Jumlah Ambil Stok Obat : ");
                            ambilStok = input.nextInt();
                            if (ambilStok <= stok[pilihStok][1]) {
                                stok[pilihStok][1] -= ambilStok;
                            } else if (ambilStok > stok[pilihStok][1]) {
                                System.out.println("ERROR! Stok Tidak Mencukupi!");
                            }

                            System.out.println(
                                    "Jumlah stok obat " + namaObat[stok[pilihStok][0]] + " saat ini adalah "
                                            + stok[pilihStok][1]);
                        } else {
                            System.out.println("ERROR! Masukkan Nomor ID obat yang benar!");
                        }

                        System.out.print("Masukkan apapun untuk kembali ke menu : ");
                        x = input.next();
                        break;

                    // Show Every Data
                    case '4':
                        System.out.println("\n".repeat(15) + "============== Data Seluruh Obat ==============");
                        for (int i = 0; i < gudang.length; i++) {
                            System.out.println("Data Obat Gudang " + gudang[i] + ": ");
                            for (int j = 0; j < stok.length; j++) {
                                if (stok[j][2] == i) {
                                    System.out.println("(" + j + ") " + namaObat[stok[j][0]] + ": " + stok[j][1]);
                                }
                            }
                        }
                        System.out.println("Masukkan apapun untuk kembali ke menu");
                        x = input.next();
                        break;
                    // Exit Section
                    case '5':
                        // Exit Menu
                        System.out.print("\n".repeat(15) + "1. Keluar Akun\n2. Keluar Program\n3. Kembali\n");
                        System.out.print("Pilih Menu : ");
                        pilihMenuKeluar = input.next().charAt(0);
                        switch (pilihMenuKeluar) {
                            // Exit Account
                            case '1':
                                login = false;
                                break;
                            // Exit Program Menu
                            case '2':
                                running = false;
                                break;
                            // Back to Menu
                            case '3':
                                running = true;
                                break;
                            default:
                                System.out.println("Error: Invalid User Input!");
                                System.out.println("Masukkan apapun untuk kembali ke menu");
                                x = input.next();
                                break;
                        }
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