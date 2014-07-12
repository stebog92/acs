CC=g++
CCFLAGS=-Wall -g
SRV=server
CLT=client
LIBSOCKET= -lnsl

all: $(SRV) $(CLT) 

$(SRV): $(SRV).cpp
	$(CC) -o $(SRV) $(CCFLAGS) $(LIBSOCKET) $(SRV).cpp

$(CLT) :$(CLT).cpp
	$(CC) -o $(CLT) $(CCFLAGS) $(LIBSOCKET) $(CLT).cpp

clean :
	rm -f *.o
	rm -f $(SRV) $(CLT) 

