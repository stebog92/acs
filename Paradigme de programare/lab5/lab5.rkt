(define empty-stream '())
(define-syntax cell
  (syntax-rules ()
    ((_ term rest)
     (cons term (delay rest)))))

(define head car)
(define tail (lambda (s) (force (cdr s))))
(define empty-stream? null?)

(define take-stream
  (lambda (n s)
    (if (or (zero? n) (empty-stream? s))
        '()  ;; lista vida pt ca take trebuie sa intoarca o LISTA
        (cons (head s) (take-stream (- n 1) (tail s))))))

(define drop-stream
  (lambda (n s) 
    (if (or (empty-stream? s) (zero? n))
        s  ;; fluxul vid pt ca drop trebuie sa intoarca un FLUX
        (drop-stream (- n 1) (tail s)))))

(define (map-stream f s)
  (cond ((empty-stream? s) s)
        (else (cell (f (head s)) (map-stream f (tail s))))))

(define (filter-stream p s)
  (cond ((empty-stream? s) s)
        ((p (head s)) (cell (head s) (filter-stream p (tail s))))
        (else (filter-stream p (tail s)))))

(define (zip-with-stream f s1 s2)
  (cond ((or (empty-stream? s1) (empty-stream? s2)) empty-stream)
        (else (cell (f (head s1) (head s2)) (zip-with-stream f (tail s1) (tail s2))))))

;; Observati urmatoarea definitie a fluxului puterilor lui 2
;(define powers-of-2-stream
;  (cell 1
;        (zip-with-stream + powers-of-2-stream powers-of-2-stream)))
;
;(take-stream 10 powers-of-2-stream)

;; 1. (2p)
;; construiti fluxul termenilor din seria care aproximeaza numarul e
;; obtineti apoi diverse aproximari pentru e adunand un numar
;; mai mic sau mai mare de termeni din acest flux
;; obs: asta nu inseamna sa construiti fluxul sumelor direct

;; 2. (2p)
;; sa se implementeze fluxul numerelor lui fibonacci si sa se
;; determine primul numar fibonacci care are k cifre
;; (first-fibonacci 2) => 13 (pt k=2)
;; (first-fibonacci 1000) => 10700..............27816
;; BONUS (1p): DACA REALIZATI O DEFINITIE RECURSIVA PENTRU FLUX, 
;; SIMILAR CU FELUL IN CARE AM DEFINIT FLUXUL PUTERILOR LUI 2

;; 3. (1p)
;; sa se realizeze functia merge-stream care interclaseaza 2 fluxuri sortate
;; dupa urmatoarea regula: e1 precede pe e2 daca e respectata conditia
;; (comp (f e1) (f e2)) - unde comparatorul comp si functia f sunt date ca
;; parametri pentru functia merge-stream
;; (take-stream 10 ((merge-stream < sqr) naturals-stream naturals-stream))
;; => (0 0 1 1 2 2 3 3 4 4)

;; 4. (3p)
;; sa se construiasca fluxul perechilor de numere naturale 
;; sortate crescator dupa suma elementelor din pereche
;; perechea (x, y) este considerata identica cu perechea (y, x) si nu trebuie
;; sa apara de 2 ori in rezultat
;; (take 10 natural-pairs)
;; => ((0 0) (0 1) (1 1) (0 2) (1 2) (0 3) (2 2) (1 3) (0 4) (2 3))
;; hint: prelucrati 2 fluxuri s1 si s2 unde initial s1 = s2 = naturals-stream
;; util: functia merge-stream

;; 5. (1p)
;; sa se construiasca fluxul perechilor de numere naturale 
;; sortate crescator dupa suma cuburilor elementelor din pereche
;; perechea (x, y) este considerata identica cu perechea (y, x) si nu trebuie
;; sa apara de 2 ori in rezultat

;; 6. (1p)
;; folosind fluxul anterior:
;; sa se construiasca fluxul (sortat crescator al) numerelor care pot fi scrise
;; in cel putin 2 moduri diferite ca suma de cuburi 
;; (take-stream 3 pb2-stream) => (1729 4104 13832)

;; 7. (BONUS - 2p)
;; sa se construiasca fluxul de permutari:
;; mai intai (permutari '(1)), apoi (permutari '(1 2)) etc
;; (take-stream 3 P-stream) => 
;; (((1)) 
;;  ((2 1) (1 2)) 
;;  ((3 1 2) (1 3 2) (1 2 3) (3 2 1) (2 3 1) (2 1 3)))



