%%%%%%%
%%%%%%% Tema 4: CSP
%%%%%%%




%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%TESTED
%%% gac3(+Vars,+Domains,-RevDomains,+HyperArcs,+Constraints)
%%%
%%% +Vars - lista cu toate variabilele
%%% +Domains - listă de domeniile de valori ale variabilelor
%%%       Vars și Domains au aceeași lungime.
%%%       Elementul de pe poziția i în Domains reprezintă domeniul de valori al
%%%       variabilei de pe poziția i în Vars
%%% -RevDomains = listă cu domeniile de valori după consistency check
%%% +HyperArcs = lista de hiperarce ce trebuie verificate
%%%       Un hiperarc este reprezentat astfel: hyperarc(X,Ys,Constraint).
%%%       X este variabila al cărei domeniu va fi verificat
%%% +Constraints = lista tuturor constrângerilor
%%%       O constrângere este reprezentată astfel: constraint(Vars,Constraint)

%%% Algoritmul GAC3.

nth(0,[Elem|T],Elem2):-(Elem==Elem2).
nth(N,[H|T],Elem):-H\==Elem,nth(M,T,Elem),N is M + 1.

getnth(0,[Elem|T],Elem):-true.
getnth(N,[H|T],Elem):-M is N - 1, M > -1,getnth(M,T,Elem).

modify(X,[H|T],[X|T],0):-true.
modify(X,[H|T],[H|L],Pos):-Pos2 is Pos - 1, Pos2 > -1, modify(X,T,L,Pos2).

testConstraint(Var,Value,C):-not((Var=Value,C)),!,fail.
testConstraint(_,_,_).

sublist(_,[],[],C):-true.
sublist(V,Sl,[H|T],C):-not(testConstraint(V,H,C)),sublist(V,Sl,T,C).
sublist(V,[H|Sl],[H|T],C):-testConstraint(V,H,C),sublist(V,Sl,T,C).

addHP(T,T,D,RD,[],X):-true.
addHP(T,T,D,D,_,_):-true.
addHP(T,Mt,D,RD,[H|L],X):-not(D==RD),(constraint([S,Y],C) = H, not(S==X);constraint([S],C) = H), addHP(T,Mt,D,RD,L,X).
addHP(T,Mt2,D,RD,[H|L],X):-
	not(D==RD),
	constraint([S,Y],C) = H,
	S==X,
	addHP(T,Mt,D,RD,L,X),
	not(member(constraint([S,Y],C),Mt)),
	append(Mt,[hyperarc(Y,[X],C)],Mt2).
testConstraints(X,T,C,Vars,Domains,NewDom):-
	T == [],
	nth(N,Vars,X),
	getnth(N,Domains,Dom),
	member(X,Dom),
	not(C),

	.
testConstraints(X,[F|L],C,Dom):-
	
sample(_,_):-true.

revise(X,L,C,Domains,RevDomains,Constraints,Vars):-
	(var(X),
	nth(N,Vars,X),
	getnth(N,Domains,Dom),
	L==[],
	sublist(X,Sl,Dom,C),
	modify(Sl,Domains,RevDomains,N),
	(length(Sl,J),J == 1,member(P,Sl),testConstraint(X,P,C),X = P));(L==[],RevDomains=Domains).
revise(X,L,C,Domains,RevDomains,Constraints,Vars):-
	write(X),nl,write(':'),nl,
	[Node|T] = L,
	write(Node),nl,
	nth(N,Vars,X),
	getnth(N,Domains,Dom),
	((
	member(Node,Dom),
	subtract(Dom,[Node],NewDom),
	(
	(length(NewDom,P),P == 1, [X] = NewDom);true),
	modify(NewDom,Domains,RevDomains1,N));true),!,
	revise(X,T,C,RevDomains1,RevDomains,Constraints,Vars).
