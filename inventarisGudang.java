import java.util.Scanner;

/**
 * inventarisGudang
 */
public class inventarisGudang {
    public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int obat1 = 100, obat2 = 100;
    int jml_ambil1, jml_ambil2;

    // Lihat stok


    // Tambah stok


    // Ambil stok

    System.out.println("Ambil stok obat1 : ");
    jml_ambil1 = input.nextInt();
    obat1 -= jml_ambil1;

    System.out.println("Ambil stok obat2  : ");
    jml_ambil2 = input.nextInt();
    obat2 -= jml_ambil2;

    System.out.println("Stok obat1 setelah diambil: " + obat1);
    System.out.println("Stok obat2 setelah diambil: "+ obat2);
    }
}