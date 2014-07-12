% replace O with Rin a list
replace(_, _, [], []).
replace(O, R, [O|T], [R | T2]):- replace(O, R, T, T2).
replace(O, R, [H|T], [H | T2]):- H \= O, replace(O, R, T, T2).

% get coordinates from location = [X, Y, _] or place = [X, Y]
get_coord(Loc, X, Y):- Loc = [X, Y].
get_coord(Loc, X, Y):- Loc = [X, Y, _].

% get max of 2 var
max(A, B, A):- A >= B.
max(A, B, B):- B > A.

% get energy of a place
get_energy([X, Y], Loc, 0,  _):- not(member([X, Y, energy], Loc)).
get_energy([X, Y], Loc, PE, PE):- member([X, Y, energy], Loc).

get_original_loc([X, Y], Loc, [X, Y]):- not(member([X, Y, _], Loc)).
get_original_loc([X, Y], Loc, [X, Y, S]):- member([X, Y, S], Loc). 

get_hist([X,Y], Hist, 0):- not(member([X, Y, _], Hist)).
get_hist([X,Y], Hist, H):- member([X, Y, H], Hist).

update_hist([X,Y], NH, Hist, NHist):- not(member([X, Y, _], Hist)), NHist = [[X, Y, NH] | Hist].
update_hist([X,Y], NH, Hist, NHist):- member([X,Y,_], Hist), replace([X,Y,_], [X,Y,NH], Hist, NHist).

% prints the reversed solution
print_path([]).
print_path([S|T]):- print_path(T), write(S), write(' ').

% compute signals in a certain location
get_signals(_, [],   _,  _, E, G, E, G).
get_signals(Curr_place, [Loc | RL], EG, EI, E, G, FE, FG):- Loc = [X, Y, gate],
                                                       Curr_place = [MX, MY],
                                                       Dist is abs(MX - X) + abs(MY - Y),
                                                       CS is EG - Dist,
                                                       max(CS, 0, NI),
                                                       NG is NI + G,
                                                       get_signals(Curr_place, RL, EG, EI, E, NG, FE, FG).

get_signals(Curr_place, [Loc | RL], EG, EI, E, G, FE, FG):- Loc = [X, Y, energy],
                                                       Curr_place = [MX, MY],
                                                       Dist is abs(MX - X) + abs(MY - Y),
                                                       CS is EI - Dist,
                                                       max(CS, 0, NI),
                                                       NE is NI + E,
                                                       get_signals(Curr_place, RL, EG, EI, NE, G, FE, FG).

get_signals(Curr_place, [   _ | RL], EG, EI, E, G, FE, FG):- get_signals(Curr_place, RL, EG, EI, E, G, FE, FG).

% transform all elements of type [X, Y, _] to [X, Y]
get_places([], Places, Places).
get_places([Loc|RL], Places, New_Places):- Loc = [X, Y, _],
                                           get_places(RL, [[X,Y] | Places], New_Places).

get_places([Loc|RL], Places, New_Places):- Loc = [_, _],
                                           get_places(RL, [Loc | Places], New_Places).

% print portal information                   
print_portal_info([X, Y], S, G):- format("[ ~d, ~d | ~d, ~d]", [X, Y, G, S]).


% print location information
print_current_loc_info(X, Y, E, S, G):- format("[ ~d, ~d | ~d | ~d, ~d]", [X, Y, E, G, S]).


% compute information for a list of portals
compute_signal_for_portals([],    _,   _,   _, P, P).
compute_signal_for_portals([Portal | RP], Loc, EG, EI, P, NewP):- get_signals(Portal, Loc, EG, EI, 0, 0, FE, FG), !,
                                                                  print_portal_info(Portal, FE, FG),
                                                                  write('   '),
                                                                  compute_signal_for_portals(RP, Loc, EG, EI, [[Portal, FE, FG] | P], NewP).

% update all other lists of portals with the new portal a location
% added so they can be bidirectional
update_portal_list(Place, New_Portal, Portal_List, New_Portal_List, Portal_Size, New_Portal_Size):- not(member([New_Portal, _], Portal_List)),
                                                                      New_Portal_List = [[New_Portal, [Place]] | Portal_List],
                                                                      X is random(3), nth0(X, [2, 3, 4], Size),
                                                                      New_Portal_Size = [[New_Portal, Size] | Portal_Size].

