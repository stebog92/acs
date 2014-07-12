import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

public class Lab4p1 
{
  /* Numarul de intrari in recursivitate (pentru evaluarea performantelor). */
  static int recursions = 0;

  /* Cuvintele disponibile in dictionar (ca vector de stringuri). */
  static Vector<String> vocabular = new Vector<String>();

  /* Obiectul Rebus in sine. Are doar cateva metode ajutatoare gata scrise,
   * verificati documentatia pentru detalii. */
  static Rebus rebus = new Rebus();

  public static void readDictionar()
  {
    File file = new File("Vocabular.txt");
    Scanner scanner = null;
    try 
    {
      scanner = new Scanner(file);
    }
    catch (FileNotFoundException e) 
    {
      e.printStackTrace();
    }

    while(scanner.hasNextLine())
    {
      vocabular.add(scanner.nextLine());
    }
    scanner.close();
  }

  public static void main(String[] args) throws FileNotFoundException 
  {
    /* citim rebusul */
    rebus.read(new Scanner(new File("Puzzle.rebus")));

    /* citim dictionarul */
    readDictionar();

    /* Inregistram timpul de inceput */
    long timeStartNs = System.nanoTime();

    Domains horizontal = new Domains(rebus.rows, rebus.columns);
    Domains vertical = new Domains(rebus.rows, rebus.columns);
    buildWordSets(horizontal, vertical);

    Vector<String> prefixes = new Vector<String>();
    for(int i = 0; i < rebus.columns; i++)
      prefixes.add("");
    backtracking(0, 0, horizontal, vertical, prefixes);

    /* Inregistram timpul de sfarsit */
    long timeEndNs = System.nanoTime();

    /* Afisam numarul de apeluri recursive si diferenta de timp. */
    long timeDiff = timeEndNs - timeStartNs;
    System.out.println("Total recursive calls: " + recursions);
    System.out.println("Total time spent: " + timeDiff);
  }

  /* Functie care umple cele doua tabele de restrictii, orizontala sau verticala
   * cu cuvinte care ar putea, ipotetic, sa fie completate in Rebus. */
  static void buildWordSets(Domains horizontal, Domains vertical)
  {
    /* Si completam. */
    for (int i = 0; i < rebus.rows; ++i) {
      for (int j = 0; j < rebus.columns; ++j) {
        if (rebus.is_empty(i, j) &&
            (j == 0 || rebus.is_empty(i, j - 1) == false)) {
          /* Completam orizontal. */
          int span = 0;
          while (j + span < rebus.columns && rebus.is_empty(i, j + span)) {
            ++span;
          }

          /* Adaugam toate cuvintele de span litere aici. */
          for (int index = 0; index < vocabular.size(); ++index) {
            if (vocabular.elementAt(index).length() == span) {
              horizontal.get(new Position(i, j)).add(vocabular.elementAt(index));
            }
          }
        }
        if (rebus.is_empty(i, j) &&
            (i == 0 || rebus.is_empty(i - 1, j) == false)) {
          /* Completam vertical. */
          int span = 0;
          while (i + span < rebus.rows && rebus.is_empty(i + span, j)) {
            ++span;
          }

          /* Adaugam toate cuvintele de span litere aici. */
          for (int index = 0; index < vocabular.size(); ++index) {
            if (vocabular.elementAt(index).length() == span) {
              vertical.get(new Position(i, j)).add(vocabular.elementAt(index));
            }
          }
        }
      }
    }
  }

