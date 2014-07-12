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
	rpcgen rshell.x
	gcc -o rserver -g rserver.c rshell_svc.c rshell_xdr.c -lnsl
	gcc -o rclient -g rclient.c rshell_clnt.c rshell_xdr.c -lnsl

run:
	(sudo ./rserver &) && sleep 2 && ./rclient localhost

clean:
	rm -f rclient rserver rshell.h rshell_svc.c rshell_clnt.c rshell_xdr.c
