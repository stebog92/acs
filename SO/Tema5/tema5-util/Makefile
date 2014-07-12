CPPFLAGS = -DDEBUG -DLOG_LEVEL=LOG_DEBUG -I. -I./lin/. -I./http-parser/.
CFLAGS = -Wall -g

.PHONY: clean

build: aws

aws: aws.o lin/sock_util.o http-parser/http_parser.o

lin/sock_util.o: lin/sock_util.c lin/sock_util.h debug.h util.h

http-parser/http_parser.o: http-parser/http_parser.c http-parser/http_parser.h
	make -C http_parser/ http_parser.o

clean:
	-rm -f *~
	-rm -f *.o
