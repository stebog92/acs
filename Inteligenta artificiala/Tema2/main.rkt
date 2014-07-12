; reads a rule
(define (read-rule input)
  (let ((name (symbol->string (read input)))
        (conditions (read-conditions input))
        (conclusions (read-conclusions input)))
    (list (string->symbol (substring name 0 (- (string-length name) 1)))
          conditions conclusions          
          )
    )
  )

; reads conclusions
(define (read-conclusions input)
  (let f ((y (read input))
          (equ (read input))
          (z (read input))
          (score (next-char input)))
    (cond ((eq? score #\s) (read input) (cons (list y z 1) (f (read input) (read input) (read input) (next-char input))))
          (else (if (eof-object? score)
                    (list (list y z 1))
                    (if (char-numeric? score)
                        (let ((s (read input))
                              (next-token (next-char input)))
                          (cond
                            ((eq? #\s next-token) (read input) (cons (list y z s) (f (read input) (read input) (read input) (next-char input))))
                            (else  (cons (list y z s) '()))
                            )
                          )
                        (list (list y z 1))
                        )
                    )
                )
          )
    )
  )

;reads conditions
(define (read-conditions input)
  (let f ((x (read input)))
    (if (or (eq? x 'daca) (eq? x 'si))
        (let ((y (read input))
              (equ (read input))
              (z (read input)))
          (cons (list y z) (f (read input))))
        '()
        )
    )
  )

;returns next char non-ws
(define (next-char input)
  (let f ((x (peek-char input)))
      (if (eof-object? x)
          x
          (if (char-whitespace? x)
              (cadr (list (read-char input) (f (peek-char input))))
              x
              )
          )
      )
    )

; reads rules from file
(define rules (call-with-input-file "reguli" 
                (lambda (p)
                  (cons 'reguli (let r ((rule (read-rule p)))
                                  (if (eof-object? (peek-char p))
                                      (list rule)
                                      (cons rule (r (read-rule p)))
                                      )
                                  )
                        )
                  )
                )
  )

; returns conclusions of rule
(define (get_conclusions rule)
  (caddr rule)
  )

; returns premises of rule
(define (get_premises rule)
  (cadr rule)
  )

; define facts
(define facts '(fapte (has-sauce yes)
                      (has-veal no)
                      (main-component meat)
                      (preferred-body full)
                      (sauce cream)
                      (best-sweetness medium)
                      
                      (componenta-meniu peste)
                      (sos-meniu sos-alb)))
; define monovalued attributes
(define mono '(monoval (componenta-meniu culoare-vin sos-meniu)))
; define multivalued attributes
(define multi '(multival (tip-vin vin feature main-component preferred-body preferred-color preferred-sweetness
                                  sauce wine tastiness)))
; define result
(define result '(concluzie vin))

; returns facts that match conclusion
(define (match-fact? facts conclusion)
  (foldr (λ (x y) (or x y)) #f (map (λ (x) (equal? x conclusion)) (cdr facts)))
  )

; returns if rule contains conclusion
(define (contains_conclusion rule conclusion)
  (foldr (λ (x y) (or x y)) #f (map (λ (x) (equal? (list (car x) (cadr x)) conclusion)) (get_conclusions rule)))
  )

; return rules that match conclusion 
(define (match-rules rules conclusion)
  (if (eq? (car conclusion) 'concluzie)
      (map (λ (x) (car x)) 
           (filter (λ (x) (cdr x)) (map (λ (x) (cons x (eq? (caar (get_conclusions x)) (cadr conclusion)))) (cdr rules)))
           )
      (filter (λ (x) (contains_conclusion x conclusion)) (cdr rules))
      )
  )

; return attribute if in memory
(define (ml-return-conclusion ml conclusion)
  (let ((res (filter (λ (x) (and
                  (eq? (car conclusion) (car x))
                  (eq? (cadr conclusion) (cadr x))
                  )
                 ) ml
               )))
    (if (null? res)
        #f
        (car res)
        )
    )  
  )

; get coef of conclusion
(define (get-coef rule conclusion)
  (caddar (filter (λ (x) (eq? (cadr x) (cadr conclusion))) (get_conclusions rule)))
  )

; process premises
(define (process-premises ml rules facts mono multi goal conclusion)
  (let ((goal_res (foldr (λ (x s) (if (eq? s #f)
                                      #f
                                      (let ((temp (satisfy (car s) rules facts mono multi x)))
                                        (if (not (cadr temp))
                                            #f
                                            (list (car temp) (min (caddr temp) (cadr s)))
                                            )
                                        )
                                      )
                           ) (list ml 1) (get_premises goal))))
    (if goal_res
        (let ((new_data (update-mem (list (car conclusion) 
                                          (cadr conclusion)
                                          (* (get-coef goal conclusion) (cadr goal_res))) (car goal_res))))
          (list (car new_data) #t (cadr new_data))
          )
        (list ml #f 0)
        )
    )
  )
; update memory with new attr
(define (update-mem entry mem)
  (let ((new_mem (_update_mem entry mem)))
    (list new_mem (caddar (filter (λ (x) (and (eq? (car entry) (car x)) (eq? (cadr x) (cadr entry)))) new_mem)))
    )
  )

; used by update-mem
(define (_update_mem entry mem)
  (if (null? mem)
      (list entry)
      (if (and (eq? (caar mem) (car entry)) (eq? (cadar mem) (cadr entry)))
          (let ((new_val (- (+ (caddr entry) (caddar mem)) (* (caddr entry) (caddar mem)))))
            (cons (list (caar mem) (cadar mem) new_val) (cdr mem))
            )
          (cons (car mem) (_update_mem entry (cdr mem)))
          )
      )
  )
; returns if attribute is monovalued 
(define (monovalued? mono y)
  (not (null? (filter (λ (x) (eq? x y)) (cadr mono))))
  )

; returns if memory contains attribute
(define (ml-contains-attribute ml attr)
  (not (null? (filter (λ (x) (eq? (car attr) (car x))) ml)))
  )
 
; display message if satisfied or not
(define (display-and-return c x memory)
  (if (cadr x)
      (display `("satisfied:" ,c "memory:" ,memory))
      (display `("failed to satisfy:" ,c "memory:", memory))
      )(newline)
  x
  )

; main procedure that tries to satisfy conclusion
(define (satisfy ml rules facts mono multi conclusion)
  (display `("try to satisfy:", conclusion))(newline)
  (let ((c (ml-return-conclusion ml conclusion)))
    (if c
        (list ml #t (caddr c))
        (if (match-fact? facts (list (car conclusion) (cadr conclusion)))
            (let ((new_data (update-mem (list (car conclusion) (cadr conclusion) 1) ml)))
                    (display `("satisfied:" ,conclusion "memory:" ,(car new_data)))(newline)
                    (list (car new_data) #t (cadr new_data))
                    )
            (let ((mrules (match-rules rules conclusion)))
              (if (null? mrules)
                  (list ml #f 0)
                  (if (and (monovalued? mono (car conclusion)) (ml-contains-attribute ml conclusion))
                      (list ml #f 0)
                      (let ((res (foldr (λ (x s) (let ((res (process-premises (car s) rules facts mono multi x conclusion)))
                                                   (if (not (cadr res))
                                                       (list (car res) (cadr s) (caddr s))
                                                       res
                                                       )
                                                   )
                                          ) (list ml #f 0) mrules)
                                 ))
                        (if (cadr res)
                            (display-and-return conclusion (list (car res) #t (caddr res)) (car res))
                            (display-and-return conclusion (list (car res) #f 0) (car res))
                            )
                        )
                      )
                  )
              )
            )
        )
    )
  )


(define (get_result type entries)
  (if (null? entries)
      '()
      (if (eq? (caar entries) type)
          (cons (list (cadar entries) (caddar entries)) (get_result type (cdr entries)))
          (get_result type (cdr entries))
          )
      )
  )

; get all conclusions of a rules, useful for gathering all wine conclusions
(define (get_all_conclusions rules)
  (if (null? rules)
      '()
      (append (get_conclusions (car rules)) (get_all_conclusions (cdr rules)))
      )
  )
; remove duplicates from list
(define (remove-duplicates lst)
    (foldr (lambda (f r)
             (cons f (filter (lambda (x) (not (equal? x f))) r))) empty lst))

; wrapper of satisfy procedure; returns results
(define (solve rules facts mono multi result)
  (let ((rulz (match-rules rules result)))
    (cons (cadr result)
          (get_result (cadr result) 
                      (foldr (λ (x s) (let ((res (satisfy s rules facts mono multi (list (car x) (cadr x)))))
                                        (car res)
                                        )
                               )
                             '()
                             (remove-duplicates (get_all_conclusions rulz)))))
    )
  )

(solve rules facts mono multi result)
