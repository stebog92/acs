CC=g++
CFLAGS=-g -Wall
SOURCES=client_stub_lin_v1-4.cpp bkg_strategy.cpp

all: client

client: $(SOURCES)
	$(CC) $(CFLAGS) $(SOURCES) -o client
run :
	./client 127.0.0.1 6666 8 8

clean:
	rm -f client
