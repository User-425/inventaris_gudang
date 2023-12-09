import java.util.Scanner;

/**
 * inventarisGudang
 */
public class inventarisGudang {
    static String[][] substrings = new String[100][100];
    static int[][] weights = new int[100][100];
    static int indexDatabase = 0;
    static String userInput;
    static int y, z;
    static boolean isLoggedIn = false, isRunning = true;
    static String[] namaObat = { "Pfizer", "Promag", "Paracetamol", "Amoxicillin", "Decolgen" };
    static String[] gudang = { "Malang", "Jakarta", "Kediri", "Surabaya", "Gresik" };
    static String userData = "admin";
    static String userPass = "test";
    // Stok keseluruhan
    static Object[][] stok = {
        { 0, 21, 0, "Batch123", "2024-03-15" },
        { 0, 98, 0, "Batch56", "2024-07-20" },
        { 1, 57, 1, "Batch125", "2024-09-10" },
        { 2, 73, 2, "Batch154", "2024-11-25" },
        { 3, 59, 1, "Batch175", "2025-01-18" },
        { 4, 34, 3, "Batch159", "2025-03-02" },
        { 0, 82, 1, "Batch150", "2024-05-12" },
        { 2, 141, 3, "Batch135", "2024-08-07" },
        { 3, 42, 0, "Batch21", "2024-12-30" },
        { 4, 29, 2, "Batch51", "2024-02-14" },
        { 1, 65, 2, "Batch71", "2025-04-05" },
        { 0, 45, 3, "Batch89", "2024-06-23" },
        { 1, 30, 4, "Batch102", "2024-10-28" },
        { 3, 87, 1, "Batch33", "2025-02-08" },
        { 4, 50, 0, "Batch44", "2025-05-17" },
        { 2, 63, 2, "Batch77", "2024-04-01" },
        { 0, 76, 1, "Batch95", "2024-09-14" },
        { 3, 22, 3, "Batch112", "2024-11-02" },
        { 4, 39, 0, "Batch120", "2025-01-30" },
        { 1, 54, 2, "Batch132", "2024-03-20" },
        { 2, 68, 4, "Batch143", "2024-07-09" },
        { 0, 33, 1, "Batch160", "2024-08-25" },
        { 1, 78, 3, "Batch175", "2024-05-03" },
        { 2, 40, 2, "Batch187", "2025-02-18" },
        { 3, 25, 0, "Batch192", "2024-10-10" },
        { 4, 62, 4, "Batch205", "2024-12-12" }
    };
    

