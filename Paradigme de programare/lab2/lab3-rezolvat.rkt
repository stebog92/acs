;; Functii curry si uncurry
;;--------------------------

;; un macro ajutator pt pretty print
(require (lib "defmacro.ss"))
(define-macro test
  (lambda (n expr)
    `(display (format "~v.  ~v => ~a\n" ,n ',expr ,expr))))

;; 1. (1p)
;; sa se implementeze functia uncurry->curry, dupa urmatoarea specificatie:
;; fiind data o functie uncurry f care ia in Scheme 2 argumente,
;; (uncurry->curry f) trebuie sa produca o functie curry cu acelasi efect ca functia f
;; practic uncurry->curry este o functie care primeste mai intai ca parametru o functie
;; uncurry, apoi argumentele acesteia (pe rand, pt ca altfel (uncurry->curry f) nu ar 
;; fi o functie curry) si face exact ce facea f asupra acelor argumente
;; (((uncurry->curry >) 5) 4) => #t
(define uncurry->curry
  (lambda (f)
    ;; headerul meu e ce vreau sa intorc: "(uncurry->curry f) trebuie sa produca o FUNCTIE CURRY"
    (lambda (x)
      (lambda (y)
        ;; apelez f dupa conventia uncurry ("fiind data o functie UNCURRY f")
        (f x y)))))

(test 1 (((uncurry->curry >) 5) 4))

;; 2. (1p)
;; sa se implementeze functia curry->uncurry (procesul invers celui de la punctul 1)
;; acum (curry->uncurry g) va produce o functie uncurry dintr-o functie curry g de 
;; 2 argumente
(define curry->uncurry 
  (lambda (g)
    ;; headerul meu e ce vreau sa intorc: "(curry->uncurry g) va produce o FUNCTIE UNCURRY"
    (lambda (x y)
      ;; apelez g dupa conventia curry (g era CURRY)
      ((g x) y))))

(test 2 ((curry->uncurry (uncurry->curry cons)) 3 5))

;; Functionale
;;------------

;; functionala = o functie care primeste functii ca parametri sau returneaza functii
;; in specificatiile de mai jos
;; f = functie binara
;; p = predicat (functie care testeaza o proprietate a argumentului
;; sau si intoarce true sau false)
;; L, L1, L2 = liste
;; x = element in lista

;; 3. (0.5p)
;; sa se implementeze functionala foldleft
;; (foldleft f init lst) acumuleaza aplicatia functiei binare f pe lista lst in felul urmator:
;; mai intai (f init (car lst)) => rezultat1
;; apoi (f rezultat1 (cadr lst)) => rezultat2, (f rezultat2 (caddr lst)) => rezultat3 etc
;; pana la (f rezultat-pana-acum ultimul-element-din-lst)
(define (foldleft f init lst)
  (if (null? lst)
      ;; init functioneaza ca o variabila de acumulare
      init
      (foldleft f (f init (car lst)) (cdr lst))))

(test 3 (foldleft * 1 '(1 2 3 4 5)))

;; 4. (0.5p)
;; sa se obtina inversa unei liste ca o aplicatie a lui foldleft

;; daca privim inversarea unei liste cu recursivitate pe coada din laboratorul 2,
;; observam ca implementarea este aproape identica cu implementarea lui foldleft
;; diferenta este in felul in care lucram cu variabila de acumulare
;; acolo aveam (cons (car lst) init), aici avem (f init (car lst))
;; deducem ca inversarea unei liste este un foldleft cu 
;; f = un cons care isi ia parametrii in ordine inversa 

;; in spiritul abstractizarii, definim o metoda de a permite ORICAREI
;; functii binare sa isi primeasca parametrii in ordine inversa
(define (flip f) (lambda (b a) (f a b)))

(define (rev L) (foldleft (flip cons) '() L))

(test 4 (rev '(d e e p s)))

;; 5. (0.5p)
;; sa se implementeze functionala zip-with 
;; (zip-with f L1 L2) = lista aplicarilor lui f asupra elementelor de pe 
;; pozitii corespunzatoare in L1 si L2
;; (zip-with * '(2 4 6) '(3 5 7 9)) => (6 20 42)
(define (zip-with f L1 L2)
  (if (or (null? L1) (null? L2))
      '()
      (cons (f (car L1) (car L2))
            (zip-with f (cdr L1) (cdr L2)))))

(test 5 (zip-with * '(2 4 6) '(3 5 7 9)))

;; 6. (0.5p)
;; sa se obtina zip-fun din zip-with
;; (zip-fun L1 L2) = lista aplicarilor functiilor din L2 asupra elementelor de pe 
;; pozitiile corespunzatoare din L1
;; (zip-fun (list 1 2 '(1 2 3) 4 #t) (list odd? list? list? even? boolean?)) => (#t #f #t #t #t)
(define (zip-fun L1 L2) 
  (zip-with (lambda (x f) (f x)) L1 L2))

(test 6 (zip-fun (list 1 2 '(1 2 3) 4 #t) (list odd? list? list? even? boolean?)))

;; OBSERVATIE:
;; daca as fi definit zip-with ca pe o functie curry
;; (define (zip-with f)
;;   (lambda (L1 L2)
;;     ...))
;; as fi putut obtine mai usor functia zip-fun:
;; (define zip-fun (zip-with (lambda (x f) (f x))))

;; 7. (0.5p)
;; sa se implementeze functionala forall?
;; (forall? p L) = true daca oricare ar fi x in L avem p(x) adevarat
;; (forall? even? '(2 4 6 8)) => #t
(define (forall? p l)
  (cond
    ((null? l) #t) ;; nu am avut contradictii, deci toate de pana acum au fost OK
    ((p (car l)) (forall? p (cdr l))) ;; daca elem curent e bun, trebuie ca si TOATE celelalte sa fie
    (else #f))) ;; nu e bun elementul curent; nu are rost sa mai caut, ca deja stiu ca nu-s toate

(test 7 (forall? even? '(2 4 6 8)))
(test 7 (forall? even? '(2 4 6 7 8)))

;; varianta 2
(define (forall2? p l)
  (if (null? l)
      #t
      (and (p (car l))
           (forall2? p (cdr l)))))

;; explicatie: 
;; - and evalueaza pe rand: 
;;   - daca argumentul curent da false, atunci intoarce false
;;   - altfel evalueaza mai departe si intoarce valoarea ultimului argument

;; variantele 3-4, frumoase dar ineficiente
(define (forall3? p l)
  (= (length l) (length (filter p l))))

(define (forall4? p l)
  (null? (filter (lambda (x)
                   (not (p x)))
                 l)))

;; 8. (1p)
;; sa se implementeze functia prime? dupa specificatia:   
;; pt n > 1, (prime? n) <=> forall d in 2..n/2 . n mod d <> 0
;; se cere NEAPARAT sa folositi o functie anonima pt reprezentarea 
;; predicatului n mod d <> 0, definita direct ca parametru pt forall?
;; (modulo 19 4) - asta e functia mod in Scheme...
;; (prime? 2) => #t
;; (prime? 17) => #t
;; (prime? 10) => #f

;; o functie ajutatoare care genereaza o lista cu numerele naturale din intervalul a..b
(define (range a b)
  (range-helper a b '()))

(define (range-helper a b Result)
  (if (> a b)
      Result
      (range-helper a (- b 1) (cons b Result))))

(define (prime? n)
  (if (< n 2)
      #f
      (forall? (lambda (d) (> (modulo n d) 0))
               (range 2 (floor (sqrt n))))))

(test 8 (prime? 29)) 
(test 8 (prime? 39))

;; OBSERVATII:
;; - daca al 2lea argument al lui range nu este numar intreg,
;;   in functie de implementarea lui range, daca aceasta construieste
;;   de la a catre b ati putea intra in bucla infinita (daca ati pus
;;   conditia de oprire a = b, nu a > b), iar daca range o ia
;;   de la b catre a ati putea genera numere fractionare pe parcurs
;; - ASA trebuia facut cu definitia explicita:  
;;   (lambda (d) (not (zero? (modulo n d)))), sau
;;   (lambda (d) (> (modulo n d) 0))

;; 9. (0.5p)
;; sa se determine lista tuturor numerelor prime <= n
;; (primes-up-to 20) => (2 3 5 7 11 13 17 19)

;; - notati cat este de usor folosind functionale
;; - ganditi-va cum ati fi scris fara functionale si
;;   observati ca respectati exact patternul din functia filter
(define (primes-up-to n)
  (filter prime? (range 1 n)))

(test 9 (primes-up-to 40))

;; 10. (2p)
;; sa se implementeze produsul cartezian a n liste dintr-o lista de liste
;; (cartesian-product '((1 2) (a b) (x y z))) =>
;; ((1 a x) (1 a y) (1 a z) (1 b x) (1 b y) (1 b z) (2 a x) (2 a y) (2 a z) (2 b x) (2 b y) (2 b z))
;; nu neaparat in aceasta ordine
;; trebuie sa obtineti o singura lista cu toate tuplurile, nu tot felul de liste imbricate!
;; util: functia uncurry->curry

;; o functie care calculeaza { (f e1 e2) | e1<-L1, e2<-L2 }
(define (combine f) 
  (lambda (L1 L2)
    (if (null? L1)
        '()
        (append (map ((uncurry->curry f) (car L1)) L2)
                ((combine f) (cdr L1) L2)))))

(define (cartesian-product Ls)
  (cond ((null? Ls) '())
        ((null? (cdr Ls)) (map list (car Ls)))
        (else ((combine cons) (car Ls) (cartesian-product (cdr Ls))))))

(test 10 (cartesian-product '((1 2) (a b) (x y z))))

;; explicatie :  
;; - ((uncurry->curry f) (car L1)) face o functie care asteapta un argument x 
;;   pentru a calcula (f (car L1) x)
;; - mapand aceasta functie peste L2, obtin { (f (car L1) x) | x<-L2 }
;; - ramane sa combin (recursiv) si celelalte elemente din L1 cu fiecare element din L2
;; - produsul cartezian a n liste se poate defini ca prima lista combinata cu produsul
;;   cartezian al urmatoarelor n-1 liste
;; - functia de combinare in cazul produsului cartezian este cons

;; 11. (2p)
;; o functie care calculeaza transpusa unei matrici
;; (transpose '((1 2 3 4) (5 6 7 8) (9 10 11 12)))
;; => '((1 5 9) (2 6 10) (3 7 11) (4 8 12))
(define transpose
  (lambda (M)
    ;; (car M) -> primul rand
    ;; (null? (car M)) -> primul rand e gol -> deci matricea e goala
    (if (null? (car M))
        '()
        (cons (map car M) ;; primul element e de fapt prima coloana
              (transpose (map cdr M))))))

(test 11 (transpose '((1 2 3 4) (a b c d) (+ - * /))))

;; varianta 2
;; In primul rand, ati intalnit functia map doar cu 2 argumente: 
;; o functie de un argument si o lista.  De fapt map accepta si
;; mai multe argumente, cu conditia ca numarul de argumente de dupa
;; functie sa corespunda cu numarul de argumente acceptate de functie.
;; De exemplu, e perfect OK sa scrieti:
;; (map + '(  1   2   3   4)
;;        '( 10  20  30  40)
;;        '(100 200 300 400)), si veti obtine 
;;     ==> (111 222 333 444), pentru
;; ca map ia argumentele functiei sincronizat din listele date, si functia +
;; stie sa accepte mai multe argumente (cate 3 in cazul curent)
;;
;;
;; In al doilea rand exista functia apply in Scheme.
;; Functia ia oricate argumente (cel putin 2), din care ULTIMUL trebuie sa
;; fie o LISTA.  Cel mai simplu mod de a ne gandi la apply este ca la o
;; transformare a codului, de exemplu:
;;
;; (apply + 1 2 '(3 4 5)) devine
;;       (+ 1 2   3 4 5 ) [a se observa ca 1 si 2 sunt neatinse, dar din jurul lui 3 4 5 au disparut paranteze
;; iar 
;; (apply + '(1 2)) devine
;;       (+   1 2)
;; 
;; Adica apply "muta" paranteza sa dupa apply, si "desface" parantezele din ULTIMUL
;; argument, rezultand un apel de functie cu argumentele date dupa numele functiei.
;;
;; Acum urmeaza o observatie interesanta:  Stim ca functia list intoarce o lista a
;; argumentelor sale, de exemplu (list 1 2 3 4) intoarce lista (1 2 3 4).
;; Combinat cu map, list poate face lucruri interesante, si anume definitia finala:

(define (transpose2 M)
  (apply map list M))

(test 11 (transpose2 '((1 2 3 4) (x y z t) (! ? !? ?!))))

;; CUM FUNCTIONEAZA?
;;
;; Sa zicem ca M este '((1 2 3 4) (a b c d) (x y z t)), pe care o vom scrie formatata
;; ca matrice mai jos.
;;
;; Din evaluarea (transpose2 M) se expandeaza:
;;
;; (apply map list '((1 2 3 4)
;;                   (a b c d)
;;                   (x y z t)))
;; 
;; ... si apply isi face efectul, desface un rand de paranteze, codul devenind:
;;
;; (map list '(1 2 3 4)
;;           '(a b c d)
;;           '(x y z t))
;;
;; map ia fiecare "coloana" din matricea pe care a spart-o apply in linii, si
;; face liste cu aceste coloane, transformandu-le in linii.
;;
;; Rezultatul:  Direct matricea transpusa, adica
;; '((1 a x)
;;   (2 b y)
;;   (3 c z)
;;   (4 d t))

;; 12. (bonus - 2p)
;; Se da o expresie in Scheme formata doar din aplicatiile functiilor:
;; +, *, -, /, cons, append, list si corespondentele lor curry, definite
;; curry+, curry*, curry-, curry/, curry-cons, curry-append, curry-list
;; Expresia data poate contine greseli in sensul ca functii uncurry 
;; au fost aplicate de parca ar fi functii curry. (exemplu: ((+ 1) 2))
;; Scrieti functia curry-corrector care primeste o astfel de expresie (data sub forma de lista)
;; si corecteaza greselile existente, inlocuind functiile aplicate incorect
;; cu formele lor curry.
;; Functii ajutatoare: number?, boolean?, list?, symbol?, equal?
;;
;; (symbol? '+) => #t
;; (symbol? +) => #f
;; (symbol? (car '(+ 1 2))) => #t
;; (equal? '+ (car '(+ 1 2))) => #t
;; (equal? + '+) => #f
;;
;; Exemple:
;;
;; (curry-corrector '(+ 1 2)) => (+ 1 2)
;; (curry-corrector '((+ 1) 2)) => ((curry+ 1) 2)
;; (curry-corrector '(cons (- 1 ((* 3) 2)) (list ((+ 1) 3) (* 2 2) (/ 3 ((- 1) 2))))) => 
;;                => (cons (- 1 ((curry* 3) 2)) (list ((curry+ 1) 3) (* 2 2) (/ ((curry- 1) 2))))
;; 
;; Folositi eval pe expresia rezultata pentru a verifica corectitudinea. (eval expresie)

;; rezolvarea de mai jos corecteaza atat formele uncurry in formele curry (cum se cerea in enunt)
;; precum si formele curry in formele uncurry ale functiilor, atunci cand este necesar
(define curry+ (uncurry->curry +))
(define curry* (uncurry->curry *))
(define curry- (uncurry->curry -))
(define curry/ (uncurry->curry /))
(define curry-cons (uncurry->curry cons))
(define curry-append (uncurry->curry append))
(define curry-list (uncurry->curry list))

(define correspondence
  '((+ curry+)
    (* curry*)
    (- curry-)
    (/ curry/)
    (cons curry-cons)
    (append curry-append)
    (list curry-list)))

;; o functie care inlocuieste forma uncurry a unei functii cu echivalenta ei curry
(define (curry-replace f)
  (let search ((where correspondence))
    (cond ((null? where) f)
          ((eq? f (caar where)) (cadar where))
          (else (search (cdr where))))))

;; si invers
(define (uncurry-replace f)
  (let search ((where correspondence))
    (cond ((null? where) f)
          ((eq? f (cadar where)) (caar where))
          (else (search (cdr where))))))

(define (corrector-eval E)
  (eval (curry-corrector E)))

(define (curry-corrector E)
  (cond ((empty? E) '())  ;; ne protejam pentru mai jos
        ((and (list? E) (list? (car E))) ;; apel gen curry, cu 2 paranteze deschise
         (cons (force-curry (car E)) (map curry-corrector (cdr E))))
        ((list? E) ;; lista care nu incepe cu apel gen curry - deci apel normal
         (cons (uncurry-replace (car E)) (map curry-corrector (cdr E))))
        (else E)))

;; Observatie
;; odata ce identificam un apel de forma ((functie arg1 ... argk) argk+1 ... argn)
;; sau de forma (functie arg1 ... argn),
;; avem grija sa folosim forma corecta (curry sau uncurry) a functiei
;; si prelucram ca pe noi expresii fiecare din argumentele primite de functie
;; cu apelul (map curry-corrector (cdr E)).
;; daca apelam (curry-corrector (cdr E)), n-am fi putut face dinstinctia intre
;; elementele listei E care identifica o functie si elementele listei E
;; care identifica argumentele unei functii

(define (force-curry E)
  (if (list? E)
      (cons (force-curry (car E)) (map curry-corrector (cdr E)))
      (curry-replace E)))

(test 12 (curry-corrector '((+ 1) 2)))
(test 12 (curry-corrector '(cons (- 1 ((* 3) 2)) (list ((+ 1) 3) (* 2 2) (/ 3 ((- 1) 2)))))) 