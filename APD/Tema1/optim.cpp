#include <fstream>
#include <stdlib.h>
#include <vector>
#include <queue>
#define INF 100000000

using namespace std;
int Pmin, Pmax, n, ***price, ***budget, ***res, t, ***res_loc, size_a, size_b, size_a_next, size_b_next;


int minim_optim (int i, int j, int resource, int tm) {
    int dist, min = INF;
    queue<int> q;
    vector< vector <int> > visited(n, vector<int> (n, 0));
    q.push(i * n + j);
    while (!q.size()) {
        int v = q.front();
        int _i = v/n;
        int _j = v%n;
        q.pop();
        visited[_i][_j] = 1;

        dist = abs(i - _i) + abs(j - _j);
        if (min < dist)
            continue;

        if (res[tm][i][j] == resource && price[tm][i][j] + dist < min) {
            min = price[tm][i][j] + dist;
        }
        if (_i - 1 >= 0 && !visited[_i - 1][_j])
            q.push((_i - 1) * n + _j);
        if (_i + 1 < n && !visited[_i + 1][_j])
            q.push((_i + 1) *n + _j);
        if (_j + 1 < n && !visited[_i][_j + 1])
            q.push(_i * n + _j + 1);
        if (_j - 1 >= 0 && !visited[_i][_j - 1])
            q.push(_i * n + _j - 1);
    }

    return min;
}


int minim (int i,int j, int resource, int tm) {
    int min = INF, _i, _j, val, size, last_i, last_j, dist;

    if (resource == 0)
        size = size_a;
    else
        size = size_b;

    for (int l = 0; l < size; l++) {
        _i = res_loc[tm][resource][l] / n;
        _j = res_loc[tm][resource][l] % n;
        dist = abs(i - _i) + abs(j - _j);
        val = price[tm][_i][_j] + dist;
        if (val < min) {
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
    
    res_loc = new int**[2];
    res_loc[0] = new int*[2];
    res_loc[1] = new int*[2];
    res_loc[0][0] = new int[10001];
    res_loc[0][1] = new int[10001];
    res_loc[1][0] = new int[10001];
    res_loc[1][1] = new int[10001];
    res = new int**[2];
    res[0] = new int*[n]; 
    res[1] = new int*[n];
    for (i = 0; i < n; i++) {
        res[0][i] = new int[n];
        res[1][i] = new int[n];
        for (j = 0; j < n; j++) {
            in >> res[0][i][j];
            if (res[0][i][j] == 0)
                res_loc[0][res[0][i][j]][size_a_next++] = i * n + j;
            else
                res_loc[0][res[0][i][j]][size_b_next++] = i * n + j;
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
        size_a = size_a_next;
        size_b = size_b_next;
        size_a_next = 0;
        size_b_next = 0;
        
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                costij = minim_optim (i, j, 1 - res[tm][i][j], tm);
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
                    res_loc[1 - tm][0][size_a_next++] = i * n + j;
                else
                    res_loc[1 - tm][1][size_b_next++] = i * n + j;
               
                if (res[1 - tm][i][j] == 0)
                    count_a++;
                else
                    count_b++;
                if (res[1 - tm][i][j] == 0 && price[1 - tm][i][j] > Pmax_a) {
                    Pmax_a = price[1 - tm][i][j];
                }
                if (res[1 - tm][i][j] == 1 && price[1 - tm][i][j] > Pmax_b) {
                    Pmax_b = price[1 - tm][i][j];
                }

            }

        }
    }
    return 0;
}
