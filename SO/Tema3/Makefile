CC=gcc
CFLAGS= -Wall -g
SOURCE = vmsim.c

build: libvmsim.so

libvmsim.so: vmsim.o common_lin.o
	$(CC) $(CFLAGS) -shared vmsim.o common_lin.o -o $@

vmsim.o: $(SOURCE)
	$(CC) $(CFLAGS) -fPIC -c $(SOURCE) -o $@

common_lin.o: common_lin.c
	$(CC) $(CFLAGS) -fPIC -c common_lin.c -o $@

clean:
	rm libvmsim.so vmsim.o common_lin.o
