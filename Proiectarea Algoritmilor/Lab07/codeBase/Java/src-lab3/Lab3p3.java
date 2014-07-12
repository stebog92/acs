import java.util.Scanner;


public class Lab3p3 
{
  public static void main(String[] args)
  {
    /* Declaram si citim lungimea unei linii si un vector de cuvinte. */
    Scanner inputScanner = new Scanner(System.in);
    int l = inputScanner.nextInt();
    String line = inputScanner.nextLine();
    while(line.split(" ").length == 0)
      line = inputScanner.nextLine();
    String[] words = inputScanner.nextLine().split(" ");

    /* Verificam sa nu avem cuvinte mai lungi de o linie. */
    for (int i = 0; i < words.length; ++i) {
      if (words[i].length() > l) {
        System.out.println("Imposibil de rezolvat problema. Cuvantul " 
                           + words[i] + " este mai lung decat o linie.");
        return;
      }
    }

    /* Afisam impartirea greedy. Se vor afisa L caractere pe o linie, urmate de un
     * caracter pipe ('|') la final. */
    greedyFormat(l, words);

    System.out.println();

    /* Afisam impartirea folosind functia de cost din TeX. Se vor afisa L
     *caractere pe o linie, urmate de un caracter pipe ('|') la final.*/
    texFormat(l, words);

  }
  static void texFormat(int l, String[] v)
  {
    System.out.println("Tex format:");

    /* Daca ne dorim sa minimizam costul, nu mai putem folosi un algoritm in O(N)
     * deoarece suma de patrate perfecte nu este o functie optimizabila Greedy.
     *
     * In schimb ne vom folosi de o recurenta PD. Sa pornim cu urmatoarea
     * constatare: daca am considera ca word[i] este primul cuvant de pe linia sa,
     * pentru un "i" oarecare, costul minim care se poate obtine pentru textul
     * format din cuvintele:
     *      word[i] word[i+1] ... word[n-1]
     * este:
     * cost_optim[i] = MIN{ line_padding(word[i] word[i+1] ... word[k]) +
     *                      cost_optim[k + 1]
     *                 }, unde i <= k < n
     *
     * Cu alte cuvinte, putem sa incercam sa plasam inca 0, 1, 2, 3, ... etc.
     * cuvinte pe linie cu word[i] si sa adunam costul respectivei linii cu costul
     * optim al formatarii textului care incepe de la cuvantul imediat urmator.
     *
     * Cum word[0] va fi mereu primul cuvant de pe linia sa si textul incepe cu
     * word[0], solutia problemei va fi data cost_optim[0]. Completarea vectorului
     * se face de la sfarsit spre inceput.
     *
     * Pentru a reconstitui impartirea textului, vom pastra inca un vector
     * line_break[i] = indexul cuvantului unde incepe o linie noua in formatarea
     *                 optima, daca word[i] este primul cuvant de pe linia sa.
     */

    int[] cost_optim = new int[v.length + 1];
    int[] line_break = new int[v.length + 1];
    for(int i = 0 ; i < v.length + 1; i++) 
      cost_optim[i] = line_break[i] = 0;

    /* Dupa ultimul cuvant nu mai poate urma nimic. */
    cost_optim[v.length - 1] = (l - v[v.length - 1].length()) *
        (l - v[v.length - 1].length());
    line_break[v.length - 1] = v.length;

    /* Completam de la sfarsit spre inceput. */
    for (int i = v.length - 2; i >= 0; --i){
      /* Pornim de la prezumtia ca lasam doar word[i] pe linia sa. */
      int j = i + 1;
      int line_fill = v[i].length();
      cost_optim[i] = (l - line_fill) * (l - line_fill) + cost_optim[j];
      line_break[i] = j;

      /* Dar incercam si variantele in care adaugam cu el pe linie pe (i + 1),
       * (i + 2), ... etc. pana se umple linia. */
      for (j = i + 2;
           j < v.length + 1 && line_fill + 1 + v[j - 1].length() <= l;
           ++j) {
        line_fill += 1 + v[j - 1].length();
        if (cost_optim[i] > (l - line_fill) * (l - line_fill) + cost_optim[j]) {
          cost_optim[i] = (l - line_fill) * (l - line_fill) + cost_optim[j];
          line_break[i] = j;
        }
      }
    }

    /* Printam textul formatat, din line break in line break. */
    for (int i = 0; i != v.length; i = line_break[i]) {
      int line_fill = 0;
      /* Afisam cuvintele de pe linie si calculam lungimea totala. */
      for (int j = i; j < line_break[i] && j < v.length; ++j) {
        System.out.print((j == i ? "" : " ") + v[j]);
        line_fill += (j == i ? 0 : 1) + v[j].length();
      }
      /* Si inchidem linia cu "|". */
      fillWithSpaces(l - line_fill);
      System.out.println("|");
    }

    /* Afisam costul total. */
    System.out.println("TOTAL COST: " + cost_optim[0]);

  }

  static void greedyFormat(int l, String[] v)
  {
    System.out.println("Greedy format:");
    int cost = 0;
    int line_fill = 0;

    /* Parcurgem textul si afisam cuvintele linie pe linie. */
    for (int i = 0; i < v.length; ++i) {
      if (l - line_fill >= v[i].length() + (line_fill == 0 ? 0 : 1)) {
        /* Mai avem loc pe linie, afisam cuvantul si eventual si un spatiu. */
        System.out.print((line_fill == 0 ? "" : " ") + v[i]);
        line_fill += v[i].length() + (line_fill == 0 ? 0 : 1);
      } else {
        /* Nu mai avem loc pe linie, trebuie sa punem | si sa trecem la linia
         * urmatoare. */
        fillWithSpaces(l - line_fill);
        System.out.println("|");
        System.out.print(v[i]);
        cost += (l - line_fill) * (l - line_fill);
        line_fill = v[i].length();
      }
    }

    /* Inchidem si ultima linie si actualizam costul. */
    cost += (l - line_fill) * (l - line_fill);
    fillWithSpaces(l - line_fill);
    System.out.println("|");
    System.out.println("TOTAL COST: " + cost);
  }

  static void fillWithSpaces(int spaceCount)
  {
    while(spaceCount > 0)
    {
      System.out.print(" ");
      spaceCount--;
    }
  }
}
