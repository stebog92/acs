;; Eficientizarea proceselor recursive in Scheme
;;----------------------------------------------
(require (lib "trace.ss"))

;; o functie ajutatoare pt testele de eficienta
;; functia produce o lista cu numerele naturale din intervalul a..b
(define (range a b)
  (range-helper a b '()))

(define (range-helper a b Result)
  (if (> a b)
      Result
      (range-helper a (- b 1) (cons b Result))))

;; 1. 
;; de implementat inversarea unei liste (cu recursivitate pe coada)

;; REZOLVARE
;; ca sa pastram reverse2 cu un argument, vom folosi un helper
;; care poate fi recursiv pe coada (deci iterativ) si va primi 2 argumente
(define reverse2
  (lambda (L)
    (rev2-helper L '())))

(define rev2-helper
  (lambda (parte-ramasa parte-inversata)
    (if (null? parte-ramasa)
        parte-inversata
        (rev2-helper (cdr parte-ramasa)
                     (cons (car parte-ramasa) parte-inversata)))))

(display "1. (reverse2 '(a b c d e f g))\n\t")
(reverse2 '(a b c d e f g))

;; Explicatie: pornesc din starea "mai am de inversat toata lista"
;; si "pana acum am inversat nimic", si iau cate un element de la
;; inceputul partii ramase, si il pun la inceputul partii inversate.
;; Cand nu mai am nimic ramas de prelucrat, inseamna ca am acumulat
;; toata lista in parte-inversata, si pot sa intorc rezultatul final.

;; 2.
;; de implementat functia take (cu recursivitate pe stiva)
;; (take n L) da lista primelor n elemente din lista L
;; (take 5 '(1 2 3 4 5 6 7 8)) => '(1 2 3 4 5)

;; REZOLVARE
(define (take n L)
  (if (or (null? L) (<= n 0))
      '()
      (cons (car L) (take (- n 1) (cdr L)))))

(display "2. (take 5 '(1 2 3 4 5 6 7 8))\n\t")
(take 5 '(1 2 3 4 5 6 7 8))

;; Explicatie: a lua primele n elemente din L e echivalent cu a lua
;; intai primul element si apoi urmatoarele n-1
;; acest mod de a gandi genereaza un apel recursiv care se termina
;; --- ori cand am luat toate cele n elemente: (<= n 0)
;; --- ori cand am epuizat lista: (null? L) - pt cereri ca (take 5 '(1 2 3))

;; 3.
;; de implementat take (cu recursivitate pe coada)

;; REZOLVARE
(define (take2 n L)
  (take2-helper n L '()))

(define (take2-helper n L Result)
  (if (or (null? L) (<= n 0))
      (reverse Result)
      (take2-helper (- n 1) (cdr L) (cons (car L) Result))))

(display "3. (take2 5 '(1 2 3 4 5 6 7 8))\n\t")
(take2 5 '(1 2 3 4 5 6 7 8))


;; Observatii
;; - rezultatul este construit in variabila de acumulare Result 
;;   in ordine inversa, de aceea necesita aplicarea unui reverse
;;   inainte de return
;; - este preferabil sa facem asa decat sa adaugam de fiecare data
;;   (car L) la coada lui Result cu (append Result (list (car L)))
;;   varianta cu append ar realiza in fiecare pas o copiere a 
;;   listei Result (mult mai ineficient decat cons), pe cand reverse
;;   realizeaza o singura parcurgere, la sfarsit

;; 4.
;; de implementat functia replicate-to-size (cu recursivitate pe stiva)
;; (replicate-to-size n L) multiplica lista L pana ajunge la
;; o lista de dimensiune n
;; (replicate-to-size 10 '(a b c)) => '(a b c a b c a b c a)
;; (replicate-to-size 2 '(a b c)) => '(a b)

;; REZOLVARE
(define (replicate-to-size n L)
  (rts n L (length L)))

(define (rts n L length-L)
  (if (<= n length-L)
      (take n L)
      (append L (rts (- n length-L) L length-L))))


(display "4. (replicate-to-size 10 '(a b c))\n\t")
(replicate-to-size 10 '(a b c))

;; Explicatie
;; - recursivitatea este dupa n (= cate elemente mai am de reprodus)
;; in fiecare pas adaug cate o lista L intreaga, ceea ce imi lasa
;; inca n-lungime(L) elemente de reprodus in rezultatul final
;; - foarte probabil, in pasul final nu imi ramane o lista L intreaga
;; de adaugat la rezultat, asa ca este necesar sa iau fix un numar
;; de n elemente (n ramas < lungime(L)), folosind functia take
;; - programarea functionala este despre a folosi si refolosi functii
;; - de aceea acestea trebuie scrise cat mai general, favorizand 
;; reutilizarea de cod

;; Observatii
;; - preferam ca L sa fie primul parametru al lui append (si nu al 2lea)
;;   intrucat L este lista mai scurta (iar recursivitatea implementata de
;;   append presupune copierea integrala a primelor liste)
;; - am calculat lungimea lui L doar prima oara, dand-o ca parametru unei
;;   noi functii (evitam astfel sa facem acelasi calcul de multe ori)

;; 5.
;; de implementat functia replicate-to-size (cu recursivitate pe coada)

;; REZOLVARE
(define (replicate-to-size2 n L)
  (rts2 n L (length L) '()))

(define (rts2 n L length-L Result)
  (if (<= n length-L)
      (append Result (take n L))
      (rts2 (- n length-L) L length-L (append L Result))))

(display "5. (replicate-to-size2 10 '(a b c))\n\t")
(replicate-to-size2 10 '(a b c))

;; Observatii
;; - acesta este replicate-to-size cu iteratie dupa n
;; - se putea itera si modificand L, ca in varianta de mai jos,
;;   dar cititi observatia de dupa!
(define (replicate-to-size2-var n L)
  (rts2-var n L (length L)))

(define (rts2-var n L length-L)
  (if (<= n length-L)
      (take n L)
      (rts2-var n (append L L) (* 2 length-L))))

;; Observatia de dupa :-)
;; - in aceasta a 2a varianta, take-ul final este f costisitor
;;   (am de luat f multe elemente dintr-o lista f mare)
;; - testati aceasta folosind apelurile comentate mai jos
;;   (s-ar putea sa fie nevoie de numere mai mari pentru a vedea
;;   diferentele, daca aveti calculatoare performante)
;; - daca nu va vine sa credeti ca e din cauza take-ului,
;;   inlocuiti in ambele variante take-ul final cu (display 'gata)
;;   si apelati iarasi cate un time pe cele 2 apeluri

;(time (length (replicate-to-size2 1234567 (range 1 1000))))
;(time (length (replicate-to-size2-var 1234567 (range 1 1000))))

;; 6. (1p)
;; de implementat functia zip (cu recursivitate pe stiva)
;; (zip L1 L2) intoarce o lista in care elementele sunt perechi
;; de elemente de pe pozitii echivalente din L1 si L2
;; lista produsa de zip are lungimea celei mai scurte liste 
;; dintre L1 si L2
;; (zip '(1 2 3 4) '(a b c d e)) => '((1 a) (2 b) (3 c) (4 d))
(define (zip L1 L2)
  (if (or (null? L1) (null? L2))
      '()
      (cons (list (car L1) (car L2)) (zip (cdr L1) (cdr L2)))))

(display "6. (zip '(1 2 3 4) '(a b c d e))\n\t")
(zip '(1 2 3 4) '(a b c d e))

;; 7. (1p)
;; de implementat functia zip (cu recursivitate pe coada)

(define (zip-iter L1 L2)
  (zip-iter-helper L1 L2 '()))

(define (zip-iter-helper L1 L2 acum)
  (if (or (null? L1) (null? L2))
      (reverse acum)
      (zip-iter-helper (cdr L1) (cdr L2) (cons (list (car L1) (car L2)) acum))))

(display "7. (zip-iter '(1 2 3 4) '(a b c d e))\n\t")
(zip-iter '(1 2 3 4) '(a b c d e))


;; 8.
;; de implementat functia deep-length (cu recursivitate pe coada)
;; (deep-length L) numara elementele din lista L, inclusiv pe cele care 
;; apar in eventualele subliste
;; (deep-length '(1 (2 (3 4)) 5 (6) (7 ((8 9) 10)))) => 10
;; util: functia list? care verifica daca argumentul sau este o lista

;; REZOLVARE
(define (deep-length L)
  (dl-helper L 0))

(define (dl-helper L result)
  (cond
    ((null? L) result)
    ((list? (car L)) (dl-helper (append (car L) (cdr L)) result))
    (else (dl-helper (cdr L) (+ 1 result)))))

(display "8. (deep-length '(1 (2 (3 4)) 5 (6) (7 ((8 9) 10))))\n\t")
(deep-length '(1 (2 (3 4)) 5 (6) (7 ((8 9) 10))))

;; Explicatie
;; - pentru a implementa aceasta functie cu recursivitate pe coada 
;;   am folosit un truc: de fiecare data cand primul element este 
;;   o lista, elementele acesteia sunt adaugate la inceputul listei mari
;;   (practic se scapa de un rand de paranteze) si se continua cu noua
;;   lista (decomentati trace-ul de mai jos ca sa fie clar cum evolueaza)

;(trace dl-helper)
;(deep-length '(1 (2 (3 4)) 5 (6) (7 ((8 9) 10))))

;; 9.
;; de implementat functia flatten
;; (flatten L) liniarizeaza lista in cazul in care aceasta contine subliste
;; (flatten '(1 (2 (3 4)) 5 (6) (7 ((8 9) 10)))) => '(1 2 3 4 5 6 7 8 9 10)

;; REZOLVARE
;; am folosit acelasi truc ca mai sus
(define (flatten L)
  (flatten-helper L '()))

(define (flatten-helper L Result)
  (cond
    ((null? L) (reverse Result))
    ((list? (car L)) (flatten-helper (append (car L) (cdr L)) Result))
    (else (flatten-helper (cdr L) (cons (car L) Result)))))

(display "9. (flatten '(1 (2 (3 4)) 5 (6) (7 ((8 9) 10))))\n\t")
(flatten '(1 (2 (3 4)) 5 (6) (7 ((8 9) 10))))

;; 10. (bonus)
;; de implementat functia split-to-sublists
;; (split-to-sublists L k) imparte L in liste de dimensiune k
;; (split-to-sublists '(1 2 3 4 5 6 7 8) 3) => '((1 2 3) (4 5 6) (7 8))
(define (take-and-drop n L)
  (tad-helper n L '()))

(define (tad-helper n L Taken)
  (if (or (null? L) (<= n 0))
      (cons (reverse Taken) L)
      (tad-helper (- n 1) (cdr L) (cons (car L) Taken))))

(define (split-to-sublists L k)
  (sts-helper L k '()))

(define (sts-helper L k Result)
  (if (null? L)
      (reverse Result)
      (let* ((TaD (take-and-drop k L))
             (Taken (car TaD))
             (Dropped (cdr TaD)))
        (sts-helper Dropped k (cons Taken Result)))))

(display "10. (split-to-sublists '(1 2 3 4 5 6 7 8) 3)\n\t")
(split-to-sublists '(1 2 3 4 5 6 7 8) 3)

;; Explicatie
;; - functia take-and-drop imi intoarce, dupa o singura parcurgere
;;   de n elemente, atat primele n elemente din lista cat si restul
;;   listei (fara primele n elemente)
;; - este doar un take iterativ modificat sa intoarca si restul listei
;; - split-to-sublists ia cate n elemente din lista initiala (care se pun
;;   in lista Taken), le adauga la rezultat si repeta procedeul pe 
;;   restul listei (lista Dropped)
;; - am folosit (cons Taken Result) - cons primeste un element si o lista,
;;   cand elementul este o lista (lista Taken), aceasta se adauga ca
;;   element la inceputul listei Result

;; Observatie
;; - o rezolvare care face separat take si drop (in 2 parcurgeri diferite)
;;   este un pic mai ineficienta
;; - puteti verifica folosind testele comentate mai jos
(define (drop n L)
  (if (or (null? L) (<= n 0))
      L
      (drop (- n 1) (cdr L))))

(define (split-to-sublists-var L k)
  (sts-helper-var L k '()))

(define (sts-helper-var L k Result)
  (if (null? L)
      (reverse Result)
      (let* ((Taken (take2 k L))
             (Dropped (drop k L)))
        (sts-helper-var Dropped k (cons Taken Result)))))

;(time (length (split-to-sublists (range 1 5000000) 3)))
;(time (length (split-to-sublists-var (range 1 5000000) 3)))