  static boolean found_solution = false;
  static void backtracking(
      int row,
      int col,
      Domains horizontal,
      Domains vertical,
      Vector<String> verticalPrefixes)
  {

    /* Marcam faptul ca am mai efectuat o intrare in recursivitate. */
    recursions++;

    /* Daca e solutie, afiseaz-o si iesi. */
    if (rebus.is_done()){
      System.out.println(rebus.toString());
      found_solution = true;
      return;
    } else if (row == rebus.rows) {
      return;
    }

    if (rebus.is_empty(row, col)) {
      /* Daca la (row, col) nu este completat, atunci incercam sa completam
       * orizontal unul dintre stringurile din domeniu pentru (row, col). */
      Vector<String> possibilities = horizontal.get(new Position(row, col));
      for (int i = 0; i < possibilities.size(); ++i) {
        String s = possibilities.elementAt(i);

        /* Completeaza aceasta varianta. */
        rebus.putString(row, col, s);

        /* Modifica prefixele verticale. */
        Vector<String> newVerticalPrefixes = copyPrefixes(verticalPrefixes);
        for (int j = 0; j < s.length(); ++j) {
          newVerticalPrefixes.set(col + j, newVerticalPrefixes.elementAt(col + j) + s.charAt(j));
        }

        /* Doar daca verificarea s-a incheiat cu succes. */
        Domains newHorizontal = copyDomains(horizontal, rebus.rows, rebus.columns);
        Domains newVertical = copyDomains(vertical, rebus.rows, rebus.columns);

        if (verificaInainte(row, col, s, newHorizontal, newVertical,
                            newVerticalPrefixes)) {

          /* Apeleaza intai propagarea constrangerilor. */
          if (propagateConstraints(newHorizontal, newVertical)) {
            backtracking(row + (col + 1) / rebus.columns,
                         (col + 1) % rebus.columns,
                         newHorizontal,
                         newVertical,
                         newVerticalPrefixes);
          }
        }
        /* Retrage-ti miscarea. */
        rebus.eraseString(row, col);
      }
    }
    else 
    {
      /* Cand treci peste un '*', sterge prefixul pentru coloana respectiva. */
      if (rebus.get(row, col) == '*') 
      {
        verticalPrefixes.set(col, "");
      }
      /* Daca e completat deja, treci mai departe. */
      backtracking(row + (col + 1) / rebus.columns,
                   (col + 1) % rebus.columns,
                   horizontal,
                   vertical,
                   verticalPrefixes);
    }
  }

  /* Functie care verifica daca avem blocare. */
  static boolean verificaInainte(int row,
                                 int col,
                                 String s,
                                 Domains horizontal,
                                 Domains vertical,
                                 Vector<String> prefixes) {

    /* Reducem domeniul orizontal la s. */
    horizontal.get(new Position(row, col)).clear();
    horizontal.get(new Position(row, col)).add(s);

    /* Parcurgem prefixele nou modificate si stergem din domeniul vertical tot ce
     * nu se potriveste cu prefixele. */
    for (int i = 0; i < s.length(); ++i) {
      /* Selectam domeniile vizate, le golim, si punem inapoi doar variantele care
       * au prefix matching. */
      int prefixLength = prefixes.elementAt(col + i).length();
      Position verticalBegin = new Position(row - prefixLength + 1, col + i);
      Vector<String> verticalDomain = copyVector(vertical.get(verticalBegin));
      vertical.get(verticalBegin).clear();

      /* Parcurgem vechea lista si adaugam inapoi doar ceea ce se potriveste cu
       * prefixul. */
      for (int word = 0; word < verticalDomain.size(); ++word) 
      {
        if (prefixes.elementAt(col + i).equals(verticalDomain.elementAt(word).substring(0, prefixLength))) 
        {
          vertical.get(verticalBegin).add(verticalDomain.elementAt(word));
        }
      }

      /* Daca cumva domeniul este gol, atunci intoarce false. */
      if (vertical.get(verticalBegin).size() == 0) {
        return false;
      }
    }
    return true;
  }