update_portal_list(Place, New_Portal, Portal_List, New_Portal_List, PS, PS):- member([New_Portal, Portals], Portal_List),
                                                                      not(member(Place, Portals)),
                                                                      replace([New_Portal, Portals],
                                                                          [New_Portal, [Place | Portals]],
                                                                          Portal_List,
                                                                          New_Portal_List).
% generate portals for of a location unless they are already generated     
generate_portal(Place,      _, Portal_List, Portal_List, Portal_Size,   _, Portal_Size):- member([Place, Portals], Portal_List),
                                              member([Place, Size], Portal_Size),
                                              length(Portals, S),
                                              S >= Size.

generate_portal(Place, Places, Portal_List, NPortal_List, Portal_Size, SE, New_PS):- member([Place, Portals], Portal_List),
                                                            member([Place, Size], Portal_Size),
                                                            Place = [X, Y],
                                                            not(length(Portals, Size)),
                                                            findall(NPlace,
                                                                    (member(NPlace,Places),
                                                                     not(member(NPlace, Portals)),
                                                                     NPlace = [NX, NY], Dist is abs(NX - X) + abs(NY - Y),
                                                                     Dist =< 3 * SE,
                                                                     NPlace \= Place),
                                                            Possible_Moves),
                                                            length(Possible_Moves, L),
                                                            R is random(L),
                                                            nth0(R, Possible_Moves, New_Portal),
                                                            update_portal_list(Place, New_Portal, Portal_List, Updated_Portal_List,
                                                                               Portal_Size, NPS),
                                                            replace([Place, Portals], [Place, [New_Portal | Portals]], Updated_Portal_List, PL),
                                                            generate_portal(Place, Places, PL, NPortal_List, NPS, SE, New_PS).

generate_portal(Place, Places, Portal_List, NPortal_List, Portal_Size, SE, New_PS):- not(member([Place, _], Portal_List)),
                                                            X is random(3), nth0(X, [2, 3, 4], Size),
                                                            generate_portal(Place,
                                                                Places,
                                                                [[Place, []] | Portal_List],
                                                                NPortal_List,
                                                                [[Place, Size] | Portal_Size],
                                                                SE,
                                                                New_PS).


% select best portal based on signal information
select_best_portal(Best_Portal, [], Best_Portal,     _,    _,        _,       _, _).


select_best_portal(Best_Portal, [Portal | RPortals], New_Portal, Energy, Loc, GateEnergy, BestEnergy, Hist):- Portal = [[X, Y],   _,  _],
                                                              member([X, Y, gate], Loc),
                                                              Energy < GateEnergy,
                                                              select_best_portal(Best_Portal,
                                                                  RPortals,
                                                                  New_Portal,
                                                                  Energy,
                                                                  Loc,
                                                                  GateEnergy,
                                                                  BestEnergy,
                                                                  Hist).

select_best_portal(    _, [Portal |        _], Portal, Energy, Loc, GateEnergy,             _, _):- Portal = [[X, Y], _, _],
                                                              member([X, Y, gate], Loc),
                                                              Energy >= GateEnergy.

select_best_portal(    _, [Portal | RPortals], New_Portal, Energy, Loc, GateEnergy, BestEnergy, Hist):- Portal = [[X, Y], SE, _],
                                                              get_hist([X,Y], Hist, H),
                                                              Score is SE - H,
                                                              Score >= BestEnergy,
                                                              select_best_portal(Portal,
                                                                  RPortals,
                                                                  New_Portal,
                                                                  Energy,
                                                                  Loc,
                                                                  GateEnergy,
                                                                  Score,
                                                                  Hist).

select_best_portal(Best_Portal, [Portal | RPortals], New_Portal, Energy, Loc, GateEnergy, BestEnergy, Hist):- Portal = [[X, Y], SE, _],
                                                              get_hist([X,Y], Hist, H),
                                                              Score is SE - H,
                                                              Score < BestEnergy,
                                                              select_best_portal(Best_Portal,
                                                                  RPortals,
                                                                  New_Portal,
                                                                  Energy,
                                                                  Loc,
                                                                  GateEnergy,
                                                                  BestEnergy,
                                                                  Hist).

