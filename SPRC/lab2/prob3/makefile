#/**
#	Sisteme de programe pentru retele de calculatoare
#	
#	Copyright (C) 2008 Ciprian Dobre & Florin Pop
#	Univerity Politehnica of Bucharest, Romania
#
#	This program is free software; you can redistribute it and/or modify
#	it under the terms of the GNU General Public License as published by
#   the Free Software Foundation; either version 2 of the License, or
#   (at your option) any later version.

#   This program is distributed in the hope that it will be useful,
#   but WITHOUT ANY WARRANTY; without even the implied warranty of
#   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#   GNU General Public License for more details.
# */

build:
	rpcgen -C shell.x
	gcc -o server rpc_server.c shell_svc.c shell_xdr.c -lnsl -Wall
	gcc -o client rpc_client.c shell_clnt.c shell_xdr.c -lnsl -Wall

clean:
	rm -f client server shell.h shell_svc.c shell_clnt.c shell_xdr.c
