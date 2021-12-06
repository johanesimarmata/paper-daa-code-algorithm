/*
     Reverse Colussi Algorithm
     Referensi:
     - https://github.com/uzuki-P/find-this-song/blob/68dfa27960bb8c3e1e390a50aa9ba3dfb51509a5/app/src/main/java/skrip/si/findthissong/algorithm/ReverseColussiAlgorithm.java
     - https://www-igm.univ-mlv.fr/~lecroq/string/node17.html
*/

import java.util.ArrayList;

public class ReverseColussi {
     private final static int ASIZE = 256;
     private int XSIZE;
    
     private char[] x;
     private char[] y;
     private int n;
     private int m;
     private int[][] rcBc;
     private int[] rcGs;
     private int[] h;
     private int[] locc;
     private int[] link;
     private int[] hmin;
     private int[] kmin;
     private int[] rmin;

     private ArrayList<Integer> resultIndex = new ArrayList<>();

     public boolean search(String pattern, String text) {
          x = pattern.toCharArray();
          y = text.toCharArray();
          m = pattern.length();
          n = text.length();

          XSIZE = m+1;

          rcBc = new int[ASIZE][XSIZE];
          rcGs = new int[ASIZE];
          h    = new int[ASIZE];
          locc = new int[ASIZE];
          link = new int[XSIZE];
          hmin = new int[XSIZE];
          kmin = new int[XSIZE];
          rmin = new int[XSIZE];
          preRC();
          return searchingProcess();
     }

     public ArrayList<Integer> indexes() {
          return this.resultIndex;
     }

     private boolean searchingProcess() {
          boolean result = false;
          /* Searching */
          int i = -1;
          int j = 0;
          int s = m;
          while (j <= n - m) {
                    while (j <= n - m && x[m - 1] != y[j + m - 1]) {
                         s = rcBc[y[j + m - 1]][s];
                         j += s;
                    }

                    for (i = 1; i < m && x[h[i]] == y[j + h[i]]; ++i);


               if (i >= m) {
                    this.resultIndex.add(j);
                    result = true;
               }
               s = rcGs[i];
               j += s;
          }

//        remove false positive result
          if (!resultIndex.isEmpty() && resultIndex.get(resultIndex.size() - 1 ) > n - m) {
               resultIndex.remove(resultIndex.size() - 1);
               if (resultIndex.isEmpty()){
                    result = false;
               }
          }

          return result;
     }

     private void preRC(){
          /* Computation of link and locc */
          for (int a = 0; a < ASIZE; ++a) {
               locc[a] = -1;
          }
          link[0] = -1;
          for (int i = 0; i < m - 1; ++i) {
               link[i + 1] = locc[x[i]];
               locc[x[i]] = i;
          }
          /* Computation of rcBc */
          int i, j;
          for (int a = 0; a < ASIZE; ++a) {
               for (int s = 1; s <= m; ++s) {
                    i = locc[a];
                    j = link[m - s];
                    while (i - j != s && j >= 0) {
                         if (i - j > s) {
                         i = link[i + 1];
                         } else {
                         j = link[j + 1];
                         }
                    }
                    while (i - j > s) {
                         i = link[i + 1];
                    }
                    rcBc[a][s] = m - i - 1;
               }
          }

          /* Computation of hmin */
          int k = 1;
          int q;
          i = m - 1;
          while (k <= m) {
               while (i - k >= 0 && x[i - k] == x[i]) {
                    --i;
               }
               hmin[k] = i;
               q = k + 1;
               while (hmin[q - k] - (q - k) > i) {
                    hmin[q] = hmin[q - k];
                    ++q;
               }
               i += (q - k);
               k = q;
               if (i == m) {
                    i = m - 1;
               }
          }

          /* Computation of kmin */
          for (int a = 0; a < XSIZE; ++a) {
               kmin[a] = 0;
          }
          for (k = m; k > 0; --k) {
               kmin[hmin[k]] = k;
          }

          /* Computation of rmin */
          int r = 0;
          for (i = m - 1; i >= 0; --i) {
               if (hmin[i + 1] == i) {
                    r = i + 1;
               }
               rmin[i] = r;
          }

          /* Computation of rcGs */
          i = 1;
          for (k = 1; k <= m; ++k) {
               if (hmin[k] != m - 1 && kmin[hmin[k]] == k) {
                    h[i] = hmin[k];
                    rcGs[i++] = k;
               }
          }
          i = m-1;
          for (j = m - 2; j >= 0; --j) {
               if (kmin[j] == 0) {
                    h[i] = j;
                    rcGs[i--] = rmin[j];
               }
          }
          rcGs[m] = rmin[0];
     }

