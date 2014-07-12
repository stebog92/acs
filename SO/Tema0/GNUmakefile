CC = gcc
CFLAGS = -Wall -g
EXE = tema0

build: main.o hash.o hashtable.o
	$(CC) $(CFLAGS) -o $(EXE) $^


hashtable.o : hashtable.c
main.o : main.c
hash.o : hash.c

.PHONY : clean
clean:
	rm -f $(EXE) *.o
    
