#include <mpi.h>
#include <stdio.h>
#include <vector>
#include <string.h>
#include <queue>
#include <utility>
#include <stdlib.h>
#include <time.h>
#define SONDAJ (-1)

using namespace std;

// trimite mesaje pentru un nod pe baza tabelei de rutare
void send_message(char* msg, int rank, int  *routing_table, int dest) {
    char buffer[1024];
    memset(buffer, 0, 1024 * sizeof(char));
    sprintf(buffer, "%d %d %s\n", rank, dest, msg);
    //printf ("%s to dest %d\n", buffer, routing_table[dest]);
    MPI_Send(buffer, strlen(buffer), MPI_CHAR, routing_table[dest], rank, MPI_COMM_WORLD);
}
// trimite mesaje de broadcast
void send_message_broadcast(char* msg, int rank, int *routing_table, int _size) {
    char buffer[1024];
    memset(buffer, 0, 1024 * sizeof(char));
    for (int j = 0; j < _size; j++) {
        if (j != rank) {
            //printf("sending  %s\n", msg);
            memset(buffer, 0, 1024 * sizeof(char));
            sprintf(buffer, "%d %d %s\n", rank, j, msg);
            //printf("sending  %s\n", buffer);

            MPI_Send(buffer, strlen(buffer), MPI_CHAR, routing_table[j], rank, MPI_COMM_WORLD);
        }
   }
}
//mesaje de inchidere a comunicatiei
void quit (int rank, int* routing_table, int *distances, int _size) {
    char *end = new char[10];
    for (int i = _size + 1; i >= 1; i--) {
        for (int j = 0; j < _size; j++) {
            if (distances[j] == i) {
                memset(end, 0, 10  * sizeof(char));
                sprintf(end, "%d %d %s\n", rank, j, "END");
                MPI_Send (end, 10, MPI_CHAR, routing_table[j], rank, MPI_COMM_WORLD);
            }
        }
    }
}

