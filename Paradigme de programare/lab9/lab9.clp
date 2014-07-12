;; ATENTIE! Desi CLIPS permite definirea de functii,
;; in acest laborator trebuie sa rezolvati toate exercitiile
;; folosind reguli, nu functii.

;; 1. (1p)
;; un program care verifica daca 2 siruri sunt identice
;; (contin aceleasi elemente in aceeasi ordine)



;; fara a pierde sirul
;; desi exista o functie length, nu este permis sa o folositi aici
(deffacts facts (sir 1 2 3 7 8 9 4 5))
;(defrule r1
;	?f <- (sir $?p1) 
;	=>
;	(retract ?f)
;	(assert(lungime 0))
;	(assert(sir d:d $?p1)))
	
;(defrule r2
;	(declare (salience 10))
;	?s<-(sir $?p1 d:d ?rs $?p2)
;	?f<-(lungime ?x)
;	=>
;	(modify ?f (lungime ?x + 1))
;	(modify ?s (sir $?p1 ?rs d:d $?p2)))

;; 3. (1p)
;; un program care elimina duplicatele dintr-un sir
;; doar cand acestea sunt numere pare

;; 4. (1p)
;; un program care realizeaza reuniunea a 2 multimi

;; 5. (1p)
;; un program care realizeaza intersectia a 2 multimi

;; 6. (1p)
;; un program care realizeaza diferenta simetrica a 2 multimi

;; 7. (1p)
;; un program care realizeaza produsul cartezian a 2 siruri

;; 8. (1p)
;; un program care sorteaza un sir de numere
;(defrule r5
;	?g<-(sir $?x ?a ?b $?y)
;	(test(> ?a ?b))
;	=>
;	(retract ?g)
;	(assert (sir $?x ?b ?a $?y)))

;; 9. (1p)
;; un program care realizeaza toate permutarile elementelor unui sir
;(defrule r9
;	(declare (salience 10))
;	(sir $?s1)
;	(sir1 $?s2&:(eq $?s1 $?s2))
;	=>
;	(assert (da)))
;; 10. (1p)
;; un program care inverseaza un sir 
;; (manual, nu cautand functii de inversare)
;(deffacts facts (f 0))
;(defrule r ?f<- (f ?x)
;=> 
;(assert (f (+ 1 ?x))))
(defrule x
	=>
(refresh x))
