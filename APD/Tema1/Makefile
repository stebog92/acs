CC=g++
CFLAGS=-fopenmp
SERIAL=serial.cpp
PARALLEL=parallel.cpp
PARALLEL_E=parallel
SERIAL_E=serial

all: $(SERIAL_E) $(PARALLEL_E)

$(SERIAL_E):
	$(CC) $(CFLAGS) $(SERIAL) -o $(SERIAL_E)

$(PARALLEL_E):
	$(CC) $(CFLAGS) $(PARALLEL) -o $(PARALLEL_E)
clean:
	rm -rf $(PARALLEL_E) $(SERIAL_E)
