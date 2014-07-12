CC=gcc
CFLAGS= -Wall -g
LIBS = -lcblas -latlas
BLAS_SOURCE=blas.c
SOURCE = main.c
LPATH = /opt/tools/libraries/atlas

all:blas-quad blas-nehalem blas-opteron blas-my optimized-O2

blas-nehalem: $(BLAS_SOURCE)
	$(CC) $(CFLAGS) $(BLAS_SOURCE) -o $@ $(LIBS) -L$(LPATH)/3.10.1-nehalem-gcc-4.4.6/lib -I$(LPATH)/3.10.1-nehalem-gcc-4.4.6/include
 
blas-opteron: $(BLAS_SOURCE)
	$(CC) $(CFLAGS) $(BLAS_SOURCE) -o $@ $(LIBS) -L$(LPATH)/3.10.1-opteron-gcc-4.4.6/lib -I$(LPATH)/3.10.1-opteron-gcc-4.4.6/include

blas-quad: $(BLAS_SOURCE)
	$(CC) $(CFLAGS) $(BLAS_SOURCE) -o $@ $(LIBS) -L$(LPATH)/3.10.1-quad-gcc-4.4.6/lib -I$(LPATH)/3.10.1-quad-gcc-4.4.6/include

blas-my: $(SOURCE)
	$(CC) $(CFLAGS) $(SOURCE) -o $@

optimized-O2: $(SOURCE)
	$(CC) $(CFLAGS) -O2 $(SOURCE) -o $@

clean:
	rm -f blas-*
cleanf:
	rm quad nehalem opteron my-*
	rm -r out