  /* Functie care propaga constrangerile dupa completarea unui string s orizontal
   * incepand de la pozitia (row, col) din rebus. */
  static boolean propagateConstraints(Domains horizontal, Domains vertical)
  {
    /* Cel mai simplu de implementat este AC1, deoarece ne lipseste o reprezentare
     * determinista a domeniilor care se intersecteaza, iar cautarea suplimentara
     * ar aduce un cost de performanta destul de mare. Se poate implementa,
     * desigur, si AC3.
     *
     * Predictia partiala sau completa se poate obtine modificand aceasta
     * functie (eliminand bucla do-while). Diferenta dintre ele nu este sesizabila
     * deoarece multimea de variabile din problema este bipartita si nu exista
     * restrictii intre doua cuvinte orizontale sau intre doua cuvinte verticale,
     * astfel incat este impropriu sa distingem intre cele doua tipuri de
     * predictie. Intr-o alta reprezentare a problemei, pot aparea diferente intre
     * cele doua tipuri de implementari.
     */

    boolean changed;
    do {
      changed = false;
      /* Consideram toate domeniile, doua cate doua. */
      Set<Entry<Position, Vector<String>>> horizontalEntrySet = horizontal.entrySet();
      Set<Entry<Position, Vector<String>>> verticalEntrySet = vertical.entrySet();

      Iterator<Entry<Position, Vector<String>>> ith = horizontalEntrySet.iterator();
      Iterator<Entry<Position, Vector<String>>> itv = verticalEntrySet.iterator();

      Entry<Position, Vector<String>> eith;
      Entry<Position, Vector<String>> eitv;

      while(ith.hasNext()) 
      {
        eith = ith.next();
        while(itv.hasNext())
        {
          eitv = itv.next();

          /* Verificam daca cele doua domenii fac overlap. Domeniile sunt sigur
           * nevide. */
          Position ph = eith.getKey();
          Position pv = eitv.getKey();

          if(eith.getValue().size() == 0 || eitv.getValue().size() == 0) continue;

          int spanh = eith.getValue().elementAt(0).length();
          int spanv = eitv.getValue().elementAt(0).length();

          if (pv.first <= ph.first && ph.first <= pv.first + spanv &&
              ph.second <= pv.second && pv.second <= ph.second + spanh) {
            int indexh = pv.second - ph.second;
            int indexv = ph.first - pv.first;

            /* Si acum facem purge reciproc la domenii. */
            Vector<String> newHorizontal = new Vector<String>();
            for (int h = 0; h < eith.getValue().size(); ++h) {
              /* Cautam sa vedem daca exista vreun cuvant vertical care sa aiba pe
               * pozitia indexv litera pe care o are cuvantul acesta orizontal pe
               * pozitia indexh. */
              boolean matches = false;
              for (int v = 0; v < eitv.getValue().size() && !matches; ++v) {
                if (eith.getValue().elementAt(h).charAt(indexh) == 
                    eitv.getValue().elementAt(v).charAt(indexv)) {
                  matches = true;
                }
              }
              /* Iar daca exista, il adaugam in noul domeniu. */
              if (matches) {
                newHorizontal.add(eith.getValue().elementAt(h));
              } else {
                changed = true;
              }
            }

            Vector<String> newVertical = new Vector<String>();
            for (int v = 0; v < eitv.getValue().size(); ++v) {
              /* Cautam sa vedem daca exista vreun cuvant orizontal care sa aiba
               * pe pozitia indexh litera pe care o are cuvantul acesta orizontal
               * pe pozitia indexv. */
              boolean matches = false;
              for (int h = 0; h < eith.getValue().size() && !matches; ++h) {
                if (eith.getValue().elementAt(h).charAt(indexh) == eitv.getValue().elementAt(v).charAt(indexv)) {
                  matches = true;
                }
              }

              /* Iar daca exista, il adaugam in noul domeniu. */
              if (matches) {
                newVertical.add(eitv.getValue().elementAt(v));
              } else {
                changed = true;
              }
            }

            /* In definitiv, daca vreo unul dintre cele doua domenii a ajuns vid,
             * atunci inseamna ca ne oprim cu "false", altfel actualizam
             * domeniile. */
            if (newHorizontal.isEmpty() || newVertical.isEmpty()) {
              return false;
            } else {
              eith.setValue(newHorizontal);
              eitv.setValue(newVertical);
            }
          }
        }
      }
    } while (changed);
    return true;
  }

  static Domains copyDomains(Domains d, int rows, int columns)
  {
    Domains newD = new Domains();
    for(int i = 0; i < rows; i++)
    {
      for(int j = 0; j < columns; j++)
      {
        Position pos = new Position(i, j);
        newD.put(pos, copyVector(d.get(pos)));
      }
    }
    return newD;
  }

  static Vector<String> copyPrefixes(Vector<String> p)
  {
    return copyVector(p);
  }

  static Vector<String> copyVector(Vector<String> v)
  {
    Vector<String> newV = new Vector<String>();
    for(String s : v)
    {
      newV.add(s);
    }
    return newV;
  }

}

class Position
{
  public int first;
  public int second;

  Position(int first, int second)
  {
    this.first = first;
    this.second = second;
  }

  @Override
      public int hashCode() 
      {
        return first % 666013 + second % 666013;
      }

  @Override
      public boolean equals(Object obj) 
      {
        Position other = (Position)obj;
        return other.first == this.first && other.second == this.second;
      }
}

/* Vom modela o tabela de variable ca fiind un map de la o coordonata (i, j)
 * la o multime de cuvinte care ar putea ipotetic sa fie introduse in Rebus
 * incepand cu prima litera de la (i, j), fie in pozitie orizontala (spre
 * dreapta), fie in pozitie verticala (spre in jos). */
@SuppressWarnings("serial")
class Domains extends HashMap<Position, Vector<String>>
{
  public Domains(int rows, int columns)
  {
    super();

    /* Init void domains */
    for(int i = 0; i < rows; i++)
    {
      for(int j = 0; j < columns; j++)
      {
        this.put(new Position(i, j), new Vector<String>());
      }
    }
  }

  public Domains()
  {
    super();
  }
}
