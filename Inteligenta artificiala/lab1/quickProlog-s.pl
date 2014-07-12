% quick Prolog excercises;
% @author Andrei Olaru, Tudor Berariu
%

todo. % dummy predicate.

%
%
% ======== INTRO
%
% tips SWI_Prolog in Windows:
%
% Deschiderea unui fisier Prolog (.pl) deschide consola Prolog.
% File -> Edit fisier.pl deschide editorul Prolog pentru fisier.
%
% activarea editorului prolog (daca nu functioneaza implicit):
%   Settings -> User init file -> [ OK -> ] decomentare linia
%     :- set_prolog_flag(editor, pce_emacs).
%
% Compilare din editor
%   (echivalent comanda in prolog File -> Reload : Modified Files)
%   Compile -> Compile buffer (sau Ctrl-c Ctrl-b)
%
% Help prolog: Help -> Online manual sau help -> Help (depinde de
% versiune)

% ========= PREDICATE SIMPLE

p(a). % litera mica sau numar -> simbol constant
p(b). % orice declaratie sau interogare se termina cu .
p(c).
p(5).

% EXECUTATI LA CONSOLA:
% p(a).
% p(x).
% p(X). % litera mare -> variabila
% Folositi punct si virgula ";" pentru defilarea rezultatelor si
% punct "." pentru terminarea interogarii


% ============== EGALITATE

% EXECUTATI LA CONSOLA:
% 'azi'=azi. % literal (permite spatiu) vs literal
% "azi"=azi. % string vs literal
% '2'=2.     % literal vs numar
% Azi='azi'. % unificare variabila la literal
% azi=Azi.   % unificare literal la variabila
% 2=Azi.     % unificare numar la variabila
% Azi==azi.  % testare la posibilitatea unificarii
% Azi=azi,Azi==azi.
% 2\='2'     % testare la imposibilitatea unif.

% ================= LEGAREA VARIABILELOR

orizontala(linie(punct(_,Y),punct(_,Y))).
verticala(linie(punct(X,_),punct(X,_))).

% EXECUTATI:
% orizontala(linie(punct(2,3),P)).
% verticala(linie(punct(2,3),P)).
% orizontala(linie(punct(1,1),P)),verticala(linie(punct(2,2),P)).

% ==================== PREDICATE RECURSIVE

da :- da.

% EXECUTATI:
% da. % utilizati Ctrl-c si apasati "h" pentru instructiuni.

% EXECUTATI:
% trace.
% da.
% notrace.

% ===================== PREDICATE CU CONDITII

q(X) :- X > 0. % conditie

% EXECUTATI:
% q(1).
% q(X). % observa eroarea.


% ======================= GENERAREA NUMERELOR; EGALITATE

% decomentati pe rand cate una dintre ultimele 3 definitii
% ale lui r si executati:
% r(X). % folositi ";" pentru a defila rezultatele.

r(5).
% r(X) :- r(Y), Y < 10, X is Y + 1. % dupa 10 programul va cicla
% r(X) :- r(Y), X = Y + 1.
% r(X) :- r(Y), X =:= Y + 1. % ce se intampla? de ce?


% ========================= PREDICATUL CUT "!"

r1(a).
r1(b).
r1(c).
r2(b).
r2(c).

% Decomentati, pe rand, cate una din definitiile
% predicatului q (exceptie ultima), si executati: q(e, f).
 q(X,Y) :- r1(X), r2(Y).
% q(X,Y) :- r1(X), r2(Y), !.
% q(X,Y) :- r1(X), !, r2(Y).
% q(X,Y) :- !, r1(X), r2(Y).
q(e,f).


% =========================== PREDICATUL FAIL

zboara(fifi).
zboara(lili).
liliac(lili).

pasare(X) :- liliac(X), !, fail.
pasare(X) :- zboara(X).

% EXECUTATI:
% pasare(fifi)
% pasare(lili).


% ============================== PREDICATE CU VARIABILE	DE IESIRE

% max(+X, +Y, -Rez)
max(X, Y, Z) :- Y > X, !, Z=Y.
max(X, _, X).