    public static void main(String[] args) {
        cleanDisplay();
        // Declaration
        Scanner input = new Scanner(System.in);
        int pilihMenu, pilihMenuKeluar;
        int pilihGudang, pilihStok, ambilStok;
        populateDatabase();
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
                        headLine(" Gudang " + gudang[pilihGudang - 1] + " ");
                        for (int i = 0; i < namaObat.length; i++) {
                            int stokCount = 0;
                            for (int j = 0; j < stok.length; j++) {
                                if ((int) stok[j][2] == pilihGudang - 1 && (int) stok[j][0] == i) {
                                    stokCount += getStokObat(j);
                                }
                            }
                            if (stokCount > 0) {
                                System.out.println(namaObat[i] + ": " + stokCount);
                            }
                        }
                        System.out.print("Masukkan apapun untuk kembali ke menu ");
                        userInput = input.next();
                        break;

                    // Pilih Gudang
                    case 2:
                        cleanDisplay();
                        displayWarehouse();
                        System.out.print("Pilih Gudang : ");
                        pilihGudang = (getUserInput(input, 1, gudang.length) - 1);
                        // Tambah Stok Section
                        cleanDisplay();
                        headLine(" Gudang " + gudang[pilihGudang] + " ");
                        for (int i = 0; i < stok.length; i++) {
                            if ((int) stok[i][2] == pilihGudang) {
                                System.out.println("(" + i + ") " + getNamaObat(i) + ": " + getStokObat(i));
                            }
                        }
                        System.out.print("Masukkan ID Obat : ");
                        pilihStok = getUserInput(input, 0, stok.length - 1);

                        if ((int) stok[pilihStok][2] == pilihGudang) {
                            System.out.print("Masukkan Jumlah Tambah Stok Obat : ");
                            stok[pilihStok][1] = (int) stok[pilihStok][1] + input.nextInt();

                            System.out.println(
                                    "Jumlah stok obat " + getNamaObat(pilihStok) + " saat ini adalah "
                                            + getStokObat(pilihStok));
                        } else {
                            System.out.println("ERROR! Masukkan Nomor ID obat yang benar!");
                        }

                        System.out.print("Masukkan apapun untuk kembali ke menu : ");
                        userInput = input.next();
                        break;

                    // Pilih Gudang
                    case 3:
                        cleanDisplay();
                        displayWarehouse();
                        System.out.print("Pilih Gudang : ");
                        pilihGudang = getUserInput(input, 1, gudang.length);
                        cleanDisplay();
                        // Ambil Stok Section
                        headLine(" Gudang " + gudang[pilihGudang - 1] + " ");
                        for (int i = 0; i < stok.length; i++) {
                            if ((int) stok[i][2] == pilihGudang - 1) {
                                System.out.println("(" + i + ") " + getNamaObat(i) + ": " + getStokObat(i));
                            }
                        }
                        System.out.print("Masukkan ID Obat : ");
                        pilihStok = getUserInput(input, 0, stok.length - 1);

                        if ((int)stok[pilihStok][2] == pilihGudang - 1) {
                            System.out.print("Masukkan Jumlah Ambil Stok Obat : ");
                            ambilStok = input.nextInt();
                            if (ambilStok <= getStokObat(pilihStok)) {
                                stok[pilihStok][1] = (int) stok[pilihStok][1] - ambilStok;
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
                        userInput = input.next();
                        break;
                    // Cari Obat
                    case 4:
                        cleanDisplay();
                        System.out.print("Masukkan Obat yang ingin dicari : ");
                        String cariObat = input.next();
                        int searchIndex = searchSubstrings(cariObat);
                        for (int i = 0; i < gudang.length; i++) {
                            boolean hasObat = false;
                            for (int j = 0; j < stok.length; j++) {
                                if ((int) stok[j][2] == i && (int) stok[j][0] == searchIndex) {
                                    System.out.println("\nData Obat Gudang " + gudang[i] + ": ");
                                    System.out.println("(" + j + ") " + getNamaObat(j) + ": " + getStokObat(j));
                                    hasObat = true;
                                }
                            }
                        }
                        System.out.print("\nMasukkan apapun untuk kembali ke menu ");
                        userInput = input.next();
                        break;
                    // Data Keseluruhan
                    case 5:
                        cleanDisplay();
                        headLine(" Data Seluruh Obat ");
                        for (int i = 0; i < gudang.length; i++) {
                            System.out.println("\nData Obat Gudang " + gudang[i] + ": ");
                            boolean hasObat = false;
                            for (int j = 0; j < stok.length; j++) {
                                if ((int) stok[j][2] == i) {
                                    System.out.println("(" + j + ") " + getNamaObat(j) + ": " + getStokObat(j));
                                    hasObat = true;
                                }
                            }
                            if (!hasObat) {
                                System.out.println("- Gudang Kosong -");
                            }
                        }
                        System.out.print("\nMasukkan apapun untuk kembali ke menu ");
                        userInput = input.next();
                        break;
                    case 6:
                        cleanDisplay();
                        System.out.println("1. Tambah Jenis Obat");
                        System.out.println("2. Hapus Obat");
                        System.out.println("3. Lihat Keseluruhan Jenis Obat");
                        System.out.println("4. Tambah Obat di Gudang");
                        System.out.println("5. Hapus Obat di Gudang");
                        System.out.println("6. Transfer Obat");
                        System.out.print("Pilih Menu : ");
                        pilihMenu = input.nextInt();
                        switch (pilihMenu) {
                            case 1: // Tambah Jenis Obat
                                cleanDisplay();
                                System.out.print("Masukkan Nama Obat Baru : ");
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
                                indexDatabase = 0;
                                populateDatabase();
                                break;
                            case 2: // Hapus Obat
                                cleanDisplay();
                                displayMedicine();
                                System.out.print("Pilih Obat yang Akan Dihapus : ");
                                pilihMenu = (getUserInput(input, 1, namaObat.length) - 1);
                                tempArray = new String[namaObat.length - 1];
                                int index = 0;
                                for (int i = stok.length - 1; i >= 0; i--) {
                                    if ((int) stok[i][0] > pilihMenu) {
                                        stok[i][0] = (int)stok[i][0] - 1;
                                    } else if ((int)stok[i][0] == pilihMenu) {
                                        deleteStock(i);
                                    }
                                }
                                for (int i = 0; i < namaObat.length; i++) {
                                    if (i == pilihMenu) {
                                        continue;
                                    } else {
                                        tempArray[index] = namaObat[i];
                                        index++;
                                    }
                                }
                                namaObat = tempArray;
                                indexDatabase = 0;
                                populateDatabase();
                                break;
                            case 3: // Lihat Keseluruhan Jenis Obat
                                cleanDisplay();
                                displayMedicine();
                                System.out.print("\nMasukkan apapun untuk kembali ke menu ");
                                userInput = input.next();
                                break;
                            case 4: // Tambah Obat di Gudang
                                cleanDisplay();
                                displayWarehouse();
                                System.out.print("Pilih Gudang : ");
                                int pilihPenambahanGudang = input.nextInt() - 1;
                                cleanDisplay();
                                displayMedicine();
                                System.out.print("Pilih Obat : ");
                                int pilihPenambahanObat = input.nextInt() - 1;
                                System.out.print("Masukkan Jumlah Obat : ");
                                int jumlahObat = input.nextInt();
                                boolean obatSudahAda = false;
                                for (int i = 0; i < stok.length; i++) {
                                    if ((int)stok[i][2] == pilihPenambahanGudang && (int)stok[i][0] == pilihPenambahanObat) {
                                        obatSudahAda = true;
                                        System.out.println("Obat sudah tersedia!");
                                        System.out.print("\nMasukkan apapun untuk kembali ke menu ");
                                        userInput = input.next();
                                        break;
                                    }
                                }
                                if (!obatSudahAda) {

                                    Object[][] stokBaru = new Object[stok.length + 1][5];
                                    for (int i = 0; i < stok.length; i++) {
                                        stokBaru[i] = stok[i];
                                    }
                                    stokBaru[stok.length][0] = pilihPenambahanObat;
                                    stokBaru[stok.length][1] = jumlahObat;
                                    stokBaru[stok.length][2] = pilihPenambahanGudang;

                                    stok = stokBaru;
                                    System.out.println("Obat berhasil ditambahkan ke gudang!");
                                    System.out.print("\nMasukkan apapun untuk kembali ke menu ");
                                    userInput = input.next();
                                    break;
                                }
                                break;
                            case 5: // Hapus Obat di Gudang
                                cleanDisplay();
                                displayWarehouse();
                                System.out.print("Pilih Gudang : ");
                                int pilihPenghapusanGudang = input.nextInt() - 1;
                                cleanDisplay();
                                headLine(" Gudang " + gudang[pilihPenghapusanGudang] + " ");
                                for (int i = 0; i < stok.length; i++) {
                                    if ((int)stok[i][2] == pilihPenghapusanGudang) {
                                        System.out.println("(" + i + ") " + getNamaObat(i) + ": " + getStokObat(i));
                                    }
                                }
                                System.out.print("Pilih Obat : ");
                                int pilihPenghapusanObat = input.nextInt();

                                // Membuat array baru dengan ukuran yang lebih kecil
                                deleteStock(pilihPenghapusanObat);
                                System.out.println("Obat berhasil dihapus dari gudang!");
                                System.out.print("Masukkan apapun untuk kembali ke menu ");
                                userInput = input.next();
                                break;
                            case 6: // Transfer Obat
                                cleanDisplay();
                                displayWarehouse();
                                System.out.print("Pilih Gudang : ");
                                pilihGudang = getUserInput(input, 1, gudang.length) - 1;
                                cleanDisplay();
                                headLine(" Gudang " + gudang[pilihGudang] + " ");
                                for (int i = 0; i < namaObat.length; i++) { // repeat per obat name
                                    int stokCount = 0;
                                    for (int j = 0; j < stok.length; j++) { // repeat per obat batch
                                        if ((int) stok[j][2] == pilihGudang && (int) stok[j][0] == i) { // if the gudang is same then
                                            stokCount += (int) stok[j][1];
                                        }
                                    }
                                    if (stokCount > 0) {
                                     System.out.println("("+i+") "+namaObat[i]+": " + stokCount);    
                                    } 
                                }
                                System.out.println("Pilih Obat yang Akan ditransfer:");
                                int pilihObatTransfer = getUserInput(input, 0, namaObat.length - 1);
                                cleanDisplay();
                                headLine(" Data Obat " + namaObat[pilihObatTransfer] + " di Gudang " + gudang[pilihGudang] + " ");
                                boolean hasObat = false;
                                for (int j = 0; j < stok.length; j++) {
                                    if ((int)stok[j][2] == pilihGudang && (int)stok[j][0] == pilihObatTransfer) {
                                        System.out.println("(" + j + ") " + getNamaObat(j) + ": " + getStokObat(j) + " Batch: " + getBatchObat(j) + " Exp: " + getExpObat(j));
                                        hasObat = true;
                                    }
                                }   
                                if (!hasObat) {
                                    System.out.println("- Gudang Kosong -");
                                }
                                System.out.print("\nPilih ID Obat : ");
                                pilihStok = getUserInput(input, 0, stok.length - 1);

                                System.out.print("\nMasukkan Jumlah yang akan dipindah : ");
                                ambilStok = getUserInput(input, 1, (int)stok[pilihStok][1]);

                                cleanDisplay();
                                displayWarehouse();
                                System.out.print("Pilih Gudang Tujuan: ");
                                int pilihGudangTujuan = getUserInput(input, 1, gudang.length) - 1;
                                System.out.println(pilihGudangTujuan);

                                // Get index of stok in the gudang tujuan
                                y = -1;
                                for (int i = 0; i < stok.length; i++) {
                                    if ((int) stok[i][2] == pilihGudangTujuan && (int) stok[i][0] == pilihStok) {
                                        y = i;
                                        break;
                                    }
                                }

                                cleanDisplay();
                                // Show before after
                                headLine(" Gudang " + gudang[pilihGudang] + " ");
                                System.out.println(getNamaObat(pilihStok) + ": ");
                                System.out.printf("%d => %d\n", getStokObat(pilihStok),
                                        getStokObat(pilihStok) - ambilStok);

                                headLine(" Gudang " + gudang[pilihGudangTujuan] + " ");
                                System.out.println(getNamaObat(pilihStok) + ": ");
                                System.out.printf("%d => %d\n", (y == -1) ? 0 : getStokObat(y),
                                        ((y == -1) ? 0 : getStokObat(y)) + ambilStok);

                                // Confirmation Prompt
                                System.out.print("Apakah anda yakin? (Y/N): ");
                                String yakin = input.next();

                                if (yakin.equalsIgnoreCase("y")) {
                                    // Implement Pindah Gudang
                                    hasObat = false;
                                    for (int j = 0; j < stok.length; j++) {
                                        if ((int) stok[j][2] == pilihGudangTujuan && (int) stok[j][0] == pilihStok && getBatchObat(pilihStok).equals(getBatchObat(j))) {
                                            stok[j][1] = (int)stok[j][1] + ambilStok;
                                            hasObat = true;
                                            break;
                                        }
                                    }
                                    if (!hasObat) {
                                        addStock((int) stok[pilihStok][0], ambilStok, pilihGudangTujuan, getBatchObat(pilihStok), getExpObat(pilihStok));
                                    }
                                    stok[pilihStok][1] = (int) stok[pilihStok][1] - ambilStok;
                                }

                                System.out.print("\nMasukkan apapun untuk kembali ke menu ");
                                userInput = input.next();
                                break;

                            default:
                                break;
                        }
                        break;
                    // Exit Section
                    case 7:
                        cleanDisplay();
                        System.out.println("1. Tambah Gudang");
                        System.out.println("2. Hapus Gudang");
                        System.out.print("Pilih Menu : ");
                        pilihMenu = input.nextInt();
                        switch (pilihMenu) {
                            case 1: // Tambah Gudang
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
                            case 2: // Hapus Gudang
                                cleanDisplay();
                                displayWarehouse();
                                System.out.print("Pilih Gudang yang Akan Dihapus : ");
                                pilihMenu = (getUserInput(input, 1, gudang.length) - 1);
                                tempArray = new String[gudang.length - 1];
                                int index = 0;
                                for (int i = stok.length - 1; i >= 0; i--) {
                                    if ((int)stok[i][2] > pilihMenu) {
                                        stok[i][2] = (int) stok [i][2] - 1;
                                    } else if ((int)stok[i][2] == pilihMenu) {
                                        deleteStock(i);
                                    }
                                }
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

                    case 8:
                        cleanDisplay();
                        // Exit Menu
                        System.out.println("1. Keluar Akun");
                        System.out.println("2. Keluar Program");
                        System.out.println("3. Kembali");
                        System.out.print("Pilih Menu : ");
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
                                System.out.print("Masukkan apapun untuk kembali ke menu ");
                                userInput = input.next();
                                break;
                        }
                        break;
                    default:
                        System.out.println("Error: Invalid User Input!");
                        System.out.print("Masukkan apapun untuk kembali ke menu ");
                        userInput = input.next();
                        break;
                }
            }
        }
    }

    static void addStock(int nama, int stokObat, int gudang, String batch, String expired) {
        Object[][] tempArray = newArray(stok.length + 1);
        for (int i = 0; i < stok.length; i++) {
            tempArray[i] = stok[i];
        }
        tempArray[tempArray.length - 1] = new Object[] {nama, stokObat, gudang, batch, expired };
        stok = tempArray;
    }

    static void deleteStock(int idObat) {
        Object[][] tempArray = newArray(stok.length - 1);
        int index = 0;
        for (int i = 0; i < stok.length; i++) {
            if (i != idObat) {
                tempArray[index] = stok[i];
                index++;
            }
        }
        stok = tempArray;
    }

    static Object[][] newArray(int size) {
        return new Object[size][5];
    }

    static void displayWarehouse() {
        headLine(" GUDANG ");
        for (int i = 0; i < gudang.length; i++) {
            System.out.println((i + 1) + "." + gudang[i] + " ");
        }
    }

    static void displayMedicine() {
        headLine(" JENIS OBAT ");
        for (int i = 0; i < namaObat.length; i++) {
            System.out.println((i + 1) + "." + namaObat[i] + " ");
        }
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
            headLine(" GUDANG OBAT ");
            System.out.print("Masukkan Username : ");
            String user = input.nextLine();
            System.out.print("Masukkan Password : ");
            String pass = input.nextLine();
            if (userData.equals(user) && userPass.equals(pass)) {
                isLoggedIn = true;
                break;
            } else if (loginAttempt == 3) {
                isRunning = false;
                cleanDisplay();
                System.out.println("Akses Ditolak!");
            } else {
                cleanDisplay();
                headLine(" !NOTIFIKASI! ");
                System.out.printf(
                        "Username atau Password yang Anda Masukkan Salah!\nPercobaan yang tersisa adalah %d \n",
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
        System.out.println("4. Cari Obat");
        System.out.println("5. Data Keseluruhan");
        System.out.println("6. Konfigurasi Obat");
        System.out.println("7. Konfigurasi Gudang");
        System.out.println("8. Keluar");
    }

    static String getNamaObat(int index) {
        return namaObat[(int)stok[index][0]];
    }

    static int getStokObat(int index) {
        return (int) stok[index][1];
    }

    static String getBatchObat(int index) {
        return (String) stok[index][3];
    }

    static String getExpObat(int index) {
        return (String) stok[index][4];
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

    static void headLine(String title) {
        System.out.println("<=============[" + title + "]===============>");
    }

    public static void splitWordWithConstantWeights(String word) {
        int length = word.length();
        int indexY = 0;
        // Generate all substrings and calculate weights
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j <= length; j++) {
                substrings[indexDatabase][indexY] = word.substring(i, j);
                // Calculate weight based on the number of letters
                int weight = (j - i) * 100 / length;
                weights[indexDatabase][indexY] = weight;
                indexY++;
            }
        }
        indexDatabase++;
    }

    public static int searchSubstrings(String userInput) {
        int length = userInput.length();
        String[] userQuerySubstrings = new String[100];
        int[] queryWeight = new int[100]; // weight per data in the database
        int highestWeight = 0;
        String highestQuery = "";
        int x = 0;
        // Generate all substrings and calculate weights
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j <= length; j++) {
                userQuerySubstrings[x] = userInput.substring(i, j);
                x++;
            }
        }
        int searchIndex = 0;
        for (int i = 0; i < namaObat.length; i++) { // repeat per database
            for (int k = 0; k < userQuerySubstrings.length; k++) { // repeat per query's substrings
                if (userQuerySubstrings[k] != null) {
                    for (int j = 0; j < substrings[i].length; j++) { // repeat per database's substrings
                        // System.out.println( queryWeight[i] + " " + substrings[i][j] + " " +
                        // userQuerySubstrings[k]);
                        if (substrings[i][j] != null && substrings[i][j].equalsIgnoreCase(userQuerySubstrings[k])) {
                            queryWeight[i] += weights[i][j];
                        }
                    }
                }
            }
            if (highestWeight < queryWeight[i]) {
                highestWeight = queryWeight[i];
                highestQuery = namaObat[i];
                searchIndex = i;
            }
        }
        return searchIndex;
    }

    static void populateDatabase() {
        for (String string : namaObat) {
            splitWordWithConstantWeights(string);
        }
    }
}