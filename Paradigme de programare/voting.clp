;____________________________________________________________
(defmodule MAIN (export deftemplate ?ALL))
; Load facts: {(candidates c1...cn) crlf | (votes who whom) crlf }*
(deftemplate candidate (slot who) (slot votes))
(deffacts ff (candidates) (votes))
(defrule input_data
=>
(focus VOTING RESULT)
(printout t "Input file: ")
(set-fact-duplication TRUE)
(load-facts (readline))
; load facts from a file
(set-fact-duplication FALSE))

;____________________________________________________________
(defmodule CONVERT (import MAIN ?ALL))
; Converts a list of candidates into facts for each candidate
; setting the votes counter of the candidate to 0
(defrule register_candidate
(declare (auto-focus TRUE))
(candidates $? ?name $?)
(not (candidate (who ?name)))
=>
(assert (candidate (who ?name) (votes 0))))

;____________________________________________________________
(defmodule CHECK (import MAIN ?ALL))
; Looks up for multiple ballots of the same voter or for
; ballots of an already "invalidated" voter
(defrule voting_twice
(declare (auto-focus TRUE))
?f1 <- (votes ?x ?)
?f2 <- (votes ?x ?)
(test (neq ?f1 ?f2))
(not (invalidate ?x))
=>
(printout t ?x " voted several times" crlf)
(assert (invalidate ?x)))
(defrule invalidate_votes
(declare (auto-focus TRUE))
(invalidate ?x)
?f <- (votes ?x ?)
=> (retract ?f))
;____________________________________________________________
(defmodule VOTING (import MAIN ?ALL))
; Count the (valid) ballots for the existing candidates
(defrule voting_for_non_candidate
?f <- (votes ?x ?y)
(not (candidate (who ?y)))
=> (retract ?f))
(defrule voting_for_candidate
?f <- (votes ?x ?y)
?c <- (candidate (who ?y) (votes ?v))
=>
(modify ?c (votes (+ ?v 1)))
(retract ?f))
;____________________________________________________________
(defmodule RESULT (import MAIN ?ALL))
; Decide if the elections ended in a tie or there is a winner
(deftemplate tie (slot votes))
(defrule ballot_winner
(candidate (who ?x) (votes ?vx))
(not (candidate (who ?y & ~?x) (votes ?vy & :(>= ?vy ?vx))))
=>
(printout t "Winner " ?x " with " ?vx " votes" crlf))
(defrule tie_detect
(not (tie))
(candidate (who ?x) (votes ?vx))
(not (candidate (who ?y & ~?x) (votes ?vy &:(> ?vy ?vx))))
(exists (candidate (who ?z & ~?x) (votes ?vx)))
=>
(printout t "Tie with " ?vx " votes:" crlf)
(assert (tie (votes ?vx))))
(defrule tie_result
(tie (votes ?v))
(candidate (who ?c) (votes ?v))
=>
(printout t "
- " ?c crlf))