     public static void main(String[] args) {
          String text = "Teknologi informasi dan pertumbuhan telekomunikasi saat ini sangat berkembang, sehingga penggunaan dan pemanfaatan teknologi informasi dan teknologi jaringan merupakan suatu indikator pertumbuhan kemajuan teknologi dan pertumbuhan disegala bidang, khususnya dunia pendidikan. Perkembangan teknologi informasi dan telekomunikasi menuntut cara kerja yang cepat dan tepat untuk memberikan informasi yang serba akurat. Salah satu bentuk perkembangan teknologi adalah jaringan internet yaitu suatu bentuk penyampaian informasi teknologi dalam dunia maya yang didukung oleh infrastruktur teknologi seperti hardware, software dan brainware. Dengan jaringan internet kita dapat menerima atau mengakses informasi dalam berbagai format. Komputer merupakan media teknologi informasi yang dapat menyajikan informasi yang diperlukan, juga dapat membantu mengurangi kesalahan atau kekeliruan yang sering terjadi pada sistem manual, sehingga hasil yang diperoleh dapat dimanfaatkan dengan tepat dan cepat. Oleh karena itu penggunaan teknologi 341 komputer dan perangkat lunak (software) yang baik akan mengurangi kesalahan dalam penyajian data yang diperlukan. Katalog merupakan daftar koleksi sebuah pusat dokumentasi atau beberapa pusat dokumentasi yang disusun menurut sistem tertentu.[1] Daftar tersebut dapat berbentuk kartu, lembaran, buku atau bentuk lain, yang memuat informasi mengenai pustaka atau kepustakaan yang terdapat di perpustakaan atau unit informasi[2]. Sesuai dengan perkembangan jaman bentuk-bentuk sumber informasi itu tidak hanya berupa barang cetak semata, seperti buku, Koran, surat kabar dan sejenisnya. Jenis-jenis sekarang sudah banyak yang di ahli mediakan, seperti CD, Flash Disk, Film dan sejenisnya. Mengingat beragamnya dari sumber informasi akan mempengaruhi kecepatan dan ketepatan penggunannya untuk menemukan kembali sumber informasi yang dibutuhkannya. Horspool merupakan penyederhanaan dari Boyer-Moore. Perbedaan antara keduanya adalah pada metode penggeseren patternnya. Jika Boyer-Moore menggunakan dua metode praproses bad character shift dan good shufix shift, akan tetapi Horspool hanya menggunakan satu metode praproses yaitu bad character shift. Kompleksitas rata-rata algoritma ini sama dengan Boyer-Moore O(n), seangkan untuk metode praproses nya adalah O(m+σ)";
          String[] testCasePattern = new String[]{"penyederhanaan", "perkembangan teknologi", "metode praproses nya", "perangkat lunak (software)", "serba akurat", "shufix", "manual", "Mengingat", "Komputer merupakan", "pada sistem manual" }; 
          System.out.println("PERCOBAAN MENGGUNAKAN ALGORITMA REVERSE COLUSSI \n");
          for(String pattern : testCasePattern){
               ReverseColussi reverseColussi = new ReverseColussi();
               long startTime = System.nanoTime();
               reverseColussi.search(pattern, text);
               System.out.println("POsisi yang muncul dalam teks string pola adalah :" + reverseColussi.indexes().get(0));
               long endTime   = System.nanoTime();
               System.out.print("Untuk pattern "+ "\'" + pattern + "\'" + " running time nya adalah :");
               System.out.println(endTime - startTime + " nano second \n");
          }
     }
}