% make a move
move(_, _,      _,   Loc,   Portal_List,  _,  _,    _,            _, _,  Portal_Size, _, _):- Portal_List \= [],
                                                                                        not((member([[X, Y], Portals], Portal_List),
                                                                                        not(member([X, Y, gate], Loc)),
                                                                                        member([[X, Y], Size], Portal_Size),
                                                                                        length(Portals, LSize),
                                                                                        LSize < Size)),
                                                                                        not((member([[NX, NY], _], Portal_List),
                                                                                       member([NX, NY, gate], Loc))),
                                                                                       write('There is no solution: No route to gate!'), nl.

move(_, Energy,      _,   Loc,   Portal_List,  _,  _, GateEnergy,            _, _,  Portal_Size, _, _):- Portal_List \= [],
                                                                                        not((member([[X, Y], Portals], Portal_List),
                                                                                        not(member([X, Y, gate], Loc)),
                                                                                        member([[X, Y], Size], Portal_Size),
                                                                                        length(Portals, LSize),
                                                                                        LSize < Size)),
                                                                                        not((member([[NX, NY], _], Portal_List),
                                                                                        member([NX, NY, energy], Loc))),
                                                                                        Energy < GateEnergy,
                                                                                        write('There is no solution!'), nl.
                                                                                        
move(Place, Energy,      _, Loc,           _, SE, SG, GateEnergy,            _, Path,  _, Sol, _):- get_coord(Place, X, Y),
                                                                                 member([X, Y, gate], Loc),
                                                                                 Sol = Path,
                                                                                 get_signals([X, Y], Loc, SG, SE, 0, 0, S, G),
                                                                                 print_current_loc_info(X, Y, Energy, G, S), 
                                                                                 Energy > GateEnergy,
                                                                                 write(': Done'), nl,
                                                                                 print('Path is : '),
                                                                                 print_path(Sol),!.

move(Place, Energy, Places, Loc, Portal_List, SE, SG, GateEnergy, PacketEnergy, Path, Portal_Size, Sol, Hist):- get_coord(Place, X, Y),
                      get_signals([X, Y], Loc, SG, SE, 0, 0, S, G),
                      generate_portal([X, Y], Places, Portal_List, NPortal_List, Portal_Size, SE, New_Portal_Size), !,
                      member([[X, Y], Portals], NPortal_List),
                      print_current_loc_info(X, Y, Energy, G, S),
                      write(':  '),
                      compute_signal_for_portals(Portals, Loc, SG, SE, [], NewP),nl,
                      NewP = [H | _],
                      select_best_portal(H, NewP, New_Portal, Energy, Loc, GateEnergy, -100, Hist),!,
                      New_Portal = [[NX, NY], _, _],
                      get_energy([NX, NY], Loc, E, PacketEnergy),
                      New_Energy is E + Energy,
                      get_original_loc([NX, NY], Loc, CurrLoc),
                      replace([NX, NY, energy], [NX, NY], Loc, New_Loc),
                      get_hist([NX, NY], Hist, Visited),
                      NH is Visited + 1,
                      update_hist([NX,NY], NH, Hist, NHist),
                      move([NX, NY],
                        New_Energy,
                        Places, New_Loc, NPortal_List, SE, SG, GateEnergy, PacketEnergy, [CurrLoc | Path], New_Portal_Size, Sol, NHist),!.

% start the quest
go(problema(Loc, Packets, Gate), Sol):- member(Initial, Loc),
                                           Initial = [X, Y, initial],
                                           get_places(Loc, [], Places),
                                           Packets = pachete(SE, PacketEnergy),
                                           Gate = poarta(SG, GateEnergy),
                                           move(Initial, 0, Places, Loc, [], SE, SG, GateEnergy, PacketEnergy, [[X, Y, initial]], [], Sol,
                                           [[X,Y, 1]]).

%go(problema([
%            [15, 15], [43, 5], [9, 25, initial], [25, 25, gate],   
%            [50, 14, energy], [13, 31], [29, 36, energy], [40, 31],
%            [51, 33, energy]
%        ], pachete(17, 10), poarta(20, 25)), Solutie).

