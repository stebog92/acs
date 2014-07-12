import java.util.Scanner;

public class Lab3p2 
{
  public static void main(String[] args)
  {
    /* Declaram si citim un vector de matrice de la consola. */
    Matrice[] v = VectorUtil.readArrayOfReadables(new Matrice[0], Matrice.class);

    /* Verificam intai ca lantul de matrice chiar se poate inmulti. */
    for (int i = 0; i < v.length - 1; ++i) 
    {
      if (v[i].nrColoane != v[i + 1].nrLinii) 
      {
        System.out.println("Nu se pot inmulti matricele " + i + " si " + (i + 1) + 
                           " de dimensiuni: " + v[i] + ", respectiv " + v[i + 1]);
        System.exit(0);
      }
    }

    /* Afisam numarul minim de operatii. */
    System.out.println("Numarul minim de operatii de inmultire elementare este: " +
                       minMultiplications(v));

    return;
  }

  private static int minMultiplications(Matrice[] v) 
  {
    /* Vom folosi aceeasi abordare bottom-up ca la problema 1. Notam:
     * minm[i][j] = numarul de minim operatii pentru a inmulti subsecventa de
     *              matrice (v[i], v[i+1], ... v[j]).
     *
     * Deoarece inmultirea matricelor este asociativa, practic operatiile se pot
     * efectua in orice ordine, rezultatul final fiind o matrice de dimensiuni:
     *              (#linii(v[i]), #coloane(v[j]))
     *
     * Pentru a calcula valorile minm[i][j], ne vom folosi de urmatoarea
     * recurenta:
     *
     * minm[i][j] = MIN{
     *                minm[i][k] +
     *                minm[k + 1][j] +
     *                #linii(v[k]) * #coloane(v[k]) * #coloane(v[k + 1])
     *              }, pentru toti i <= k < j.
     *
     * Cu alte cuvinte daca avem lantul de matrice:
     *              (m1 m2 m3 m4 m5 m6, ... mn)
     * vom incerca toate modalitatile sa il spargem in doua lanturi mai scurte si
     * apoi sa efectua produsul celor doua rezultate partiale:
     *              (m1 m2 ... mk) (mk+1 ... mn)
     *
     * Pentru tiparirea rezultatului, vom folosi si o matrice auxiliara
     * last[i][j] in care sa retinem unde se face ultima inmultire din lantul
     * (mi mi+1 ... mj).
     */
    int[][] minm = new int[v.length][v.length];
    int[][] last = new int[v.length][v.length];
    for(int i = 0; i < v.length; i++)
      for(int j = 0; j < v.length; j++)
        minm[i][j] = last[i][j] = 0;

    for (int len = 2; len <= v.length; ++len) {
      for (int i = 0; i <= v.length - len; ++i) {
        int j = i + len - 1;

        /* Presupunem initial impartirea (mi) (mi+1 ... mj) */
        minm[i][j] = minm[i + 1][j] + v[i].nrLinii * v[i].nrColoane * v[j].nrColoane;
        last[i][j] = i;

        for(int k = i; k < j; ++k) {
          /* Daca impartirea (mi ... mk) (mk+1 ... mj) da rezultate mai bune,
           * atunci alegem aceasta impartire. */
          int alternative = minm[i][k] + minm[k + 1][j] +
              v[i].nrLinii * v[k].nrColoane * v[j].nrColoane;
          if (alternative < minm[i][j]) {
            minm[i][j] = alternative;
            last[i][j] = k;
          }
        }
      }
    }

    /* Tiparim efectiv lantul parantezat. */
    printParantheses(last, v.length, 0, v.length - 1);
    System.out.println();

    /* Rezultatul final este minm pentru tot lantul. */
    return minm[0][v.length - 1];
  }

  static void printParantheses(int[][] last, int n, int begin, int end)
  {
    if(begin == end)
    {
      System.out.print("m" + begin);
    }
    else
    {
      System.out.print("(");
      printParantheses(last, n, begin, last[begin][end]);
      System.out.print(" x ");
      printParantheses(last, n, last[begin][end] + 1, end);
      System.out.print(")");
    }
  }
}

class Matrice implements Readable
{
  int nrLinii;
  int nrColoane;

  public void read(Scanner scanner)
  {
    nrLinii = scanner.nextInt();
    nrColoane = scanner.nextInt();
  }

  public String toString()
  {
    return "[" + nrLinii + " x " + nrColoane + "]";
  }
}
