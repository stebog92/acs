/*
 * Ciocan Mihai
 * 334CA
 */
   
#pragma once

typedef struct _bucket {
    char **words;
    int size;
    int last;
} bucket;

typedef struct _hashtable {
    unsigned int size;
    bucket *buckets;
} hashtable;

bucket *create_hashtable (int);
void add_word (bucket*, char*);
void remove_word (bucket*, char*);
void clear (hashtable);
int find_word (char*, bucket);
void print_hash (hashtable, FILE*);
void print_bucket (bucket , FILE*);
void resize_hashtable (hashtable*, int);
void delete_hashtable (hashtable*);
