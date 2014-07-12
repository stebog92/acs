#include <stdio.h>
#include <stack>
#include <utility>

using namespace std;

typedef struct player {
    int x;
    int y;
} Player;

int map[100][100];
int _map[100][100];
int visited[100][100];


/* read map */
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

/* read sensor value in a position */
int getSensorValue(int x, int y) {
    int value = 0;
    if (x > 0) {
        value += map[x - 1][y] == '@' ? 1 : 0;
    }
    if (x < xSize - 1) {
        value += map[x + 1][y] == '@' ? 1 : 0;
    }
    if (y > 0) {
        value += map[x][y - 1] == '@' ? 1 : 0;
    }
    if (y < ySize - 1) {
        value += map[x][y + 1] == '@' ? 1 : 0;
    }
    return value;
}

/* check valid move */
int validMove(int x, int y) {
    if (map[x][y] == '#') {
        return 0;
    }
    return 1;
}

/* print map */
void printMap() {
    int i, j;
    for (i = 0; i < xSize; i++) {
        if(i == 0) {
            printf("\n\nSymbol map and visited map\n\n");
        }
        for (j = 0; j < ySize; j++) {
            printf("%c ", _map[i][j]);
        }

        printf("   ");

        for (j = 0; j < ySize; j++) {
            printf("%d ", visited[i][j]);
        }
        printf("\n");
    }
    
}

/* start and build player journey */
void start(Player player) {
    stack< pair<int, int> > s;
    s.push(make_pair(player.x, player.y));
    int safeOrWall = 0;
    int swampX, swampY;

    int i, j;
    for (i = 0; i < xSize; i++) {
        for (j = 0;j < ySize; j++) {
            _map[i][j] = 's';
        }
    }
    while(!s.empty()) {
        pair<int, int> u = s.top();

        s.pop();
        if (!visited[u.first][u.second]) {
            visited[u.first][u.second] = 1;
            _map[u.first][u.second] = 'v';
            if (getSensorValue(u.first, u.second)) {
                safeOrWall = 0;

                /* set unsafe tiles */
                if (u.first > 0 && !visited[u.first - 1][u.second] && _map[u.first - 1][u.second] != 'w') {
                    _map[u.first - 1][u.second] = 'u';
                }
                if (u.second + 1 < ySize && !visited[u.first][u.second + 1] && _map[u.first][u.second + 1] != 'w') {
                    _map[u.first][u.second + 1] = 'u';
                }
                if (u.first + 1 < xSize && !visited[u.first + 1][u.second] && _map[u.first + 1][u.second] != 'w') {
                    _map[u.first + 1][u.second] = 'u';
                }
                if (u.second > 0 && !visited[u.first][u.second - 1] && _map[u.first][u.second - 1] != 'w') {
                    _map[u.first][u.second - 1] = 'u';
                }

                if (u.first > 0 && _map[u.first - 1][u.second] == 'w' || _map[u.first - 1][u.second] == 's') {
                    safeOrWall++;
                } else if(u.first > 0) {
                    swampX = u.first - 1;
                    swampY = u.second;
                }

                if (u.second + 1 < ySize && _map[u.first][u.second + 1] == 'w' || _map[u.first][u.second + 1] == 's') {
                    safeOrWall++;
                } else if (u.second + 1) {
                    swampX = u.first;
                    swampY = u.second + 1;
                }
                if (u.first + 1 < xSize && _map[u.first + 1][u.second] == 'w' || _map[u.first + 1][u.second] == 's') {
                    safeOrWall++;
                } else if (u.first + 1 < xSize) {
                    swampX = u.first + 1;
                    swampY = u.second;
                }
                if (u.second > 0 && _map[u.first][u.second  - 1] == 'w' || _map[u.first][u.second - 1] == 's') {
                    safeOrWall++;
                } else if (u.second > 0) {
                    swampX = u.first;
                    swampY = u.second - 1;
                }
                if (safeOrWall == 3) {
                    _map[swampX][swampY] = 'm';
                }
            }

            /* add neighbour tiles in stack if safe */
            if (validMove(u.first - 1, u.second)) {
                if(_map[u.first - 1][u.second] != 'u') {
                    s.push(make_pair(u.first - 1, u.second));
                }
            } else {
                _map[u.first - 1][u.second] = 'w';
            }
            if (validMove(u.first + 1, u.second)) {
                if(_map[u.first + 1][u.second] != 'u') {
                    s.push(make_pair(u.first + 1, u.second));
                }
            } else {
                _map[u.first + 1][u.second] = 'w';
            }
            if (validMove(u.first, u.second + 1)) {
                if (_map[u.first][u.second + 1] != 'u') {
                    s.push(make_pair(u.first, u.second + 1));
                }
            } else {
                _map[u.first][u.second + 1] = 'w';
            }
            if (validMove(u.first, u.second - 1)) {
                if (_map[u.first][u.second - 1] != 'u') {
                    s.push(make_pair(u.first, u.second - 1));
                }
            } else {
                _map[u.first][u.second - 1] = 'w';
            }
        }
    }
}

int main(int argc, char** argv) {

    Player player;
    if (argc < 2) {
        printf("Usage : ./main file.in\n");
        return 0;
    }
    read_map(argv[1], &player);
    start(player);
    printMap();

    return 0;
}
