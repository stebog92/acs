; Tema1 - Evaluator de lambda0 in Scheme
;=========================================================
;Ciocan Mihai
;324CA
;=========================================================

; intoarce true daca termenul e de tip (lambda0 x corp)
(define lambdaAbs?
  (lambda (x)
    (and (list? x)
         (eq? (length x) 3) 
         (eq? (car x) 'lambda0))
  )
)

; intoarce parametru x in cazul (lambda0 x corp)
(define varOf
  (lambda (x)
    (cadr x)
  )
)

; intoarce corpul in cazul (lambda0 x corp)
(define bodyOf
  (lambda (x)
    (caddr x)
  )
)

; intoarce true daca termenul e o aplicatie
(define lambdaApl?
  (lambda (x)
    (and (list? x) (eq? (length x) 2))
  )
)
    
; intoarce lista de variabile libere a unui termen
(define freeVar
  (lambda (x)
    (if  (lambdaAbs? x)
        (filter (lambda (z) (not (eq? (varOf x) z))) (freeVar (bodyOf x)))
        (if (lambdaApl? x)
            (append (freeVar (car x)) (freeVar (cadr x)))
            (list x)
        )
    )
  )
)

; verifica daca expresia e un termen lambda (3 cazuri)
(define lambdaTerm?
  (lambda (x)
    (or (symbol? x)
        (and (list? x) (not (null? x)) (eq? (car x) 'lambda0)) 
        (and (list? x) (eq? (length x) 2) (lambdaTerm? (car x)) (lambdaTerm? (cadr x)))
    )
  )
)

      
; aplica o betareducere asupra unei aplicatii
(define betaReduce
  (lambda (x y)
    (substitute (varOf x) y (bodyOf x))
        
  )
)

; substituie recursiv toate aparitiile lui x cu y in expresia z
(define substitute
  (lambda (x y z)
    (if (lambdaAbs? z)
        (if (member (varOf z) (freeVar y))
            (substitute x y (changeVar z (gensym (varOf z))))
            (if (eq? (varOf z) x)
                z
                (list (car z) (cadr z) (substitute x y (bodyOf z)))
            )
        )
        (if (lambdaVar? z)
            (if (eq? z x)
                y
                z
            )
            (if (lambdaApl? z)
                (list (substitute x y (car z)) (substitute x y (cadr z)))
                z
            )
        )
    )
  )
)

; intoarce true daca expresia e un simbol
(define lambdaVar?
  (lambda (x)
    (symbol? x)
  )
)

; aplica o alfaconversie pe o expresie
(define changeVar
  (lambda (x y)
    (list (car x) y (replace (varOf x) y (bodyOf x)))
  )
)

; inlocuieste x cu y intr-o lista
(define replace
  (lambda (x y z)
    (if (null? z)
        '()
        (if (list? z)
            (cons (replace x y (car z)) (replace x y (cdr z)))
            (if (eq? z x)
                y
                z
            )
        )
    )
  )
)

; intoarce true daca mai exista betareduceri in expresie
(define hasBetaRedex
  (lambda (x)
    (if (symbol? x)
        #f
        (if (lambdaAbs? x)
            (hasBetaRedex (caddr x))
            (if (lambdaAbs? (car x))
                #t
                (or (hasBetaRedex (car x)) (hasBetaRedex (cadr x)))
            )
        )
     )
  )
)

; aplica betaReducerea leftmost outermost intr-o expresie
(define betaReduceLO
  (lambda (x)
    (if (lambdaAbs? (car x))
        (betaReduce (car x) (cadr x))
        (if (hasBetaRedex (car x))
            (list (betaReduceLO (car x)) (cadr x))
            (if (hasBetaRedex (cadr x))
                (list (car x) (betaReduceLO (cadr x)))
                x
            )
        )
    )
  )
)

; evalueaza o expresie in ordine normala (din exterior spre interior)
(define (eval0 expr)
  (if (symbol? expr)
      expr
      (if (lambdaAbs? expr)
          (list (car expr) (cadr expr) (eval0 (bodyOf expr)))
          (let ((x (betaReduceLO expr)))
                (if (equal? x expr)
                    x
                    (eval0 x)
                )
          )
      )
  )
)
              
               
