#include <fstream>
#include <stdlib.h>
#include <omp.h>
#include <string.h>
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
    int i, j, an, costij, tm, count_a, count_b, *Pmax_a, *Pmax_b, num_threads;
    t = atoi (argv[1]);
    ifstream in (argv[2], ifstream::in);
    ofstream out (argv[3], ofstream::out);

    in >> Pmin >> Pmax >> n;
    num_threads = 10;
    Pmax_a = new int[num_threads];
    Pmax_b = new int[num_threads];
    memset(Pmax_a, 0, num_threads * sizeof(int));
    memset(Pmax_b, 0, num_threads * sizeof(int));

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
            for (i = 1; i < num_threads; i++) {
                if (Pmax_a[0] < Pmax_a[i]) {
                    Pmax_a[0] = Pmax_a[i];
                }
                if (Pmax_b[0] < Pmax_b[i]) {
                    Pmax_b[0] = Pmax_b[i];
                }
                Pmax_a[i] = 0;
                Pmax_b[i] = 0;
            }
            out << count_a << " " << Pmax_a[0] << " " << count_b << " " << Pmax_b[0] << endl;
            Pmax_a[0] = 0;
            Pmax_b[0] = 0;
        }
        if (an == t) {
            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++) {
                    out << "(" << res[tm][i][j] << "," << price[tm][i][j] << "," << budget[tm][i][j] << ") ";
                }
                out << endl;
            }
            for (i = 0; i < n; i++) {
                delete [] res[0][i];
                delete [] budget[0][i];
                delete [] price[0][i];
                delete [] res[1][i];
                delete [] budget[1][i];
                delete [] price[1][i];
            }
            delete[] res[0];
            delete[] res[1];
            delete[] res;
            delete[] budget[0];
            delete[] budget[1];
            delete[] budget;
            delete[] price[0];
            delete[] price[1];
            delete[] price;
            return 0;
        }

        count_a = count_b = 0;
        

        #pragma omp parallel for private(i, j, costij), shared(Pmax_a, Pmax_b, tm, Pmin, Pmax), reduction(+:count_a, count_b)
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
                int id = omp_get_thread_num();
                if (res[1 - tm][i][j] == 0 && price[1 - tm][i][j] > Pmax_a[id])
                    Pmax_a[id] = price[1 - tm][i][j];
                if (res[1 - tm][i][j] == 1 && price[1 - tm][i][j] > Pmax_b[id])
                    Pmax_b[id] = price[1 - tm][i][j];

            }

        }
    }
    return 0;
}
