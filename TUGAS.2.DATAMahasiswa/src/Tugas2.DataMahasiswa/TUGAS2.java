package TRIWAHYU;
// MUH.TRI WAHYU MARJIN
// 22116012

import java.util.ArrayList;
import java.util.Scanner;

public class TUGAS2 {
    public static void main(String[] args) {

        ArrayList<String> NAMA = new ArrayList<>();
        ArrayList<String> NIM = new ArrayList<>();
        ArrayList<String> ALAMAT = new ArrayList<>();
        Scanner input= new Scanner(System.in);

        int PILIHAN;

        do {
            System.out.println(" PROGRAM  DATA MAHASISWA ");
            System.out.println(" -------------------------");
            System.out.println(" 1. Masukan Data");
            System.out.println(" 2. Tampilkan Data");
            System.out.println(" 3. Cari Data");
            System.out.println(" 4. Hapus Data ");
            System.out.println(" 5. Keluar");
            System.out.println();
            System.out.print(" Pilih Menu :");
            PILIHAN = input.nextInt();

            if (PILIHAN==1){
                System.out.print(" 1. Masukan Nama :");
                String nm= input.next();
                NAMA.add(nm);

                System.out.print(" 2. Masukan Nim :");
                String nim= input.next();
                NIM.add(nim);

                System.out.print(" 3. Masukan Alamat :");
                String alt= input.next();
                ALAMAT.add(alt);

            } else if (PILIHAN==2) {
                System.out.println(" program Data Mahasiswa");
                System.out.println(" -------------------------");

                for (int i = 0; i < NAMA.size(); i++) {
                    System.out.println(i+1+"."+ NAMA.get(i));
                }
            } else if (PILIHAN==3) {
                System.out.print(" Data Keberapa yang akan di cari:");
                int CARI= input.nextInt();

                if (CARI==1){
                    System.out.println("1. Nama:"+NAMA.get(0));
                    System.out.println("2.. Nim:"+NIM.get(0));
                    System.out.println("3. Alamat:"+ALAMAT.get(0));
                }

                if (CARI==2){
                    System.out.println("1. Nama:"+NAMA.get(1));
                    System.out.println("2.. Nim:"+NIM.get(1));
                    System.out.println("3. Alamat:"+ALAMAT.get(1));
                }
            } else if (PILIHAN==4) {
                System.out.println(" Data Mahasiswa");
                for (int i = 0; i < NAMA.size(); i++) {
                    System.out.println(i+1+"."+ NAMA.get(i));
                }
                System.out.print(" Masukan Nama yang akan di hapus:");
                String nm= input.next();
                NAMA.remove(nm);

            } else if (PILIHAN==5) {
                System.out.println(">>>Terima Kasih sudah Mencoba Menu Ini<<<");
            }
            else {
                System.err.println(" Menu tidak ada/inputan error");
            }
        }while (PILIHAN !=5);

    }
}
