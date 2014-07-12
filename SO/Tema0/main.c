/*
 * Ciocan Mihai
 * 334CA
 */

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include "hash.h"
#include "hashtable.h"

hashtable hash_table;
FILE *commands;



/* proceseaza comenzile primite */
void process_commands()
{
    char *command, *word, *output_file;
    char line[20005];
    FILE *output;
    int bucket_id;

    while (fgets(line, 20000, commands) != NULL) {
        command = strtok (line, " \n");

        /* ignora liniile albe */
        if (command == NULL) {
            continue;
        } else if (strcmp(command, "add") == 0) {
            /* adauga cuvant in hashtable */
            word = strtok (NULL, " \n");
            bucket_id = hash(word, hash_table.size);
            add_word(&hash_table.buckets[bucket_id], word);

        } else if (strcmp(command, "remove") == 0) {
            /* sterge cuvant din tabela */
            word = strtok(NULL, " \n");
            bucket_id = hash(word, hash_table.size);
            remove_word(&hash_table.buckets[bucket_id], word);

        } else if (strcmp (command, "clear") == 0) {
            /* goleste tabela */
            clear(hash_table);

        } else if (strcmp (command, "find") == 0) {
            /* cauta cuvant in tabela */
            word = strtok (NULL, " \n");
            output_file = strtok (NULL, " \n");

            if (output_file != NULL) {
                output = fopen (output_file, "a");
            }
            else {
                output = stdout;
            }
            bucket_id = hash(word, hash_table.size);
            /* 
             * afiseaza True/False in fisierul de iesire daca 
             * acesta se afla sau nu in tabela.
             */
            if (find_word(word, hash_table.buckets[bucket_id])) {
                fprintf (output, "True\n");
            }
            else {
                fprintf (output, "False\n");
            }

            if (output_file != NULL) {
                fclose(output);
            }
        } else if (strcmp(command, "print_bucket") == 0) {
            /* afiseaza cuvintele din bucket-ul respectiv */
            int bucket = atoi(strtok (NULL, " \n"));
            output_file = strtok(NULL, " \n");

            if (output_file == NULL) {
                output = stdout;
            }
            else {
                output = fopen(output_file, "a");
            }
            print_bucket(hash_table.buckets[bucket], output);
            if (output_file != NULL) {
                fclose(output);
            }
        } else if (strcmp(command, "print") == 0) {
            /* afiseaza tabela intreaga */
            output_file = strtok(NULL, " \n");

            if (output_file != NULL) {
                output = fopen(output_file, "a");
            } else {
                output = stdout;
            }
            print_hash(hash_table, output);
            if (output_file != NULL) {
                fclose(output);
            }
        } else if (strcmp(command, "resize") == 0) {
            /*
             * modifica dimensiunea tabelei in functie
             * de parametrii double (dublare) sau 
             * halve (injumatatire)
             */
            int new_size;
            word = strtok(NULL, " \n");
            if (strcmp(word, "double") == 0) {
                new_size = hash_table.size * 2;
            }
            else if (strcmp(word, "halve") == 0) {
                new_size = hash_table.size / 2;
            }
            resize_hashtable(&hash_table, new_size);
        }
    }
}

int main (int argc, char** argv)
{
    int i;
    if (argc < 2) {
        fprintf (stderr, "Usage: ./tema0 [ SIZE ] [FILE] ...\n");
        exit (EXIT_FAILURE);
    }

    hash_table.size = atoi(argv[1]);
    if (hash_table.size == 0) {
        fprintf (stderr, "Usage: ./tema0 [ SIZE ] [FILE] ...\n");
        exit (EXIT_FAILURE);
    }

    hash_table.buckets = create_hashtable (hash_table.size);

    /* procesare comenzi din fisierele de intrare */
    if (argc > 2) {
        for (i = 2; i < argc; i++) {
            commands = fopen(argv[i], "r");
            if (commands == NULL) {
                delete_hashtable (&hash_table);
                fprintf (stderr, "%s: No such file or directory\n", argv[i]);
                exit (EXIT_FAILURE);
            }
            process_commands();
            fclose(commands);
        }
    }
    else {
        commands = stdin;
        process_commands();
    }
    /* stergere tabela */
    delete_hashtable(&hash_table);
    exit (EXIT_SUCCESS);
}