revise(X,L,C,Domains,RevDomains,Constraints,Vars):-
	([Node|T] = L,
	nth(N,Vars,X),
	getnth(N,Domains,Dom),
	not(member(Node,Dom)),
	revise(X,T,C,Domains,RevDomains,Constraints,Vars));([Node|T]=L,var(X),var(Node)).

%gac3(_,_,_,_,_):-fail.
%gac3(Vars,Domains,[],HyperArcs,Constrainds:-true.
gac3(Vars,Domains,RevDomains,HyperArcs,Constraints):-
	([hyperarc(X,Ys,C)|T] = HyperArcs,!,
	revise(X,Ys,C,Domains,NewDomains,Constraints,Vars),!,
	addHP(T,NewHp,Domains,NewDomains,Constraints,X),
	write(NewDomains),write(':'),write(NewHp),nl,!,
	gac3(Vars,NewDomains,RevDomains,NewHp,Constraints));(HyperArcs == [], RevDomains = Domains).
	%HyperArcs = Mt.
%gac3(Vars,Domains,RevDomains,HyperArcs,Constraints):-member(hyperarc(X,Ys,C),HyperArcs),!,

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%TESTED
%% solveCSP(+Vars,+Domains,+Constraints,-Solution)
%% Rezolvă problema cu variabilele Vars (neinstanțiate),
%% lista de domenii de valori Domains
%% constrângerile Constraints (listă de predicate constraint(CVars,Relation))
%%           unde CVars este lista variabilelor implicate în acea constrângere
%%           și Relation este o expresie cu acestea
%% Solution = listă cu valori pentru variabile care compun o soluție a problemei

solveCSP(_,_,_,_):-fail.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% Verificare solver
%% mai jos: exemple de CSP + teste

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% CSP - Exemplul 0 (Colorarea hărților - DEMO)
%% Treubie colorată o hartă cu 5 țări folosind culorile roșu, verde și albastru
%% astfel încât oricare două țări vecine să fie coloare diferit
%% Adăugăm o constrângere ca țara A să fie colorată cu roșu, iar B cu verde

%% Test pentru arc-consistenta (gac3)
gac3_0(RevDoms):-
	Vars=[A,B,C,D,E],
	Domains=[[r,g,b],[r,g,b],[r,g,b],[r,g,b],[r,g,b]],
	Hyperarcs=[hyperarc(A,[],A==r)],
	Constraints=[constraint([A],A==r),constraint([A,B],A\==B),
	constraint([A,C],A\==C),constraint([A,D],A\==D),constraint([B,C],B\==C),
	constraint([B,E],B\==E),constraint([C,D],C\==D),constraint([C,E],C\==E),
	constraint([D,E],D\==E)],
	gac3(Vars,Domains,RevDoms,Hyperarcs,Constraints).

test0a:-gac3_0(RevDoms),
	RevDoms==[[r],[g,b],[g,b],[g,b],[r,g,b]],!.

%% Test pentru solver (solveCSP)
solve0(S):-
	Vars=[A,B,C,D,E],
	Domains=[[r,g,b],[r,g,b],[r,g,b],[r,g,b],[r,g,b]],
	Constraints=[constraint([A],A==r),constraint([B],B==g),
	constraint([A,B],A\==B),constraint([A,C],A\==C),constraint([A,D],A\==D),
	constraint([B,C],B\==C),constraint([B,E],B\==E),constraint([C,D],C\==D),
	constraint([C,E],C\==E),constraint([D,E],D\==E)],
	solveCSP(Vars,Domains,Constraints,S).

test0b:-bagof(S,solve0(S),L),
	ok(L,[[r,g,b,g,r]]),!.


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%



%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% CSP - Exemplul 1 (Colorarea hărților)
%% Treubie colorată o hartă cu 7 țări folosind culorile roșu, verde și albastru
%% astfel încât oricare două țări vecine să fie coloare diferit
%% La fel, fixăm printr-o restricție culoarea țării A

%% Test pentru arc-consistenta (gac3)
%% Atenție, din domeniul lui b lipsește 'b'
gac3_1(RevDoms):-
	Vars=[A,B,C,D,E,F,G],
	Domains=[[r,g,b],[r,g],[r,g,b],[r,g,b],[r,g,b],[r,g,b],[r,g,b]],
	Hyperarcs=[hyperarc(A,[],A==r)],
	Constraints=[constraint([A],A==r),
	constraint([A,B],A\==B),constraint([A,C],A\==C),constraint([A,E],A\==E),
	constraint([B,C],B\==C),constraint([B,D],B\==D),constraint([C,D],C\==D),
	constraint([C,E],C\==E),constraint([C,F],C\==F),constraint([D,G],D\==G),
	constraint([E,F],E\==F),constraint([F,G],F\==G)],
	gac3(Vars,Domains,RevDoms,Hyperarcs,Constraints).

test1a:-gac3_1(RevDoms),RevDoms==[[r],[g],[b],[r],[g],[r],[g,b]],!.

%% Test pentru solver (solveCSP)

solve1(S):-
	Vars=[A,B,C,D,E,F,G],
	Domains=[[r,g,b],[r,g,b],[r,g,b],[r,g,b],[r,g,b],[r,g,b],[r,g,b]],
	Constraints = [constraint([A],A==r),
	constraint([A,B],A\==B),constraint([A,C],A\==C),constraint([A,E],A\==E),
	constraint([B,C],B\==C),constraint([B,D],B\==D),constraint([C,D],C\==D),
	constraint([C,E],C\==E),constraint([C,F],C\==F),constraint([D,G],D\==G),
	constraint([E,F],E\==F),constraint([F,G],F\==G)],
	solveCSP(Vars,Domains,Constraints,S).

test1b:-bagof(S,solve1(S),L),
	ok(L,[[r,g,b,r,g,r,g],[r,g,b,r,g,r,b],
	[r,b,g,r,b,r,g],[r,b,g,r,b,r,b]]),!.
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% CSP - Exemplul 2 (Pătratul fermecat)
%% Un pătrat fantastic 3x3 conține toate numerele de la 1 la 9 dispuse în
%% așa fel încât suma pe toate liniile, coloanele și cele 2 diagonale să fie 15

%% allDifferent(+L)
allDifferent([]).
allDifferent([X|Rest]):-
	not(member(X,Rest)),allDifferent(Rest).

%% Test pentru arc-consistenta (gac3)
%% Prin 2 hiperarce care nu țin de restricțiile problemei,
%% primul pătrățel e fixat la 2, iar al doilea la {7,9}.

gac3_2(RevDoms):-
	Vars=[X11,X12,X13,X21,X22,X23,X31,X32,X33],
	Domains=[[1,2,3,4,5,6,7,8,9],[1,2,3,4,5,6,7,8,9],[1,2,3,4,5,6,7,8,9],
	[1,2,3,4,5,6,7,8,9],[1,2,3,4,5,6,7,8,9],[1,2,3,4,5,6,7,8,9],
	[1,2,3,4,5,6,7,8,9],[1,2,3,4,5,6,7,8,9],[1,2,3,4,5,6,7,8,9]],
	Hyperarcs=[hyperarc(X11,[],X11 is 2),
	hyperarc(X12,[],(X12 is 7 ; X12 is 9))],
	%% Cifrele nu au voie să se repete în cadrul pătratului fascinant
	Constraints=[constraint([X11,X12],X11=\=X12),
	constraint([X11,X13],X11=\=X13),constraint([X11,X21],X11=\=X21),
	constraint([X11,X22],X11=\=X22),constraint([X11,X23],X11=\=X23),
	constraint([X11,X31],X11=\=X31),constraint([X11,X32],X11=\=X32),
	constraint([X11,X33],X11=\=X33),constraint([X12,X13],X12=\=X13),
	constraint([X12,X21],X12=\=X21),constraint([X12,X22],X12=\=X22),
	constraint([X12,X23],X12=\=X23),constraint([X12,X31],X12=\=X31),
	constraint([X12,X32],X12=\=X32),constraint([X12,X33],X12=\=X33),
	constraint([X13,X21],X13=\=X21),constraint([X13,X22],X13=\=X22),
	constraint([X13,X23],X13=\=X23),constraint([X13,X31],X13=\=X31),
	constraint([X13,X32],X13=\=X32),constraint([X13,X33],X13=\=X33),
	constraint([X21,X22],X21=\=X22),constraint([X21,X23],X21=\=X23),
	constraint([X21,X31],X21=\=X31),constraint([X21,X32],X21=\=X32),
	constraint([X21,X33],X21=\=X33),constraint([X22,X23],X22=\=X23),
	constraint([X22,X31],X22=\=X31),constraint([X22,X32],X22=\=X32),
	constraint([X22,X33],X22=\=X33),constraint([X23,X31],X23=\=X31),
	constraint([X23,X32],X23=\=X32),constraint([X23,X33],X23=\=X33),
	constraint([X31,X32],X31=\=X32),constraint([X31,X33],X31=\=X33),
	constraint([X32,X33],X32=\=X33),
	constraint([X11,X12,X13],15 is X11+X12+X13),
	constraint([X21,X22,X23],15 is X21+X22+X23),
	constraint([X31,X32,X33],15 is X31+X32+X33),
	constraint([X11,X21,X31],15 is X11+X21+X31),
	constraint([X12,X22,X32],15 is X12+X22+X32),
	constraint([X13,X23,X33],15 is X13+X23+X33),
	constraint([X11,X22,X33],15 is X11+X22+X33),
	constraint([X13,X22,X31],15 is X13+X22+X31)],
	gac3(Vars,Domains,RevDoms,Hyperarcs,Constraints).

test2a:-gac3_2(RevDoms),
	RevDoms==[[2],[7,9],[4,6],[7,9],[5,7],[1,3],[4,6],[1,3],[6,8]],!.

%% Test pentru solver (solveCSP)

solve2(S):-
	Vars=[X11,X12,X13,X21,X22,X23,X31,X32,X33],
	Domains=[[1,2,3,4,5,6,7,8,9],[1,2,3,4,5,6,7,8,9],[1,2,3,4,5,6,7,8,9],
	[1,2,3,4,5,6,7,8,9],[1,2,3,4,5,6,7,8,9],[1,2,3,4,5,6,7,8,9],
	[1,2,3,4,5,6,7,8,9],[1,2,3,4,5,6,7,8,9],[1,2,3,4,5,6,7,8,9]],
	%% Cifrele nu au voie să se repete în cadrul pătratului fabulos
	Constraints=[constraint([X11,X12],X11=\=X12),
	constraint([X11,X13],X11=\=X13),constraint([X11,X21],X11=\=X21),
	constraint([X11,X22],X11=\=X22),constraint([X11,X23],X11=\=X23),
	constraint([X11,X31],X11=\=X31),constraint([X11,X32],X11=\=X32),
	constraint([X11,X33],X11=\=X33),constraint([X12,X13],X12=\=X13),
	constraint([X12,X21],X12=\=X21),constraint([X12,X22],X12=\=X22),
	constraint([X12,X23],X12=\=X23),constraint([X12,X31],X12=\=X31),
	constraint([X12,X32],X12=\=X32),constraint([X12,X33],X12=\=X33),
	constraint([X13,X21],X13=\=X21),constraint([X13,X22],X13=\=X22),
	constraint([X13,X23],X13=\=X23),constraint([X13,X31],X13=\=X31),
	constraint([X13,X32],X13=\=X32),constraint([X13,X33],X13=\=X33),
	constraint([X21,X22],X21=\=X22),constraint([X21,X23],X21=\=X23),
	constraint([X21,X31],X21=\=X31),constraint([X21,X32],X21=\=X32),
	constraint([X21,X33],X21=\=X33),constraint([X22,X23],X22=\=X23),
	constraint([X22,X31],X22=\=X31),constraint([X22,X32],X22=\=X32),
	constraint([X22,X33],X22=\=X33),constraint([X23,X31],X23=\=X31),
	constraint([X23,X32],X23=\=X32),constraint([X23,X33],X23=\=X33),
	constraint([X31,X32],X31=\=X32),constraint([X31,X33],X31=\=X33),
	constraint([X32,X33],X32=\=X33),
	%% Condițiile de mai sus se pot înlocui cu una singură ?
	%% constraint([X11,X12,X13,X21,X22,X23,X31,X32,X33],
	%% allDifferent([X11,X12,X13,X21,X22,X23,X31,X32,X33])),

	%% Suma pe linii, colaone și diagonale trebuie să fie 15
	constraint([X11,X12,X13],15 is X11+X12+X13),
	constraint([X21,X22,X23],15 is X21+X22+X23),
	constraint([X31,X32,X33],15 is X31+X32+X33),
	constraint([X11,X21,X31],15 is X11+X21+X31),
	constraint([X12,X22,X32],15 is X12+X22+X32),
	constraint([X13,X23,X33],15 is X13+X23+X33),
	constraint([X11,X22,X33],15 is X11+X22+X33),
	constraint([X13,X22,X31],15 is X13+X22+X31)],
	solveCSP(Vars,Domains,Constraints,S).

test2b:-bagof(S,solve2(S),L),
	ok(L,[[2,7,6,9,5,1,4,3,8],[2,9,4,7,5,3,6,1,8],[4,3,8,9,5,1,2,7,6],
	[4,9,2,3,5,7,8,1,6],[6,1,8,7,5,3,2,9,4],[6,7,2,1,5,9,8,3,4],
	[8,1,6,3,5,7,4,9,2],[8,3,4,1,5,9,6,7,2]]),!.
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% CSP - Exemplul 3 (6-Queens)
%% Pe o tablă de șah mai mică (6x6) trebuie plasate 6 regine astfel încât să
%% nu se atace reciproc. Cum 2 regine nu pot sta pe aceeași coloană, variabilele
%% vor reprezenta linia pe care se află fiecare regină

%% Test pentru arc-consistenta (gac3)

%% Am fixat prin 2 constrângeri dama 1 la linia 1 și dama 2 la linia 3
%% Prin impunerea arc-consistenței, domeniile devin nule
gac3_3(RevDoms):-
	Vars=[C1,C2,C3,C4,C5,C6],
	Domains=[[1,2,3,4,5,6],[1,2,3,4,5,6],[1,2,3,4,5,6],
	[1,2,3,4,5,6],[1,2,3,4,5,6],[1,2,3,4,5,6]],
	Hyperarcs=[hyperarc(C1,[],C1 is 1),hyperarc(C2,[],C2 is 3)],
	Constraints=[constraint([C1,C2],(abs(C1-C2,R12),R12=\=1,R12=\=0)),
	constraint([C1,C3],(abs(C1-C3,R13),R13=\=2,R13=\=0)),
	constraint([C1,C4],(abs(C1-C4,R14),R14=\=3,R14=\=0)),
	constraint([C1,C5],(abs(C1-C5,R15),R15=\=4,R15=\=0)),
	constraint([C1,C6],(abs(C1-C6,R16),R16=\=5,R16=\=0)),
	constraint([C2,C3],(abs(C2-C3,R23),R23=\=1,R23=\=0)),
	constraint([C2,C4],(abs(C2-C4,R24),R24=\=2,R24=\=0)),
	constraint([C2,C5],(abs(C2-C5,R25),R25=\=3,R25=\=0)),
	constraint([C2,C6],(abs(C2-C6,R26),R26=\=4,R26=\=0)),
	constraint([C3,C4],(abs(C3-C4,R34),R34=\=1,R34=\=0)),
	constraint([C3,C5],(abs(C3-C5,R35),R35=\=2,R35=\=0)),
	constraint([C3,C6],(abs(C3-C6,R36),R36=\=3,R36=\=0)),
	constraint([C4,C5],(abs(C4-C5,R45),R45=\=1,R45=\=0)),
	constraint([C4,C6],(abs(C4-C6,R46),R46=\=2,R46=\=0)),
	constraint([C5,C6],(abs(C5-C6,R56),R56=\=1,R56=\=0))],
	gac3(Vars,Domains,RevDoms,Hyperarcs,Constraints).

test3a:-gac3_3(RevDoms),RevDoms==[[],[],[],[],[],[]],!.

%% Test pentru solver (solveCSP)

solve3(S):-
	Vars=[C1,C2,C3,C4,C5,C6],
	Domains=[[1,2,3,4,5,6],[1,2,3,4,5,6],[1,2,3,4,5,6],
	[1,2,3,4,5,6],[1,2,3,4,5,6],[1,2,3,4,5,6]],
	Constraints=[constraint([C1,C2],(abs(C1-C2,R12),R12=\=1,R12=\=0)),
	constraint([C1,C3],(abs(C1-C3,R13),R13=\=2,R13=\=0)),
	constraint([C1,C4],(abs(C1-C4,R14),R14=\=3,R14=\=0)),
	constraint([C1,C5],(abs(C1-C5,R15),R15=\=4,R15=\=0)),
	constraint([C1,C6],(abs(C1-C6,R16),R16=\=5,R16=\=0)),
	constraint([C2,C3],(abs(C2-C3,R23),R23=\=1,R23=\=0)),
	constraint([C2,C4],(abs(C2-C4,R24),R24=\=2,R24=\=0)),
	constraint([C2,C5],(abs(C2-C5,R25),R25=\=3,R25=\=0)),
	constraint([C2,C6],(abs(C2-C6,R26),R26=\=4,R26=\=0)),
	constraint([C3,C4],(abs(C3-C4,R34),R34=\=1,R34=\=0)),
	constraint([C3,C5],(abs(C3-C5,R35),R35=\=2,R35=\=0)),
	constraint([C3,C6],(abs(C3-C6,R36),R36=\=3,R36=\=0)),
	constraint([C4,C5],(abs(C4-C5,R45),R45=\=1,R45=\=0)),
	constraint([C4,C6],(abs(C4-C6,R46),R46=\=2,R46=\=0)),
	constraint([C5,C6],(abs(C5-C6,R56),R56=\=1,R56=\=0))],
	solveCSP(Vars,Domains,Constraints,S).

test3b:-bagof(S,solve3(S),L),
	ok(L,[[2,4,6,1,3,5],[3,6,2,5,1,4],[4,1,5,2,6,3],[5,3,1,6,4,2]]),!.
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% CSP - Exemplul 4 (N-Queens)
%% Aceeași problemă, dar de data aceasta se construiesc automat
%% Vars, Domains și Constraints pentru CSP

%% listOfVars(+K,-Vars)
%% Construiește o listă cu K variabile neinstanțiate
listOfVars(0,[]):-!.
listOfVars(K,[_|Other]):-K1 is K-1,listOfVars(K1,Other).

%% getKth(+K,+L,-E)
%% E va fi variabila de pe poziția K din L
getKth(1,[E|_],E):-!.
getKth(K,[_|Other],T):-K1 is K-1,getKth(K1,Other,T).

%% fromLtoK(+L,+K,+Bigger,-N)
%% Adaugă numerele de la L la K listei Bigger și pune totul în N.
fromLtoK(L,L,N,[L|N]):-!.
fromLtoK(L,K,Bigger,N):-L=<K,K1 is K-1,fromLtoK(L,K1,[K|Bigger],N),!.
fromLtoK(L,K,N,N):-L>K.

%% constraintList(+K,-Vars,-Domains,-Constraints)
constraintList(K,Vars,Domains,Constraints):-
	fromLtoK(1,K,[],Is),M is K * K,
	listOfVars(K,Vars),listOfVars(M,Rs),
	buildDomains(K,K,Domains),
	buildForI(K,Is,Vars,Rs,[],Constraints),!.

buildDomains(0,_,[]):-!.
buildDomains(N,K,[D|Others]):-
	fromLtoK(1,K,[],D),
	N1 is N-1,
	buildDomains(N1,K,Others).

buildForI(_,[],_,_,C,C).
buildForI(K,[I|Is],Vars,Rs,C1,C2):-
	I1 is I+1,fromLtoK(I1,K,[],Js),
	buildForJ(K,Js,I,Vars,Rs,C1,C3),
	buildForI(K,Is,Vars,Rs,C3,C2).

buildForJ(_,[],_,_,_,C,C).
buildForJ(K,[J|Js],I,Vars,Rs,C1,C2):-
	getKth(I,Vars,X),getKth(J,Vars,Y),
	Rn is (I-1)*K + J,abs(I-J,D),
	getKth(Rn,Rs,R),
	buildForJ(K,Js,I,Vars,Rs,
	[constraint([X,Y],(abs(X-Y,R),R=\=D,R=\=0))|C1],C2).

%% Test pentru arc-consistenta (gac3)

gac3_4(RevDoms,N):-
	constraintList(N,Vars,Domains,Constraints),
	[X|_]=Vars,
	gac3(Vars,Domains,RevDoms,[hyperarc(X,[],X is N-1)],Constraints).

test4a:-gac3_4(RevDoms,4),RevDoms==[[3],[1],[4],[2]],!.

%% Test pentru solver (solveCSP)
solve4(S,N):-
	constraintList(N,Vars,Domains,Constraints),
	solveCSP(Vars,Domains,Constraints,S).

test4b:-bagof(S,solve4(S,4),L),
	ok(L,[[2,4,1,3],[3,1,4,2]]),!.
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% CSP - Exemplul 5 (Puzzle aritmetic)
%% Fiecare literă de mai jos ascunde o cifră. Să se alfe valorile lor, știind că
%%   SEND +
%%   MORE
%%  -----
%%  MONEY

%% Test pentru arc-consistenta (gac3)
%% Poate rula mai mult decât testele anterioare. (spre 1-2 minute)
%% Fixăm M la 1


gac3_5(RevDoms):-
	Vars=[S,E,N,D,M,O,R,Y],
	Domains=[[0,1,2,3,4,5,6,7,8,9],[0,1,2,3,4,5,6,7,8,9],
	[0,1,2,3,4,5,6,7,8,9],[0,1,2,3,4,5,6,7,8,9],[0,1,2,3,4,5,6,7,8,9],
	[0,1,2,3,4,5,6,7,8,9],[0,1,2,3,4,5,6,7,8,9],[0,1,2,3,4,5,6,7,8,9]],
	Hyperarcs=[hyperarc(M,[],M is 1)],
	Constraints=[constraint([S],S>0),constraint([M],(M>0,M=<1)),
	constraint([S,M],S+M>=9),
	constraint([S,E],S=\=E),constraint([S,N],S=\=N),constraint([S,D],S=\=D),
	constraint([S,M],S=\=M),constraint([S,O],S=\=O),constraint([S,R],S=\=R),
	constraint([S,Y],S=\=Y),constraint([E,N],E=\=N),constraint([E,D],E=\=D),
	constraint([E,M],E=\=M),constraint([E,O],E=\=O),constraint([E,R],E=\=R),
	constraint([E,Y],E=\=Y),constraint([N,D],N=\=D),constraint([N,M],N=\=M),
	constraint([N,O],N=\=O),constraint([N,R],N=\=R),constraint([N,Y],N=\=Y),
	constraint([D,M],D=\=M),constraint([D,O],D=\=O),constraint([D,R],D=\=R),
	constraint([D,Y],D=\=Y),constraint([M,O],M=\=O),constraint([M,R],M=\=R),
	constraint([M,Y],M=\=Y),constraint([O,R],O=\=R),constraint([O,Y],O=\=Y),
	constraint([R,Y],R=\=Y),
	constraint([S,E,N,D,M,O,R,Y],
	(T is S*1000+E*91+D+R*10-M*9000-O*900-N*90-Y,T==0))],
	gac3(Vars,Domains,RevDoms,Hyperarcs,Constraints).

test5a:-gac3_5(RevDoms),RevDoms==[[9],[5,6],[6,7],[6,7],[1],[0],[8],[2,3]],!.

%% Test pentru solver (solveCSP)

solve5(Sol):-
	Vars=[S,E,N,D,M,O,R,Y],
	Domains=[[0,1,2,3,4,5,6,7,8,9],[0,1,2,3,4,5,6,7,8,9],
	[0,1,2,3,4,5,6,7,8,9],[0,1,2,3,4,5,6,7,8,9],[0,1,2,3,4,5,6,7,8,9],
	[0,1,2,3,4,5,6,7,8,9],[0,1,2,3,4,5,6,7,8,9],[0,1,2,3,4,5,6,7,8,9]],
	Constraints=[constraint([S],S>0),constraint([M],(M>0,M=<1)),
	constraint([S,M],S+M>=9),
	constraint([S,E],S=\=E),constraint([S,N],S=\=N),constraint([S,D],S=\=D),
	constraint([S,M],S=\=M),constraint([S,O],S=\=O),constraint([S,R],S=\=R),
	constraint([S,Y],S=\=Y),constraint([E,N],E=\=N),constraint([E,D],E=\=D),
	constraint([E,M],E=\=M),constraint([E,O],E=\=O),constraint([E,R],E=\=R),
	constraint([E,Y],E=\=Y),constraint([N,D],N=\=D),constraint([N,M],N=\=M),
	constraint([N,O],N=\=O),constraint([N,R],N=\=R),constraint([N,Y],N=\=Y),
	constraint([D,M],D=\=M),constraint([D,O],D=\=O),constraint([D,R],D=\=R),
	constraint([D,Y],D=\=Y),constraint([M,O],M=\=O),constraint([M,R],M=\=R),
	constraint([M,Y],M=\=Y),constraint([O,R],O=\=R),constraint([O,Y],O=\=Y),
	constraint([R,Y],R=\=Y),
	constraint([S,E,N,D,M,O,R,Y],
	(T is S*1000+E*91+D+R*10-M*9000-O*900-N*90-Y,T==0))],
	solveCSP(Vars,Domains,Constraints,Sol).

test5b:-bagof(S,solve5(S),L),
	ok(L,[[9,5,6,7,1,0,8,2]]),!.


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%% extractSols(X,L,L2)
extract(X,[Y|L],L):-X==Y.
extract(X,[Y|L1],[Y|L2]):-extract(X,L1,L2).

%% ok(Solution,Correct)
ok([],[]).
ok([S1|OtherSols],Correct):-extract(S1,Correct,Other),ok(OtherSols,Other).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

testAC3:-
	test0a,write('Testul 0a trecut\n'),
	test1a,write('Testul 1a trecut\n'),
	test2a,write('Testul 2a trecut\n'),
	test3a,write('Testul 3a trecut\n'),
	test4a,write('Testul 4a trecut\n'),
	test5a,write('Testul 5a trecut\n'),
	write('Toate testele pentru arc-consistenta au fost trecute!\n'),!.
testAC3:-
	write('Esti aproape!\n'),fail.

testMAC:-
	test0b,write('Testul 0b trecut\n'),
	test1b,write('Testul 1b trecut\n'),
	test2b,write('Testul 2b trecut\n'),
	test3b,write('Testul 3b trecut\n'),
	test4b,write('Testul 4b trecut\n'),
	test5b,write('Testul 5b trecut\n'),
	write('Toate problemele au fost rezolvate corect!\n'),!.
testMAC:-
	write('Esti aproape!\n'),fail.

testall:-testAC3,testMAC,write('Bravo!\n').
