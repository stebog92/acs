lungime([], 0).
lungime([_ | RestLista], Lungime) :- lungime(RestLista, LungimeRest), Lungime is LungimeRest + 1. % pas de inductie;

append([],List,List).
append([H|Tail],X,[H|NewTail]) :- append(Tail,X,NewTail).
