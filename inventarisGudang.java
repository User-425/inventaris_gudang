import java.util.Scanner;

/**
 * inventarisGudang
 */
public class inventarisGudang {
  
    public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int obat1 = 100, obat2 = 100;
    int jml_tambah1, jml_tambah2;
    char pemilihan;
    
    System.out.println("1.Lihat Stok\n2.Tambah Stok\n3.Ambil Stok");
    pemilihan = input.next().charAt(0);
    switch (pemilihan) {
    // Lihat stok


   
    // Tambah stok
    case '2':
    //obat1
    System.out.println("Tambah Stok Obat 1 : ");
    jml_tambah1 = input.nextInt();
    obat1 += jml_tambah1;
    
        //obat2
    System.out.println("Tambah Stok Obat 2 : ");
    jml_tambah2 = input.nextInt();
    obat2 += jml_tambah2;

    //Stok
    System.out.println("Jumlah Stok Akhir Obat 1 " + obat1);
    System.out.println("Jumlah Stok Akhir Obat 2 " + obat2);
    break;

    

    // Ambil stok
    default:
    System.out.println("Error");
    break;
    }
    }
}