int main (int argc, char** argv) {
    
    int rank, _size; 
    MPI_Init(&argc, &argv);
    
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &_size);
    int *distances;
    MPI_Status status;
    MPI_Request request;
    char* buffer = new char[1024];
    char *data;
    int node;
    size_t size;
    int offset, _rank;
    FILE* file = fopen (argv[1], "r");

    int *routing_table;
    //citire lista de adiacenta
    do {
        getline(&buffer, &size, file);
        sscanf (buffer, "%d%n: ", &node, &offset);
    } while (node != rank);
    vector<int> nlist;
    data = buffer;
    data += offset + 1;
    while (sscanf(data, "%d %n", &node, &offset) == 1) {
        nlist.push_back(node);
        data += offset;
    }
    delete buffer;
    fclose (file);

    // nodul 0 initiaza algoritmul de stabilire a topologiei folosind
    // mesaje de sondaj cu ecou

    if (rank == 0) {
        int *_top = new int[_size * _size];
        memset (_top, 0, _size * _size * sizeof(int));
        int *top = new int[_size * _size];
        for (int i = 0; i < nlist.size(); i++) {
            _top[rank * _size + nlist[i]] = 1;
            _top[nlist[i] * _size + rank] = 1;
        }

        for (int i = 0; i < nlist.size(); i++) {
            int ms = SONDAJ;
            MPI_Send(&ms, 1, MPI_INT, nlist[i], rank, MPI_COMM_WORLD);
        }

        for (int i = 0; i < nlist.size(); i++) {
            MPI_Recv(top, _size * _size, MPI_INT, nlist[i], nlist[i], MPI_COMM_WORLD, &status);
            for (int j = 0; j < _size; j++) {
                for (int k = 0; k < _size; k++) {
                    _top[j * _size + k] |= top[j * _size + k];
                }
            }
        }
        for (int i = 0; i < nlist.size(); i++) {
            MPI_Send(_top, _size * _size, MPI_INT, nlist[i], rank, MPI_COMM_WORLD);
        }
        /*for (int i = 0; i < _size; i++) {
            for (int j = 0; j < _size; j++) {
                printf("%d ", _top[i * _size + j]);
            }
            printf("\n");
        }*/


        //creaza tabela de rutare folosing bfs
        routing_table = new int[_size];
        distances = new int[_size];
        routing_table[rank] = rank;
        queue<int> q;
        distances[rank] = 0;
        int *visited = new int[_size];
        memset(visited, 0, _size * sizeof(int));
        q.push(rank);
        visited[rank] = 1;
        while(!q.empty()) {
            int v = q.front();
            q.pop();
            for (int i = 0; i < _size; i++) {
                if (_top[v * _size + i] == 1) {
                    if (visited[i] == 0) {
                        q.push(i);
                        visited[i] = 1;
                        if (v == rank) {
                            routing_table[i] = i;
                        }
                        else {
                            routing_table[i] = routing_table[v];
                        }
                        distances[i] = distances[v] + 1;
                    }
                }
            }
        }
    }

    //nodurile vor astepta mesaje de sondaj, vor trimite la randul lor 
    // mesaje de sondaj, dupa care vor construi topologia partiala pe 
    // baza rezultatelor primite si vor propaga catre nodul initiator
    if (rank != 0) {
        int ms;
        int *part_top = new int[_size * _size];
        memset (part_top, 0, _size * _size * sizeof(int));
        int *ntop = new int[_size * _size];
        for (int i = 0; i < nlist.size(); i++) {
            part_top[rank * _size + nlist[i]] = 1;
            part_top[nlist[i] * _size + rank] = 1;
        }
        MPI_Recv(&ms, 1, MPI_INT, MPI_ANY_SOURCE, MPI_ANY_TAG, MPI_COMM_WORLD, &status);
        int source = status.MPI_SOURCE;
        //printf ("%d %d\n", source, rank);
        part_top[rank * _size + source] = 1;
        part_top[source * _size + rank] = 1;
        for (int i = 0; i < nlist.size(); i++) {
            if (nlist[i] != source) {
                MPI_Send(&ms, 1, MPI_INT, nlist[i], rank, MPI_COMM_WORLD);
            }
        }
        for (int i = 0; i < nlist.size(); i++) {
            if (nlist[i] != source) {
                MPI_Recv(ntop, _size *_size, MPI_INT, nlist[i], nlist[i], MPI_COMM_WORLD, &status);
                if (ntop[0] != SONDAJ) {
                    for (int j = 0; j < _size; j++) {
                        for (int k = 0; k < _size; k++) {
                            part_top[j * _size + k] |= ntop[j * _size + k];
                        }
                    }
                }
            }
        }
        // trimit topologia catre nodul initiator
        MPI_Send (part_top, _size * _size, MPI_INT, source, rank, MPI_COMM_WORLD);
        
        // astept topologia completa
        MPI_Recv (part_top, _size * _size, MPI_INT, MPI_ANY_SOURCE, MPI_ANY_TAG, MPI_COMM_WORLD, &status);
        _rank = 1;

        //trimit mai departe topologia completa
        for (int i = 0; i < nlist.size(); i++) {
            if (nlist[i] != source) {
                MPI_Send (part_top, _size*_size, MPI_INT, nlist[i], rank, MPI_COMM_WORLD);
            }
        }
        
        // stabilirea tabelei de rutare
        routing_table = new int[_size];
        distances = new int[_size];
        routing_table[rank] = rank;
        queue<int> q;
        int *visited = new int[_size];
        memset(visited, 0, _size * sizeof(int));
        q.push(rank);
        distances[rank] = 0;
        visited[rank] = 1;
        while(!q.empty()) {
            int v = q.front();
            q.pop();
            for (int i = 0; i < _size; i++) {
                if (part_top[v * _size + i] == 1) {
                    if (visited[i] == 0) {
                        q.push(i);
                        visited[i] = 1;
                        if (v == rank) {
                            routing_table[i] = i;
                        }
                        else {
                            routing_table[i] = routing_table[v];
                        }
                        distances[i] = distances[v] + 1;
                    }
                }
            }
        }
    }
    MPI_Barrier(MPI_COMM_WORLD);
    //afisarea tabelelor de rutare
    _rank = 0;
    while (_rank < _size) {
        if (rank == _rank) {
            printf ("Routing table pentru buncarul %d: ", rank);
            for (int i = 0; i < _size; i++) {
                printf ("%d ", routing_table[i]);
            }
            printf("\n");
        }
        _rank ++;
        MPI_Barrier (MPI_COMM_WORLD);
    }

    bool listening_mode;
    int msg_total, node_src, node_dest;
    char* msg = new char[1024];
    vector<pair<int, char*> > messages;
    buffer = new char[1024];
    // citirea mesajelor de trimis
    file = fopen(argv[2], "r");
    fscanf(file, "%d", &msg_total);
    for (int i = 0; i < msg_total; i++) {
        fscanf(file, "%d %[^\n]s", &node_src, msg);
        offset = 1;
        if (msg[0] == 'B') {
            node_dest = -1;
        }
        else {
            sscanf(msg, "%d%n", &node_dest, &offset);
        }
        if (node_src == rank) {
            messages.push_back(make_pair (node_dest, msg + offset + 1));
            //printf("%d %s\n", node_dest, msg + offset + 1);
            msg = new char[1024];
        }
    }
    for (int i = 0; i < messages.size(); i++) {
        printf("%d %d %s\n", rank, messages[i].first, messages[i].second);
    }
    _rank = 0;
    MPI_Barrier(MPI_COMM_WORLD);
    while (_rank < _size) {
        if (_rank == rank) {
            for (int i = 0; i < messages.size(); i++) {
                if (messages[i].first == -1) {
                    send_message_broadcast(messages[i].second, rank, routing_table, _size);
                }
                else {
                    //printf ("%s\n", messages[i].second);
                    send_message(messages[i].second, rank, routing_table, messages[i].first);
                }
            }
            quit (rank, routing_table, distances, _size);
        } else {
            listening_mode = true;
            while (listening_mode) {
                memset(buffer, 0, 1024 * sizeof(char));
                MPI_Recv(buffer, 1024, MPI_CHAR, MPI_ANY_SOURCE, MPI_ANY_TAG, MPI_COMM_WORLD, &status);
                memset(msg, 0, 1024 * sizeof(char));
                sscanf (buffer, "%d %d %[^\n]s", &node_src, &node_dest, msg);
                if (node_dest == rank) {
                    if (strcmp (msg, "END") == 0) {
                        listening_mode = false;
                    } else {
                        if (strlen (msg) != 0) {
                            printf ("Buncar %d : Mesaj primit de la nodul sursa %d ajuns la destinatie %d : %s\n", rank, node_src, node_dest, msg);
                        }
                    }
                } else {
                    if (strlen (msg) != 0) {
                        if (strcmp(msg, "END") != 0) {
                            printf ("Buncar %d : Mesaj primit de la %d pentru buncarul %d si trimis spre %d : %s\n", rank, node_src, node_dest,
                            routing_table[node_dest], msg);
                        }
                        MPI_Send(buffer, strlen(buffer), MPI_CHAR, routing_table[node_dest], rank, MPI_COMM_WORLD);
                    }
                }
            }
        }
        _rank++;
    }

    MPI_Barrier(MPI_COMM_WORLD);
    int *votes_lider = new int[_size];
    int *votes_adj = new int[_size];
    memset(votes_lider, 0, sizeof(int)*_size);
    memset(votes_adj, 0, sizeof(int)*_size);

    bool lider_choosed = false, adj_choosed = false;
    //stabilirea liderului si a adj
    vector <char*> results;
    while (!lider_choosed || !adj_choosed) {

        srand(rank * time(NULL)); 
        int lider = rand()%_size;
        int adj = rand()%_size;

        while (lider == rank) {
            lider = rand()%_size;
        }
        while (adj == rank || adj == lider) {
            adj = rand()%_size;
        }
        if (rank == 0) {
            votes_lider[lider]++;
            votes_adj[adj]++;
        }
        MPI_Barrier(MPI_COMM_WORLD);
        results.clear();
        if (rank == 0) {
            bool duplicate = false, duplicate_adj = false;
            int m_lider = 0, m_adj = 0;
            for (int i = 0; i < _size; i++) {

                if (m_lider < votes_lider[i]) {
                    lider = i;
                    m_lider = votes_lider[i];
                    duplicate = false;
                } else if (m_lider == votes_lider[i]) {
                    duplicate = true;
                }
                if (m_adj < votes_adj[i]) {
                    m_adj = votes_adj[i];
                    adj = i;
                    duplicate_adj = false;
                } else if (m_adj == votes_adj[i]) {
                    duplicate_adj = true;
                }
            }
            /*printf ("res\n");
            for (int i = 0; i < _size; i++) {
                printf("%d ", votes_lider[i]);
            }*/
            buffer = new char[1024];
            if (duplicate || m_lider == 1) {
                sprintf(buffer, "REVOTE lider");
                results.push_back(buffer);
            } else {
                sprintf (buffer, "VOTED lider %d", lider);
                if (!lider_choosed) {
                    results.push_back(buffer);
                    lider_choosed = true;
                }
            }
            buffer = new char[1024];
            if (duplicate_adj || m_adj == 1) {
                sprintf(buffer, "REVOTE adj");
                results.push_back(buffer);
            } else {
                sprintf (buffer, "VOTED adj %d", adj);
                if (!adj_choosed) {
                    results.push_back(buffer);
                    adj_choosed = true;
                }
            }
        memset(votes_lider, 0, sizeof(int)*_size);
        memset(votes_adj, 0, sizeof(int)*_size);
        } 
        MPI_Barrier(MPI_COMM_WORLD);

        _rank = 0;
        while (_rank < _size) {
            if (_rank == rank) {
                if (rank == 0) {
                    send_message_broadcast (results[0], rank, routing_table, _size);
                    send_message_broadcast (results[1], rank, routing_table, _size);
                    quit (rank, routing_table, distances, _size);
                }
                else {
                    for (int i = 0; i < results.size(); i++) {
                        //printf ("%s\n",results[i]); 
                        send_message (results[i], rank, routing_table, 0);
                    }
                    quit (rank, routing_table, distances, _size);
                }
            } else {
                listening_mode = true;
                while (listening_mode) {
                    MPI_Recv(buffer, 1024, MPI_CHAR, MPI_ANY_SOURCE, MPI_ANY_TAG, MPI_COMM_WORLD, &status);
                    sscanf (buffer, "%d %d %[^\n]s", &node_src, &node_dest, msg);
                    if (node_dest == rank) {
                        if (strcmp (msg, "END") == 0) {
                            listening_mode = false;
                        } else {
                           if (strstr(msg, "VOTED lider") != NULL) {
                                sscanf(msg, "VOTED lider %d", &lider);
                                printf ("Buncar %d : Am primit rezultatul voturilor : lider %d\n", rank, lider);
                                lider_choosed = true;
                            } else
                            if (strstr(msg, "VOTED adj") != NULL) {
                                sscanf(msg, "VOTED adj %d", &adj);
                                printf ("Buncar %d : Am primit rezultatul voturilor : adj %d\n", rank, adj);
                                adj_choosed = true;
                            } else
                            if (strstr(msg, "REVOTE lider") != NULL) {
                                sprintf(buffer, "lider %d", lider);
                                results.push_back(buffer);
                                buffer = new char[1024];
                            } else
                            if (strstr(msg, "REVOTE adj") != NULL) {
                                sprintf(buffer, "adj %d", adj);
                                results.push_back(buffer);
                                buffer = new char[1024];
                            } else
                            if (strstr(msg, "lider ") != NULL) {
                                sscanf(msg, "lider %d", &lider);
                                votes_lider[lider]++;
                                //printf ("%s\n", msg);
                            } else
                            if (strstr(msg, "adj ") != NULL) {
                                sscanf(msg, "adj %d", &adj);
                                votes_adj[adj]++;
                                //printf ("%s\n", msg);
                            }
                        }
                    } else {
                        MPI_Send(buffer, strlen(buffer), MPI_CHAR, routing_table[node_dest], rank, MPI_COMM_WORLD);
                    }
                }
            }
            _rank++;
        }
    }

    MPI_Finalize();
    return 0;
}

