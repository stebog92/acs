#include "bkg_strategy.h"
#include <stdio.h>
#include <string.h>
#include <utility>
#include <vector>
#define INF 100000

using namespace std;

double perform(bkg_board board,
        color player,
        double *alfa,
        double *beta,
        int *maxMoves,
        int count,
        vector< pair<int, int> > a,
        char* buffer, int step, int *size, double prob) {

    if (count > *maxMoves) {
        *maxMoves = count;
        *alfa = -9999;
        *beta = 9999;
    }

    if (count == *maxMoves) {
        double value = -expectiMax(board, buffer, size, step + 1, (color) opponent(player), -(*beta), -(*alfa));
        if (step != 0)
            value = value * prob;
        if (value >= *beta && step != 0)
            return value;
        if (value > *alfa) {
            *alfa = value;
            if (step == 0) {
                *size = count * 2;
                for (int i = 0; i < a.size(); i++)
                    buffer[i * 2] = a[i].first, buffer[i * 2 + 1] = a[i].second;
            }
        }
    }
    return 100000;
}

/*initializare piese pe tabla*/
void init_board(bkg_board* board, color myClr) {
    bkg_board *b = board;
    memset((*b).player_board[BLACK], 0, 31 * sizeof (int));
    memset((*b).player_board[WHITE], 0, 31 * sizeof (int));
    (*b).player_board[BLACK][1] = (*b).player_board[WHITE][24] = 2;
    (*b).player_board[BLACK][19] = (*b).player_board[WHITE][6] = 5;
    (*b).player_board[BLACK][17] = (*b).player_board[WHITE][8] = 3;
    (*b).player_board[BLACK][12] = (*b).player_board[WHITE][13] = 5;
    (*b).myColor = myClr;
}

/*updatare tabla cu mutari transmise de mesajul primit de la server*/
void update_board(bkg_board* board, int size, char *message, color player) {
    int i = 0, steps, piece_location, new_location;
    if (player != (*board).myColor)
        size -= 2;
    for (i = 0; i < size; i += 2) {
        if (message[i] == 30) {
            if (player == WHITE)
                piece_location = 25;
            else
                piece_location = 0;
        } else
            piece_location = message[i];
        if (player == WHITE)
            steps = -message[i + 1];
        else
            steps = message[i + 1];
        new_location = piece_location + steps;
        if (player == WHITE && new_location < 1)
            new_location = 0;
        if (player == BLACK && new_location > 24)
            new_location = 25;
        if ((*board).player_board[opponent(player)][new_location] == 1) {
            (*board).player_board[opponent(player)][new_location]--;
            (*board).player_board[opponent(player)][30]++;
            if (piece_location == 25 || piece_location == 0)
                (*board).player_board[player][30]--;
            else
                (*board).player_board[player][piece_location]--;
            (*board).player_board[player][new_location]++;
        } else {
            if ((piece_location == 25 || piece_location == 0) && (*board).player_board[opponent(player)][new_location] > 1)
                return;
            if (piece_location == 25 || piece_location == 0)
                (*board).player_board[player][30]--;
            else
                (*board).player_board[player][piece_location]--;
            (*board).player_board[player][new_location]++;
        }
    }
    (*board).dice1 = message[i];
    (*board).dice2 = message[i + 1];
}

/*intoarce culoarea oponentului playerului primit ca parametru*/
int opponent(color clr) {
    return 1 - clr;
}

/*afiseaza tabla*/
void print_board(bkg_board board) {
    printf("BLACK :\n");
    for (int i = 1; i <= 24; i++) {
        printf("%d -> %d; ", i, board.player_board[BLACK][i]);
    }
    printf("BAR : %d\n", board.player_board[BLACK][30]);
    printf("\n");
    printf("WHITE :\n");
    for (int i = 1; i <= 24; i++) {
        printf("%d -> %d; ", i, board.player_board[WHITE][i]);
    }
    printf("BAR : %d\n", board.player_board[WHITE][30]);

}

