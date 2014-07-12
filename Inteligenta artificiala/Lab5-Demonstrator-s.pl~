%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%% Inteligenta Artificiala 2013-2014
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


% Demonstrator teoreme


% clauses(-Clauses)
% Returns the entire set of clauses.
% Clauses are in the form [PositiveLiteral, [NegativeLiteral, ...]]
% Where each literal is in the form [LiteralName, LiteralSemantics]
clauses([
    [[vb, "este vreme frumoasa"], []],
    [[bs, "Mihai a primit bursa"], []],
    [[a, "masina este curata"], [[np, "nu ploua"]]],
    [[np, "nu ploua"], [[vb, "este vreme frumoasa"]]],
    [[b, "Mihai are bani"], [[bs, "Mihai a primit bursa"]]],
    [[m, "Mihai pleaca in vacanta"], [[a, "masina este curata"], [b, "Mihai are bani"]]],
    [[m, "Mihai pleaca in vacanta"], [[f, "masina este frumoasa"], [b, "Mihai are bani"]]]
	]).

% conclusion(-Conclusion)
% Returns the conclusion - a sentence with one positive literal.
conclusion([[m, "Mihai pleaca in vacanta"], []]).


% writeSemantics(+Clause)
% Outputs to the screen the semantics of the clause.
writeSemantics([null, []]) :- !, format("Clauza vida").
writeSemantics([[_, PozSem], []]) :- !, format("~s", [PozSem]).
writeSemantics([null, [[_, NegSem1] | Negs]]) :- !, format("Nu este adevarat ca ~s", [NegSem1]), writeAll(Negs, null).
writeSemantics([[_, PozSem], [[_, NegSem1] | Negs]]) :- !, format("Daca ~s", [NegSem1]), writeAll(Negs, PozSem).
writeAll([[_, NegSem] | Negs], PozSem) :- !, format(" si ~s", [NegSem]), writeAll(Negs, PozSem).
writeAll([], null) :- !, format(".").
writeAll([], PozSem) :- !, format(" atunci ~s.", [PozSem]).




% demonstrate(+Clauses, +Conclusion)
% Attempts to demonstrate the Conclusion, using the given Clauses.
% Returns true if the conclusion can be demonstrated,
%  and false otherwise.
% It should print on the screen each step of the resolution
%  as SemanticsClause1 + SemanticsClause2 -> SemanticsResult
% It should also printout the result of the demonstration
%  (success or failure).


demonstrate(Clauses, Conclusion):- member([Conclusion, []], Clauses). 
















