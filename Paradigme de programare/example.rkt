(define x
  (call/cc
   (lambda (cont1)
     (+ (call/cc
         (lambda (cont2)
           (cont1 (cont2 (cont1 cont2)))))
        39))))

;cont1 = (lambda^ (v) (define x v)
;cont2 = (lambda^ (v1) (+ v1 39))
;x = cont2