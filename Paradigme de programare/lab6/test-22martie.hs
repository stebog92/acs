
-- Fie urmatoarele definitii Haskell:

inversesFrom n = 1/n : inversesFrom (n+1)

result = drop 1 ((take 3) (inversesFrom 0))

{-

Ce se intampla cand cerem sa se afiseze result ?

a) Programul ruleaza la infinit pentru ca (inversesFrom 0) nu are nicio conditie de oprire

b) Programul va da o eroare de tipul "Division by zero" pentru ca primul element
este 1/0.

c) Se va afisa valoarea lui result care este [1.0,0.5]

Motivati pe scurt raspunsul vostru.

-}