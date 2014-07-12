CC=gcc
CFLAGS= -Wall -g
LIBS = -lcblas -latlas
SOURCE = blas.c

all:blas-quad blas-nehalem blas-opteron blas-my

blas-nehalem: $(SOURCE)
	$(CC) $(CFLAGS) $(SOURCE) -o $@ $(LIBS) -L/opt/tools/libraries/atlas/3.10.1-nehalem-gcc-4.4.6/lib

blas-opteron: $(SOURCE)
	$(CC) $(CFLAGS) $(SOURCE) -o $@ $(LIBS) -L/opt/tools/libraries/atlas/3.10.1-opteron-gcc-4.4.6/lib/

blas-quad: $(SOURCE)
	$(CC) $(CFLAGS) $(SOURCE) -o $@ $(LIBS) -L/opt/tools/libraries/atlas/3.10.1-quad-gcc-4.4.6/lib/

blas-my: main.c
	$(CC) $(CFLAGS) main.c -o $@

clean:
	rm -f blas-*
cleanf:
	rm quad nehalem opteron my-*
	rm -r out
