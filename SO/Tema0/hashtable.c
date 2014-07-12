/* 
 * Ciocan Mihai
 * 334CA
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "hashtable.h"
#include "hash.h"

/* aloca memorie pentru buckets in hashtable 
 *
 * size - dimensiunea tabelei
 */
bucket *create_hashtable (int size)
{
    int i;
    bucket *new_hashtable = (bucket*) calloc (size, sizeof(bucket));
    for (i = 0; i < size; i++) {
        new_hashtable[i].last = 0;
        new_hashtable[i].size = 0;
        new_hashtable[i].words = NULL;
    }
    return new_hashtable;
}

/* adauga cuvant in hashtable in caz ca nu exista deja */
void add_word (bucket *bkt, char *word)
{
    if (bkt->size == 0) {
        bkt->words = (char**) calloc (5, sizeof(char*));
        bkt->size = 5;
    }

    if (find_word (word, *bkt)) {
        return;
    }
    if (bkt->last >= bkt->size) {
        bkt->size *= 2;
        bkt->words = (char**) realloc (bkt->words, bkt->size * sizeof(char*));
    }
    bkt->words[bkt->last] = strdup (word);
    bkt->last += 1;
}

/* sterge cuvant din hashtable */
void remove_word (bucket *bkt, char *word)
{
    int i;
    for (i = 0; i < bkt->last; i++) {
        if (strcmp(word, bkt->words[i]) == 0) {
            free(bkt->words[i]);
            for(; i < bkt->last - 1; i++) {
                bkt->words[i] = bkt->words[i + 1];
            }
            bkt->last --;
            break;
        }
    }
}

/* goleste tabela */
void clear (hashtable ht)
{
    int j;
    unsigned int i;
    for (i = 0; i < ht.size; i++) {
        for (j = 0; j < ht.buckets[i].last; j++) {
            free(ht.buckets[i].words[j]);
        }
        ht.buckets[i].last = 0;
    }
}

/* cauta cuvantul si afiseaza True sau False
 * daca exista in tabela sau nu
 */
int find_word (char *word, bucket bkt)
{
    int i;
    for (i = 0; i < bkt.last; i++) {
        if (strcmp(word, bkt.words[i]) == 0) {
            return 1;
        }
    }
    return 0;
}

/* printeaza bucketul respectiv */
void print_bucket (bucket bkt, FILE *output)
{
    int i = 0;
    for (i = 0; i < bkt.last - 1; i++) {
        fprintf (output, "%s ", bkt.words[i]);
    }
    if (i < bkt.last) {
        fprintf (output, "%s\n", bkt.words[i]);
    }
}

/* printeaza intreaga tabela */
void print_hash (hashtable ht, FILE *output)
{
    unsigned int j;
    for (j = 0; j < ht.size; j++) {
        print_bucket(ht.buckets[j], output);
    }
}

/* redimensioneaza tabela 
 * double - dubleaza dimensiunea
 * halve - injumatateste dimensiunea
 */
void resize_hashtable (hashtable *ht, int new_size)
{
    int j;
    unsigned int i;
    bucket *new_buckets = create_hashtable(new_size);
    for (i = 0; i < ht->size; i++) {
        for (j = 0; j < ht->buckets[i].last; j++) {
            char *word = ht->buckets[i].words[j];
            add_word(&new_buckets[hash(word, new_size)], word);
        }
    }
    delete_hashtable(ht);
    ht->size = new_size;
    ht->buckets =  new_buckets;
}

/* sterge tabela - eliberarea memoriei */
void delete_hashtable (hashtable *ht)
{
    int j;
    unsigned int i;
    for (i = 0; i < ht->size; i++) {
        for (j = 0; j < ht->buckets[i].last; j++) {
            free(ht->buckets[i].words[j]);
        }
        free(ht->buckets[i].words);
    }
    free(ht->buckets);
}
