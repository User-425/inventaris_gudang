import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;

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
            { 0, 21, 0, "Batch001", "15-03-2024" },
            { 0, 98, 0, "Batch002", "20-02-2023" },
            { 1, 57, 1, "Batch003", "10-09-2024" },
            { 2, 73, 2, "Batch004", "25-11-2024" },
            { 3, 59, 1, "Batch005", "18-01-2025" },
            { 4, 34, 3, "Batch006", "02-03-2025" },
            { 0, 82, 1, "Batch001", "12-03-2024" },
            { 2, 141, 3, "Batch008", "07-08-2024" },
            { 3, 42, 0, "Batch009", "30-12-2024" },
            { 4, 29, 2, "Batch010", "14-02-2024" },
            { 1, 65, 2, "Batch012", "05-04-2025" },
            { 0, 45, 3, "Batch015", "23-06-2024" },
            { 1, 30, 3, "Batch016", "28-10-2024" },
            { 3, 87, 1, "Batch017", "08-02-2025" },
            { 4, 50, 0, "Batch018", "17-05-2025" },
            { 2, 63, 2, "Batch019", "01-04-2024" },
            { 0, 76, 1, "Batch020", "14-09-2024" },
            { 3, 22, 3, "Batch021", "02-11-2024" },
            { 4, 39, 0, "Batch022", "30-01-2025" },
            { 1, 54, 2, "Batch023", "20-03-2024" },
            { 2, 68, 2, "Batch024", "09-07-2024" },
            { 0, 33, 1, "Batch025", "25-08-2024" },
            { 1, 78, 3, "Batch026", "03-05-2024" },
            { 2, 40, 2, "Batch027", "18-02-2025" },
            { 3, 25, 0, "Batch028", "10-10-2024" },
            { 4, 62, 0, "Batch029", "12-12-2024" }
    };

    public static void main(String[] args) throws ParseException {
        cleanDisplay();
        populateDatabase();
        // Main Program
        run();
    }

    static void run() throws ParseException {
        while (isRunning) {
            if (isLoggedIn != true) {
                // isLoggedIn = true;
                loginPage();
            } else {
                menuPage();
            }
        }

    }

    static void menuPage() throws ParseException {
        Scanner input = new Scanner(System.in);
        cleanDisplay();
        // Notification if login valid
        System.out.printf("Selamat Datang Kembali %s\n",
                userData);
        displayMenu();

        System.out.print("Pilih Menu : ");
        int pilihMenu = input.nextInt();

        switch (pilihMenu) {
            // Lihat Stok
            case 1:
                lihatStockPage();
                break;
            // Tambah Stok
            case 2:
                tambahStockPage();
                break;
            // Ambil Stok
            case 3:
                ambilStockPage();
                break;
            // Cari Obat
            case 4:
                searchPage();
                break;
            // Data Keseluruhan
            case 5:
                allDatasPage();
                break;
            // Setting Obat
            case 6:
                obatSettingPage();
                break;
            // Setting Gudang
            case 7:
                gudangSettingPage();
                break;
            // Menu Keluar   
            case 8:
                exitPage();
                break;
            default:
                System.out.println("Error: Invalid User Input!");
                exitPrompt();
                break;
        }
    }

    static void lihatStockPage() throws ParseException {
        Scanner input = new Scanner(System.in);
        cleanDisplay();
        displayWarehouse();
        System.out.print("Pilih Gudang : ");
        int pilihGudang = getUserInput(input, 1, gudang.length) - 1;
        // Lihat Stok Section
        cleanDisplay();
        headLine(" Gudang " + gudang[pilihGudang] + " ");
        for (int i = 0; i < namaObat.length; i++) { // Repeat per Nama Obat
            int stokCount = 0;
            for (int j = 0; j < stok.length; j++) { // Repeat per Stock
                if ((int) stok[j][2] == pilihGudang && (int) stok[j][0] == i) {
                    stokCount += getStokObat(j);
                }
            }
            if (stokCount > 0) {
                System.out.println("(" + i + ") " + namaObat[i] + ": " + stokCount);
            }
        }
        System.out.println("Pilih Obat : ");
        int pilihNamaObat = getUserInput(input, 0, namaObat.length - 1);
        cleanDisplay();
        headLine(" Gudang " + gudang[pilihGudang] + " ");
        for (int i = 0; i < stok.length; i++) {
            if ((int) stok[i][2] == pilihGudang && (int) stok[i][0] == pilihNamaObat) {
                boolean isExpired = checkExpiry(getExpObat(i));
                System.out.println("(" + i + ") " + getNamaObat(i) + ": " + getStokObat(i) + "  Batch: "
                        + getBatchObat(i) + "  Exp: " + getExpObat(i) + " Status: "
                        + getStatusObat(i));
            }
        }
        exitPrompt();
    }

    static void tambahStockPage() throws ParseException {
        Scanner input = new Scanner(System.in);
        cleanDisplay();
        displayWarehouse();
        System.out.print("Pilih Gudang : ");
        int pilihGudang = (getUserInput(input, 1, gudang.length) - 1);
        // Tambah Stok Section
        cleanDisplay();
        headLine(" Gudang " + gudang[pilihGudang] + " ");
        for (int i = 0; i < namaObat.length; i++) { // Repeat per Nama Obat
            int stokCount = 0;
            for (int j = 0; j < stok.length; j++) { // Repeat per Stock
                if ((int) stok[j][2] == pilihGudang && (int) stok[j][0] == i) {
                    stokCount += getStokObat(j);
                }
            }
            if (stokCount > 0) {
                System.out.println("(" + i + ") " + namaObat[i] + ": " + stokCount);
            }
        }
        System.out.println("Pilih Obat : ");
        int pilihNamaObat = getUserInput(input, 0, namaObat.length - 1);
        cleanDisplay();
        headLine(" Gudang " + gudang[pilihGudang] + " ");
        for (int i = 0; i < stok.length; i++) {
            if ((int) stok[i][2] == pilihGudang && (int) stok[i][0] == pilihNamaObat) {
                boolean isExpired = checkExpiry(getExpObat(i));
                System.out.println("(" + i + ") " + getNamaObat(i) + ": " + getStokObat(i) + "  Batch: "
                        + getBatchObat(i) + "  Exp: " + getExpObat(i) + " Status: "
                        + getStatusObat(i));
            }
        }
        System.out.print("Masukkan ID Obat : ");
        int pilihStok = getUserInput(input, 0, stok.length - 1);

        if ((int) stok[pilihStok][2] == pilihGudang) {
            System.out.print("Masukkan Jumlah Tambah Stok Obat : ");
            int jumlahTambah = input.nextInt();
            boolean isExpired = checkExpiry(getExpObat(pilihStok));
            cleanDisplay();
            headLine(" Tambah Obat ");
            System.out.println("ID Obat       : " + stok[pilihStok][0]);
            System.out.println("Nama Obat     : " + getNamaObat(pilihStok));
            System.out.println("Batch         : " + getBatchObat(pilihStok));
            System.out.println("Expired       : " + getExpObat(pilihStok));
            System.out.println("Status        : " + getStatusObat(pilihStok));
            System.out.println("Gudang        : " + gudang[(int) stok[pilihStok][2]]);
            System.out.println("Stok awal     : " + stok[pilihStok][1]);
            System.out.println("Stok akhir    : " + ((int) stok[pilihStok][1] + jumlahTambah));
            System.out.println("Jumlah tambah : " + jumlahTambah);
            if (confirmationPrompt()) {
                stok[pilihStok][1] = (int) stok[pilihStok][1] + jumlahTambah;
            }
        } else {
            System.out.println("ERROR! Masukkan Nomor ID obat yang benar!");
        }

        exitPrompt();
    }

    static void ambilStockPage() throws ParseException {
        Scanner input = new Scanner(System.in);
        cleanDisplay();
        displayWarehouse();
        System.out.print("Pilih Gudang : ");
        int pilihGudang = getUserInput(input, 1, gudang.length) - 1;
        cleanDisplay();
        // Ambil Stok Section
        headLine(" Gudang " + gudang[pilihGudang] + " ");
        for (int i = 0; i < namaObat.length; i++) { // Repeat per Nama Obat
            int stokCount = 0;
            for (int j = 0; j < stok.length; j++) { // Repeat per Stock
                if ((int) stok[j][2] == pilihGudang && (int) stok[j][0] == i) {
                    stokCount += getStokObat(j);
                }
            }
            if (stokCount > 0) {
                System.out.println("(" + i + ") " + namaObat[i] + ": " + stokCount);
            }
        }
        System.out.println("Pilih Obat : ");
        int pilihNamaObat = getUserInput(input, 0, namaObat.length - 1);
        cleanDisplay();
        headLine(" Gudang " + gudang[pilihGudang] + " ");
        for (int i = 0; i < stok.length; i++) {
            if ((int) stok[i][2] == pilihGudang && (int) stok[i][0] == pilihNamaObat) {
                boolean isExpired = checkExpiry(getExpObat(i));
                System.out.println("(" + i + ") " + getNamaObat(i) + ": " + getStokObat(i) + "  Batch: "
                        + getBatchObat(i) + "  Exp: " + getExpObat(i) + " Status: "
                        + getStatusObat(i));
            }
        }
        System.out.print("Masukkan ID Obat : ");
        int pilihStok = getUserInput(input, 0, stok.length - 1);

        if ((int) stok[pilihStok][2] == pilihGudang) {
            System.out.print("Masukkan Jumlah Ambil Stok Obat : ");
            int ambilStok = input.nextInt();
            boolean isExpired = checkExpiry(getExpObat(pilihStok));
            if (ambilStok <= getStokObat(pilihStok)) {
                cleanDisplay();
                headLine(" Ambil Obat ");
                System.out.println("ID Obat       : " + stok[pilihStok][0]);
                System.out.println("Nama Obat     : " + getNamaObat(pilihStok));
                System.out.println("Batch         : " + getBatchObat(pilihStok));
                System.out.println("Expired       : " + getExpObat(pilihStok));
                System.out.println("Status        : " + getStatusObat(pilihStok));
                System.out.println("Gudang        : " + gudang[(int) stok[pilihStok][2]]);
                System.out.println("Stok awal     : " + stok[pilihStok][1]);
                System.out.println("Stok akhir    : " + ((int) stok[pilihStok][1] - ambilStok));
                System.out.println("Jumlah Ambil  : " + ambilStok);
                if (confirmationPrompt()) {
                    stok[pilihStok][1] = (int) stok[pilihStok][1] - ambilStok;
                }
            } else if (ambilStok > getStokObat(pilihStok)) {
                System.out.println("ERROR! Stok Tidak Mencukupi!");
            }
        } else {
            System.out.println("ERROR! Masukkan Nomor ID obat yang benar!");
        }

        exitPrompt();
    }

    static void searchPage() {
        Scanner input = new Scanner(System.in);
        cleanDisplay();
        System.out.print("Masukkan Obat yang ingin dicari : ");
        String cariObat = input.next();
        search(cariObat);
        exitPrompt();
    }

    static void allDatasPage() {
        cleanDisplay();
        headLine(" Data Seluruh Obat ");
        for (int i = 0; i < gudang.length; i++) {
            System.out.println("\nData Obat Gudang " + gudang[i] + ": ");
            boolean hasObat = false;
            for (int j = 0; j < namaObat.length; j++) {
                int stockCount = 0;
                boolean hasStok = false;
                for (int k = 0; k < stok.length; k++) {
                    if ((int) stok[k][2] == i && (int) stok[k][0] == j) {
                        stockCount += getStokObat(k);
                        hasStok = true;
                        hasObat = true;
                    }
                }
                if (hasStok) {
                    System.out.println(namaObat[j] + ": " + stockCount);
                }
            }
            if (!hasObat) {
                System.out.println("- Gudang Kosong -");
            }
        }
        exitPrompt();
    }

    static void obatSettingPage() {
        Scanner input = new Scanner(System.in);
        cleanDisplay();
        System.out.println("1. Tambah Jenis Obat");
        System.out.println("2. Hapus Obat");
        System.out.println("3. Lihat Keseluruhan Jenis Obat");
        System.out.println("4. Tambah Obat di Gudang");
        System.out.println("5. Hapus Obat di Gudang");
        System.out.println("6. Transfer Obat");
        System.out.print("Pilih Menu : ");
        int pilihMenu = input.nextInt();
        switch (pilihMenu) {
            case 1: // Tambah Jenis Obat
                addObatTypePage();
                break;
            case 2: // Hapus jenis Obat
                deleteObatTypePage();
                break;
            case 3: // Lihat Keseluruhan Jenis Obat
                allObatTypePage();
                break;
            case 4: // Tambah Obat di Gudang
                addObatToGudangPage();
                break;
            case 5: // Hapus Obat di Gudang
                deleteObatInGudangPage();
                break;
            case 6: // Transfer Obat
                obatTransferPage();
                break;

            default:
                break;
        }
    }

    static void addObatTypePage() {
        Scanner input = new Scanner(System.in);
        cleanDisplay();
        System.out.print("Masukkan Nama Obat Baru : ");
        String obatBaru = input.next();
        if (confirmationPrompt()) {
            namaObat = addElement(namaObat, obatBaru);
            indexDatabase = 0;
            populateDatabase();
        }
        exitPrompt();
    }

    static void deleteObatTypePage() {
        Scanner input = new Scanner(System.in);
        cleanDisplay();
        displayMedicine();
        System.out.print("Pilih Obat yang Akan Dihapus : ");
        int pilihMenu = (getUserInput(input, 1, namaObat.length) - 1);
        if (confirmationPrompt()) {
            for (int i = stok.length - 1; i >= 0; i--) {
                if ((int) stok[i][0] > pilihMenu) {
                    stok[i][0] = (int) stok[i][0] - 1;
                } else if ((int) stok[i][0] == pilihMenu) {
                    deleteStock(i);
                }
            }
            namaObat = deleteElement(namaObat, pilihMenu);
            indexDatabase = 0;
            populateDatabase();
        }
        exitPrompt();
    }

    static void allObatTypePage() {
        cleanDisplay();
        displayMedicine();
        exitPrompt();
    }

    static void addObatToGudangPage() {
        Scanner input = new Scanner(System.in);
        cleanDisplay();
        displayWarehouse();
        System.out.println("Pilih Gudang : ");
        int pilihPenambahanGudang = input.nextInt() - 1;
        cleanDisplay();
        displayMedicine();
        System.out.println("Pilih Obat : ");
        int pilihPenambahanObat = input.nextInt() - 1;
        System.out.println("Masukkan Jumlah Obat : ");
        int jumlahObat = input.nextInt();
        System.out.println("Masukkan Batch Obat : ");
        String batch = input.next();
        System.out.println("Masukkan Expired Obat (DD-MM-YYYY) : ");
        String expired = input.next();
        boolean obatSudahAda = false;
        for (int i = 0; i < stok.length; i++) {
            if ((int) stok[i][2] == pilihPenambahanGudang
                    && (int) stok[i][0] == pilihPenambahanObat
                    && batch.equals(getBatchObat(i))) {
                obatSudahAda = true;
                System.out.println("Obat sudah tersedia!");
                exitPrompt();
                break;
            }
        }
        if (!obatSudahAda) {
            if (confirmationPrompt()) {
                addStock(pilihPenambahanObat, jumlahObat, pilihPenambahanGudang, batch,
                        expired);
                System.out.println("Obat berhasil ditambahkan ke gudang!");
            }
            exitPrompt();
        }
    }

    static void deleteObatInGudangPage() {
        Scanner input = new Scanner(System.in);
        cleanDisplay();
        displayWarehouse();
        System.out.print("Pilih Gudang : ");
        int pilihPenghapusanGudang = input.nextInt() - 1;
        cleanDisplay();
        headLine(" Gudang " + gudang[pilihPenghapusanGudang] + " ");
        for (int i = 0; i < namaObat.length; i++) { // Repeat per Nama Obat
            int stokCount = 0;
            for (int j = 0; j < stok.length; j++) { // Repeat per Stock
                if ((int) stok[j][2] == pilihPenghapusanGudang && (int) stok[j][0] == i) {
                    stokCount += getStokObat(j);
                }
            }
            if (stokCount > 0) {
                System.out.println("(" + i + ") " + namaObat[i] + ": " + stokCount);
            }
        }
        System.out.println("Pilih Obat : ");
        int pilihNamaObat = getUserInput(input, 0, namaObat.length - 1);
        cleanDisplay();
        headLine(" Gudang " + gudang[pilihPenghapusanGudang] + " ");
        for (int i = 0; i < stok.length; i++) {
            if ((int) stok[i][2] == pilihPenghapusanGudang
                    && (int) stok[i][0] == pilihNamaObat) {
                System.out.println(
                        "(" + i + ") " + getNamaObat(i) + ": " + getStokObat(i) + "  Batch: "
                                + getBatchObat(i) + "  Exp: " + getExpObat(i));
            }
        }

        System.out.print("Masukkan ID Obat : ");
        int pilihStok = getUserInput(input, 0, stok.length - 1);

        // Menghapus obat
        if (confirmationPrompt()) {
            deleteStock(pilihStok);
            System.out.println("Obat berhasil dihapus dari gudang!");
        }
        exitPrompt();
    }

    static void obatTransferPage() {
        Scanner input = new Scanner(System.in);
        cleanDisplay();
        displayWarehouse();
        System.out.print("Pilih Gudang : ");
        int pilihGudang = getUserInput(input, 1, gudang.length) - 1;
        cleanDisplay();
        headLine(" Gudang " + gudang[pilihGudang] + " ");
        for (int i = 0; i < namaObat.length; i++) { // repeat per obat name
            int stokCount = 0;
            for (int j = 0; j < stok.length; j++) { // repeat per obat batch
                if ((int) stok[j][2] == pilihGudang && (int) stok[j][0] == i) { // if the gudang
                                                                                // is same then
                    stokCount += (int) stok[j][1];
                }
            }
            if (stokCount > 0) {
                System.out.println("(" + i + ") " + namaObat[i] + ": " + stokCount);
            }
        }
        System.out.println("Pilih Obat yang Akan ditransfer:");
        int pilihObatTransfer = getUserInput(input, 0, namaObat.length - 1);
        cleanDisplay();
        headLine(" Data Obat " + namaObat[pilihObatTransfer] + " di Gudang "
                + gudang[pilihGudang] + " ");
        boolean hasObat = false;
        for (int j = 0; j < stok.length; j++) {
            if ((int) stok[j][2] == pilihGudang && (int) stok[j][0] == pilihObatTransfer) {
                System.out.println("(" + j + ") " + getNamaObat(j) + ": " + getStokObat(j)
                        + " Batch: " + getBatchObat(j) + " Exp: " + getExpObat(j));
                hasObat = true;
            }
        }
        if (!hasObat) {
            System.out.println("- Gudang Kosong -");
        }
        System.out.print("\nPilih ID Obat : ");
        int pilihStok = getUserInput(input, 0, stok.length - 1);

        System.out.print("\nMasukkan Jumlah yang akan dipindah : ");
        int ambilStok = getUserInput(input, 1, (int) stok[pilihStok][1]);

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
        if (confirmationPrompt()) {
            // Implement Pindah Gudang
            hasObat = false;
            for (int j = 0; j < stok.length; j++) {
                if ((int) stok[j][2] == pilihGudangTujuan && (int) stok[j][0] == pilihStok
                        && getBatchObat(pilihStok).equals(getBatchObat(j))) {
                    stok[j][1] = (int) stok[j][1] + ambilStok;
                    hasObat = true;
                    break;
                }
            }
            if (!hasObat) {
                addStock((int) stok[pilihStok][0], ambilStok, pilihGudangTujuan,
                        getBatchObat(pilihStok), getExpObat(pilihStok));
            }
            stok[pilihStok][1] = (int) stok[pilihStok][1] - ambilStok;
        }

        exitPrompt();
    }

    static void gudangSettingPage() {
        Scanner input = new Scanner(System.in);
        cleanDisplay();
        System.out.println("1. Tambah Gudang");
        System.out.println("2. Hapus Gudang");
        System.out.print("Pilih Menu : ");
        int pilihMenu = input.nextInt();
        switch (pilihMenu) {
            case 1: // Tambah Gudang
                addGudangPage();
                break;
            case 2: // Hapus Gudang
                deleteGudangPage();
                break;
            default:
                break;
        }
    }

    static void addGudangPage() {
        Scanner input = new Scanner(System.in);
        cleanDisplay();
        System.out.print("Nama Gudang Baru : ");
        String gudangBaru = input.next();
        if (confirmationPrompt()) {
            gudang = addElement(gudang, gudangBaru);
        }
        exitPrompt();
    }

    static void deleteGudangPage() {
        Scanner input = new Scanner(System.in);
        cleanDisplay();
        displayWarehouse();
        System.out.print("Pilih Gudang yang Akan Dihapus : ");
        int pilihMenu = (getUserInput(input, 1, gudang.length) - 1);
        if (confirmationPrompt()) {
            int index = 0;
            for (int i = stok.length - 1; i >= 0; i--) {
                if ((int) stok[i][2] > pilihMenu) {
                    stok[i][2] = (int) stok[i][2] - 1;
                } else if ((int) stok[i][2] == pilihMenu) {
                    deleteStock(i);
                }
            }
            gudang = deleteElement(gudang, pilihMenu);
        }
        exitPrompt();
    }

    static void exitPage() {
        Scanner input = new Scanner(System.in);
        cleanDisplay();
        // Exit Menu
        System.out.println("1. Keluar Akun");
        System.out.println("2. Keluar Program");
        System.out.println("3. Kembali");
        System.out.print("Pilih Menu : ");
        int pilihMenuKeluar = input.nextInt();
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
                exitPrompt();
                break;
        }
    }

    static void addStock(int nama, int stokObat, int gudang, String batch, String expired) {
        Object[] elementToAdd = new Object[] { nama, stokObat, gudang, batch, expired };
        stok = addElement(stok, elementToAdd);
    }

    static void deleteStock(int idObat) {
        stok = deleteElement(stok, idObat);
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

    static void exitPrompt() {
        Scanner input = new Scanner(System.in);
        System.out.println("Masukkan apapun untuk kembali ke menu ");
        userInput = input.next();
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
        return namaObat[(int) stok[index][0]];
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

    static String getStatusObat(int index) throws ParseException {
        return (checkExpiry(getExpObat(index)) ? "Expired" : "Aman");
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

    public static boolean confirmationPrompt() {
        Scanner input = new Scanner(System.in);
        System.out.println("Apakah anda yakin? (Y/N)");
        if (input.nextLine().equalsIgnoreCase("y")) {
            return true;
        } else {
            return false;

        }
    }

    public static int[] searchFull(String word) {
        int matchedQuery[] = {};
        for (int i = 0; i < namaObat.length; i++) { // repeat per database
            for (int j = 0; j < substrings[i].length; j++) { // repeat per database's substrings
                if (substrings[i][j] != null && substrings[i][j].equalsIgnoreCase(word)) {
                    matchedQuery = addElement(matchedQuery, i);
                    break;
                }
            }
        }
        return matchedQuery;
    }

    public static void search(String word) {
        if (searchFull(word).length != 0) {
            int[] matchedQueries = searchFull(word);
            for (int i = 0; i < gudang.length; i++) {
                System.out.println("\nData Obat Gudang " + gudang[i] + ": ");
                boolean hasObat = false;
                for (int j = 0; j < matchedQueries.length; j++) { // repeat per matched obat query
                    int stockCount = 0;
                    boolean hasStok = false;
                    for (int k = 0; k < stok.length; k++) {
                        if ((int) stok[k][2] == i && (int) stok[k][0] == matchedQueries[j]) {
                            stockCount += getStokObat(k);
                            hasStok = true;
                            hasObat = true;
                        }
                    }
                    if (hasStok) {
                        System.out.println(namaObat[matchedQueries[j]] + ": " + stockCount);
                    }
                }
                if (!hasObat) {
                    System.out.println("- Gudang Kosong -");
                }
            }
        } else {
            int matchedQuery = searchSubstrings(word);
            System.out.println("Tidak ada obat yang sesuai");
            System.out.println("Mungkin yang anda maksud adalah : " + namaObat[matchedQuery] + " (Y/N)");
            Scanner scanner = new Scanner(System.in);
            char c = scanner.next().charAt(0);
            if (c == 'Y' || c == 'y') {
                for (int i = 0; i < stok.length; i++) { // repeat per stok
                    if ((int) stok[i][0] == matchedQuery) {
                        System.out.println(
                                (i + 1) + ". " + getNamaObat(matchedQuery) + " " + getStokObat(matchedQuery)
                                        + " " + getBatchObat(matchedQuery) + " " + getExpObat(matchedQuery));

                    }
                }
            }
        }
    }

    public static int searchSubstrings(String userInput) {
        int length = userInput.length();
        String[] userQuerySubstrings = new String[100];
        int[] queryWeight = new int[100]; // weight per data in the database
        int highestWeight = 0;
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

    static boolean checkExpiry(String expiryDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date today = new Date();
        Date expiry = sdf.parse(expiryDate);

        return today.after(expiry);
    }

    public static int[] addElement(int[] originalArray, int elementToAdd) {
        int[] newArray = Arrays.copyOf(originalArray, originalArray.length + 1);
        newArray[newArray.length - 1] = elementToAdd;
        return newArray;
    }

    public static String[] addElement(String[] originalArray, String elementToAdd) {
        String[] newArray = Arrays.copyOf(originalArray, originalArray.length + 1);
        newArray[newArray.length - 1] = elementToAdd;
        return newArray;
    }

    public static Object[][] addElement(Object[][] originalArray, Object[] elementToAdd) {
        Object[][] newArray = Arrays.copyOf(originalArray, originalArray.length + 1);
        newArray[newArray.length - 1] = elementToAdd;
        return newArray;
    }

    public static int[] deleteElement(int[] originalArray, int indexToDelete) {
        if (indexToDelete < 0 || indexToDelete >= originalArray.length) {
            return originalArray;
        }
        int[] newArray = new int[originalArray.length - 1];
        System.arraycopy(originalArray, 0, newArray, 0, indexToDelete);
        System.arraycopy(originalArray, indexToDelete + 1, newArray, indexToDelete, newArray.length - indexToDelete);
        return newArray;
    }

    public static String[] deleteElement(String[] originalArray, int indexToDelete) {
        if (indexToDelete < 0 || indexToDelete >= originalArray.length) {
            return originalArray;
        }
        String[] newArray = new String[originalArray.length - 1];
        System.arraycopy(originalArray, 0, newArray, 0, indexToDelete);
        System.arraycopy(originalArray, indexToDelete + 1, newArray, indexToDelete, newArray.length - indexToDelete);
        return newArray;
    }

    public static Object[][] deleteElement(Object[][] originalArray, int indexToDelete) {
        if (indexToDelete < 0 || indexToDelete >= originalArray.length) {
            return originalArray;
        }
        Object[][] newArray = new Object[originalArray.length - 1][];
        System.arraycopy(originalArray, 0, newArray, 0, indexToDelete);
        System.arraycopy(originalArray, indexToDelete + 1, newArray, indexToDelete, newArray.length - indexToDelete);
        return newArray;
    }
}  
