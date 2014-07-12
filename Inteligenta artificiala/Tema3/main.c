#include <stdio.h>
typedef struct player {
    int x;
    int y;
} Player;

int map[100][100];
int _map[100][100];
int visited[100][100];


int xSize, ySize;
void read_map(char* file, Player *player) {
    FILE *in = fopen(file, "r");
    int i, j;
    fscanf(in, "%d %d", &xSize, &ySize);
    char tile;
    for (i = 0; i < xSize; i++) {
        for (j = 0; j < ySize; j++) {
            fscanf(in, "%c ", &tile);
            if (tile == '\n') {
                j--;
            }
            map[i][j] = tile;
        }
    }

    fscanf(in, "%d %d", &(player->x), &(player->y));
}

int getSensorValue(int x, int y) {
    int value = 0;
    if (x > 0) {
        value = map[x - 1][y] == '@' ? 1 : 0;
    }
    if (x < xSize - 1) {
        value = map[x + 1][y] == '@' ? 1 : 0;
    }
    if (y > 0) {
        value = map[x][y - 1] == '@' ? 1 : 0;
    }
    if (y < ySize - 1) {
        value = map[x][y + 1] == '@' ? 1 : 0;
    }
    return value;
}

int validMove(int x, int y) {
    if (map[x][y] == '#') {
        return 0;
    }
    return 1;
}


void start(Player player) {



}

int main(int argc, char** argv) {

    Player player;
    read_map(argv[1], &player);
    start(player);

    return 0;
}
