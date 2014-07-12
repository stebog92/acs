;; Laborator IA

;; author: Tudor Berariu / 14 Noiembrie 2011
;; author: Andrei Olaru / Octombrie 2012, Noiembrie 2013


; unify: unfies two expressions x and y (if possible)
; kb: the knowledge base
; x: the first expression (sentence, atom, or term)
; y: the second expression (sentence, atom, or term)
; returns: a complete substitution so that x unifies with y,
;         as a list of the form '( (var1 . Val1) (var2 . Val2) (var3 . Val3) )
(define (unify kb x y) (unify-r kb x y '()))

; unify: unfies two expressions x and y (if possible), making the substitutions in subst
; kb: the knowledge base
; x: the first expression (sentence, atom, or term)
; y: the second expression (sentence, atom, or term)
; subst: the partial substitution obtained so far
; returns: a complete substitution so that x unifies with y
; NOTE: subst is a list of the form '( (var1 . Val1) (var2 . Val2) (var3 . Val3) )
(define (unify-r kb x y s)
;  (display (list "unify here: " x " : " y " with " s)) (newline)
  (cond
    ((not s) s)
    ((eq? x y) s)
    ((isVariable? x) (unify-var kb x y s))
    ((isVariable? y) (unify-var kb y x s))
    ((or
      (and (isAtomicSentence? kb x) (isAtomicSentence? kb y))
      (and (isSentence? kb x) (isSentence? kb y) (not (isAtomicSentence? kb x)) (not (isAtomicSentence? kb y)))
      (and (list? x) (list? y) (not (isSentence? kb x)) (not (isSentence? kb y)) (not (isAtomicSentence? kb x)) (not (isAtomicSentence? kb y)))
     )
            (unify-r kb (cdr x) (cdr y) (unify-r kb (car x) (car y) s)))
    (#t #f)
  )
)


; unify-var: unfies variable var tp expression x (if possible), making the substitutions in subst
; kb: the knowledge base
; var: a variable
; x: the expression (sentence, atom, or term)
; subst: the partial substitution obtained so far
; returns: a complete substitution so that var unifies with x
; NOTE: subst is a list of the form '( (var1 . Val1) (var2 . Val2) (var3 . Val3) )
(define (unify-var kb var x s)
  (let ((s-var (or (assoc var s) '())) (s-x (or (assoc x s) '())))
    (cond
      ((not (null? s-var)) (unify-r kb (cdr s-var) x s))
      ((not (null? s-x)) (unify-r kb var (cdr s-var) s))
      ((occur-check? var x) #f)
      (#t (cons (cons var x) s))
    )
  )
)

; occur-check: returns true if the variable var appears in expression e
(define (occur-check? var e)
  (cond
    ((equal? var e) #t)
    ((and (list? e) (not (null? e))) (ormap (λ (part) (occur-check? var part)) (cdr e)))
    (#t #f)
  )
)

; substitute: applies the substitution subst to the expression x
; x: the expression (sentence, atom, or term)
; subst: the substitution; it is a list of the form '( (var1 . Val1) (var2 . Val2) (var3 . Val3) )
; returns: an expression where the variables appearing in x and having an entry in subst have been replaced with the appropriate substituion
(define (substitute x s)
  (cond
    ((not s) #f)
    ((isVariable? x) (if (assoc x s) (cdr (assoc x s)) x))
    ((list? x) (if (null? x) x (map (λ (part) (substitute part s)) x)))
    (#t x)
  )
)


