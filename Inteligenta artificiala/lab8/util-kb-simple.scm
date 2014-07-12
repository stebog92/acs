;; Laborator IA

;; authors:
;; Tudor Berariu / 14 Noiembrie 2011
;; Andrei Olaru / Octombrie 2012, Noiembrie 2013


;;;;;;
;; Functii pentru extragerea componentelor din baza de cunostinte

(define predicatesFrom (lambda (kb) (car kb)))
(define functionsFrom (lambda (kb) (cadr kb)))
(define factsFrom (lambda (kb) (caddr kb)))

;; Functie ce intoarce numarul de argumente al unei functii
;; din baza de cunostinte
(define noOfFuncArgs
  (lambda (kb funcName)
    (let ((no-of-args (assoc funcName (functionsFrom kb))))
      (if no-of-args (cdr no-of-args) no-of-args)
      )
    )
  )

;; Functie ce intoarce numarul de argumente al unui predicat
;; din baza de cunostinte
(define noOfPredicateArgs
  (lambda (kb predicateName)
    (let ((no-of-args (assoc predicateName (predicatesFrom kb))))
      (if no-of-args (cdr no-of-args) no-of-args)
      )
    )
  )

;;; Functii ce identifica tipul unei expresii
;;; verifica existenta obiectelor/relatiilor in kb

;; Functie ce verifica daca x este o variabila
(define isVariable? 
  (lambda (x) 
    (and (symbol? x)
         (let ((s (symbol->string x)))
           (and (string<=? "a" s) (string<=? s "{"))
           )
         )
    )
  )

;; Functie ce verifica daca x este o constanta in baza de cunostinte
(define isConstant? 
  (lambda (kb x)
    (and
     (or (number? x)
         (string? x)
         (and (symbol? x)
              (let ((s (symbol->string x)))
                (and (string<=? "A" s)
                     (string<? s "["))
                )
;              (member x (constantsFrom kb))
              #t
              )
         )
     )
    )
  )
;; Functie ce verifica daca x este o functie in kb
(define isFunction?
  (lambda (kb x)
    (and 
     (not (symbol? x))
     (member x (map car (functionsFrom kb)))
     #t
     )
    )
  )

;; Functie ce verifica daca x este un predicat in kb
(define isPredicate?
  (lambda (kb x)
    (and 
     (symbol? x)
     (let ((s (symbol->string x)))
       (and (string<=? "A" s)
            (string<=? s "["))
       )
     (member x (map car (predicatesFrom kb)))
     )
    )
  )

;;; Functie ce verifica daca x este un cuantificator
(define isQuantifier?
  (lambda (x)
    (or (equal? x 'exists) (equal? x 'forall))
    )
  )
;;; Functie ce verifica daca x este un conector  
(define isConnective?
  (lambda (x)
    (or (equal? x 'and) (equal? x 'or) (equal? x 'implies) (equal? x 'equiv))
    )
  )
  
;;; Functie ce verifica daca x este un termen in baza de cunostinte
(define isTerm?
  (lambda (kb x)
    (or (isVariable? x)
        (isConstant? kb x)
        (and (list? x)
             (or
               (and (symbol? (car x)) (equal? (substring (symbol->string (car x)) 0 2) "SK") (not (null? (cdr x))))
               (and (isFunction? kb (car x)) (= (length (cdr x)) (noOfFuncArgs kb (car x))))
             )
             (foldl (lambda (z1 z2) (and z1 z2))
                    #t
                    (map (lambda (z) (isTerm? kb z)) (cdr x)))
             )
        )
    )
  )
      
           
;; isAtomicSentence?
(define isAtomicSentence? 
  (lambda (kb x) 
    (or (and (list? x) (isPredicate? kb (car x)) (eq? (- (length x) 1) (noOfPredicateArgs kb (car x))) (andmap (λ (t) (isTerm? kb t)) (cdr x)))
        (and (list? x) (= 3 (length x)) (eq? (car x) 'equals) (isTerm? kb (cadr x)) (isTerm? kb (caddr x)))
        )
  )
)
;; isSentence?
(define isSentence? 
  (lambda (kb x) 
    (or
     (isAtomicSentence? kb x)
     (and (list? x) (= 3 (length x)) (isConnective? (car x)) (isSentence? kb (cadr x)) (isSentence? kb (caddr x)))
     (and (list? x) (= 2 (length x)) (eq? 'neg (car x)) (isSentence? kb (cadr x)))
     (and (list? x) (= 3 (length x)) (isQuantifier? (car x)) (isVariable? (cadr x)) (isSentence? kb (caddr x)))
    )
  )
)
