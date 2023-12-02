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
        cleanDisplay();
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
                cleanDisplay();
                // Notification if login valid
                System.out.printf("Selamat Datang Kembali %s\n",
                        userData);
                displayMenu();

                System.out.print("Pilih Menu : ");
                pilihMenu = input.nextInt();

                switch (pilihMenu) {
                    // Pilih Gudang
                    case 1:
                        cleanDisplay();
                        displayWarehouse();
                        System.out.print("Pilih Gudang : ");
                        pilihGudang = getUserInput(input, 1, gudang.length);
                        // Lihat Stok Section
                        cleanDisplay();
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
                        cleanDisplay();
                        displayWarehouse();
                        System.out.print("Pilih Gudang : ");
                        pilihGudang = (getUserInput(input, 1, gudang.length) - 1);
                        // Tambah Stok Section
                        cleanDisplay();
                        System.out.printf("============== Gudang %s ==============\n",
                                gudang[pilihGudang]);
                        for (int i = 0; i < stok.length; i++) {
                            if (stok[i][2] == pilihGudang) {
                                System.out.println("(" + i + ") " + getNamaObat(i) + ": " + getStokObat(i));
                            }
                        }
                        System.out.print("Masukan ID Obat : ");
                        pilihStok = getUserInput(input, 0, stok.length);

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
                        cleanDisplay();
                        displayWarehouse();
                        System.out.print("Pilih Gudang : ");
                        pilihGudang = getUserInput(input, 1, gudang.length);
                        cleanDisplay();
                        // Ambil Stok Section
                        System.out.printf("============== Gudang %s ==============\n",
                                gudang[pilihGudang - 1]);
                        for (int i = 0; i < stok.length; i++) {
                            if (stok[i][2] == pilihGudang - 1) {
                                System.out.println("(" + i + ") " + getNamaObat(i) + ": " + getStokObat(i));
                            }
                        }
                        System.out.print("Masukan ID Obat : ");
                        pilihStok = getUserInput(input, 0, stok.length);

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
                    case 5:
                        cleanDisplay();
                        System.out.println("1. Tambah Jenis Obat");
                        System.out.println("2. Hapus Obat");
                        System.out.println("3. Lihat Keseleruhan Jenis Obat");
                        System.out.println("4. Tambah Obat di Gudang");
                        System.out.println("5. Hapus Obat di Gudang");
                        System.out.print("Pilih Menu : ");
                        pilihMenu = input.nextInt();
                        switch (pilihMenu) {
                            case 1:
                                cleanDisplay();
                                System.out.print("Masukan Nama Obat Baru : ");
                                String obatBaru = input.next();
                                String[] tempArray = new String[namaObat.length + 1];
                                for (int i = 0; i < namaObat.length; i++) {
                                    tempArray[i] = namaObat[i];
                                }
                                tempArray[tempArray.length - 1] = obatBaru;
                                namaObat = new String[tempArray.length];
                                for (int i = 0; i < tempArray.length; i++) {
                                    namaObat[i] = tempArray[i];
                                }
                                break;
                            case 2:
                                cleanDisplay();
                                displayMedicine();
                                System.out.print("Pilih Obat yang Akan Dihapus : ");
                                pilihMenu = input.nextInt() - 1;
                                tempArray = new String[namaObat.length - 1];
                                int index = 0;
                                for (int i = 0; i < namaObat.length; i++) {
                                    if (i == pilihMenu) {
                                        continue;
                                    } else {
                                        tempArray[index] = namaObat[i];
                                        index++;
                                    }
                                }
                                namaObat = tempArray;
                                break;
                            case 3:
                                cleanDisplay();
                                displayMedicine();
                                System.out.print("\nMasukkan apapun untuk kembali ke menu");
                                x = input.next();
                                break;

                            default:
                                break;
                        }
                        break;

                    // Show Every Data
                    case 4:
                        cleanDisplay();
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
                    case 6:
                        cleanDisplay();
                        System.out.println("1. Tambah Gudang");
                        System.out.println("2. Hapus Gudang");
                        System.out.print("Pilih Menu : ");
                        pilihMenu = input.nextInt();
                        switch (pilihMenu) {
                            case 1:
                                cleanDisplay();
                                System.out.print("Nama Gudang Baru : ");
                                String gudangBaru = input.next();
                                String[] tempArray = new String[gudang.length + 1];
                                for (int i = 0; i < gudang.length; i++) {
                                    tempArray[i] = gudang[i];
                                }
                                tempArray[tempArray.length - 1] = gudangBaru;
                                gudang = new String[tempArray.length];
                                for (int i = 0; i < tempArray.length; i++) {
                                    gudang[i] = tempArray[i];
                                }
                                break;
                            case 2:
                                cleanDisplay();
                                displayWarehouse();
                                System.out.print("Pilih Gudang yang Akan Dihapus : ");
                                pilihMenu = input.nextInt() - 1;
                                tempArray = new String[gudang.length - 1];
                                int index = 0;
                                for (int i = 0; i < gudang.length; i++) {
                                    if (i == pilihMenu) {
                                        continue;
                                    } else {
                                        tempArray[index] = gudang[i];
                                        index++;
                                    }
                                }
                                gudang = tempArray;
                                break;
                            default:
                                break;
                        }
                        break;
                    case 7:
                        cleanDisplay();
                        displayWarehouse();
                        System.out.println("Pilih Gudang : ");
                        pilihGudang = getUserInput(input, 1, gudang.length) - 1;
                        cleanDisplay();
                        System.out.print("============== Gudang " + gudang[pilihGudang] + " ==============\n");
                        boolean hasObat = false;
                            for (int j = 0; j < stok.length; j++) {
                                if (stok[j][2] == pilihGudang) {
                                    System.out.println("(" + j + ") " + getNamaObat(j) + ": " + getStokObat(j));
                                    hasObat = true;
                                }
                            }
                            if (!hasObat) {
                                System.out.println("- Gudang Kosong -");
                            }
                        System.out.println("\nPilih ID Obat : ");
                        pilihStok = getUserInput(input, 0, stok.length);
                        
                        System.out.println("\nMasukkan apapun untuk kembali ke menu");
                        x = input.next();
                        break;
                    case 8:
                        cleanDisplay();
                        // Exit Menu
                        System.out.println("1. Keluar Akun");
                        System.out.println("2. Keluar Program");
                        System.out.println("3. Kembali");
                        System.out.println("Pilih Menu : ");
                        pilihMenuKeluar = input.nextInt();
                        switch (pilihMenuKeluar) {
                            // Exit Account
                            case 1:
                                cleanDisplay();
                                isLoggedIn = false;
                                break;
                            // Exit Program Menu
                            case 2:
                                isRunning = false;
                                break;
                            // Back to Menu
                            case 3:
                                cleanDisplay();
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

    static void displayMedicine() {
        System.out.print("============== Jenis Obat ==============\n");
        for (int i = 0; i < namaObat.length; i++) {
            System.out.println((i + 1) + "." + namaObat[i] + " ");
        }
    }

    static void deleteElementArray() {

    }

    static void cleanDisplay() {
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
                cleanDisplay();
                System.out.println("Akses Ditolak!");
            } else {
                cleanDisplay();
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
        System.out.println("5. Konfigurasi Obat");
        System.out.println("6. Konfigurasi Gudang");
        System.out.println("7. Pindah Gudang");
        System.out.println("8. Keluar");
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
                System.out.println("Input tidak valid. Silakan coba lagi.");
                scanner.next();
            }
            input = scanner.nextInt();
            if (input < min || input > max) {
                System.out.println("Input tidak valid. Silakan coba lagi.");
            }
        } while (input < min || input > max);
        return input;
    }
}