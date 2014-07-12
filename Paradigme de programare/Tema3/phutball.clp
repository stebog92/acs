(deftemplate visited
	(slot id)
	(multislot places))
(deftemplate visited_in
	(slot id)
	(multislot places))
 
(defrule r1
	(declare (salience 10))
	?u <- (world (limit $?l) (ball $?z) (men $?k) (id ?id) (moves))
	=>
	(retract ?u)
	(assert (world (limit $?l) (ball $?z) (men - $?k) (id ?id) (moves $?z -)))
	(assert (visited (id ?id) (places - $?z -)))
	(assert (visited_in (id ?id) (places -))))
; reguli de mutare in cele 8 directii
(defrule r2
	(declare (salience 10))
	(logical (world (limit $?limit) (ball ?x ?y) (men $?fst - ?dx&:(= ?dx (- ?x 1)) ?dy&:(= ?dy (+ ?y 1)) - $?snd) (id ?id) (moves $?z)))
	?g <- (visited (id ?id) (places $?places))
	(not (visited (id ?id) (places $? - ?nx&:(= ?nx (- ?x 2)) ?ny&:(= ?ny (+ ?y 2)) - $?)))
	(not (visited_in (id ?id) (places $? - ?x ?y ?xx&:(= ?xx (- ?x 2)) ?yy&:(= ?yy (+ ?y 2)) - $?)))
	=>
	(retract ?g)
	(assert (world (limit $?limit) (ball (- ?x 2) (+ ?y 2)) (men $?fst - $?snd) (id ?id) (moves $?z (- ?x 2) (+ ?y 2) -)))
	(assert (visited (id ?id) (places $?places (- ?x 2) (+ ?y 2) -))))

(defrule r3
	(declare (salience 10))
	(logical (world (limit $?limit) (ball ?x ?y) (men $?fst - ?dx&:(= ?dx (- ?x 1)) ?dy&:(= ?dy ?y) - $?snd) (id ?id) (moves $?z)))
	?g <- (visited (id ?id) (places $?places))
	(not (visited (id ?id) (places $? - ?nx&:(= ?nx (- ?x 2)) ?y - $?)))
	(not (visited_in (id ?id) (places $? - ?x ?y ?xx&:(= ?xx (- ?x 2)) ?y - $?)))
	=>
	(retract ?g)
	(assert (world (limit $?limit) (ball (- ?x 2) ?y) (men $?fst - $?snd) (id ?id) (moves $?z (- ?x 2) ?y -)))
	(assert (visited (id ?id) (places $?places (- ?x 2) ?y -))))
(defrule r4
	(declare (salience 10))
	(logical (world (limit $?limit) (ball ?x ?y) (men $?fst - ?dx&:(= ?dx (- ?x 1)) ?dy&:(= ?dy (- ?y 1)) - $?snd) (id ?id) (moves $?z)))
	?g <- (visited (id ?id) (places $?places))
	(not (visited (id ?id) (places $? - ?nx&:(= ?nx (- ?x 2)) ?ny&:(= ?ny (- ?y 2)) - $?)))
	(not (visited_in (id ?id) (places $? - ?x ?y ?xx&:(= ?xx (- ?x 2)) ?yy&:(= ?yy (- ?y 2)) - $?)))
	=>
	(retract ?g)
	(assert (world (limit $?limit) (ball (- ?x 2) (- ?y 2)) (men $?fst - $?snd) (id ?id) (moves $?z (- ?x 2) (- ?y 2) -)))
	(assert (visited (id ?id) (places $?places (- ?x 2) (- ?y 2) -))))

(defrule r5
	(declare (salience 10))
	(logical (world (limit $?limit) (ball ?x ?y) (men $?fst - ?dx&:(= ?dx ?x) ?dy&:(= ?dy (- ?y 1)) - $?snd) (id ?id) (moves $?z)))
	?g <- (visited (id ?id) (places $?places))
	(not (visited (id ?id) (places $? - ?x ?ny&:(= ?ny (- ?y 2)) - $?)))
	(not (visited_in (id ?id) (places $? - ?x ?y ?x ?yy&:(= ?yy (- ?y 2)) - $?)))
	=>
	(retract ?g)
	(assert (world (limit $?limit) (ball ?x (- ?y 2)) (men $?fst - $?snd) (id ?id) (moves $?z ?x (- ?y 2) -)))
	(assert (visited (id ?id) (places $?places ?x (- ?y 2) -))))

(defrule r6
	(declare (salience 10))
	(logical (world (limit $?limit) (ball ?x ?y) (men $?fst - ?dx&:(= ?dx (+ ?x 1)) ?dy&:(= ?dy (- ?y 1)) - $?snd) (id ?id) (moves $?z)))
	?g <- (visited (id ?id) (places $?places))
	(not (visited (id ?id) (places $? - ?nx&:(= ?nx (+ ?x 2)) ?ny&:(= ?ny (- ?y 2)) - $?)))
	(not (visited_in (id ?id) (places $? - ?x ?y ?xx&:(= ?xx (+ ?x 2)) ?yy&:(= ?yy (- ?y 2)) - $?)))
	=>
	(retract ?g)
	(assert (world (limit $?limit) (ball (+ ?x 2) (- ?y 2)) (men $?fst - $?snd) (id ?id) (moves $?z (+ ?x 2) (- ?y 2) -)))
	(assert (visited (id ?id) (places $?places (+ ?x 2) (- ?y 2) -))))

