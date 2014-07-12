CC = gcc
CFLAGS = -Wall -g

build: libsompi.so mpirun

mpirun: main.c mpi.c
	$(CC) $(CFLAGS) main.c -o $@ -lrt -lsompi -L.

libsompi.so: mpi.o
	$(CC) $(CFLAGS) -shared mpi.o -o $@ -lrt -pthread

mpi.o: mpi.c
	$(CC) -g -fPIC -c mpi.c

clean:
	rm -f libsompi.so mpirun *.o
cleans:
	rm  /dev/shm/sem.* /dev/shm/SHM_MPI /dev/mqueue/*
