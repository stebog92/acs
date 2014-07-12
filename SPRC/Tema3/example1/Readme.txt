/**
	Sisteme de programe pentru retele de calculatoare
	
	Copyright (C) 2008 Ciprian Dobre & Florin Pop
	Univerity Politehnica of Bucharest, Romania

	This program is free software; you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
 */

Capitolul V. Securitate in Java.

Listing 1. Client/server TLS. 
 
Listingul prezinta un exemplu de aplicatie client server ce foloseste securizarea comunicatiei prin intermediul protocolului TLS.
Clientul realizeaza conectarea la aplicatia server si trimite comenzi si asteapta raspunsuri.
Serverul asculta cereri de conexiuni TLS pe un port furnizat ca parametru de intrare. 
Pentru fiecare noua conexiune stabilita serverul creaza un nou thread ce asteapta cereri de comenzi si trimite
inapoi raspunri - simulare comportament utila pentru testarea comunicatiei corecte intre client si server.
Aplicatia demonstreaza si posibilitatea de interceptare a certificatelor folosite in handshake-ul comunicatiei.


Pentru rulare:
'ant server' - pentru lansarea in executie a serverului (se creaza si un keystore)
'ant client' - pentru lansarea in executie a clientului

'ant clean' - sterge fisierele create


