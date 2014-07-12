;; Expresii pt controlul domeniului de vizibilitate a variabilelor
;;----------------------------------------------------------------

;; In toate exercitiile de mai jos va veti ajuta de aceste expresii pt
;; - a evita sa evaluati ceva de mai multe ori
;; - a evita sa pasati parametri inutili intr-o iteratie (care nu
;;   se schimba pe parcursul iteratiei)

;; Incercati sa implementati cu maxim de eficienta.
;; Solutiile vor fi testate cu functia time pe date de dimensiune mare.

;; 1. (3p)
;; sa se implementeze functia group-factors
;; (group-factors n) da lista ordonata a 
;; factorilor primi ai lui n grupati cu puterea la care ei apar
;; (group-factors 123456789101112) =>
;; ((2 . 3) (3 . 1) (2437 . 1) (2110805449 . 1))
(define (range a b)
  (range-helper a b '()))

(define (range-helper a b Result)
  (if (> a b)
      Result
      (range-helper a (- b 1) (cons b Result))))

(define forall?
  (lambda (p L)
    (if (null? L) #t
        (and (p (car L)) (forall? p (cdr L))))))

(define (prime? n)
  (if (< n 2)
      #f
      (forall? (lambda (d) (> (modulo n d) 0))
               (range 2 (floor (sqrt n))))))

(define times
  (lambda (x y)
    (if (not (= (modulo y x) 0)) 0
        (+ 1 (times x (/ y x))))))



(define group-factors
  (lambda (n)
    (filter (lambda (x) (and (prime? (car x)) (not (= (cdr x) 0))))
                            (foldl (lambda (x y) (cons (cons x (times x n)) y)) '() (range 2 (/ n 2))))))
;; 2. (4p)
;; sa se implementeze functia proper-divisors
;; (proper-divisors n) da lista ordonata a divizorilor proprii ai lui n
;; divizor propriu al lui n = orice divizor al lui n, mai putin n
;; (proper-divisors 12) => (1 2 3 4 6)

;; 3. (3p)
;; 2 numere x si y formeaza o pereche daca sunt diferite si 
;; suma divizorilor proprii ai lui x este y, iar suma divizorilor proprii ai 
;; lui y este x. 
;; ex: d(1184)=[1,2,4,8,16,32,37,74,148,296,592] 
;;     si suma d(1184) = 1210
;;     d(1210) = [1,2,5,10,11,22,55,110,121,242,605] 
;;     si suma d(1210) = 1184, deci ele formeaza o pereche.
;; sa se afle toate perechile in care numerele sunt mai mici ca n.
;; ex: pt n=10000:
;;     ((284 . 220) (1210 . 1184) (2924 . 2620) (5564 . 5020) (6368 . 6232))