/*functia ce alege mutarea optima pentru un player si o configuratie de tabla*/
double expectiMax(bkg_board board,
        char* buffer,
        int* size, int step, color player, double alfa, double beta) {

    int moves, count;
    vector<int> dices;
    bkg_board clone, clone2, clone3, clone4;

    /*intoarce evaluearea tablii dupa x pasi*/
    if (step == 1) {
        return evaluateBoard(board, player);
    }

    int begin, end, pas, x;

    /*initializare inceput si sfarsit pe tabla in functie de culoarea playerului
     se pleaca de la casa oponentului*/
    if (player == WHITE) {
        begin = 25;
        end = 1;
        pas = -1;
    } else {
        begin = 0;
        end = 24;
        pas = 1;
    }
    /*pasul unde se trece prin toate mutarile posibile pentru zarul cu care
     playerul trebuie sa mute*/
    if (step == 0) {

        /*daca playerul are piese pe bara si nu poate muta niciuna se returneza mesaj gol*/
        if (board.player_board[player][30] != 0 &&
                ((player == WHITE && board.player_board[opponent(player)][25 - board.dice1] > 1
                && board.player_board[opponent(player)][25 - board.dice2] > 1) ||
                (player == BLACK && board.player_board[opponent(player)][0 + board.dice1] > 1
                && board.player_board[opponent(player)][0 + board.dice2] > 1))) {
            *size = 0;
            return 0;
        }
        if (board.dice1 == board.dice2) {
            moves = 4;
        } else {
            moves = 2;
            dices.push_back(board.dice1);
            dices.push_back(board.dice2);
        }
        int maxMoves;
        /*pentru zaruri distincte*/
        if (moves == 2) {
            /*initializare nr mutari maxime si clona tablei actuale de joc*/
            maxMoves = 0;
            count = 0;
            clone = board;
            pair <int, int> a(-1, 0);
            pair <int, int> b(-1, 0);
            vector <pair <int, int> > allMoves;

            /*parcurgerea tuturor mutarilor posibile*/
            for (int dice1 = 0; dice1 < dices.size(); dice1++) {
                for (int i = begin; i != end + pas; i += pas) {
                    if (doMove(&board, i, dices[dice1], player)) {
                        a.first = i;
                        a.second = dices[dice1];
                        count++;
                        int dice2 = dices[1 - dice1];
                        allMoves.push_back(a);

                        perform(board, player, &alfa, &beta, &maxMoves, count, allMoves, buffer, step, size, 1 / 18);

                        clone2 = board;
                        for (int j = i; j != end + pas; j += pas) {
                            if (doMove(&board, j, dice2, player)) {
                                b.first = j;
                                b.second = dice2;
                                count++;
                                allMoves.push_back(b);

                                perform(board, player, &alfa, &beta, &maxMoves, count, allMoves, buffer, step, size, 1 / 18);

                                allMoves.pop_back();
                                count--;
                                board = clone2;
                            }
                        }
                        allMoves.pop_back();
                        count--;
                        board = clone;
                    }
                }
            }
        }
        /*pentru zaruri identice = 4 mutari*/
        else {
            clone = board;
            pair<int, int> a(-1, 0), b(-1, 0), c(-1, 0), d(-1, 0);
            maxMoves = 0;
            count = 0;
            vector <pair <int, int> > allMoves;
            for (int i = begin; i != end + pas; i += pas) {
                if (doMove(&board, i, board.dice1, player)) {
                    a.first = i;
                    a.second = board.dice1;
                    count++;
                    clone2 = board;
                    allMoves.push_back(a);
                    perform(board, player, &alfa, &beta, &maxMoves,
                            count, allMoves, buffer, step, size, 1 / 36);

                    for (int j = i; j != end + pas; j += pas) {
                        if (doMove(&board, j, board.dice1, player)) {
                            b.first = j;
                            b.second = board.dice1;
                            count++;
                            clone3 = board;
                            allMoves.push_back(b);

                            perform(board, player, &alfa, &beta, &maxMoves,
                                    count, allMoves, buffer, step, size, 1 / 36);
                            for (int k = j; k != end + pas; k += pas) {
                                if (doMove(&board, k, board.dice1, player)) {
                                    c.first = k;
                                    c.second = board.dice1;
                                    count++;
                                    clone4 = board;
                                    allMoves.push_back(c);
                                    perform(board, player, &alfa, &beta, &maxMoves,
                                            count, allMoves, buffer, step, size, 1 / 36);
                                    for (int l = k; l != end + pas; l += pas) {
                                        if (doMove(&board, l, board.dice1, player)) {
                                            d.first = l;
                                            d.second = board.dice1;
                                            count++;
                                            allMoves.push_back(d);

                                            perform(board, player, &alfa, &beta,
                                                    &maxMoves, count, allMoves,
                                                    buffer, step, size, 1 / 36);

                                            allMoves.pop_back();
                                            count--;
                                            d.first = -1;
                                            board = clone4;
                                        }
                                    }
                                    allMoves.pop_back();
                                    count--;
                                    c.first = -1;
                                    board = clone3;
                                }
                            }
                            allMoves.pop_back();
                            count--;
                            b.first = -1;
                            board = clone2;
                        }
                    }
                    allMoves.pop_back();
                    count--;
                    a.first = -1;
                    board = clone;

                }
            }
        }
    } else {
        int maxMoves = 0;
        count = 0;
        pair <int, int> a(-1, 0);
        pair <int, int> b(-1, 0);
        pair <int, int> c(-1, 0);
        pair <int, int> d(-1, 0);

        for (int dice1 = 1; dice1 <= 6; dice1++) {
            for (int dice2 = dice1; dice2 <= 6; dice2++) {
                dices.clear();
                dices.push_back(dice1);
                dices.push_back(dice2);
                if (dice1 != dice2) {
                    maxMoves = 0;
                    count = 0;
                    clone = board;
                    pair <int, int> a(-1, 0);
                    pair <int, int> b(-1, 0);
                    pair <int, int> c(-1, 0);
                    pair <int, int> d(-1, 0);
                    vector <pair <int, int> > allMoves;
                    for (int d1 = 0; d1 < dices.size(); d1++) {
                        for (int i = begin; i != end + pas; i += pas) {
                            if (doMove(&board, i, dices[d1], player)) {
                                a.first = i;
                                a.second = dices[d1];
                                count++;
                                int d2 = dices[1 - d1];
                                allMoves.push_back(a);
                                x = perform(board, player, &alfa, &beta,
                                        &maxMoves, count, allMoves, buffer,
                                        step, size, 1 / 18);
                                if (x != INF)
                                    return x;
                                clone2 = board;
                                for (int j = i; j != end + pas; j += pas) {
                                    if (doMove(&board, j, d2, player)) {
                                        b.first = j;
                                        b.second = d2;
                                        count++;
                                        allMoves.push_back(b);
                                        x = perform(board, player, &alfa, &beta,
                                                &maxMoves, count, allMoves, buffer
                                                , step, size, 1 / 18);
                                        if (x != INF)
                                            return x;
                                        allMoves.pop_back();
                                        count--;
                                        board = clone2;
                                    }
                                }
                                allMoves.pop_back();
                                count--;
                                board = clone;
                            }
                        }
                    }
                } else {
                    clone = board;
                    pair<int, int> a(-1, 0), b(-1, 0), c(-1, 0), d(-1, 0);
                    maxMoves = 0;
                    count = 0;
                    vector <pair <int, int> > allMoves;
                    for (int i = begin; i != end + pas; i += pas) {
                        if (doMove(&board, i, dice1, player)) {
                            a.first = i;
                            a.second = dice1;
                            count++;
                            clone2 = board;
                            allMoves.push_back(a);
                            x = perform(board, player, &alfa, &beta, &maxMoves,
                                    count, allMoves, buffer, step, size, 1 / 36);
                            if (x != INF)
                                return x;
                            for (int j = i; j != end + pas; j += pas) {
                                if (doMove(&board, j, dice1, player)) {
                                    b.first = j;
                                    b.second = dice1;
                                    count++;
                                    clone3 = board;
                                    allMoves.push_back(b);
                                    x = perform(board, player, &alfa, &beta,
                                            &maxMoves, count, allMoves, buffer,
                                            step, size, 1 / 36);
                                    if (x != INF)
                                        return x;
                                    for (int k = j; k != end + pas; k += pas) {
                                        if (doMove(&board, k, dice1, player)) {
                                            c.first = k;
                                            c.second = dice1;
                                            count++;
                                            clone4 = board;
                                            allMoves.push_back(c);
                                            x = perform(board, player, &alfa,
                                                    &beta, &maxMoves, count,
                                                    allMoves, buffer, step, size, 1 / 36);
                                            if (x != INF)
                                                return x;
                                            for (int l = k; l != end + pas; l += pas) {
                                                if (doMove(&board, l, dice1, player)) {
                                                    d.first = l;
                                                    d.second = dice1;
                                                    count++;
                                                    allMoves.push_back(d);
                                                    x = perform(board, player, &alfa,
                                                            &beta, &maxMoves, count,
                                                            allMoves, buffer, step, size, 1 / 36);
                                                    if (x != INF)
                                                        return x;
                                                    allMoves.pop_back();
                                                    count--;
                                                    board = clone4;
                                                }
                                            }
                                            allMoves.pop_back();
                                            count--;
                                            board = clone3;
                                        }
                                    }
                                    allMoves.pop_back();
                                    count--;
                                    board = clone2;
                                }
                            }
                            allMoves.pop_back();
                            count--;
                            board = clone;

                        }
                    }
                }
            }
        }
    }
    return alfa;

}

