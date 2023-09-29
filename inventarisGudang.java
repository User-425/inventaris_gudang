import java.util.Scanner;

/**
 * inventarisGudang
 */
public class inventarisGudang {
  
    public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int obat1 = 100, obat2 = 100;
    int jml_tambah1, jml_tambah2,jml_ambil1, jml_ambil2;
    char pilihMenu;
    
    System.out.println("1.Lihat Stok\n2.Tambah Stok\n3.Ambil Stok");
    pilihMenu = input.next().charAt(0);
    
    switch (pilihMenu) {
    case '1':
    // Lihat stok
    System.out.println("Jumlah stok obat1: " + obat1);
    System.out.println("Jumlah stok obat2: " + obat2); 
    break;

    case '2':        
    // Tambah stok
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

    case '3':
    // Ambil stok
    System.out.println("Ambil stok obat1 : ");
    jml_ambil1 = input.nextInt();
    obat1 -= jml_ambil1;

    System.out.println("Ambil stok obat2  : ");
    jml_ambil2 = input.nextInt();
    obat2 -= jml_ambil2;

    System.out.println("Stok obat1 setelah diambil: " + obat1);
    System.out.println("Stok obat2 setelah diambil: "+ obat2);
    break;
        default:
         System.out.println("Error");
            break;
    }
    }
}