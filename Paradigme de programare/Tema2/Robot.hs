module Robot
where

import Types

-- Initializare matrice
matrixInit :: Size -> Int -> [[Int]]
matrixInit (a, b) val = replicate a $ replicate b val

-- Seteaza o valoare intr-o lista la o anumita pozitie
setValue :: a -> Int -> [a] -> [a]
setValue val 0 (hd : tl) = val : tl
setValue val x (hd : tl) = hd : (setValue val (x - 1) tl)

-- Seteaza o valoare in matrice la un punct de coordonate
setValue' :: Int -> Point -> [[Int]] -> [[Int]]
setValue' val (0, y) (hd : tl) = (setValue val y hd) : tl
setValue' val (x, y) (hd : tl) = hd : (setValue' val ((x - 1), y) tl)

-- Intoarce o valoare dintr-o lista aflata la o anumita pozitie
getValue :: Int -> [a] -> a
getValue 0 (hd : tl) = hd
getValue a (hd : tl) = getValue (a - 1) tl

-- Intoarce o valoare din matrice aflata la un punct de coordonte
getValue' :: Point -> [[Int]] -> Int
getValue' (0, y) (hd : tl) = getValue y hd
getValue' (x, y) (hd : tl) = getValue' ((x - 1), y) tl

-- Incrementeaza valorile din matrice
increaseTime :: [[Int]] -> [[Int]]
increaseTime (hd : []) = (map (1+) hd):[]
increaseTime (hd : tl) = (map (1+) hd) : (increaseTime tl)

-- Intoarce opusul unei variabile de tip Cardinal
opposite :: Cardinal -> Cardinal
opposite x
	| x == N = S
	| x == S = N
	| x == E = W
	| x == W = E

--Intoarce noua pozitie de tip Point pe baza Cardinal si Point
aimPos :: Point -> Cardinal -> Point
aimPos (a,b) x
	| x == N = (a - 1, b)
	| x == S = (a + 1, b)
	| x == W = (a, b - 1)
	| x == E = (a, b + 1)
	| otherwise  = (a, b)

-- Intoarce True sau False in functie daca puctul de coordonate
-- se afla sau nu in dimensiunile minei
itSafe :: Point -> Size -> Bool
itSafe (a, b) (x, y)
	| a >= x || a < 0 = False
	| b >= y || b < 0 = False
	|otherwise 	 = True

-- tipul meu de memorie care contine anumite campuri
data Array2D = Array2D { mapValues :: [[Int]] -- valori detectate din mina la anumite puncte
		       , exploreMap :: [[Int]] --matricea cu timpi de explorare
		       , mapSize :: Size -- dimensiunile minei
		       , currPos :: Point -- pozitia curenta a robotului
		       , lastPos :: Point -- ultima pozitie a robotului
		       , lastDetected :: Int --ultima valoare detectata
		       , lastMove :: Cardinal --ultima miscare facuta de robot
		       } deriving (Show, Eq)

-- Intoarce o lista de tip (Int,Cardinal) reprezentand 
-- valorile de pe matrice si directiile pentru vecinii 
-- unui punct
exploreValues :: Point -> [Cardinal] -> [[Int]] -> Size -> [(Int, Cardinal)]
exploreValues poz [] _ _ = []
exploreValues poz (hd : tl)  exploreMap s = 
	let newPoz = (aimPos poz hd)
	in if (itSafe newPoz s)
		then (getValue' newPoz exploreMap, hd) : (exploreValues poz tl exploreMap s)
		else (exploreValues poz tl exploreMap s)

-- Intoarce directia cu valoarea maxima din lista calculata de functia de mai sus
toUnexplored :: [(Int, Cardinal)] -> [Cardinal] -> (Int, Cardinal) -> Cardinal
toUnexplored [] _ (a,b) = b
toUnexplored ((a,b) : tl) forbidden (c,d) = if (a >= c && not (b `elem` forbidden)) then toUnexplored tl forbidden (a,b)
					else
						toUnexplored tl forbidden (c,d)
	
{-
When the robot enters the mine it receives as input the size of the mine (it
is always placed at (0, 0)). This function should return the initial memory
element of the robot.
-}
--startRobot :: Size -> a
startRobot size = myMemory
	where myMemory = Array2D (matrixInit size 0) (matrixInit size 1000) size (0,0) (0,0) 0 N

-- Intoarce tuplu format din directia si memoria actualizata
-- directia fiind zona cu timpul de explorare cel mai mare (indepartat)
goToUnexplored s cs m = 
	let lastPos = (currPos m)
	    newExploreMap = (setValue' 0 lastPos (increaseTime (exploreMap m)))
            dir = toUnexplored (exploreValues lastPos [N,W,E,S] (exploreMap m) (mapSize m)) cs (-1,S)
	    newcurrPos = aimPos lastPos dir
		in (Just dir, (Array2D (mapValues m) newExploreMap (mapSize m) newcurrPos lastPos s dir))

-- Intoarce tuplu format din directia si memoria actualizata
-- atunci cand zonele vecine au valorile detectate mai mari 
-- sau valoarea detectata la timpul curent este nenula
nextMove s cs m =
	if (s >= (lastDetected m) || s /=0 )
	then do	(if (not ((lastMove m) `elem` cs) && (itSafe (aimPos (currPos m) (lastMove m)) (mapSize m)) 
			&& getValue' (aimPos (currPos m) (lastMove m)) (exploreMap m) > 5)
		then do (let lastPos = (currPos m)
			     newExploreMap = (setValue' 0 lastPos (increaseTime (exploreMap m)))
			     newMapValues = setValue' s lastPos (mapValues m)
			     newlastMove = (lastMove m)
			     newCurrPos = aimPos lastPos newlastMove
			     newlastDetected = s
			     in (Just newlastMove, Array2D newMapValues newExploreMap (mapSize m) newCurrPos lastPos newlastDetected newlastMove))
		else
			goToUnexplored s cs m )
	else do 
		(let lastPos = (currPos m)
		     newExploreMap = (setValue' 0 lastPos (increaseTime (exploreMap m)))
		     newMapValues = setValue' s lastPos (mapValues m)
		     newlastMove = opposite (lastMove m)
		     newCurrPos = aimPos lastPos newlastMove
		     newlastDetected = s
		     in (Just newlastMove, Array2D newMapValues newExploreMap (mapSize m) newCurrPos lastPos newlastDetected newlastMove))
	

{-
At each time step the robot sends a light beam in all 4 cardinal directions,
receives the reflected rays and computes their intensity (the first argument
of the function).

The robot sees nearby pits. The second argument of this function is the list
of neighbouring pits near the robot (if empty, there are no pits).

Taking into account the memory of the robot (third argument of the function),
it must return a tuple containing a new cardinal direction to go to and a new
memory element.

If the cardinal direction chosen goes to a pit or an wall the robot is
destroyed. If the new cell contains minerals they are immediately collected.
-}
--perceiveAndAct :: SVal -> [Cardinal] -> a -> (Action, a)
perceiveAndAct s cs m =
	if (s > 0 || (lastDetected m) > 0)
	then nextMove s cs m	
	else
		goToUnexplored s cs m		
		     
	
		


