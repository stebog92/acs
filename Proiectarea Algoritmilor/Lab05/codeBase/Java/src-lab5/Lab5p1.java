
import java.util.Vector;

public class Lab5p1 {

    public static void main(String[] args) {
        XOGame game = new XOGame(new SmartComputerAI(), new Human());
        game.runGame();
    }
}

class ScoreBoardPair {

    XOBoard board;
    int score;
    int steps;

    public ScoreBoardPair(int score, XOBoard board, int steps) {
        this.score = score;
        this.board = board;
        this.steps = steps;
    }
}

class SmartComputerAI implements IsAbleToPlay {

    private static final int INF = 100;

    private XOPlayer opponent(XOPlayer player) {
        if (player == XOPlayer.PlayerO) {
            return XOPlayer.PlayerX;
        } else {
            return XOPlayer.PlayerO;
        }
    }

    public ScoreBoardPair best_move(int alfa,int beta, XOPlayer player, XOBoard board, int steps) {
        XOBoard b = null;
        int score = -INF;
        steps = 10000;
        if (board.get_score(player) > board.get_score(opponent(player)))
            return new ScoreBoardPair(board.get_score(player) - board.get_score(opponent(player)), board, steps);
        if (board.is_full()) {
            return new ScoreBoardPair(board.get_score(player) - board.get_score(opponent(player)), board, steps);
        } else {
            ScoreBoardPair x;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.is_empty(i, j)) {
                        board.put(player, i, j);
                        x = best_move(-beta, -alfa, opponent(player), board, steps + 1);
                        if (-x.score >= beta)
                        {
                              b = board.tryClone();
                              b.erase(i, j);
                              return new ScoreBoardPair(beta, b, steps);
                        }
                        if (-x.score > alfa || (-x.score == alfa && x.steps < steps)) {
                            b = board.tryClone();
                            alfa = -x.score;
                            steps = x.steps;
                        }
                        board.erase(i, j);
                    }
                }
            }
            return new ScoreBoardPair(alfa, b, steps);
        }
    }

    public XOBoard move(XOPlayer player, XOBoard board) {
        System.out.println("Computer is thinking...");
        /*
         * TODO: Fa o miscare si returneaza tabla dupa aceasta miscare. Aceasta
         * functie de AI trebuie sa respecte acest format pentru ca este data in
         * constructorul jocului, dar puteti apela aici o functie scrisa de voi.
         */
        return best_move(INF, -INF, player, board, 0).board;
        /*
         * TODO: Stergeti linia de mai jos dupa ce rezolvati.
         */
    }
}
