
; ===================================
; =======  Laborator IA =============
; ===================================

; SBR:Bw
; ======
; Andrei Olaru


(define (load-all)
  (load "util-kb-simple.scm")
  (load "util-unify.scm")
  (load "util-rename.scm")
  (display "utilities loaded.")
)
(load-all)

; the knowledge base. It is a list of 3 elements containing:
; - the list of predicates, as pairs (Predicate-name . no-of-arguments). Predicate names begin with a capital letter
; - the list of functions (currently unused)
; - the list of facts; currently all facts are either predicates or implications
;    - constants begin with a capital, variables begin with a minuscule
;    - predicates are of the form (Predicate-name Predicate-arg-1 Predicate-arg-2 ...)
;    - implications are of the form (implies condition conclusion), where both condition and conclusion can be a single predicate or a conjunction (e.g. (and (American x) (Hostile y)) )
(define kb '(
  ( (American . 1) (USGeneral . 1) (Weapon . 1) (Sells . 3) (Hostile . 1) (Criminal . 1) (Missle . 1) (Enemy . 2) (Owns . 2) (Kills . 2) (Dead . 1) )
  ()
  (
            (USGeneral West)
            (Enemy Nono America)
            (Missle M1)
            (Owns Nono M1)
            
            (forall x (implies (USGeneral x) (American x)))
            (forall x (implies (Missle x) (Weapon x)))
            (forall x (forall y (implies (and (Owns x y) (Missle y)) (Sells West y x))))
            (forall x (forall y (forall z (implies (and (Weapon y) (Sells x y z) (Hostile z) (American x)) (Criminal x)))))
            (forall x (implies (Enemy x America) (Hostile x)))
            
            (Kills West John)
            
            (forall x (forall y (implies (Kills x y) (and (Dead y) (Criminal x)))))
  )
))

; prove
; attepts to prove a set of conclusion/goals, based on a knowledge base of facts and rules
; kb: the knowledge base, containing facts (predicates) and rules (implications with both sides consisting of predicates or conjunctions of predicates).
; goals: a list of predicates that need to be proven.
; subst: the current substitution
; returns: if the goals can be proven, a list of possible solutions; each solution contains the set of rules (in inverse order) to apply in order to obtain the goals;
;          if any of the goals cannot be proven, false.
(define (prove kb goals subst)
  (display `("goals:" ,goals "; subst: " ,subst))(newline)(newline)
  (if (eq? goals null)
      subst
      (let* ((q (substitute (car goals) subst))
            (sol  (get_first_unifier kb q)))
        (prove kb (substitute (append (car sol) (cdr goals)) (cdr sol)) (append (cdr sol) subst)))
   )  
)

(define (get_conclusion fact)
  (if (eq? 'forall (car fact))
      (get_conclusion (caddr fact))
      (if (eq? (car fact) 'implies)
          (caddr fact)
          fact
      )
  )
)

(define (get_goals fact)
  (if (eq? 'forall (car fact))
      (get_goals (caddr fact))
      (if (eq? (car fact) 'implies)
          (if (eq? 'and (caadr fact))
              (cdadr fact)
              (list (cadr fact))
          )
          '()
      )
  )
)

(define (get_first_unifier kb const)
  (car (filter (lambda (x) (list? (cdr x))) (map (lambda (x) (cons (get_goals x) (unify kb (get_conclusion x) const))) (factsFrom kb))))
)

(define (go) (let ((conclusion '(Criminal West)))
               (map reverse  ; reverses the order of the rules in every solution
                    (map (λ (sol) (cons conclusion sol)) ; adds the conclusion to each of the solutions, for easier reading of the result
                      (prove
                        (list (predicatesFrom kb) (functionsFrom kb) (map (λ (f)(eliminaCU kb (redenumeste kb f))) (factsFrom kb)))
                        (list conclusion)
                        '()
                      )
               ))
))

(go)
; exemples of functionals usage & co
; foldr example:  (foldr (λ (a v result) (cons (cons a v) result)) '() '(a b c) '(1 2 3))
; filter exemple: (filter (λ (x) (> x 3)) '(1 8 3 4 5 2 7))
; apply example: (apply + '(1 2 3 4))
; apply example: (apply append '((1 2 3) (4 5 6)))
; map example:   (map (λ (x) (+ x 10)) '(1 2 3))
; and example: (and (display "1") (newline) (+ 1 2))
; or example - replaces (if x x y): (or #f '(+ 1 2))