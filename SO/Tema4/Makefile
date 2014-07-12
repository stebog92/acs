CC=gcc
CFLAGS= -Wall -g

build: libscheduler.so


libscheduler.so: so_scheduler.o
	$(CC) $(CFLAGS) $^ -shared -o $@ -lpthread -lrt

so_scheduler.o: so_scheduler.c
	$(CC) $(CFLAGS) -fPIC -c so_scheduler.c -o $@

clean:
	rm *.o *.so