/*returneaza true daca jucatorul are piese pe bara si false in caz contrar*/
bool barIsOccupied(bkg_board board, color player) {
    if (player == WHITE && board.player_board[WHITE][25] != 0)
        return true;
    if (player == BLACK && board.player_board[BLACK][0] != 0)
        return true;
    return false;
}

/*executa o mutare pentru o pozitie si un zar si intoarce true daca mutarea e 
 posibila si false in caz contrar; daca este posibil se modifica si tabla*/
bool doMove(bkg_board* board, int location, int dice, color player) {
    int newLocation;
    if (player == WHITE) {
        if ((*board).player_board[WHITE][25] != 0) {
            if (location != 25)
                return false;
        }
    }

    if (player == BLACK) {
        if ((*board).player_board[BLACK][0] != 0) {
            if (location != 0)
                return false;
        }
    }

    if ((player == WHITE && location == 0) || (player == BLACK && location == 25))
        return false;
    if ((*board).player_board[player][location] == 0)
        return false;
    else {
        if (player == WHITE)
            dice = -dice;
        newLocation = location + dice;
        if (player == WHITE && newLocation < 1) {
            newLocation = 0;
            if (allCheckersInDaHouse(*board, player)) {
                if ((*board).player_board [WHITE][0 - dice] != 0 && location != (0 - dice))
                    return false;

                if ((*board).player_board [WHITE][0 - dice] == 0 && location < (0 - dice)) {

                    for (int i = location + 1; i <= 6; i++) {
                        if ((*board).player_board[WHITE][i] != 0) {
                            return false;
                        }
                    }
                }
            } else {
                return false;
            }
        }
        if (player == BLACK && newLocation > 24) {
            newLocation = 25;
            if (allCheckersInDaHouse(*board, player)) {
                if ((*board).player_board [BLACK][25 - dice] == 0 && location > 25 - dice) {
                    for (int i = location - 1; i >= 19; i--)
                        if ((*board).player_board[BLACK][i] != 0)
                            return false;
                }
                if ((*board).player_board [BLACK][25 - dice] != 0 && location != 25 - dice)
                    return false;
            } else {
                return false;
            }
        }
        if (newLocation != 0 && newLocation != 25) {
            if ((*board).player_board[opponent(player)][newLocation] > 1)
                return false;
            else {
                if ((*board).player_board[opponent(player)][newLocation] == 1) {
                    (*board).player_board[player][newLocation]++;
                    (*board).player_board[opponent(player)][newLocation]--;
                    if (opponent(player) == WHITE)
                        (*board).player_board[WHITE][25]++;
                    else
                        (*board).player_board[BLACK][0]++;
                    (*board).player_board[player][location]--;
                } else {
                    (*board).player_board[player][newLocation]++;
                    (*board).player_board[player][location]--;
                }
            }
        }
        if (newLocation == 0 || newLocation == 25) {
            (*board).player_board[player][newLocation]++;
            (*board).player_board[player][location]--;
        }
    }
    return true;
}

