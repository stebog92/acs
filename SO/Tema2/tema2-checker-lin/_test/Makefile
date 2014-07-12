CC = gcc
CFLAGS = -Wall -g -I..
LDFLAGS = -L..
LDLIBS = -lsompi
OUT = bin
SRC = test_mpirun.c test_simple.c test_double_init.c test_double_finalize.c \
      test_before_after.c test_send_recv.c test_send_recv_array.c test_ring.c \
      test_matrix_multiply.c
TESTS = $(patsubst %.c,%,$(addprefix $(OUT)/,$(SRC)))

tests: $(OUT) $(TESTS)

$(OUT):
	mkdir $(OUT)

bin/%: %.c check.h
	$(CC) $(CFLAGS) $(LDFLAGS) -o $@ $^ $(LDLIBS)

.PHONY: clean
clean:
	-rm -rf $(OUT)
