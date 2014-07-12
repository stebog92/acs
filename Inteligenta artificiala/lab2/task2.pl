initiala(stare(0, 0, 8, 5)).

finala(stare(4, _, 8, 5)).
finala(stare(_, 4, 8, 5)).

min(A, B, B):- A >= B.
min(A, B, A):- B > A.

max(A, B, A):- A >= B.
max(A, B, B):- B > A.

move(stare(0, X, 8, 5), stare(8, X, 8, 5)).
move(stare(X, 0, 8, 5), stare(X, 5, 8, 5)).
move(stare(Y, X, 8, 5), stare(0, X, 8, 5)):- not(Y is 0).
move(stare(Y, X, 8, 5), stare(Y, 0, 8, 5)):- not(X is 0).
move(stare(Y, X, 8, 5), stare(Y1, X1, 8, 5)):- Y2 is X + Y, X2 is X - (8 - Y), min(8, Y2, Y1), max(0, X2, X1).

move(stare(Y, X, 8, 5), stare(Y1, X1, 8, 5)):- Y2 is X + Y, X2 is Y - (5 - X), min(5, Y2, X1), max(0, X2, Y1).

afis([]):nl.
afis([X|R]):- afis(R), nl, write(X).

rezolva(Sol):- initiala(Stare), buckets(Stare, Solutie, []).

initiala
