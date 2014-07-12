CC=gcc
LEX=flex
LDFLAGS=-lfl
CFLAGS=-c -g -Wall
OFLAGS=-o $@
RM=rm

mtx: mtx.o
	$(CC) -o $@ $^ $(LDFLAGS)

mtx.o: mtx.c
	$(CC) $(CFLAGS) -o $@ $<

mtx.c: mtx.l
	$(LEX) -o $@ $<

.PHONY: clean

clean: 
	rm *~ *.o mtx.c
