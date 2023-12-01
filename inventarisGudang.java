import java.util.Scanner;

/**
 * inventarisGudang
 */
public class inventarisGudang {
    static boolean isLoggedIn = false, isRunning = true;
    static String[] namaObat = { "Pfizer", "Promag", "Paracetamol", "Amoxicillin", "Decolgen" };
    static String[] gudang = { "Malang", "Jakarta", "Kediri", "Surabaya", "Gresik" };
    static String userData = "admin";
    static String userPass = "test";
    static int[][] stok = {
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

    public static void main(String[] args) {
        CleanDisplay();
        // Declaration
        Scanner input = new Scanner(System.in);
        String x;
        int y, z;
        int pilihMenu, pilihMenuKeluar;
        int pilihGudang, pilihStok, ambilStok;

        // Main Program
        while (isRunning) {
            if (isLoggedIn != true) {
                // isLoggedIn = true;
                loginPage();
            } else {
                CleanDisplay();
                // Notification if login valid
                System.out.printf("Selamat Datang Kembali %s\n",
                        userData);

                displayMenu();

                System.out.print("Pilih Menu : ");
                pilihMenu = input.nextInt();

                switch (pilihMenu) {
                    // Pilih Gudang
                    case 1:
                        CleanDisplay();
                        displayWarehouse();
                        System.out.print("Pilih Gudang : ");
                        pilihGudang = getUserInput(input, 1, gudang.length);
                        // Lihat Stok Section
                        CleanDisplay();
                        System.out.printf("============== Gudang %s ==============\n",
                                gudang[pilihGudang - 1]);
                        for (int i = 0; i < stok.length; i++) {
                            if (stok[i][2] == pilihGudang - 1) {
                                System.out.println(getNamaObat(i) + ": " + getStokObat(i));
                            }
                        }
                        System.out.println("Masukkan apapun untuk kembali ke menu");
                        x = input.next();
                        break;

                    // Pilih Gudang
                    case 2:
                        CleanDisplay();
                        displayWarehouse();
                        System.out.print("Pilih Gudang : ");
                        pilihGudang = input.nextInt() - 1;
                        // Tambah Stok Section
                        CleanDisplay();
                        System.out.printf("============== Gudang %s ==============\n",
                                gudang[pilihGudang]);
                        for (int i = 0; i < stok.length; i++) {
                            if (stok[i][2] == pilihGudang) {
                                System.out.println("(" + i + ") " + getNamaObat(i) + ": " + getStokObat(i));
                            }
                        }
                        System.out.print("Masukan ID Obat : ");
                        pilihStok = input.nextInt();

                        if (stok[pilihStok][2] == pilihGudang) {
                            System.out.print("Masukan Jumlah Tambah Stok Obat : ");
                            stok[pilihStok][1] += input.nextInt();

                            System.out.println(
                                    "Jumlah stok obat " + getNamaObat(pilihStok) + " saat ini adalah "
                                            + getStokObat(pilihStok));
                        } else {
                            System.out.println("ERROR! Masukkan Nomor ID obat yang benar!");
                        }

                        System.out.print("Masukkan apapun untuk kembali ke menu : ");
                        x = input.next();
                        break;

                    // Pilih Gudang
                    case 3:
                        CleanDisplay();
                        displayWarehouse();
                        System.out.print("Pilih Gudang : ");
                        pilihGudang = input.nextInt();
                        CleanDisplay();
                        // Ambil Stok Section
                        System.out.printf("============== Gudang %s ==============\n",
                                gudang[pilihGudang - 1]);
                        for (int i = 0; i < stok.length; i++) {
                            if (stok[i][2] == pilihGudang - 1) {
                                System.out.println("(" + i + ") " + getNamaObat(i) + ": " + getStokObat(i));
                            }
                        }
                        System.out.print("Masukan ID Obat : ");
                        pilihStok = input.nextInt();

                        if (stok[pilihStok][2] == pilihGudang - 1) {
                            System.out.print("Masukan Jumlah Ambil Stok Obat : ");
                            ambilStok = input.nextInt();
                            if (ambilStok <= getStokObat(pilihStok)) {
                                stok[pilihStok][1] -= ambilStok;
                            } else if (ambilStok > getStokObat(pilihStok)) {
                                System.out.println("ERROR! Stok Tidak Mencukupi!");
                            }

                            System.out.println(
                                    "Jumlah stok obat " + getNamaObat(pilihStok) + " saat ini adalah "
                                            + getStokObat(pilihStok));
                        } else {
                            System.out.println("ERROR! Masukkan Nomor ID obat yang benar!");
                        }

                        System.out.print("Masukkan apapun untuk kembali ke menu : ");
                        x = input.next();
                        break;

                    // Show Every Data
                    case 4:
                        CleanDisplay();
                        System.out.println("============== Data Seluruh Obat ==============");
                        for (int i = 0; i < gudang.length; i++) {
                            System.out.println("\nData Obat Gudang " + gudang[i] + ": ");
                            boolean hasObat = false;
                            for (int j = 0; j < stok.length; j++) {
                                if (stok[j][2] == i) {
                                    System.out.println("(" + j + ") " + getNamaObat(j) + ": " + getStokObat(j));
                                    hasObat = true;
                                }
                            }
                            if (!hasObat) {
                                System.out.println("- Gudang Kosong -");
                            }
                        }
                        System.out.println("\nMasukkan apapun untuk kembali ke menu");
                        x = input.next();
                        break;
                    // Exit Section
                    case 5:
                        CleanDisplay();
                        System.out.print("1. Tambah Gudang\n2. Hapus Gudang\n");
                        System.out.print("Pilih Menu : ");
                        pilihMenu = input.nextInt();
                        switch (pilihMenu) {
                            case 1:
                            displayWarehouse();
                                break;
                            case 2:

                                break;
                            default:
                                break;
                        }
                        break;
                    case 6:
                        CleanDisplay();
                        // Exit Menu
                        System.out.print("1. Keluar Akun\n2. Keluar Program\n3. Kembali\n");
                        System.out.print("Pilih Menu : ");
                        pilihMenuKeluar = input.nextInt();
                        switch (pilihMenuKeluar) {
                            // Exit Account
                            case 1:
                                CleanDisplay();
                                isLoggedIn = false;
                                break;
                            // Exit Program Menu
                            case 2:
                                isRunning = false;
                                break;
                            // Back to Menu
                            case 3:
                                CleanDisplay();
                                isRunning = true;
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

    static void displayWarehouse() {
        System.out.print("============== Gudang ==============\n");
        for (int i = 0; i < gudang.length; i++) {
            System.out.println((i + 1) + "." + gudang[i] + " ");
        }
    }

    static void CleanDisplay() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
        System.out.flush();
    }

    static void loginPage() {
        Scanner input = new Scanner(System.in);
        short loginAttempt = 1;
        while (loginAttempt <= 3) {
            System.out.print("============== GUDANG ==============\n");
            System.out.print("Masukan Username : ");
            String user = input.next();
            System.out.print("Masukan Password : ");
            String pass = input.next();
            if (userData.equals(user) && userPass.equals(pass)) {
                isLoggedIn = true;
                break;
            } else if (loginAttempt == 3) {
                isRunning = false;
                CleanDisplay();
                System.out.println("Akses Ditolak!");
            } else {
                CleanDisplay();
                System.out.println("============== NOTIFIKASI ==============");
                System.out.printf(
                        "Username atau Password yang Anda Masukan Salah!\nPercobaan yang tersisa adalah %d \n",
                        (3 - loginAttempt));
            }
            loginAttempt++;
        }
    }

    static void displayMenu() {
        System.out.println("Menu:");
        System.out.println("1. Lihat Stok");
        System.out.println("2. Tambah Stok");
        System.out.println("3. Ambil Stok");
        System.out.println("4. Data Keseluruhan");
        System.out.println("5. Konfigurasi Gudang");
        System.out.println("6. Keluar");
    }

    static String getNamaObat(int index) {
        return namaObat[stok[index][0]];
    }

    static int getStokObat(int index) {
        return stok[index][1];
    }

    static int getUserInput(Scanner scanner, int min, int max) {
        int input;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next();
            }
            input = scanner.nextInt();
            if (input < min || input > max) {
                System.out.println("Please enter a number between " + min + " and " + max + ".");
            }
        } while (input < min || input > max);
        return input;
    }
}