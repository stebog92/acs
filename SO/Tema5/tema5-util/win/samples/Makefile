CPPFLAGS = /DDEBUG /DLOG_LEVEL=LOG_DEBUG /I. /I.. /I../..
CFLAGS = /nologo /W3 /EHsc $(CPPFLAGS)
LIBS = wsock32.lib mswsock.lib ws2_32.lib
LINK = link

build: all

all: iocp_echo_server.exe http_reply_once.exe

iocp_echo_server.exe: iocp_echo_server.obj ../sock_util.obj
	$(LINK) /nologo /out:$@ $** $(LIBS)

iocp_echo_server.obj: iocp_echo_server.c ../sock_util.h ../../debug.h ../../util.h

http_reply_once.exe: http_reply_once.obj ../sock_util.obj ../../http-parser/http_parser.obj
	$(LINK) /nologo /out:$@ $** $(LIBS)

http_reply_once.obj: http_reply_once.c ../sock_util.h ../../debug.h ../../util.h

../sock_util.obj: ../sock_util.c ../sock_util.h ../../debug.h ../../util.h

../../http-parser/http_parser.obj: ../../http-parser/http_parser.c ../../http-parser/http_parser.h

clean:
	del /Q /S *.obj
	del /Q /S *~
	del /Q /S epoll_echo_server.exe http_reply_once.exe
