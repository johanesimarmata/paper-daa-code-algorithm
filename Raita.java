/*
     Raita Algorithm:
     - https://stackjava.com/algorithm/thuat-toan-tim-kiem-raita.html
     - https://www-igm.univ-mlv.fr/~lecroq/string/node22.html#:~:text=Raita%20designed%20an%20algorithm%20which,the%20middle%20character%20of%20the
*/
public class Raita {
     public static boolean cmp(char[] x, int x1, int x2, char[] y, int y1) {
       for (int i = x1; i <= x2; i++) {
         if (x[i] != y[y1++]) {
           return false;
         }
       }
       return true;
     }
     public static int[] preBc(char[] x) {
       int[] bc = new int[255];
       int m = x.length;
       for (int i = 0; i < 255; i++) {
         bc[i] = m;
       }
       for (int i = 0; i < m - 1; i++) {
         bc[(int) x[i]] = m - i - 1;
       }
       return bc;
     }
     public static void searchWithRaita(char[] x, char[] y) {
       long startTime = System.nanoTime();
       int m = x.length;
       int n = y.length;
       int[] preBc = preBc(x);
       int i = 0;
       while (i <= n - m) {
         char c = y[i + m - 1];
         char first = x[0];
         char middle = x[m / 2];
         char last = x[m - 1];
         if (first == y[i] && middle == y[i + m / 2] && last == y[i + m - 1] && cmp(x, 1, m - 2, y, i + 1)) {
           System.out.println("Posisi yang muncul dalam teks string pola adalah: " + i);
           long endTime   = System.nanoTime();
           System.out.print("Untuk pattern "+ "\'" + String.valueOf(x) + "\'" + " running time nya adalah :");
           System.out.println(endTime - startTime + " nano second \n");
         }
         i = i + preBc[c];
       }
     }
     public static void main(String[] args) {
       char[] y = "Teknologi informasi dan pertumbuhan telekomunikasi saat ini sangat berkembang, sehingga penggunaan dan pemanfaatan teknologi informasi dan teknologi jaringan merupakan suatu indikator pertumbuhan kemajuan teknologi dan pertumbuhan disegala bidang, khususnya dunia pendidikan. Perkembangan teknologi informasi dan telekomunikasi menuntut cara kerja yang cepat dan tepat untuk memberikan informasi yang serba akurat. Salah satu bentuk perkembangan teknologi adalah jaringan internet yaitu suatu bentuk penyampaian informasi teknologi dalam dunia maya yang didukung oleh infrastruktur teknologi seperti hardware, software dan brainware. Dengan jaringan internet kita dapat menerima atau mengakses informasi dalam berbagai format. Komputer merupakan media teknologi informasi yang dapat menyajikan informasi yang diperlukan, juga dapat membantu mengurangi kesalahan atau kekeliruan yang sering terjadi pada sistem manual, sehingga hasil yang diperoleh dapat dimanfaatkan dengan tepat dan cepat. Oleh karena itu penggunaan teknologi 341 komputer dan perangkat lunak (software) yang baik akan mengurangi kesalahan dalam penyajian data yang diperlukan. Katalog merupakan daftar koleksi sebuah pusat dokumentasi atau beberapa pusat dokumentasi yang disusun menurut sistem tertentu.[1] Daftar tersebut dapat berbentuk kartu, lembaran, buku atau bentuk lain, yang memuat informasi mengenai pustaka atau kepustakaan yang terdapat di perpustakaan atau unit informasi[2]. Sesuai dengan perkembangan jaman bentuk-bentuk sumber informasi itu tidak hanya berupa barang cetak semata, seperti buku, Koran, surat kabar dan sejenisnya. Jenis-jenis sekarang sudah banyak yang di ahli mediakan, seperti CD, Flash Disk, Film dan sejenisnya. Mengingat beragamnya dari sumber informasi akan mempengaruhi kecepatan dan ketepatan penggunannya untuk menemukan kembali sumber informasi yang dibutuhkannya. Horspool merupakan penyederhanaan dari Boyer-Moore. Perbedaan antara keduanya adalah pada metode penggeseren patternnya. Jika Boyer-Moore menggunakan dua metode praproses bad character shift dan good shufix shift, akan tetapi Horspool hanya menggunakan satu metode praproses yaitu bad character shift. Kompleksitas rata-rata algoritma ini sama dengan Boyer-Moore O(n), seangkan untuk metode praproses nya adalah O(m+σ)".toCharArray();
       String[] testCasePattern = new String[]{"penyederhanaan", "perkembangan teknologi", "metode praproses nya", "perangkat lunak (software)", "serba akurat", "shufix", "manual", "Mengingat", "Komputer merupakan", "pada sistem manual" }; 
       System.out.println("PERCOBAAN MENGGUNAKAN ALGORITMA RAITA \n");
       for(String pattern : testCasePattern){
          char[] x = pattern.toCharArray();
          searchWithRaita(x, y);
       }
       
     }
   }