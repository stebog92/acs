% 1. (1p)
% lungime_para(Lista).
% lungime_impara(Lista).
% testeaza daca o lista are lungime para / impara

par(X) :- (X rem 2) =:= 0.
len([],0).
len([A|T], X1):-len(T,X),X1 is X + 1.

lungime_para(L):-len(L,X),par(X).
lungime_impara(L):-len(L,X), not(par(X)).

% 2. (1p)
% cmmdc(X,Y)
% determina cel mai mare divizor comun pt X si Y

cmmdc(X,0,X):-!.
cmmdc(X,Y,Z):-X1 is (X rem Y),cmmdc(Y,X1,Z).

% 3. (1p)
% fibonacci(N).
% determina al N-lea numar fibonacci

fibonacci(1,0):-!.
fibonacci(2,1):-!.
fibonacci(N, Z) :- N1 is N - 1,
		 N2 is N - 2, 
		fibonacci(N1,Z1),!, fibonacci(N2,Z2),!, Z is Z1 + Z2.

% 4. (1p)
% invers(Lista,Inv).
% determina inversul unei liste
invers([],[]).
invers([A|X], Y):-invers(X,Z),append(Z,[A],Y).

% 5. (1p)
% diferenta(M1,M2,M).
% determina diferenta multimilor M1 si M2

% 6. (1p)
% e_prim(N).
% determina daca numarul N este prim
% prim(N):-not(between(2,(N/2),X),A is N rem X,A=/=0).

% 7. (1p)
% descompune(N,L).
% determina lista de factori primi ai lui N

% 8. (1p)
% perm(Lista,Rez).
% determina permutari ale listei L
% le da pe rand, la cerere (nu toate intr-o lista)


% 9. (1p)
% parti(Lista,Rez).
% determina partile unei liste
% le da pe rand, la cerere (nu toate intr-o lista)

% 10. (1p)
% un program pentru turnurile din hanoi
% (N discuri)

% 11. (bonus - 1p)
% transpune(M,T).
% determina transpusa matricii M
% M este data ca lista de liste

