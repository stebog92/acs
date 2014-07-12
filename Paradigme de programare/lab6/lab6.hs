{- 1. (1.5p)
   Sa se defineasca urmatoarele functii:
   allEqual :: Integer -> Integer -> Integer -> Bool 
	testeaza daca toate cele 3 argumente
	sunt egale intre ele
   allDifferent :: Integer -> Integer -> Integer -> Bool 
   howManyEqual :: Integer -> Integer -> Integer -> Integer
      intoarce numarul maxim de numere egale 
      intre ele (1, 2 sau 3).
-}
allEqual a b c = a == b && a == c && b == c
allDifferent a b c = a /= b && a /= c && b /= c
howManyEqual a b c 
	|allEqual a b c == True = 3
	|allDifferent a b c == True = 0
	|otherwise = 2
{- 2. (3p)
   Sa se defineasca urmatoarele functii:
   member :: a -> [a] -> Bool
   allEqualInList :: [a] -> Bool
   allDifferentInList :: [a] -> Bool
   unZip :: [ (a, b) ] -> ( [a], [b] )
   myZipWith :: (a -> b -> c) -> [a] -> [b] -> [c]
   myZip :: [a] -> [b] -> [ (a, b) ]
-}

member _ [] = False
member a (b:l) = if (a /= b) then member a l
			else True

allEqualInList [a] = True
allEqualInList (a:b:l) = if (a == b) then 
					True && (allEqualInList (b:l))
					else
					False
allDifferentInList [] = True
allDifferentInList (a:b)= (foldl (&&) True (map (/= a) b)) && allDifferentInList b

unZip [] = ([],[])
unZip ((a,b):l) = ((a:(fst f)),(b:(snd f)))
		where
			f = unZip l
myZipWith f [] _ = []
myZipWith f _ [] = []
myZipWith f (a:l) (b:p) = (f a b) : myZipWith f l p

{- 3. (3p)
   Implementati-va propriul map si propriul filter,
   folosind NEAPARAT list comprehensions.
   De asemenea, implementati:
   foldleft :: (a -> b -> a) -> a -> [b] -> a
      foldleft primeste:
       o functie (de tip a -> b -> a)
	 o valoare de pornire numita "seed" (tip a) 
	 o lista (tip [b])
      si aplica functia de la stanga la dreapta pe lista
      mai intai functie de seed si primul element din lista => rezultat1
      apoi functie de rezultat1 si al doilea element din lista => rezultat2
      etc
   foldright :: (a -> b -> b) -> b -> [a] -> b
      acelasi lucru, dar merge de la dreapta catre stanga
   splitAfter :: Integer -> [a] -> ( [a], [a] )
	splitAfter n lst imparte lista lst in 2 liste,
	prima continand primele n elemente,
	a doua continand restul listei
   cartesianProduct :: [a] -> [b] -> [ (a, b) ]
-}
myMap f l = [f x | x <- l]
myFilter p l = [x | x <- l, p x]

foldleft f seed [] = seed
foldleft f seed (a:l) = foldleft f (f seed a) l

foldright f seed [] = seed
foldright f seed l = foldright f (f (last l) seed) (init l)

splitAfter 0 l = ([],l)
splitAfter _ [] = ([],[])
splitAfter n (a:l) = ((a:(fst y)), (snd y))
		where
			y = splitAfter (n - 1) l
{- 4. (2.5p)
   Sa se defineasca functia:
   subst :: String -> String -> String -> String
      subst "frumos" "destept" "Ce laborator frumos!" = "Ce laborator destept!" 
      (inlocuieste primul string cu al 2lea peste tot in al 3lea)
      stringurile sunt liste de caractere si pot fi tratate ca liste
      util: operatia append, notata in haskell cu ++
-}

subst a b [] = []
subst a b c 
	|x == a = b ++ subst a b y
	|otherwise = (head x) : subst a b ((tail x) ++ y)
	where
		(x,y) = splitAfter (length a) c