/*functie invelis pentru expectiMax*/
void nextMove(bkg_board board, char* buffer, int* size) {
    *size = 0;
    if (board.player_board[WHITE][30] != 0)
        board.player_board[WHITE][25] = board.player_board[WHITE][30];
    if (board.player_board[BLACK][30] != 0)
        board.player_board[BLACK][0] = board.player_board[BLACK][30];
    memset(buffer, -1, 12);
    expectiMax(board, buffer, size, 0, board.myColor, -9999, 9999);
    for (int i = 0; i < *size; i += 2)
        if (buffer[i] == 25 || buffer[i] == 0) {
            int dice = buffer[i + 1];
            memmove(buffer + i, buffer + i + 2, *size - i - 2);
            memmove(buffer + 2, buffer, *size);
            buffer[0] = 30;
            buffer[1] = dice;
        }

}

/*verificare daca toate jetoanele sunt in casa*/
bool allCheckersInDaHouse(bkg_board board, color player) {
    if (player == WHITE) {
        for (int i = 25; i > 6; i--) {
            if (board.player_board[player][i] != 0)
                return false;
        }
    }
    if (player == BLACK) {
        for (int i = 0; i < 19; i++) {
            if (board.player_board[player][i] != 0)
                return false;
        }
    }
    return true;
}

