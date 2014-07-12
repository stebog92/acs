import java.util.Scanner;

public class Lab3p1 
{
  public static void main(String[] args)
  {
    Scanner inputScanner = new Scanner(System.in);
    String inputString = inputScanner.nextLine();  
    BooleanExpression booleanExpression = new BooleanExpression(inputString);
    if(booleanExpression.isValid())
    {
      Lexem[] lexemArray = booleanExpression.getLexems();
      System.out.println("" + countModes(lexemArray));
    }
    else
    {
      System.out.println("Invalid expression, exiting...");
    }
  }

  private static int countModes(Lexem[] expr) 
  {
    /* TODO: Numarati modurile in care se pot pune paranteze paranteze in
     * expresie astfel incat sa se obtina rezultatul "true".
     *
     * OBS: Este obligatoriu sa puneti in expresie un numar de paranteze egal
     * cu numarul de operatori prezenti (adica parantezarea sa fie completa).
     *
     * OBS: Asa cum scrie si in documentatie, Lexem este un tip de enumerare ce
     * poate avea valorile: 
     *
     * BooleanExpression::True, 
     * BooleanExpression::False, 
     * BooleanExpression::Or, 
     * BooleanExpression::Xor, 
     * BooleanExpression::And 
     *
     * (sunt constante numerice, valoare reala nu este importanta).
     */

    /* Vom alege o abordare bazata pe programare dinamica astfel: pentru
     * fiecare subexpresie, vom calcula toate modurile de parantezare care
     * sa aduca valoarea expresiei la "false", respectiv toate modurile de
     * parantezare care aduca valoarea expresiei la "true".
     *
     * Pentru corectitudine, trebuie ca subexpresiile alese pentru calcul sa fie
     * din ce in ce mai lungi (o abordare de tip bottom-up).
     *
     * O parantezare este echivalenta cu o ordine anume de executie a
     * operatiilor din expresie, deci vom lua pe rand fiecare operator din
     * subexpresie si vom presupune ca el se executa ultimul. Subexpresiile din
     * stanga, respectiv dreapta lui sunt mai mici ca lungime, deci putem conta
     * ca numarul de parantezari pentru ele pentru a obtine un anumit rezultat
     * este deja calculat. In aceste conditii, vom face urmatoarele notatii:
     *
     * #T(e) = numarul de parantezari posibile astfel incat e sa dea True
     * #F(e) = numarul de parantezari posibile astfel incat e sa dea False
     *
     * Avem urmatoarele axiome:
     *
     * #T(True) == 1
     * #T(False) == 0
     * #F(True) == 0
     * #F(False) == 1
     *
     * In condtinuare, regulile de compunere pentru operatori sunt astfel:
     *
     * Fie e =(----e1----) and (----e2----), atunci:
     *
     * #T(e = e1 and e2) = #T(e1) * #T(e2)
     * #F(e = e1 and e2) = #F(e1) * #T(e2) +
     *                     #T(e1) * #F(e2) +
     *                     #F(e1) * #F(e2)
     *
     * Fie e = (----e1----) or (----e2----), atunci:
     *
     * #T(e1 or e2) = #T(e1) * #T(e2) +
     *                #T(e1) * #F(e2) +
     *                #F(e1) * #T(e2)
     * #F(e1 or e2) = #F(e1) * #F(e2)
     *
     * Fie e = (----e1----) xor (----e2----), atunci:
     *
     * #T(e1 xor e2) = #T(e1) * #F(e2) +
     *                 #F(e1) * #T(e2)
     * #F(e1 xor e2) = #T(e1) * #T(e2) +
     *                 #F(e1) * #F(e2)
     *
     * Pentru a lua in cacula toate parantezarile, trebuie sa consideram pe rand
     * toti operatorii din subexpresie ca fiind primul operator executat si sa
     * sumam toate posibilitatile.
     */

    int[][] nrT = new int[expr.length][expr.length];
    int[][] nrF = new int[expr.length][expr.length];

    for(int i = 0; i < expr.length; i++)
      for(int j = 0; j < expr.length; j++)
        nrT[i][j] = nrF[i][j] = 0;

    /* Initializam toate expresiile formate doar din constante. */
    for (int i = 0; i < expr.length; i += 2) {
      if (expr[i] == Lexem.True) {
        nrT[i][i] = 1;
        nrF[i][i] = 0;
      } else {
        nrT[i][i] = 0;
        nrF[i][i] = 1;
      }
    }

    /* Aplicam regulile de compunere calculand pe expresii din ce in ce mai
     * lungi. */
    for (int len = 3; len <= expr.length; ++len) {
      for (int i = 0; i <= expr.length - len; i += 2) {
        int j = i + len - 1;

        /* Calculam toate modurile de impartire ale expresiei. */
        for (int op = i + 1; op < j; op += 2) {
          switch(expr[op]) {
            case And:
              nrT[i][j] += nrT[i][op - 1] * nrT[op + 1][j];
              nrF[i][j] += nrT[i][op - 1] * nrF[op + 1][j] +
                  nrF[i][op - 1] * nrT[op + 1][j] +
                  nrF[i][op - 1] * nrF[op + 1][j];
              break;
            case Or:
              nrT[i][j] += nrT[i][op - 1] * nrF[op + 1][j] +
                  nrF[i][op - 1] * nrT[op + 1][j] +
                  nrT[i][op - 1] * nrT[op + 1][j];
              nrF[i][j] += nrF[i][op - 1] * nrF[op + 1][j];
              break;
            case Xor:
              nrT[i][j] += nrT[i][op - 1] * nrF[op + 1][j] +
                  nrF[i][op - 1] * nrT[op + 1][j];
              nrF[i][j] += nrT[i][op - 1] * nrT[op + 1][j] +
                  nrF[i][op - 1] * nrF[op + 1][j];
              break;
          }
        }
      }
    }

    /* Rezultatul este #T(expresia initiala). */
    return nrT[0][expr.length - 1];
  }
}

