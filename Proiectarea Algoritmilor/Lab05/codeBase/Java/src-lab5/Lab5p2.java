
import java.util.Scanner;

public class Lab5p2 {

    private static Scanner inputScanner = new Scanner(System.in);

    static int negaMax(NimGame game, int depth) {
        /*
         * Daca s-a terminat jocul, atunci scorul este 0.
         */
        if (game.gameOver()) {
            if (depth % 2 == 0) {
                return 0;
            } else {
                return 1;
            }
        }
        int s, a = 0, b = 0;
        boolean ok = false;
        for (int i = 3; i <= game.size(); i++) {
            if (game.get(i) != 0) {
                for (int j = 1; j <= i / 2; j++) {
                    game.split(i, j, i - j);
                    s = negaMax(game, depth + 1);
                    game.unsplit(i, j, i - j);
                    if (s == 1) {
                        a = i;
                        b = j;
                        ok = true;
                    }
                }
            }
        }
        if (ok) {
            if (depth == 0) {
                game.split(a, b, a - b);
            }
            return 1;
        } else {
            return 0;
        }


        /*
         * TODO: Altfel, fa o miscare pe tabla astfel incat sa castigi. Stergeti
         * linia de mai jos cand rezolvati, ea inseamna ca nu se face nici o
         * miscare, si ca AI-ul spune ca a pierdut deja.
         */
    }

    static void computerPlayer(NimGame game) {
        //game.print();
        System.out.print("Computer starts thinking... ");
        negaMax(game, 0);
        System.out.println();
        game.print();
        System.out.println("done");
    }

    static void humanPlayer(NimGame game) {
        game.print();
        System.out.println("Alege o gramada de impartit, "
                + "precum si dimensiunile in care vrei sa spargi gramada: ");
        int heap, a, b;
        do {
            /*
             * Citim o miscare de la consola.
             */
            heap = inputScanner.nextInt();
            a = inputScanner.nextInt();
            b = inputScanner.nextInt();

            if (game.get(heap) == 0) {
                /*
                 * Verificam sa existe o gramada de atatea pietricele.
                 */
                System.out.println("Eroare! Nu exista nici o gramada de "
                        + heap + " pietricele.");
            } else if (heap != a + b || a <= 0 || b <= 0) {
                /*
                 * Verificam ca miscarea sa fie valida.
                 */
                System.out.println("Eroare! O gramada de " + heap
                        + " pietricele nu se " + "poate imparti in " + a
                        + " + " + b + " pietricele.");
            } else if (heap >= 0 && heap <= 2) {
                /*
                 * Alta verificare de miscare valida.
                 */
                System.out.println("Eroare! Nu se poate imparti o gramada de "
                        + heap + " pietricele in gramezi mai mici.");
            } else {
                /*
                 * Efectuam miscarea si iesim din functie.
                 */
                game.split(heap, a, b);
                return;
            }
            System.out.println("Incearca din nou: ");
        } while (true);
    }

    public static void main(String[] args) {
        /*
         * Citim un numar initial de pietricele si pornim un joc.
         */
        System.out.println("Game size: ");

        int n = inputScanner.nextInt();
        NimGame game = new NimGame(n);

        do {
            /*
             * First player takes a move.
             */
            computerPlayer(game);
            System.out.println();
            if (game.gameOver()) {
                System.out.println();
                System.out.println("Player 1 wins!");
                break;
            }

            /*
             * Second player takes a move.
             */
            humanPlayer(game);
            System.out.println();
            if (game.gameOver()) {
                System.out.println();
                System.out.println("Player 2 wins!");
                break;
            }
        } while (true);
    }
}
