module Definitions where

{- 1p
1. Construiti un TDA List polimorfic (asemanator cu cel din laboratorul
precedent) si implementati pentru acesta functiile pentru
relatie de ordine (din clasa Ord) si egalitate (din clasa Eq).
Se considera ca pentru listele L1 si L2 avem:
	L1 < L2 <=> numarul de elemente unice din L1 < numarul de 
	elemente unice din L2
	
	L1 = L2 <=> L1 este o permutare a elementelor din L2
-}

{- 2p
2. Construiti un TDA arbore binar de cautare polimorfic.
Implementati urmatoarele functii pentru acest TDA:
	insert :: a -> (BSTree a) -> (BSTree a)
		insereaza un element intr-un arbore
	list2Tree :: [a] -> (BSTree a)
		construieste un arbore din elementele unei liste
	search :: a -> (BSTree a) -> Bool
		verifica existenta unui element intr-un arbore
	del :: a -> (BSTree a) -> (BSTree a)
		sterge un element dintr-un arbore, daca acesta exista
-}

{- 2p
3. Realizati clasa Foldable ce are operatia fold.
	fold :: (b -> a -> b) -> b -> t -> b
   Definiti instantele acestei clase pentru (List a)
   si (BSTree a).
   Functia primita ca parametru de fold se aplica pe init 
   (avand tip b) si pe un element de tip a continut intr-un
   (List a) sau intr-un (BSTree a) si intoarce o valoare 
   de tip b.
   In cazul TDA (List a) fold are comportament identic cu foldl
   pentru [a].
   In cazul TDA (BSTree a) rezultatul este acumulat dupa regulile:
		pentru arbore vid se intoarce valoarea init
		in rest (f radacina (f (fold subarbore stang) (fold subarbore drept)))
-}

{- 2p
4. Realizati clasa Mergeable ce are operatia merge.
	merge :: (a -> a -> Bool) -> t a -> t a -> t a
   Dupa cum observati din definitia functiei, trebuie
   sa folositi polimorfism de nivel inalt.
   Definiti instantele acestei clase pentru (List a)
   si (BSTree a).
   In cazul TDA (List a) merge va interclasa listele
   dupa relatia de ordine data.
   In cazul TDA (BSTree a) merge va insera toate elementele
   din al doilea arbore dat ca parametru in primul,
   dupa relatia de ordine data.
-}

{- 3p
5. Utilizand tipurile de date si operatiile definite
   anterior construiti si afisati un (BSTree (List a)).
   Implementati o functie care primeste ca parametru 
   un arbore binar pentru care nodurile implementeaza
   operatia de merge si intoarceti rezultatul interclasarii
   tuturor nodurilor.
   Testati cu un (BSTree (List a)).
   Testati cu un (BSTree (BSTree a)).
-}

 