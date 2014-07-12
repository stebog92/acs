#lang scheme
(include "Tema1.rkt")

;;;;;;; Definitii lambda0

;; Tipuri booleene
(define true0 '(lambda0 x (lambda0 y x)))
(define false0 '(lambda0 x (lambda0 y y)))
(define and0 '(lambda0 p (lambda0 q ((p q) p))))
(define or0 '(lambda0 p (lambda0 q ((p p) q))))
(define not0 `(lambda0 p ((p ,false0) ,true0)))

;; Liste
(define nil0 `(lambda0 z ,true0))
(define cons0 '(lambda0 x (lambda0 y (lambda0 z ((z x) y)))))
(define car0 `(lambda0 p (p ,true0)))
(define cdr0 `(lambda0 p (p ,false0)))

;; Combinator de punct fix Y
(define Y '(lambda0 f ((lambda0 x (x x)) (lambda0 g (f (lambda0 x ((g g) x)))))))

;; Numere naturale
(define _0 '(lambda0 y (lambda0 x x)))
(define _1 '(lambda0 y (lambda0 x (y x))))
(define _2 '(lambda0 y (lambda0 x (y (y x)))))
(define _3 '(lambda0 y (lambda0 x (y (y (y x))))))
(define _4 '(lambda0 y (lambda0 x (y (y (y (y x)))))))
(define _5 '(lambda0 y (lambda0 x (y (y (y (y (y x))))))))
(define _6 '(lambda0 y (lambda0 x (y (y (y (y (y (y x)))))))))
(define _7 '(lambda0 y (lambda0 x (y (y (y (y (y (y (y x))))))))))
(define succ0 '(lambda0 u (lambda0 y (lambda0 x (y ((u y) x))))))
(define pred0 '(lambda0 u (lambda0 y (lambda0 x (((u (lambda0 g (lambda0 h (h (g y))))) (lambda0 q x)) (lambda0 q q))))))
(define sub0 `(lambda0 m (lambda0 n ((n ,pred0) m))))
(define sum0 '(lambda0 m (lambda0 n (lambda0 f (lambda0 x ((m f) ((n f) x)))))))
(define zero?0 `(lambda0 n ((n (lambda0 x ,false0)) ,true0)))
(define equal_L?0 `(lambda0 x (lambda0 y (,zero?0 ((,sub0 x) y)))))
(define equal_R?0 `(lambda0 x (lambda0 y (,zero?0 ((,sub0 y) x)))))
(define equal?0 `(lambda0 x (lambda0 y ((,and0 ((,equal_L?0 x) y)) ((,equal_R?0 x) y)))))

;; Recursivitate
(define if0 '(lambda0 p (lambda0 a (lambda0 b ((p a) b)))))

;; Fibonacci
(define fibo0
  `(,Y (lambda0 g (lambda0 n 
                           (((,if0 (,zero?0 n))
                             ,_0)
                            (((,if0 ((,equal?0 n) ,_1))
                              ,_1)
                             ((,sum0 (g (,pred0 n))) (g (,pred0 (,pred0 n))))))))))                            
;; Inmultire recursiva
(define mul0
  `(,Y (lambda0 g (lambda0 m
                           (lambda0 n                                    
                                    (((,if0 ((,or0 (,zero?0 m)) (,zero?0 n)))
                                      ,_0)
                                     (((,if0 (,zero?0 (,pred0 m)))
                                      n)
                                     ((,sum0 n) ((g (,pred0 m)) n)))))))))
;; Factorial
(define fact0
  `(,Y (lambda0 g (lambda0 n
                           (((,if0 (,zero?0 n))
                             ,_1)
                            ((,mul0 n) (g (,pred0 n))))))))



;;;;;;; Testare alfa echivalenta
(define (flatten-helper L Result)
  (cond
    ((null? L) (reverse Result))
    ((list? (car L)) (flatten-helper (append (car L) (cdr L)) Result))
    (else (flatten-helper (cdr L) (cons (car L) Result)))))

(define (flatten L)
  (flatten-helper L '()))

(define (unique-syms-helper L R)
  (if (null? L)
      R
      (if (and (not (member (car L) R)) (not (equal? (car L) 'lambda0)))
          (unique-syms-helper (cdr L) (cons (car L) R))
          (unique-syms-helper (cdr L) R))))

(define (unique-syms L)
  (unique-syms-helper (flatten L) '()))

(define (make-unique-syms n)
  (if (= n 0)
      null
      (cons (string->symbol (make-string n #\x)) (make-unique-syms (- n 1)))))

(define (pair-up L)
  (let* ((unique-list (unique-syms L)) (n (length unique-list)) (the-syms (make-unique-syms n)))
    (let iter ((my-L unique-list) (my-syms the-syms) (R null))
      (if (null? my-L)
          R
          (iter (cdr my-L) (cdr my-syms) (cons (cons (car my-L) (car my-syms)) R))))))

(define (key->value k M)
  (if (null? M)
      k
      (if (equal? k (caar M))
          (cdar M)
          (key->value k (cdr M)))))

(define (my-replace L M)
  (if (null? L)
      L
      (if (list? (car L))
          (cons (my-replace (car L) M) (my-replace (cdr L) M))
          (cons (key->value (car L) M) (my-replace (cdr L) M)))))

(define (replacer L)
  (my-replace L (pair-up L)))

(define (alpha-equiv? L1 L2)
  (equal? (replacer L1) (replacer L2)))


;;;;;; Teste

(define punctaj 0)

;; 10p
(define test1 (eval0 '((lambda0 x x) (lambda0 y y))))
(define test2 (eval0 '(((lambda0 x (lambda0 y (x y))) (lambda0 a a)) (lambda0 b b))))
(define test3 (eval0 '((lambda0 x (lambda0 y (lambda0 x (x x)))) (lambda0 x x)))) 
(define test4 (eval0 '((lambda0 x ((x (lambda0 x (x x))) (lambda0 x x))) (lambda0 x (lambda0 y x)))))
(define test5 (eval0 '((lambda0 x ((x (lambda0 x (x x))) (lambda0 x x))) (lambda0 x (lambda0 y y)))))

(define rez1 '(lambda0 y y))
(define rez2 '(lambda0 b b))
(define rez3 '(lambda0 y (lambda0 x (x x))))
(define rez4 '(lambda0 x (x x)))
(define rez5 '(lambda0 x x))

;; 10p
(define test6 (eval0 `(,not0 ,true0)))
(define test7 (eval0 `((,and0 ,true0) ,false0)))
(define test8 (eval0 `((,or0 ,true0) ,false0)))
(define test9 (eval0 `((,and0 ((,or0 ((,and0 ,true0) ,true0)) ((,and0 (,not0 ,false0)) ,true0))) ,false0)))
(define test10 (eval0 `((,or0 ((,and0 ((,and0 ,true0) ,true0)) ((,and0 (,not0 ,false0)) (,not0 ,true0)))) ,false0)))

(define rez6 '(lambda0 x (lambda0 y y)))
(define rez7 '(lambda0 x (lambda0 y y)))
(define rez8 '(lambda0 x (lambda0 y x)))
(define rez9 '(lambda0 x (lambda0 y y)))
(define rez10 '(lambda0 x (lambda0 y y)))

;; 10p
(define test11 (eval0 `((,cons0 ,_1) ,nil0)))
(define test12 (eval0 `(,car0 ((,cons0 ,_1) ,nil0))))
(define test13 (eval0 `(,cdr0 (,cdr0 (,cdr0 ((,cons0 ,_0) ((,cons0 ,_1) ((,cons0 ,_2) ,nil0))))))))
(define test14 (eval0 `(,car0 (,cdr0 ((,cons0 ,_0) ((,cons0 ,true0) ((,cons0 ,_1) ,nil0)))))))
(define test15 (eval0 `(,car0 (,cdr0 (,cdr0 ((,cons0 ,_0) ((,cons0 ,true0) ((,cons0 ,_1) ,nil0))))))))

(define rez11a '(lambda0 z ((z (lambda0 g7418 (lambda0 x (g7418 x)))) (lambda0 z (lambda0 x (lambda0 y x))))))
(define rez11b '(lambda0 z ((z (lambda0 g7418 (lambda0 x (g7418 x)))) (lambda0 q (lambda0 w (lambda0 r w))))))
(define rez11c '(lambda0 z ((z (lambda0 g7418 (lambda0 x (g7418 x)))) (lambda0 z (lambda0 w (lambda0 r w))))))
(define rez11d '(lambda0 z ((z (lambda0 g7418 (lambda0 x (g7418 x)))) (lambda0 q (lambda0 x (lambda0 r x))))))
(define rez11e '(lambda0 z ((z (lambda0 y (lambda0 x (y x)))) (lambda0 z (lambda0 x (lambda0 y x))))))
(define rez11f '(lambda0 z ((z (lambda0 y (lambda0 x (y x)))) (lambda0 q (lambda0 x (lambda0 y x))))))
(define rez11g '(lambda0 z ((z (lambda0 y (lambda0 x (y x)))) (lambda0 q (lambda0 w (lambda0 y w))))))
(define rez11h '(lambda0 z ((z (lambda0 y (lambda0 x (y x)))) (lambda0 z (lambda0 w (lambda0 y w))))))
(define rez12 '(lambda0 g10592 (lambda0 x (g10592 x))))
(define rez13 '(lambda0 z (lambda0 x (lambda0 y x))))
(define rez14 '(lambda0 x (lambda0 g10594 x)))
(define rez15 '(lambda0 g10597 (lambda0 x (g10597 x))))

;; 20p
(define test16 (eval0 `(,succ0 ,_0)))
(define test17 (eval0 `(,succ0 ,_1)))
(define test18 (eval0 `(,succ0 (,succ0 ,_3))))
(define test19 (eval0 `(,pred0 ,_5)))
(define test20 (eval0 `(,pred0 (,succ0 ,_2))))
(define test21 (eval0 `((,sum0 ,_5)  ,_6)))
(define test22 (eval0 `((,sub0 ,_7)  ,_2)))
(define test23 (eval0 `((,equal?0 ,_3)  ,_4)))
(define test24 (eval0 `((,equal?0 ,_3)  ,_3)))
(define test25 (eval0 `((,equal?0 ,_3)  ,_3)))

(define rez16 '(lambda0 y (lambda0 x (y x))))
(define rez17 '(lambda0 y (lambda0 x (y (y x)))))
(define rez18 '(lambda0 y (lambda0 x (y (y (y (y (y x))))))))
(define rez19 '(lambda0 y (lambda0 x (y (y (y (y x)))))))
(define rez20 '(lambda0 y (lambda0 x (y (y x)))))
(define rez21 '(lambda0 f (lambda0 x (f (f (f (f (f (f (f (f (f (f (f x))))))))))))))
(define rez22 '(lambda0 y (lambda0 g17745 (y (y (y (y (y g17745))))))))
(define rez23 '(lambda0 g17779 (lambda0 g17762 g17762)))
(define rez24 '(lambda0 x (lambda0 g17800 x)))
(define rez25 '(lambda0 x (lambda0 g17833 x)))

;; 35p
(define test26 (eval0 `((,mul0 ,_2)  ,_2)))
(define test27 (eval0 `((,mul0 ,_3)  ,_5)))
(define test28 (eval0 `(,fact0 ,_2)))
(define test29 (eval0 `(,fact0 ,_3)))
(define test30 (eval0 `(,fibo0 ,_0)))
(define test31 (eval0 `(,fibo0 ,_1)))
(define test32 (eval0 `(,fibo0 ,_2)))
(define test33 (eval0 `(,fibo0 ,_3)))
(define test34 (eval0 `(,fibo0 ,_4)))
(define test35 (eval0 `(,fibo0 ,_5)))

(define rez26 '(lambda0 f (lambda0 x (f (f (f (f x)))))))
(define rez27 '(lambda0 f (lambda0 x (f (f (f (f (f (f (f (f (f (f (f (f (f (f (f x))))))))))))))))))
(define rez28 '(lambda0 f (lambda0 x (f (f x)))))
(define rez29 '(lambda0 f (lambda0 x (f (f (f (f (f (f x)))))))))
(define rez30 '(lambda0 y (lambda0 x x)))
(define rez31 '(lambda0 y (lambda0 x (y x))))
(define rez32 '(lambda0 f (lambda0 x (f x))))
(define rez33 '(lambda0 f (lambda0 x (f (f x)))))
(define rez34 '(lambda0 f (lambda0 x (f (f (f x))))))
(define rez35 '(lambda0 f (lambda0 x (f (f (f (f (f x))))))))

;; 15p
(define test36 (eval0 '((lambda0 x (lambda0 x (x x))) x)))
(define test37 (eval0 '((lambda0 x (lambda0 y (x y))) y)))
(define test38 (eval0 '((lambda0 x ((lambda0 y y) (lambda0 z (z x)))) (z y))))
(define test39 (eval0 '((lambda0 x ((lambda0 y y) (lambda0 z (x z)))) (z y))))
(define test40 (eval0 '((lambda0 x ((x (lambda0 y (y y))) (lambda0 z z))) z)))

(define rez36 '(lambda0 g25181 (g25181 g25181)))
(define rez37 '(lambda0 g25188 (y g25188)))
(define rez38 '(lambda0 g25190 (g25190 (z y))))
(define rez39 '(lambda0 g25192 ((z y) g25192)))
(define rez40 '((z (lambda0 y (y y))) (lambda0 g25193 g25193)))


;;;;;; Evaluarea testelor

(define (test-it test rez pctj idx)
  (if (alpha-equiv? test rez)
      (begin (set! punctaj (+ punctaj pctj)) (display "Test ") (display idx) (display " - PASSED  Punctaj curent: ") (display punctaj) (display "\n")) 
      (begin (display "Test ") (display idx) (display " - FAILED  Punctaj curent: ") (display punctaj) (display "\n"))))

(define (special-test test Lrez pctj idx)
  (if (null? Lrez)
      (begin (display "Test ") (display idx) (display " - FAILED  Punctaj curent: ") (display punctaj) (display "\n"))
      (if (alpha-equiv? test (car Lrez))
          (begin (set! punctaj (+ punctaj pctj)) (display "Test ") (display idx) (display " - PASSED  Punctaj curent: ") (display punctaj) (display "\n")) 
          (special-test test (cdr Lrez) pctj idx))))


;; 10p
(test-it test1 rez1 2 1)
(test-it test2 rez2 2 2)
(test-it test3 rez3 2 3)
(test-it test4 rez4 2 4)
(test-it test5 rez5 2 5)

;; 10p
(test-it test6 rez6 2 6)
(test-it test7 rez7 2 7)
(test-it test8 rez8 2 8)
(test-it test9 rez9 2 9)
(test-it test10 rez10 2 10)

;; 10p
(special-test test11 `(,rez11a ,rez11b ,rez11c ,rez11d ,rez11e ,rez11f ,rez11g ,rez11h) 2 11)
(test-it test12 rez12 2 12)
(test-it test13 rez13 2 13)
(test-it test14 rez14 2 14)
(test-it test15 rez15 2 15)

;; 20p
(test-it test16 rez16 2 16)
(test-it test17 rez17 2 17)
(test-it test18 rez18 2 18)
(test-it test19 rez19 2 19)
(test-it test20 rez20 2 20)
(test-it test21 rez21 2 21)
(test-it test22 rez22 2 22)
(test-it test23 rez23 2 23)
(test-it test24 rez24 2 24)
(test-it test25 rez25 2 25)

;; 35p
(test-it test26 rez26 3.5 26)
(test-it test27 rez27 3.5 27)
(test-it test28 rez28 3.5 28)
(test-it test29 rez29 3.5 29)
(test-it test30 rez30 3.5 30)
(test-it test31 rez31 3.5 31)
(test-it test32 rez32 3.5 32)
(test-it test33 rez33 3.5 33)
(test-it test34 rez34 3.5 34)
(test-it test35 rez35 3.5 35)


;; 15p
(test-it test36 rez36 3 36)
(test-it test37 rez37 3 37)
(test-it test38 rez38 3 38)
(test-it test39 rez39 3 39)
(test-it test40 rez40 3 40)


(display "Punctaj final: ") (display punctaj) (display "/100.0\n")
