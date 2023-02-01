(require "main.lisp")

(format t "~%***** Lancement des tests.~%~%")
;; Fonctions de test.

(defun test-projet (mv val code aff)
  (load-machine mv (compilation code))
  (if (eql val (run-machine mv))
      (format t "OK : ~S : ~S = ~D~%" aff code val)
    (format t "KO : ~S : ~S <> ~D~%" aff code val)
    )
  )

(defun test-projet-fun (mv val code fun aff)
  (load-machine mv (compilation fun))
  (load-machine mv (compilation code))
  (format t "F  : ~S~%" fun)
  (if (eql val (time (run-machine mv)))
      (format t "OK : ~S : ~S = ~D~%~%" aff code val)
    (format t "KO : ~S : ~S <> ~D~%~% ~S" aff code val (run-machine mv))
    )
  )

(defun test-projet-cond (mv val code aff)
  (load-machine mv (compilation code))
  (run-machine mv)
  (if (or (and val (get-reg mv :R0)) (and (not val) (not (get-reg mv :R0))))
      (format t "OK : ~S : ~S = ~D~%" aff code val)
    (format t "KO : ~S : ~S <> ~D~%" aff code val)
    )
  )

(defun test-projet-double (mv val code bis aff)
  (load-machine mv (compilation bis))
  (load-machine mv (compilation code))
  (format t "OH  : ~S~%" bis)
  (if (eql val (run-machine mv))
      (format t "OK : ~S : ~S = ~D~%" aff code val)
    (format t "KO : ~S : ~S <> ~D~%" aff code val)
    )
  )

;; Création d'une machine virtuelle dédiée aux tests.

(setf mv 'mvtest)
(make-machine mv 5000 T)


;; Tests arithmétiques.
(format t "~%Tests de calculs arithmétiques :~%")

(test-projet mv 108 '(+ 65 43) "ADD")
(test-projet mv -45 '(- 55 100) "SUB")
(test-projet mv 64 '(* 8 8) "MULT")
(test-projet mv 1 '(/ 8 8) "DIV")

(test-projet mv 1280 '(+ 650 430 200) "ADD BIS")
(test-projet mv -4500 '(- 5500 10000) "SUB BIS")
(test-projet mv 192 '(* 8 8 3) "MULT BIS")
(test-projet mv -1 '(/ -8 8) "DIV BIS")

(test-projet mv -26400 '(* (/ 70 (- 5699 5692)) (- 2 4) 33 40) "ADD SUB MULT DIV")


;; Tests d'opérateurs booléens.

(format t "~%Tests d'opérateurs booléens :~%")

(test-projet-cond mv T '(= 2 2) "= ")
(test-projet-cond mv T '(> 2 1) "> ")
(test-projet-cond mv T '(< 2 3) "< ")
(test-projet-cond mv T '(>= 2 2) ">=")
(test-projet-cond mv T '(>= 3 2) ">=")
(test-projet-cond mv T '(<= 2 2) "<=")
(test-projet-cond mv T '(<= 2 3) "<=")


;; Tests de boucles de condition.

(format t "~%Tests de structures de condition :~%")

(test-projet mv 1 '(if (> 1 0) 1 0) "IF")
(test-projet mv 0 '(if (= 1 0) 1 0) "IF BIS")

(test-projet mv 200 '(cond ((= 2 3) 5) ((> 1 100) 9) ((< 1 2) 200)) "COND")
(test-projet mv 9 '(cond ((= 2 3) 5) ((< 1 100) 9) ((< 1 2) 200)) "COND BIS")

(test-projet mv 1 '(if (and (< 0 2) (>= 3 3)) 1 0) "AND")
(test-projet mv 0 '(if (and (< 3 2) (>= 3 3)) 1 0) "AND BIS")

(test-projet mv 1 '(if (or (< 0 2) (>= 3 3)) 1 0) "OR")
(test-projet mv 1 '(if (or (< 3 2) (>= 3 3)) 1 0) "OR BIS")


;; Tests de fonctions.

(format t "~%Tests des fonctions et lambda-expressions :~%")

(test-projet-double mv 5 '(f 6) '(defun f (x) (- x 1)) "FUN")
(test-projet-double mv 15 '(g 6) '(defun g (x) (* 3 (f x))) "FUN BIS")

(test-projet mv 22 '((lambda (x) (* 2 x)) 11) "LAMBDA")
(test-projet mv -2189 '((lambda (x) (- x (* 200 x))) 11) "LAMBDA BIS")


;; Tests de structures de contrôle.

(format t "~%Tests des structures de contrôle :~%")

(test-projet-double mv 10 '(* a 2) '(setf a 5) "SETF")
(test-projet-double mv 20 '(* a 2) '(setf a (+ a 5)) "SETF BIS")

(test-projet mv 18 '(progn (setf a 9) (if (< a 10) (* 2 a) (* 3 a))) "PROGN")
(test-projet mv 33 '(progn (setf a 11) (if (< a 10) (* 2 a) (* 3 a))) "PROGN BIS")

(test-projet-double mv 30 '(test-let 10) '(defun test-let (x) (let ((a 20)) ( + a x))) "LET")
(test-projet-double mv 24 '(test-let-bis 3 4) '(defun test-let-bis (x y) (let (( a 1) (b 2)) (* x y a b))) "LET BIS")


;; Tests de fonctions classiques.

(format t "~%Tests de fonctions classiques :~%")

(test-projet-fun mv 362880 '(fact 9) '(defun fact (n) (if (<= n 1) 1 (* n (fact (- n 1))))) "Factorielle")
(test-projet-fun mv 39916800 '(fact 11) '(defun fact-rt (n &optional (acc 1)) (if (<= n 1) acc (fact-rt (- n 1) (* acc n)))) "Factorielle récursive terminale")

(test-projet-fun mv 55 '(fibo 10) '(defun fibo (n) (if (< n 2) n (+ (fibo (- n 1)) (fibo (- n 2))))) "Fibonacci")

(test-projet-fun mv 144 '(fibo-rt 12) '(defun fibo-rt (n) (labels ((calc-fib (n a b) (if (= n 0) a (calc-fib (- n 1) b (+ a b))))) (calc-fib n 0 1))) "Fibonacci récursive terminale")

  