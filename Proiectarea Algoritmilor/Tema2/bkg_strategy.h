#ifndef BKG_STRATEGY_H
#define BKG_STRATEGY_H
#include <utility>

using namespace std;

enum color {
	BLACK,
	WHITE
};
typedef struct {
	int player_board[2][31];
	int dice1;
	int dice2;
	color myColor;
} bkg_board;




void init_board (bkg_board* board, color myColor);
void update_board (bkg_board* board, int size, char* message, color player);
int opponent (color clr);
void print_board (bkg_board board);
void nextMove (bkg_board board, char* buffer, int* size);
bool doMove (bkg_board* board, int place, int dice, color player);
double expectiMax (bkg_board, char* buffer, int* size, int step, color player, double alfa, double beta);
bool allCheckersInDaHouse (bkg_board, color player);
double evaluateBoard (bkg_board board, color player);
#endif
