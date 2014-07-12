;; Laborator IA

;; author: Tudor Berariu / 14 Noiembrie 2011
;; author: Andrei Olaru / Octombrie 2012, Noiembrie 2013


;; Redenumeste variabilele omonime
; kb: the knowledge base
; e: a well formed formula with existential and universal quantifiers, with conjunctions and disjunctions between literals
; returns: a well formed formula that contains no variables with the same name
(define (redenumeste kb e . changes) 
  (cond
    ((isConstant? kb e)                e)
    ((isVariable? e)                   (if (assoc e changes) (cdr (assoc e changes)) e))
    ((or (isFunction? kb (car e))
         (isAtomicSentence? kb e)
         (isNeg? e)
         (isConn? e))
                                      `(,(car e) . ,(map (λ (part) (apply redenumeste `(,kb ,part . ,changes))) (cdr e))))
    ((isQuant? e) 
       (let* ((old (cadr e))
              (new (getNextVar))
              (changes1 `((,old . ,new) . ,changes)))
                                      `(,(car e) ,new ,(apply redenumeste `(,kb ,(caddr e) . ,changes1)))))
    (#t #f)
  )
)


;; Elimina cuantificatorii universali
; kb: the knowledge base
; e: a well formed formula with universal quantifiers, with conjunctions and disjunctions between literals
; returns: a well formed formula that contains no universal quantifiers
(define (eliminaCU kb e) 
  (cond
    ((isAtomicSentence? kb e)      e)
    ((or (isNeg? e) (isConn? e))  `(,(car e) . ,(map (λ (part) (eliminaCU kb part)) (cdr e))))
    ((eq? (car e) 'forall)         (eliminaCU kb (caddr e)))
    (#t #f)
  )
)


(define (isNeg? e) (and (list? e) (eq? (car e) 'not)))
(define (isConn? e) (and (list? e) (isConnective? (car e))))
(define (isQuant? e) (and (list? e) (isQuantifier? (car e))))

(define standardVar 0)
(define nextVar (lambda () (set! standardVar (+ 1 standardVar))))
(define getNextVar 
  (lambda () 
    (begin (nextVar)
           (string->symbol 
            (string-append
             "z"
             (number->string standardVar))
            )
           )
    )
  )