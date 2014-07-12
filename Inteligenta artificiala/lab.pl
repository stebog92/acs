%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%% Inteligenta Artificiala 2013-2014
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% algoritmul A*

% Resurse:
% Curs 2, slides 29-31; 37
% http://en.wikipedia.org/wiki/A_star

% reprezentarea unei stari in spatiul de stari ("bare state")
% s(x, y) % bareState

% reprezentarea unei stari + scorurile starii + parinte ("state")
% state(f, g, h, bareState, parentBareState)
% unde bareState si parentBareState au forma de mai sus


start(0, 0).
stop(2, 2).
grid(5, 5).
obstacol(1, 1).
obstacol(2, 1).
obstacol(1, 2).
obstacol(3, 2).
obstacol(1, 3).
% obstacol(2, 4).

% goal(?BareState)
% Adevarat daca BareState este stare(a) tinta.
goal(s(X, Y)) :- stop(X, Y).

% initial(?BareState)
% Adevarat daca BareState este starea initiala.
initial(s(X, Y)) :- start(X, Y).

% legal(+BareState)
% Adevarat daca BareState este o stare valida in spatiul problemei.
legal(s(X, Y)) :- grid(MX, MY), X >= 0, X < MX, Y >= 0, Y < MY, not(obstacol(X, Y)).

% nextState(+BareState, -BareNextState, -Cost_between)
% Adevarat pentru stari succesive.
% Leaga al doilea parametru la o stare urmatoare posibila
% (! nu neaparat legala).
% Leaga al treilea parametru la costul tranzitiei
% TODO: dreapta, jos, stanga, sus (4 reguli)
nextState(s(X,Y), s(X1, Y), 1):- X1 is X - 1.
nextState(s(X,Y), s(X1, Y), 1):- X1 is X + 1.
nextState(s(X,Y), s(X, Y1), 1):- Y1 is Y - 1.
nextState(s(X,Y), s(X, Y1), 1):- Y1 is Y + 1.

% computeH(+BareState, -H)
% leaga al doilea parametru la functia h pentru starea BareState
%
% TODO (1 regula)
computeH(s(X,Y), H):- goal(s(X1, Y1)), H is abs(X1 - X) + abs(Y1 - Y).

% insertSorted(+NewState, +OldFrontier, -NewFrontier)
% Avand o stare noua si frontiera explorarii, predicatul
% leaga al treilea parametru la frontiera in care s-a inserat
% noua stare; frontiera este sortata dupa scor (f)
insertsorted(S, [], [S]) :- !.
insertsorted(S, [S1 | R], [S, S1 | R]) :- S = state(Score, _, _, _, _), S1 = state(Score1, _, _, _, _), Score < Score1, !.
insertsorted(S, [S1 | R], [S1 | R1]) :- insertsorted(S, R, R1).

% memberState(+BareState, +StateSet, -StateInTheSet)
% Adevarat daca in StateSet se gaseste o stare corespunzand
% aceluiasi BareState.
% Daca este adevarat, leaga al treilea parametru la starea (completa)
% existenta deja in StateSet.
memberState(BS, [S1 | _], S1) :- S1 = state(_, _, _, BS, _),!.
memberState(BS, [_ | R], S1) :- memberState(BS, R, S1).

% updateState(+NewState, +Frontier, -newFrontier)
% Leaga al treilea parametru la o frontiera identica cu Frontier, numai
% ca starea cu acelasi bareState ca NewState este inlocuita cu NewState.
updateState(S, [S1 | R], [S | R]) :- S = state(_, _, _, BareState, _), S1 = state(_, _, _, BareState, _), !.
updateState(S, [S1 | R], [S1 | R1]) :- updateState(S, R, R1).


% astar(+Frontier, +Territory, -Solution)
% Executa algoritmul A* pe
% - frontiera curenta (multimea de stari neexpandate)
%   - frontiera este sortata crescator dupa scorul starilor (functia f)
% - teritoriu curent (stari complet expandate)
% - solutia (construita si legata cand se ajunge la starea finala)
% Valori initiale:
% - frontiera formata din starea initiala
% - teritoriu vid
% - solutie nelegata
%
% TODO: 2 reguli
% - cea mai buna stare este finala -> constructia solutiei
% - altfel, gaseste toate posibilele stari urmatoare legale (findall),
%    le adauga la frontiera (foloseste astar_open),
%    si continua cu apelul recursiv
astar([state(_,_,_,BS,_)|_], T, T):- goal(BS).
astar([S|RF], T, SOL):- S = state(_,G,_,BS,_),
			 findall([NBS, C], (nextState(BS, NBS, C), legal(NBS)), L),
			 astar_open(L, G, BS, RF, T, NF),
			 astar(NF, [S|T], SOL).



% astar_open(+BareStates_To_Open as [BareState, Cost],
%              +G_of_parent, +ParentState,
%              +Frontier, +Territory, -UpdatedFrontier)
% Pentru fiecare lista stare-cost din primul argument decide adaugarea
% la frontiera. Frontiera actualizata se construieste la avansare.
% 5 reguli
% - lista s-a terminat, frontiera este gata
% - starea este noua, se adauga in frontiera
% - starea este deja in frontiera, cu cost mai mare - se actualizeaza
% - starea este deja in teritoriu, cu cost mai mare - se adauga
% - starea nu va fi adaugata, se continua cu celelalte

astar_open([], _, _, Frontier, _, Frontier).
astar_open([S|RS], G, PS, Frontier, T, UpdatedFrontier):- S = [NBS, C],
					not(memberState(NBS, Frontier, _)),
					not(memberState(NBS, T, _)),
					computeH(NBS,NH),
					NG is G + C,
					F is NG + NH,
					insertsorted(state(F, NG, NH, NBS, PS), Frontier, NF),	
					astar_open(RS, G, PS, NF, T, UpdatedFrontier).


astar_open([S|RS], G, PS, Frontier, T, UpdatedFrontier):- S = [NBS, C],
					memberState(NBS, Frontier, NS),
					NS = state(Cost, _, _, _, _),
					computeH(NBS,NH),
					NG is G + C,
					F is NG + NH,
					F < Cost,
					updateState(state(F, NG, NH, NBS, PS), F, NF),
					astar_open(RS, G, PS, NF, T, UpdatedFrontier).

astar_open([S|RS], G, PS, Frontier, T, UpdatedFrontier):- S = [NBS, C],
					memberState(NBS, T, NS),
					NS = state(Cost, _, _, _, _),
					computeH(NBS,NH),
					NG is G + C,
					F is NG + NH,
					F < Cost,
					insertsorted(state(F, NG, NH, NBS, PS), Frontier, NF),
					astar_open(RS, G, PS, NF, T, UpdatedFrontier).

astar_open([_|RS], G, PS, Frontier, T, UpdatedFrontier):- astar_open(RS, G, PS, Frontier, T, UpdatedFrontier).


	



% go
% Calculeaza costul estimat pentru starea initiala si
% apeleaza astar, cu starea initiala in frontiera.
go :- initial(State), computeH(State, H),
	Frontier = [state(H, 0, H, State, null)],
	astar(Frontier, [], Sol), p(Sol).


p([]).
p([E | L]) :- write(E), nl, p(L).