/*functie evaluare tabla pentru un jucator*/
double evaluateBoard(bkg_board board, color player) {
    int n = 0, m = 0, checkers = 0, single = 0;
    for (int i = 1; i <= 24; i++) {
        if (board.player_board [player][i] > 1) {
            n += 2;
            if (board.player_board [player][i] > 4)
                n--;
            if (player == WHITE && i <= 6 && i > 0) {
                checkers += board.player_board[player][i];
                n++;
            }
            if (player == BLACK && i >= 19 && i < 25) {
                n++;
                checkers += board.player_board[player][i];
            }
        }
	if (board.player_board[player][i] == 1)
	{
		if (player == WHITE && i <= 6 && i > 0)
			single --;
		if (player == BLACK && i >= 19 && i < 25)
			single --;
	}

    }
    for (int i = 1; i <= 24; i++) {
        if (board.player_board [opponent(player)][i] > 1) {
            m -= 2;
            if (board.player_board [opponent(player)][i] > 4)
                m++;
        }
    }
    checkers = 15 - checkers;
    if (WHITE)
        return 0.01 * (n + m) + 0.029 * (board.player_board[player][0] - board.player_board[player][25]) + 0.02 * checkers/* + 0.001 * single*/;
    else
        return 0.01 * (n + m) + 0.029 * (board.player_board[player][25] - board.player_board[player][0]) + 0.02 * checkers /*+ 0.001 * single*/;


}