(defrule r7
	(declare (salience 10))
	(logical (world (limit $?limit) (ball ?x ?y) (men $?fst - ?dx&:(= ?dx (+ ?x 1)) ?dy&:(= ?dy ?y) - $?snd) (id ?id) (moves $?z)))
	?g <- (visited (id ?id) (places $?places))
	(not (visited (id ?id) (places $? - ?nx&:(= ?nx (+ ?x 2)) ?y - $?)))
	(not (visited_in (id ?id) (places $? - ?x ?y ?xx&:(= ?xx (+ ?x 2)) ?y - $?)))
	=>
	(retract ?g)
	(assert (world (limit $?limit) (ball (+ ?x 2) ?y) (men $?fst - $?snd) (id ?id) (moves $?z (+ ?x 2) ?y -)))
	(assert (visited (id ?id) (places $?places (+ ?x 2) ?y -))))


(defrule r8
	(declare (salience 10))
	(logical (world (limit $?limit) (ball ?x ?y) (men $?fst - ?dx&:(= ?dx (+ ?x 1)) ?dy&:(= ?dy (+ ?y 1)) - $?snd) (id ?id) (moves $?z)))
	?g <- (visited (id ?id) (places $?places))
	(not (visited (id ?id) (places $? - ?nx&:(= ?nx (+ ?x 2)) ?ny&:(= ?ny (+ ?y 2)) - $?)))
	(not (visited_in (id ?id) (places $? - ?x ?y ?xx&:(= ?xx (+ ?x 2)) ?yy&:(= ?yy (+ ?y 2)) - $?)))
	=>
	(retract ?g)
	(assert (world (limit $?limit) (ball (+ ?x 2) (+ ?y 2)) (men $?fst - $?snd) (id ?id) (moves $?z (+ ?x 2) (+ ?y 2) -)))
	(assert (visited (id ?id) (places $?places (+ ?x 2) (+ ?y 2) -))))

(defrule r9
	(declare (salience 10))
	(logical (world (limit $?limit) (ball ?x ?y) (men $?fst - ?dx&:(= ?dx ?x) ?dy&:(= ?dy (+ ?y 1)) - $?snd) (id ?id) (moves $?z)))
	?g <- (visited (id ?id) (places $?places))
	(not (visited (id ?id) (places $? - ?x ?ny&:(= ?ny (+ ?y 2)) - $?)))
	(not (visited_in (id ?id) (places $? - ?x ?y ?x ?yy&:(= ?yy (+ ?y 2)) - $?)))
	=>
	(retract ?g)
	(assert (world (limit $?limit) (ball ?x (+ ?y 2)) (men $?fst - $?snd) (id ?id) (moves $?z ?x (+ ?y 2) -)))
	(assert (visited (id ?id) (places $?places ?x (+ ?y 2) -))))

(defrule r13
	(declare (salience 11))
	(logical (world (limit $?limit) (ball ?lx ?ly) (men $?fst - ?x ?y - $?snd) (id ?id) (moves $?z ?lx ?ly -)))
	?u <- (world (limit $?limit) (ball ?x ?y) (men $?fst1 - ?x ?y - $?snd1) (id ?id) (moves $?z ?lx ?ly - ?x ?y -))
	?g <- (visited_in (id ?id) (places $?places))
	?p <- (visited (id ?id) (places $?plc - ?x ?y - $?plc2))
	=>
	(if (< (- ?x ?lx) 0)
	then (bind ?nx (- ?x 1))
	else (if (> (- ?x ?lx) 0)
		then (bind ?nx (+ ?x 1))
		else (bind ?nx ?x)))
	(if (< (- ?y ?ly) 0)
	then (bind ?ny (- ?y 1))
	else (if (> (- ?y ?ly) 0)
		then (bind ?ny (+ ?y 1))
		else (bind ?ny ?y)))
	(retract ?u)
	(retract ?g)
	(retract ?p)
	(assert (world (limit $?limit) (ball ?nx ?ny) (men $?fst1 - $?snd1) (id ?id) (moves $?z ?lx ?ly - ?nx ?ny -)))
	(assert (visited_in (id ?id) (places $?places ?lx ?ly ?x ?y -)))
	(assert (visited (id ?id) (places $?plc - ?nx ?ny - $?plc2))))

; regula cea mai importanta = verifica daca mingea a ajuns pe ultima linie
; daca a ajuns se sterg toate fact-urile si se insereaza fact-ul win
(defrule r11
	(declare (salience 12))
	(world (limit ?l ?c) (ball ?x&:(= ?x (- ?l 1)) ?y) (id ?id) (moves $?moves))
	?r <- (world (id ?id) (moves ? ? -))
	?g <- (visited_in (id ?id))
	=>
	(retract ?r)
	(retract ?g)
	(assert (win (id ?id) (moves $?moves))))
; daca s-au verificat toate regulile de mutare si nu se mai pot executa alte mutari
; se sterg toate facturile 
(defrule r12
	(declare (salience 9))
	?g <- (world (id ?id) (moves ? ? -))
	=>
	(retract ?g))

(defrule r14
	(declare (salience 9))
	?r <- (visited_in (id ?id))
	=>
	(retract ?r))
(defrule r15
	(declare (salience 9))
	?u <- (visited (id ?id))
	=>
	(retract ?u))