% Executati:
% max(1, 2, Max).

% RESCRIETI predicatul max mai simplu, folosind cut.
% SCRIETI un predicat pentru minimul dintre 2 numere,
% folosind predicatul max.


% ===================================== PRELUCRARE DE LISTE

% predicatul lungime(+Lista, -Lungime) :
lungime([], 0). % caz initial
lungime([_ | RestLista], Lungime) :- lungime(RestLista, LungimeRest), Lungime is LungimeRest + 1. % pas de inductie;
% vezi _ in loc de nume variabila, pentru a evita warning de Singleton variable.

% incearca:
% lungime([a,b,5], L).
% lungime([a,b,[5,6]], L).
% lungime([a,b,p(x)], L).
% lungime([a,b | x, y], L). % observa eroarea
% lungime([a,b | [x, y]], L).
% lungime([a,b | [x, y]], 4).
% lungime([a,b | [x, y]], 5).

% SCRIETI predicatul membru(+X, +L), care intoarce true daca X este in L.


% ============================================== STRUCTURI

proceseazaStructura([X, Y, Z]) :- St = structura(X, Y, Z), write('lista: '), printLista([St]), !.
proceseazaStructura(St) :- structura(X,Y,Z) = St, write('structura: '), printLista([X,Y,Z]), !.
proceseazaStructura(X, Y, Z) :- St = structura(X, Y, Z), write('elemente: '), printLista([St]), !.

% testati la consola cele 3 variante ale predicatului

% observati predicatul format (foloseste write)
printLista([]).
printLista([X | R]) :- format('~w ~n', [X]), printLista(R).


% ================== DIRECTII DE CONSTRUIRE A SOLUTIILOR
inc(X,Y) :- Y is X+1.

% Simplu
incListFwd([],[]).
incListFwd([Head|Tail],[Head1|Tail1]) :- inc(Head,Head1), incListFwd(Tail,Tail1).

% Cu acumulator
incListBwd(L,IncL) :- incListBkwd(L,[],IncL).

incListBkwd([],L,L).
incListBkwd([Head|Tail],IncList1,IncList) :- inc(Head,Head1), incListBkwd(Tail, [Head1|IncList1], IncList).


% =============================== BACKTRACKING
% lupul, capra si varza (plus taranul)
% reprezentarea starii: stare(Mal_Taran, Mal_Lup, Mal_Capra, Mal_Varza)


opus(stang,drept).
opus(drept,stang).

stare_initiala(stare(stang,stang,stang,stang)).
stare_finala(stare(drept,drept,drept,drept)).

% Miscari legale:
% taranul transporta lupul
miscare(stare(Taran1,Taran1,Capra,Varza),stare(Taran2,Taran2,Capra,Varza)) :- opus(Taran1,Taran2).

% taranul transporta capra
miscare(stare(Taran1,Lup,Taran1,Varza),stare(Taran2,Lup,Taran2,Varza)) :- opus(Taran1,Taran2).

% taranul transporta varza
miscare(stare(Taran1,Lup,Capra,Taran1),stare(Taran2,Lup,Capra,Taran2)) :- opus(Taran1,Taran2).

% taranul merge singur
miscare(stare(Taran1,Lup,Capra,Varza),stare(Taran2,Lup,Capra,Varza)) :- opus(Taran1,Taran2).

% Stari ilegale:
ilegala(stare(Taran,Mal_Lup_si_Capra,Mal_Lup_si_Capra,_)) :- opus(Taran,Mal_Lup_si_Capra).
ilegala(stare(Taran,_,Mal_Capra,Mal_Capra)) :- opus(Taran,Mal_Capra).

% Cautarea solutiei
% lcv(+Stare, -Solutie, -Vizitate).
% TODO
lcv(_, _, _). % inlocuiti aceasta definitie cu definitiile corecte.

rezolva(Solutie) :- stare_initiala(Stare), lcv(Stare, Solutie, []).
afisare([]) :- nl.
afisare([X|R]) :- afisare(R), nl, write(X). % observa afisarea in ordine inversa

go :- rezolva(L), afisare(L), fail.













