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
    static int loggedUser = -1;
    static boolean isLoggedIn = false, isRunning = true;
    static String[] namaObat = { "Pfizer", "Promag", "Paracetamol", "Amoxicillin", "Decolgen" };
    static String[] gudang = { "Malang", "Jakarta", "Kediri", "Surabaya", "Gresik" };
    static Object[][] loginDatabase = {
            { "Anfasa", "Qw123", "Manager" },
            { "Ammar", "test", "Manager" },
            { "Arjuna", "345", "Manager" },
            { "manajer", "test", "Manager" },
            { "admin", "test", "Admin" },
    };
    // Stok keseluruhan
    static Object[][] stok = {
            { 0, 21, 0, "Batch001", "15-03-2024" },
            { 0, 98, 0, "Batch002", "20-02-2023" },
            { 1, 57, 1, "Batch003", "10-09-2024" },
            { 2, 73, 2, "Batch004", "25-11-2024" },
            { 3, 59, 1, "Batch005", "18-01-2025" },
            { 4, 34, 3, "Batch006", "02-03-2025" },
            { 0, 82, 1, "Batch001", "12-03-2024" },
            { 2, 141, 3, "Batch008", "07-08-2022" },
            { 3, 42, 0, "Batch009", "30-12-2024" },
            { 4, 29, 2, "Batch010", "14-02-2024" },
            { 1, 65, 2, "Batch012", "05-04-2025" },
            { 0, 45, 3, "Batch015", "23-06-2024" },
            { 1, 30, 3, "Batch016", "28-10-2022" },
            { 3, 87, 1, "Batch017", "08-02-2025" },
            { 4, 50, 0, "Batch018", "17-05-2025" },
            { 2, 63, 2, "Batch019", "01-04-2024" },
            { 0, 76, 1, "Batch020", "14-09-2024" },
            { 3, 22, 3, "Batch021", "02-11-2022" },
            { 4, 39, 0, "Batch022", "30-01-2025" },
            { 1, 54, 2, "Batch023", "20-03-2024" },
            { 2, 68, 2, "Batch024", "09-07-2024" },
            { 0, 33, 1, "Batch025", "25-08-2024" },
            { 1, 78, 3, "Batch026", "03-05-2024" },
            { 2, 40, 2, "Batch027", "18-02-2025" },
            { 3, 25, 0, "Batch028", "10-10-2024" },
            { 4, 62, 0, "Batch029", "12-12-2022" },
            { 0, 63, 0, "Batch031", "15-03-2022" }
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
                if (loginDatabase[loggedUser][2].equals("Admin")) {
                    adminMenuPage();
                } else if (loginDatabase[loggedUser][2].equals("Manager")) {
                    managerMenuPage();
                }
            }
        }

    }

    static void adminMenuPage() throws ParseException {
        Scanner input = new Scanner(System.in);
        deleteObatHabis();
        cleanDisplay();
        // Notification if login valid
        System.out.printf("Selamat Datang Kembali %s\n",
                (String) loginDatabase[loggedUser][0]);
        displayMenu();

        System.out.print("Pilih Menu : ");
        int pilihMenu = input.nextInt();

        switch (pilihMenu) {
            // Kelola Obat
            case 1:
                kelolaObatPage();
                break;
            // Cari Obat
            case 2:
                searchPage();
                break;
            // Data Keseluruhan
            case 3:
                allDatasPage();
                break;
            // Setting Obat
            case 4:
                obatSettingPage();
                break;
            // Setting Gudang
            case 5:
                gudangSettingPage();
                break;
            // Menu Keluar
            case 6:
                exitPage();
                break;
            default:
                System.out.println("Error: Invalid User Input!");
                exitPrompt();
                break;
        }
    }

    static void managerMenuPage() throws ParseException {
        Scanner input = new Scanner(System.in);
        deleteObatHabis();
        cleanDisplay();
        // Notification if login valid
        System.out.printf("Selamat Datang Kembali %s\n",
                (String) loginDatabase[loggedUser][0]);
        displayManagerMenu();

        System.out.print("Pilih Menu : ");
        int pilihMenu = input.nextInt();

        switch (pilihMenu) {
            // Cari Obat
            case 1:
                searchPage();
                break;
            // Data Keseluruhan
            case 2:
                allDatasPage();
                break;
            // Setting Gudang
            case 3:
                gudangSettingPage();
                break;
            // Setting User
            case 4:
                userSettingPage();
                break;
            // Menu Keluar
            case 5:
                exitPage();
                break;
            default:
                System.out.println("Error: Invalid User Input!");
                exitPrompt();
                break;
        }
    }

    static void kelolaObatPage() throws ParseException {
        Scanner input = new Scanner(System.in);
        cleanDisplay();
        headLine(" Kelola Obat ");
        System.out.println("1. Lihat Stok");
        System.out.println("2. Tambah Stok");
        System.out.println("3. Ambil Stok");
        System.out.println("4. Kelola Obat Expired");
        System.out.print("Pilih Menu : ");
        int pilihMenu = input.nextInt();
        cleanDisplay();
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
            // Kelola Stock Expired
            case 4:
                kelolaStokExpiredPage();
                break;
        }
    }

    static void kelolaStokExpiredPage() throws ParseException {
        Scanner input = new Scanner(System.in);
        cleanDisplay();
        headLine(" Kelola Stok Expired");
        System.out.println("1. Lihat Stok Expired");
        System.out.println("2. Hapus Stok Expired");
        System.out.print("Pilih Menu : ");
        int pilihMenu = input.nextInt();
        cleanDisplay();
        switch (pilihMenu) {
            case 1:
                lihatStockExpiredPage();
                break;
            case 2:
                hapusStockExpiredPage();
                break;
        }
    }

    static void lihatStockExpiredPage() throws ParseException {
        int[] matchedGudang = {};
        Object[][] matchedQueries = {};
        for (int i = 0; i < gudang.length; i++) {
            boolean hasObat = false;
            for (int k = 0; k < stok.length; k++) {
                if ((int) stok[k][2] == i && checkExpiry(getExpObat(k)) == true) {
                    matchedQueries = addElement(matchedQueries,
                            new Object[] { k, stok[k][0], stok[k][1], stok[k][2], stok[k][3], stok[k][4] });
                    hasObat = true;
                }
            }
            if (hasObat) {
                matchedGudang = addElement(matchedGudang, i);
            }
        }
        for (int i = 0; i < matchedGudang.length; i++) {
            tableHeader("DATA OBAT EXPIRED" + " GUDANG " + gudang[i].toUpperCase(), 118);
            columnHeader(new Object[][] { { "ID", 13 }, { "NAMA OBAT", 40 }, { "JUMLAH STOK", 17 },
                    { "TANGGAL EXPIRED", 19 }, { "BATCH", 25 } });
            for (int j = 0; j < matchedQueries.length; j++) {
                if ((int) matchedQueries[j][3] == matchedGudang[i]) {
                    tableRow(new Object[][] { { Integer.toString((int) matchedQueries[j][0]), 13 },
                            { namaObat[(int) matchedQueries[j][1]], 40 },
                            { Integer.toString((int) matchedQueries[j][2]), 17 }, { matchedQueries[j][5], 19 },
                            { matchedQueries[j][4], 25 } });
                }
            }
            tableLine(120);
            System.out.println();
        }
        if (matchedGudang.length == 0) {
            System.out.println("Data Obat Expired Tidak Ditemukan!");
        }
        exitPrompt();
    }

    static void hapusStockExpiredPage() throws ParseException {
        Scanner input = new Scanner(System.in);
        int[] matchedGudang = {};
        Object[][] matchedQueries = {};
        for (int i = 0; i < gudang.length; i++) {
            boolean hasObat = false;
            for (int k = 0; k < stok.length; k++) {
                if ((int) stok[k][2] == i && checkExpiry(getExpObat(k)) == true) {
                    matchedQueries = addElement(matchedQueries,
                            new Object[] { k, stok[k][0], stok[k][1], stok[k][2], stok[k][3], stok[k][4] });
                    hasObat = true;
                }
            }
            if (hasObat) {
                matchedGudang = addElement(matchedGudang, i);
            }
        }
        for (int i = 0; i < matchedGudang.length; i++) {
            tableHeader("DATA OBAT EXPIRED" + " GUDANG " + gudang[i].toUpperCase(), 118);
            columnHeader(new Object[][] { { "ID", 13 }, { "NAMA OBAT", 40 }, { "JUMLAH STOK", 17 },
                    { "TANGGAL EXPIRED", 19 }, { "BATCH", 25 } });
            for (int j = 0; j < matchedQueries.length; j++) {
                if ((int) matchedQueries[j][3] == matchedGudang[i]) {
                    tableRow(new Object[][] { { Integer.toString((int) matchedQueries[j][0]), 13 },
                            { namaObat[(int) matchedQueries[j][1]], 40 },
                            { Integer.toString((int) matchedQueries[j][2]), 17 }, { matchedQueries[j][5], 19 },
                            { matchedQueries[j][4], 25 } });
                }
            }
            tableLine(120);
            System.out.println();
        }
        System.out.println("Apakah anda ingin menghapus stok expired? (Y/N)");
        char confirm = input.next().charAt(0);
        if (confirm == 'Y' || confirm == 'y') {
            for (int i = stok.length - 1; i > 0; i--) {
                if (checkExpiry(getExpObat(i)) == true) {
                    deleteStock(i);
                }
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
        // headLine(" Gudang " + gudang[pilihGudang] + " ");
        tableHeader("DATA OBAT" + " GUDANG " + gudang[pilihGudang].toUpperCase(), 57);
        columnHeader(new Object[][] { { "ID", 8 }, { "NAMA OBAT", 35 }, { "STOK", 12 } });
        for (int i = 0; i < namaObat.length; i++) { // Repeat per Nama Obat
            int stokCount = 0;
            for (int j = 0; j < stok.length; j++) { // Repeat per Stock
                if ((int) stok[j][2] == pilihGudang && (int) stok[j][0] == i) {
                    stokCount += getStokObat(j);
                }
            }
            if (stokCount > 0) {
                tableRow(new Object[][] { { Integer.toString(i), 8 }, { namaObat[i], 35 },
                        { Integer.toString(stokCount), 12 } });
                // System.out.println("(" + i + ") " + namaObat[i] + ": " + stokCount);
            }
        }
        tableLine(59);
        System.out.println("Pilih Obat : ");
        int pilihNamaObat = checkValidNamaObat(getUserInput(input, 0, namaObat.length - 1), pilihGudang);
        cleanDisplay();
        // headLine(" Gudang " + gudang[pilihGudang] + " ");
        tableHeader("DATA OBAT" + " GUDANG " + gudang[pilihGudang].toUpperCase(), 117);
        columnHeader(new Object[][] { { "ID", 8 }, { "NAMA OBAT", 35 }, { "STOK", 12 }, { "BATCH", 35 },
                { "EXPIRED", 12 }, { "STATUS", 10 } });
        for (int i = 0; i < stok.length; i++) {
            if ((int) stok[i][2] == pilihGudang && (int) stok[i][0] == pilihNamaObat) {
                boolean isExpired = checkExpiry(getExpObat(i));
                tableRow(new Object[][] { { Integer.toString(i), 8 }, { getNamaObat(i), 35 },
                        { Integer.toString(getStokObat(i)), 12 }, { getBatchObat(i), 35 }, { getExpObat(i), 12 },
                        { getStatusObat(i), 10 } });
                // System.out.println("(" + i + ") " + getNamaObat(i) + ": " + getStokObat(i) +
                // " Batch: "
                // + getBatchObat(i) + " Exp: " + getExpObat(i) + " Status: "
                // + getStatusObat(i));
            }
        }
        tableLine(119);
        System.out.print("Masukkan ID Obat : ");
        int pilihStok = checkValidInput(getUserInput(input, 0, stok.length - 1), pilihGudang, pilihNamaObat);
        if ((int) stok[pilihStok][2] == pilihGudang) {
            System.out.print("Masukkan Jumlah Tambah Stok Obat : ");
            int jumlahTambah = input.nextInt();
            boolean isExpired = checkExpiry(getExpObat(pilihStok));
            cleanDisplay();
            headLine(" Tambah Obat ");
            System.out.println("ID Obat       : " + pilihStok);
            System.out.println("Nama Obat     : " + getNamaObat(pilihStok));
            System.out.println("Batch         : " + getBatchObat(pilihStok));
            System.out.println("Expired       : " + getExpObat(pilihStok));
            System.out.println("Status        : " + getStatusObat(pilihStok));
            System.out.println("Gudang        : " + gudang[(int) stok[pilihStok][2]]);
            System.out.println("Stok awal     : " + stok[pilihStok][1]);
            System.out.println("Stok akhir    : " + ((int) stok[pilihStok][1] + jumlahTambah));
            System.out.println("Jumlah tambah : " + jumlahTambah);
            if (confirmationPrompt("Apakah anda yakin ingin menambahkan obat?")) {
                stok[pilihStok][1] = (int) stok[pilihStok][1] + jumlahTambah;
            }
        } else {
            System.out.println("ERROR! Masukkan Nomor ID obat yang benar!");
        }

        exitPrompt();
    }

    static void lihatStockPage() throws ParseException {
        Scanner input = new Scanner(System.in);
        cleanDisplay();
        displayWarehouse();
        System.out.print("Pilih Gudang : ");
        int pilihGudang = getUserInput(input, 1, gudang.length) - 1;
        // Lihat Stok Section
        cleanDisplay();
        // headLine(" Gudang " + gudang[pilihGudang] + " ");
        tableHeader("DATA OBAT" + " GUDANG " + gudang[pilihGudang].toUpperCase(), 57);
        columnHeader(new Object[][] { { "ID", 8 }, { "NAMA OBAT", 35 }, { "STOK", 12 } });
        for (int i = 0; i < namaObat.length; i++) { // Repeat per Nama Obat
            int stokCount = 0;
            for (int j = 0; j < stok.length; j++) { // Repeat per Stock
                if ((int) stok[j][2] == pilihGudang && (int) stok[j][0] == i) {
                    stokCount += getStokObat(j);
                }
            }
            if (stokCount > 0) {
                tableRow(new Object[][] { { Integer.toString(i), 8 }, { namaObat[i], 35 },
                        { Integer.toString(stokCount), 12 } });
                // System.out.println("(" + i + ") " + namaObat[i] + ": " + stokCount);
            }
        }
        tableLine(59);
        System.out.println("Pilih Obat : ");
        int pilihNamaObat = getUserInput(input, 0, namaObat.length - 1);
        cleanDisplay();
        // headLine(" Gudang " + gudang[pilihGudang] + " ");
        tableHeader("DATA OBAT" + " GUDANG " + gudang[pilihGudang].toUpperCase(), 117);
        columnHeader(new Object[][] { { "ID", 8 }, { "NAMA OBAT", 35 }, { "STOK", 12 }, { "BATCH", 35 },
                { "EXPIRED", 12 }, { "STATUS", 10 } });
        for (int i = 0; i < stok.length; i++) {
            if ((int) stok[i][2] == pilihGudang && (int) stok[i][0] == pilihNamaObat) {
                boolean isExpired = checkExpiry(getExpObat(i));
                tableRow(new Object[][] { { Integer.toString(i), 8 }, { getNamaObat(i), 35 },
                        { Integer.toString(getStokObat(i)), 12 }, { getBatchObat(i), 35 }, { getExpObat(i), 12 },
                        { getStatusObat(i), 10 } });
                // System.out.println("(" + i + ") " + getNamaObat(i) + ": " + getStokObat(i) +
                // " Batch: "
                // + getBatchObat(i) + " Exp: " + getExpObat(i) + " Status: "
                // + getStatusObat(i));
            }
        }
        tableLine(119);
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
        // headLine(" Gudang " + gudang[pilihGudang] + " ");
        tableHeader("DATA OBAT" + " GUDANG " + gudang[pilihGudang].toUpperCase(), 57);
        columnHeader(new Object[][] { { "ID", 8 }, { "NAMA OBAT", 35 }, { "STOK", 12 } });
        for (int i = 0; i < namaObat.length; i++) { // Repeat per Nama Obat
            int stokCount = 0;
            for (int j = 0; j < stok.length; j++) { // Repeat per Stock
                if ((int) stok[j][2] == pilihGudang && (int) stok[j][0] == i) {
                    stokCount += getStokObat(j);
                }
            }
            if (stokCount > 0) {
                tableRow(new Object[][] { { Integer.toString(i), 8 }, { namaObat[i], 35 },
                        { Integer.toString(stokCount), 12 } });
                // System.out.println("(" + i + ") " + namaObat[i] + ": " + stokCount);
            }
        }
        tableLine(59);
        System.out.println("Pilih Obat : ");
        int pilihNamaObat = checkValidNamaObat(getUserInput(input, 0, namaObat.length - 1), pilihGudang);
        cleanDisplay();
        // headLine(" Gudang " + gudang[pilihGudang] + " ");
        tableHeader("DATA OBAT" + " GUDANG " + gudang[pilihGudang].toUpperCase(), 117);
        columnHeader(new Object[][] { { "ID", 8 }, { "NAMA OBAT", 35 }, { "STOK", 12 }, { "BATCH", 35 },
                { "EXPIRED", 12 }, { "STATUS", 10 } });
        for (int i = 0; i < stok.length; i++) {
            if ((int) stok[i][2] == pilihGudang && (int) stok[i][0] == pilihNamaObat) {
                boolean isExpired = checkExpiry(getExpObat(i));
                tableRow(new Object[][] { { Integer.toString(i), 8 }, { getNamaObat(i), 35 },
                        { Integer.toString(getStokObat(i)), 12 }, { getBatchObat(i), 35 }, { getExpObat(i), 12 },
                        { getStatusObat(i), 10 } });
                // System.out.println("(" + i + ") " + getNamaObat(i) + ": " + getStokObat(i) +
                // " Batch: "
                // + getBatchObat(i) + " Exp: " + getExpObat(i) + " Status: "
                // + getStatusObat(i));
            }
        }
        tableLine(119);
        System.out.print("Masukkan ID Obat : ");
        int pilihStok = checkValidInput(getUserInput(input, 0, stok.length - 1), pilihGudang, pilihNamaObat);

        if ((int) stok[pilihStok][2] == pilihGudang) {
            System.out.print("Masukkan Jumlah Ambil Stok Obat : ");
            int ambilStok = input.nextInt();
            boolean isExpired = checkExpiry(getExpObat(pilihStok));
            if (ambilStok <= getStokObat(pilihStok)) {
                cleanDisplay();
                headLine(" Ambil Obat ");
                System.out.println("ID Obat       : " + pilihStok);
                System.out.println("Nama Obat     : " + getNamaObat(pilihStok));
                System.out.println("Batch         : " + getBatchObat(pilihStok));
                System.out.println("Expired       : " + getExpObat(pilihStok));
                System.out.println("Status        : " + getStatusObat(pilihStok));
                System.out.println("Gudang        : " + gudang[(int) stok[pilihStok][2]]);
                System.out.println("Stok awal     : " + stok[pilihStok][1]);
                System.out.println("Stok akhir    : " + ((int) stok[pilihStok][1] - ambilStok));
                System.out.println("Jumlah Ambil  : " + ambilStok);
                if (confirmationPrompt("Apakah anda yakin ingin mengambil obat?")) {
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

    static void lihatObatExpiredPage() throws ParseException {
        for (int i = 0; i < gudang.length; i++) {
            System.out.println("\nData Obat Expired Gudang " + gudang[i] + ": ");
            boolean hasObat = false;
            for (int j = 0; j < namaObat.length; j++) {
                int stockCount = 0;
                boolean hasStok = false;
                for (int k = 0; k < stok.length; k++) {
                    if ((int) stok[k][2] == i && (int) stok[k][0] == j && checkExpiry(getExpObat(k)) == true) {
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

    static void searchPage() {
        Scanner input = new Scanner(System.in);
        cleanDisplay();
        System.out.print("Masukkan Obat yang ingin dicari : ");
        String cariObat = input.next();
        cleanDisplay();
        search(cariObat);
        exitPrompt();
    }

    static void allDatasPage() {
        cleanDisplay();
        headLine(" Data Seluruh Obat ");
        System.out.println();
        for (int i = 0; i < gudang.length; i++) {
            tableHeader("DATA OBAT" + " GUDANG " + gudang[i].toUpperCase(), 58);
            columnHeader(new Object[][] { { "NAMA OBAT", 40 }, { "JUMLAH STOK", 17 } });
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
                    tableRow(new Object[][] { { namaObat[j], 40 }, { Integer.toString(stockCount), 17 } });
                }
            }
            if (!hasObat) {
                tableRow(new Object[][] { { "- Gudang Kosong -", 58 } });
            }
            tableLine(60);
            System.out.println();
        }
        exitPrompt();
    }

    static void obatSettingPage() throws ParseException {
        Scanner input = new Scanner(System.in);
        cleanDisplay();
        System.out.println("1. Tambah Jenis Obat");
        System.out.println("2. Hapus Jenis Obat");
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
        boolean obatAda = false;
        System.out.print("Masukkan Nama Obat Baru : ");
        String obatBaru = input.next();
        for (int i = 0; i < namaObat.length; i++) {
            if (obatBaru.equalsIgnoreCase(namaObat[i])) {
                obatAda = true;
                System.out.println("Obat dengan jenis " + obatBaru + " telah ada!");
                break;
            }
        }
        if (!obatAda) {
            if (confirmationPrompt("Apakah anda yakin ingin membuat obat baru?")) {
                namaObat = addElement(namaObat, obatBaru);
                indexDatabase = 0;
                populateDatabase();
            }
        }
        exitPrompt();
    }

    static void deleteObatTypePage() {
        Scanner input = new Scanner(System.in);
        cleanDisplay();
        displayMedicine();
        System.out.print("Pilih Jenis Obat yang Akan Dihapus : ");
        int pilihMenu = (getUserInput(input, 1, namaObat.length) - 1);
        if (confirmationPrompt("Apakah anda yakin ingin menghapus jenis obat ini?")) {
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
        String batch = input.nextLine();

        // Validate the expiration date
        String expired = "";
        boolean validDate = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);

        while (!validDate) {
            System.out.println("Masukkan Expired Obat (DD-MM-YYYY) : ");
            expired = input.nextLine();

            try {
                Date expirationDate = dateFormat.parse(expired);

                // If parsing is successful, set validDate to true
                validDate = true;

            } catch (ParseException e) {
                System.out.println("Format tanggal tidak valid atau tanggal tidak valid. Gunakan format DD-MM-YYYY.");
            }
        }

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
            if (confirmationPrompt("Apakah anda yakin ingin menambahkan obat ini?")) {
                addStock(pilihPenambahanObat, jumlahObat, pilihPenambahanGudang, batch, expired);
                System.out.println("Obat berhasil ditambahkan ke gudang!");
            }
            exitPrompt();
        }
    }

    static void deleteObatInGudangPage() throws ParseException {
        Scanner input = new Scanner(System.in);
        cleanDisplay();
        displayWarehouse();
        System.out.print("Pilih Gudang : ");
        int pilihPenghapusanGudang = input.nextInt() - 1;
        cleanDisplay();
        tableHeader("DATA OBAT" + " GUDANG " + gudang[pilihPenghapusanGudang].toUpperCase(), 68);
        columnHeader(new Object[][] { { "ID", 9 }, { "NAMA OBAT", 40 }, { "JUMLAH STOK", 17 } });
        for (int i = 0; i < namaObat.length; i++) { // Repeat per Nama Obat
            int stokCount = 0;
            for (int j = 0; j < stok.length; j++) { // Repeat per Stock
                if ((int) stok[j][2] == pilihPenghapusanGudang && (int) stok[j][0] == i) {
                    stokCount += getStokObat(j);
                }
            }
            if (stokCount > 0) {
                tableRow(new Object[][] { { Integer.toString(i), 9 }, { namaObat[i], 40 },
                        { Integer.toString(stokCount), 17 } });
            }
        }
        tableLine(70);
        System.out.println("Pilih Obat : ");
        int pilihNamaObat = checkValidNamaObat(getUserInput(input, 0, namaObat.length - 1), pilihPenghapusanGudang);
        cleanDisplay();
        tableHeader("DATA OBAT" + " GUDANG " + gudang[pilihPenghapusanGudang].toUpperCase(), 114);
        columnHeader(new Object[][] { { "ID", 8 }, { "NAMA OBAT", 35 }, { "STOK", 12 }, { "BATCH", 30 },
                { "EXPIRED", 12 }, { "STATUS", 12 } });
        for (int i = 0; i < stok.length; i++) {
            if ((int) stok[i][2] == pilihPenghapusanGudang
                    && (int) stok[i][0] == pilihNamaObat) {
                tableRow(new Object[][] { { Integer.toString(i), 8 }, { getNamaObat(i), 35 },
                        { Integer.toString(getStokObat(i)), 12 }, { getBatchObat(i), 30 }, { getExpObat(i), 12 },
                        { getStatusObat(i), 12 } });
            }
        }
        tableLine(116);

        System.out.println("Masukkan ID Obat : ");
        int pilihStok = checkValidInput(getUserInput(input, 0, stok.length - 1), pilihPenghapusanGudang, pilihNamaObat);

        // Menghapus obat
        if (confirmationPrompt("Apakah anda yakin ingin menghapus obat ini?")) {
            deleteStock(pilihStok);
            System.out.println("Obat berhasil dihapus dari gudang!");
        }
        exitPrompt();
    }

    static void obatTransferPage() throws ParseException {
        Scanner input = new Scanner(System.in);
        cleanDisplay();
        displayWarehouse();
        System.out.print("Pilih Gudang : ");
        int pilihGudang = getUserInput(input, 1, gudang.length) - 1;
        cleanDisplay();
        tableHeader("DATA OBAT" + " GUDANG " + gudang[pilihGudang].toUpperCase(), 68);
        columnHeader(new Object[][] { { "ID", 9 }, { "NAMA OBAT", 40 }, { "JUMLAH STOK", 17 } });
        for (int i = 0; i < namaObat.length; i++) { // repeat per obat name
            int stokCount = 0;
            for (int j = 0; j < stok.length; j++) { // repeat per obat batch
                if ((int) stok[j][2] == pilihGudang && (int) stok[j][0] == i) { // if the gudang
                                                                                // is same then
                    stokCount += (int) stok[j][1];
                }
            }
            if (stokCount > 0) {
                tableRow(new Object[][] { { Integer.toString(i), 9 }, { namaObat[i], 40 },
                        { Integer.toString(stokCount), 17 } });
            }
        }
        tableLine(70);
        System.out.println("\nPilih Obat yang Akan ditransfer:");
        int pilihObatTransfer = checkValidNamaObat(getUserInput(input, 0, namaObat.length - 1), pilihGudang);
        cleanDisplay();
        tableHeader("DATA OBAT " + namaObat[pilihObatTransfer].toUpperCase() + " GUDANG "
                + gudang[pilihObatTransfer].toUpperCase(), 114);
        columnHeader(new Object[][] { { "ID", 8 }, { "NAMA OBAT", 35 }, { "STOK", 12 }, { "BATCH", 30 },
                { "EXPIRED", 12 }, { "STATUS", 12 } });
        boolean hasObat = false;
        for (int i = 0; i < stok.length; i++) {
            if ((int) stok[i][2] == pilihGudang && (int) stok[i][0] == pilihObatTransfer) {
                tableRow(new Object[][] { { Integer.toString(i), 8 }, { getNamaObat(i), 35 },
                        { Integer.toString(getStokObat(i)), 12 }, { getBatchObat(i), 30 }, { getExpObat(i), 12 },
                        { getStatusObat(i), 12 } });
                hasObat = true;
            }
        }
        if (!hasObat) {
            System.out.println("- Gudang Kosong -");
        }
        tableLine(116);

        System.out.print("\nPilih ID Obat : ");
        int pilihStok = checkValidInput(getUserInput(input, 0, stok.length - 1), pilihGudang, pilihObatTransfer);
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
        if (confirmationPrompt("Apakah data sudah benar?")) {
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

    static void userSettingPage() {
        Scanner input = new Scanner(System.in);
        cleanDisplay();
        headLine("SETTING USER");
        System.out.println("1. Lihat User");
        System.out.println("2. Tambah User");
        System.out.println("3. Hapus User");
        System.out.print("Pilih Menu : ");
        int pilihMenu = input.nextInt();
        switch (pilihMenu) {
            case 1: // Lihat User
                lihatUserPage();
                break;
            case 2: // Tambah User
                addUserPage();
                break;
            case 3: // Hapus User
                deleteUserPage();
                break;
            default:
                break;
        }
    }

    static void lihatUserPage() {
        cleanDisplay();
        showAllUsers();
        exitPrompt();
    }

    static void showAllUsers() {
        tableHeader("DATA USER", 44);
        columnHeader(new Object[][] { { "ID", 7 }, { "USERNAME", 20 }, { "ROLE", 15 } });
        for (int i = 0; i < loginDatabase.length; i++) {
            tableRow(new Object[][] { { Integer.toString(i), 7 }, { loginDatabase[i][0], 20 },
                    { loginDatabase[i][2], 15 } });
        }
        tableLine(46);
    }

    static void addUserPage() {
        Scanner input = new Scanner(System.in);
        cleanDisplay();
        headLine("TAMBAH USER");
        System.out.print("Nama User Baru : ");
        String userBaru = input.next();
        System.out.println("Masukkan Sandi User: ");
        String sandiUser = input.next();
        System.out.println("Pilih Role User: ");
        System.out.println("1. Admin");
        System.out.println("2. Manager");
        int pilihRole = getUserInput(input, 1, 2);
        String userRole = "";
        if (pilihRole == 1) {
            userRole = "Admin";
        } else if (pilihRole == 2) {
            userRole = "Manager";
        }
        if (confirmationPrompt("Apakah data sudah benar?")) {
            Object[] newUser = new Object[] { userBaru, sandiUser, userRole };
            loginDatabase = addElement(loginDatabase, newUser);
            System.out.println("User " + userBaru + " Berhasil ditambahkan");
        }
        exitPrompt();
    }

    static void deleteUserPage() {
        Scanner input = new Scanner(System.in);
        cleanDisplay();
        headLine("HAPUS USER");
        showAllUsers();
        System.out.print("Masukkan ID User yang Akan Dihapus : ");
        int pilihMenu = (getUserInput(input, 1, loginDatabase.length));
        if (confirmationPrompt("Apakah Anda yakin ingin menghapus user " + loginDatabase[pilihMenu][0] + " ?")) {
            int index = 0;
            loginDatabase = deleteElement(loginDatabase, pilihMenu);
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
        if (confirmationPrompt("Apakah data sudah benar?")) {
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
        if (confirmationPrompt("Apakah data sudah benar?")) {
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
        headLine("EXIT");
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
                loggedUser = -1;
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
        tableHeader("JENIS OBAT", 31);
        columnHeader(new Object[][] { { "NO", 7 }, { "NAMA OBAT", 23 } });
        for (int i = 0; i < namaObat.length; i++) {
            tableRow(new Object[][] { { Integer.toString(i + 1), 7 }, { namaObat[i], 23 } });
        }
        tableLine(33);
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
        while (loginAttempt <= 3 && !isLoggedIn) {
            headLine(" Login ");
            System.out.print("Masukkan Username : ");
            String user = input.nextLine();
            System.out.print("Masukkan Password : ");
            String pass = input.nextLine();
            for (int i = 0; i < loginDatabase.length; i++) {
                if (user.equalsIgnoreCase((String) loginDatabase[i][0]) && pass.equals((String) loginDatabase[i][1])) {
                    isLoggedIn = true;
                    loggedUser = i;
                }
            }
            if (loginAttempt == 3 && !isLoggedIn) {
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
        System.out.println("1. Kelola Obat");
        System.out.println("2. Cari Obat");
        System.out.println("3. Data Keseluruhan");
        System.out.println("4. Konfigurasi Obat");
        System.out.println("5. Konfigurasi Gudang");
        System.out.println("6. Keluar");
    }

    static void displayManagerMenu() {
        System.out.println("Menu:");
        System.out.println("1. Cari Obat");
        System.out.println("2. Data Keseluruhan");
        System.out.println("3. Konfigurasi Gudang");
        System.out.println("4. Konfigurasi User");
        System.out.println("5. Keluar");
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

    static int checkValidInput(int id, int gudang, int indexNamaObat) {
        Scanner input = new Scanner(System.in);
        while (!((int) stok[id][2] == gudang && (int) stok[id][0] == indexNamaObat)) {
            System.out.println("ID obat tidak valid!");
            System.out.println("Masukkan id obat: ");
            id = getUserInput(input, 0, stok.length - 1);
        }
        return id;
    }

    static int checkValidNamaObat(int index, int gudang) {
        Scanner input = new Scanner(System.in);
        boolean isValid = false;
        while (!isValid) {
            for (int i = 0; i < stok.length; i++) {
                if ((int) stok[i][0] == index && (int) stok[i][2] == gudang) {
                    isValid = true;
                    break;
                }
            }
            if (!isValid) {
                System.out.println("ID obat tidak valid!");
                System.out.println("Masukkan id obat: ");
                index = input.nextInt();
            }
        }
        return index;
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

    public static boolean confirmationPrompt(String sentence) {
        Scanner input = new Scanner(System.in);
        System.out.println(sentence + " (Y/N)");
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
            System.out.println("Tidak ada obat yang sesuai!");
            System.out.println("Mungkin yang anda maksud adalah : " + namaObat[matchedQuery] + " (Y/N)");
            Scanner scanner = new Scanner(System.in);
            char c = scanner.next().charAt(0);
            cleanDisplay();
            if (c == 'Y' || c == 'y') {
                tableHeader(" Hasil Pencarian ", 134);
                columnHeader(new Object[][] { { "ID", 13 }, { "NAMA OBAT", 40 }, { "JUMLAH STOK", 17 },
                    { "TANGGAL EXPIRED", 19 }, { "BATCH", 25 }, {"GUDANG", 15}});
                for (int i = 0; i < stok.length; i++) { // repeat per stok
                    if ((int) stok[i][0] == matchedQuery) {
                    tableRow(new Object[][] { { Integer.toString(i), 13 },{namaObat[(int) stok[i][0]], 40}, {Integer.toString(getStokObat(i)), 17},{getExpObat(i), 19}, {getBatchObat(i), 25} , {gudang[(int) stok[i][2]], 15}});   
                    }
                }
                tableLine(136);
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

    static void tableHeader(String title, int width) {
        int length = title.length();
        int sisiHeadline = (width - length) / 2;

        String leftSide = " ".repeat(sisiHeadline);
        String rightSide = " ".repeat(width - length - sisiHeadline);

        System.out.println("-".repeat(width + 2));
        System.out.println("|" + leftSide + title + rightSide + "|");
        System.out.println("-".repeat(width + 2));
    }

    static void columnHeader(Object[][]... columns) {
        int totalWidth = 0;
        System.out.print("|");
        for (int i = 0; i < columns[0].length; i++) {
            int width = (int) columns[0][i][1];
            int length = ((String) columns[0][i][0]).length();
            int sidePadding = (width - length) / 2;
            totalWidth += width;
            String leftSide = " ".repeat(sidePadding);
            String rightSide = " ".repeat(width - length - sidePadding);

            System.out.print(leftSide + (String) columns[0][i][0] + rightSide + "|");
        }
        System.out.println("\n" + "-".repeat(totalWidth + columns[0].length + 1));
    }

    static void tableRow(Object[]... columns) {
        int totalWidth = 0;
        System.out.print("|");
        for (int i = 0; i < columns.length; i++) {
            int width = (int) columns[i][1];
            int length = ((String) columns[i][0]).length();
            int sidePadding = (width - length) / 2;
            totalWidth += width;
            String leftSide = " ".repeat(sidePadding);
            String rightSide = " ".repeat(width - length - sidePadding);

            System.out.print(leftSide + (String) columns[i][0] + rightSide + "|");
        }
        System.out.println("");
    }

    static void tableLine(int width) {
        System.out.println("-".repeat(width));
    }

    static void deleteObatHabis() {
        for (int i = stok.length - 1; i >= 0; i--) {
            if ((int) stok[i][1] <= 0) {
                stok = deleteElement(stok, i);
            }
        }
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
