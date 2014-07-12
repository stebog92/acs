#include <fstream>
#include <stdlib.h>
#define INF 100000000

using namespace std;
int Pmin, Pmax, n, ***price, ***budget, ***res, t;

int minim (int i,int j, int resource, int tm) {
    int min = INF;

    for (int _i = 0; _i < n; _i++)
        for (int _j = 0; _j < n; _j++) {
            if (res[tm][_i][_j] == resource) {
                int val = price[tm][_i][_j] + abs(i - _i) + abs(j - _j);
                if (val < min)
                    min = val;
            }
        }
   return min;
}


int main (int argc, char** argv) {
    int i, j, an, costij, tm, count_a, count_b, Pmax_a, Pmax_b;
    t = atoi (argv[1]);
    ifstream in (argv[2], ifstream::in);
    ofstream out (argv[3], ofstream::out);

    in >> Pmin >> Pmax >> n;
    res = new int**[2];
    res[0] = new int*[n]; 
    res[1] = new int*[n];
    for (i = 0; i < n; i++) {
        res[0][i] = new int[n];
        res[1][i] = new int[n];
        for (j = 0; j < n; j++) {
            in >> res[0][i][j];
        }
    }
    price = new int**[2];
    price[0] = new int*[n]; 
    price[1] = new int*[n];
    for (i = 0; i < n; i++) {
        price[0][i] = new int[n];
        price[1][i] = new int[n];
        for (j = 0; j < n; j++) {
            in >> price[0][i][j];
        }
    }
    budget = new int**[2];
    budget[0] = new int*[n]; 
    budget[1] = new int*[n];
    for (i = 0; i < n; i++) {
        budget[0][i] = new int[n];
        budget[1][i] = new int[n];
        for (j = 0; j < n; j++) {
            in >> budget[0][i][j];
        }
    }

    for (an = 0; an <= t; an++) {
        tm = an % 2;
        if (an > 0) {
            out << count_a << " " << Pmax_a << " " << count_b << " " << Pmax_b << endl;
        }
        if (an == t) {
            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++) {
                    out << "(" << res[tm][i][j] << "," << price[tm][i][j] << "," << budget[tm][i][j] << ") ";
                }
                out << endl;
            }
            return 0;
        }

        count_a = count_b = 0;
        Pmax_a = Pmax_b = 0;


        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                costij = minim (i, j, 1 - res[tm][i][j], tm);
                if (costij > budget[tm][i][j]) {
                    budget[1 - tm][i][j] = costij;
                    price[1 - tm][i][j] = price[tm][i][j] + costij - budget[tm][i][j];
                }
                else if (costij < budget[tm][i][j]) {
                    budget[1 - tm][i][j] = costij;
                    price[1 - tm][i][j] = price[tm][i][j] + (costij - budget[tm][i][j])/2;
                    if (price[1 - tm][i][j] < Pmin)
                        price[1 - tm][i][j] = Pmin;
                }
                else if (costij == budget[tm][i][j]) {
                    budget[1 - tm][i][j] = costij;
                    price[1 - tm][i][j] = minim(i,j,res[tm][i][j], tm) + 1;
                }

                res[1 - tm][i][j] = res[tm][i][j];

                if (price[1 - tm][i][j] > Pmax) {
                    res[1 - tm][i][j] = 1 - res[tm][i][j];
                    budget[1 - tm][i][j] = Pmax;
                    price[1 - tm][i][j] = (Pmin + Pmax) / 2;
                }
                if (res[1 - tm][i][j] == 0)
                    count_a++;
                else
                    count_b++;
                if (res[1 - tm][i][j] == 0 && price[1 - tm][i][j] > Pmax_a)
                    Pmax_a = price[1 - tm][i][j];
                if (res[1 - tm][i][j] == 1 && price[1 - tm][i][j] > Pmax_b)
                    Pmax_b = price[1 - tm][i][j];

            }

        }
    }
    return 0;
}
