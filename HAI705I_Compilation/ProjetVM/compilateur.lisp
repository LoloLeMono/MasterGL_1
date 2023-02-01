;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;   ___ ___  __  __ ___ ___ _      _ _____ ___ _   _ ___   ;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;  / __/ _ \|  \/  | _ \_ _| |    /_\_   _| __| | | | _ \  ;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;; | (_| (_) | |\/| |  _/| || |__ / _ \| | | _|| |_| |   /  ;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;  \___\___/|_|  |_|_| |___|____/_/ \_\_| |___|\___/|_|_\  ;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;                                                          ;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;



;;*************** FONCTIONS ********************

;; Gestion de l'analyse par cas.

(defun compare (exp inst)
  (eql (car exp) inst)
)

;;*************** DIFFERENTS CAS ***************

;; Compilation de littéraux.

(defun compil-const (exp)
  `((MOVE (:DIESE ,exp) :R0))
)

(defun compil-varg (exp)
  `((MOVE (:* :@ ,exp) :R0))
  )

(defun compil-litt (exp env fenv nomf)
  (let ((var (assoc exp env)))
    (cond
     ((not (null var))
      (if (eql (cadr var) 'loc) 
	  `((MOVE ,(cdr var) :R0))
	(if (numberp (cadr var)) 
	    (compil-const (cdr var)))
	)
      )
     ((and (symbolp exp) (not (null exp))) (compil-varg exp))
     (t (compil-const exp))
     )
    )
  )

;; Compilation d'opérations arithmétiques

(defun compil-op (exp env fenv nomf)
  (let ((op (car exp))
	(arg (cdr exp)))
    (if (null (cddr arg))
	(append (compilation (car arg) env fenv nomf)
		'((PUSH :R0))
		(compilation (cadr arg) env fenv nomf)
		'((PUSH :R0))
		'((POP :R1))
		'((POP :R0))
		(case op
		  ('+ '((ADD :R1 :R0)))
		  ('- '((SUB :R1 :R0)))
		  ('* '((MULT :R1 :R0)))
		  ('/ '((DIV :R1 :R0)))))      
      (append (compilation  `(,op ,(list op (car arg) (cadr arg)) ,@(cddr arg)) env fenv nomf))
      )
    )
  )


;; Compilation d'opérateurs de comparaison.

(defun compil-comp (exp env fenv nomf)
  (let ((op (car exp))
	(fin (gensym "finTest")))
    (append (compilation (cadr exp) env fenv nomf)
	    '((PUSH :R0))           
	    (compilation (caddr exp) env fenv nomf)
	    '((PUSH :R0))
	    '((POP :R0))
	    '((POP :R1))
	    '((CMP :R1 :R0)) 
	    '((MOVE (:DIESE T) :R0)) 
	    (case op
	      ('= `((JEQ (@ ,fin))))
	      ('< `((JL (@ ,fin))))
	      ('> `((JG (@ ,fin))))
	      ('<= `((JLE (@ ,fin))))
	      ('>= `((JGE (@ ,fin))))
	      )
	    '((MOVE (:DIESE NIL) :R0))
	    `((@ ,fin)))
    )
  )

(defun compil-and (exp etiqfin env fenv nomf)
  (if (null exp) 
      (append '((MOVE (:DIESE T) :R0)) `((@ ,etiqfin)))
    (append (compilation (car exp) env fenv nomf) 
	    '((CMP :R0 (:DIESE T)))
	    `((JNE (@ ,etiqfin)))
	    (compil-and (cdr exp) etiqfin env fenv nomf))
    )
  )

(defun compil-or (exp etiqfin env fenv nomf)
  (if (null exp) 
      (append '((MOVE (:DIESE NIL) :R0)) `((@ ,etiqfin)))
    (append (compilation (car exp) env fenv nomf)
	    '((CMP :R0 (:DIESE T)))
	    `((JEQ (@ ,etiqfin)))
	    (compil-or (cdr exp) etiqfin env fenv nomf))
    )
  )


;; Compilation de structures de condition.

(defun compil-if (exp env fenv nomf)
  (let ((sinon (gensym "sinon"))
	(finSi (gensym "finSi")))
    (append (compilation (car exp) env fenv nomf)  
	    '((CMP :R0 (:DIESE nil)))
	    `((JEQ (@ ,sinon)))
	    (compilation (cadr exp) env fenv nomf) 
	    `((JMP (@ ,finSi)))
	    `((@ ,sinon))  
	    (compilation (caddr exp) env fenv nomf)
	    `((@ ,finSi)))
    )
  )

(defun compil-cond (exp etiqfin env fenv nomf)
  (if (null exp)
      (append '((MOVE (:DIESE NIL) :R0)) `((@ , etiqfin)))      
    (let ((etiqcond (gensym "etiqcond")))                              
      (append (compilation (caar exp) env fenv nomf)                
	      '((CMP :R0 (:DIESE NIL)))                                
	      `((JEQ (@ ,etiqcond)))                             
	      (compilation (cadar exp) env fenv nomf)              
	      `((JMP (@ ,etiqfin)))                               
	      `((@ ,etiqcond))                                    
	      (compil-cond (cdr exp) etiqfin env fenv nomf))
      )
    )
  )


;; Compilation de structures itératives.

(defun compil-progn (exp env fenv nomf)
  (if (null exp) 
      ()
    (append (compilation (car exp) env fenv nomf) (compil-progn (cdr exp) env fenv nomf))
    )
  )

(defun compil-boucle (exp env fenv nomf)
  (case (car exp)
   ('while (compil-while (cdr exp) env fenv nomf))
    ('until (compil-until (cdr exp) env fenv nomf))
    )
  )

(defun compil-while (exp env fenv nomf)
  (let ((fin (gensym "finwhile"))
	(boucle (gensym "while"))) 
    (if (eql (cadr exp) 'do) 
	(append `((@ ,boucle))
		(compilation (car exp) env fenv nomf) 
		`((CMP :R0 (:DIESE nil)))
		`((JEQ (@ ,fin)))
		(compilation (caddr exp) env fenv nomf) 
		`((JMP (@ ,boucle)))
		`((@, fin)))
      (error "Syntaxe incorrecte : ~s" exp)
      )
    )
  )

(defun compil-until (exp env fenv nomf)  
  (let ((finuntil (gensym "FINUNTIL"))
	(boucle (gensym "UNTIL")))
    (append `((@ ,boucle))
	    (compilation (car exp) env fenv nomf)
	    '((CMP :R0 (:DIESE T)))
	    `((JEQ (@ ,finuntil)))
	    (compilation (caddr exp) env fenv nomf)
	    `((JMP (@ ,boucle)))
	    `((@ ,fin)))
    )
  )


;; Compilation de fonctions.

(defun compil-defun (exp env fenv nomf)
  (let ((nivem (assoc nomf fenv)))
    (append '((FENTRY))
	    `((@ ,(car exp)))
	    (compil-progn (cddr exp)
			       (param-env (cadr exp) env 1 (if nivem (+ 1 (cadr nivem)) 0)) 
			       (fun-env (list exp) fenv (if nivem (+ 1 (cadr nivem)) 0)) 
			       (car exp))
	    '((RTN))
	    '((FEXIT)))
    )
  )

(defun param-env (exp env dep nivem)   
  (if (atom exp) 
      env
    (param-env (cdr exp) (cons (cons (car exp) `(LOC ,(- 0 dep) ,nivem)) env) (+ 1 dep) nivem)
    )
  )

(defun fun-env (exp fenv nivem)
  (if (atom exp) 
      fenv
    (fun-env (cdr exp) (cons `(,(caar exp) ,nivem) fenv) nivem)
    )
  )

(defun compil-appel (exp env fenv nomf)
  (let ((n (length (cdr exp))) 
	(nivem (assoc (car exp) fenv)))
    (append (compil-param (cdr exp) env fenv nomf)
	    `((PUSH (:DIESE ,n)))
	    `((MOVE :FP :R1))
	    `((MOVE :SP :FP))
	    `((MOVE :SP :R2))
	    `((SUB  (:DIESE ,n) :R2))
	    `((SUB  (:DIESE 1) :R2))
	    `((PUSH :R2)) 
	    `((PUSH :R1))
	    (if nivem  `((PUSH (:DIESE ,(cadr nivem))))  `((PUSH (:DIESE ,0))))
	    `((JSR (@ ,(car exp)))))
    )
  )

(defun compil-param (exp env fenv nomf)
  (if (atom exp) 
      ()
    (append (compilation (car exp) env fenv nomf)
	    `((PUSH :R0))
	    (compil-param (cdr exp) env fenv nomf))
    )
  )

(defun compil-lambda (exp env fenv nomf)
  (let ((lambdaexpr (gensym "lambdaexpr"))
	(n (length (cdr exp)))
	(nivem (assoc nomf fenv)))
    (append (compil-param (cdr exp) env fenv nomf)
	    `((PUSH (:DIESE ,n)))
	    `((MOVE :FP :R1))
	    `((MOVE :SP :FP))
	    `((MOVE :SP :R2))
	    `((SUB  (:DIESE ,n) :R2))	
	    `((SUB  (:DIESE 1) :R2))
	    `((PUSH :R2))
	    `((PUSH :R1))
	    (if nivem  `((PUSH (:DIESE ,(+ 1 (cadr nivem)))))  `((PUSH (:DIESE ,0))))
	    `((PUSH (:DIESE 0)))
	    (compilation (caddar exp)
			 (param-env (cadar exp) env 1 (if nivem   (+ 1 (cadr nivem)) 0)) 
			 (fun-env  (list (cons lambdaexpr (cdar exp))) fenv (if nivem (+ 1 (cadr nivem)) 0))
			 lambdaexpr)
	    `((MOVE ( 1 :FP) :SP))
	    `((MOVE ( 2 :FP) :FP)))
    )
  )


;; Compilation des déclarations de variables.

(defun compil-setf (exp env fenv nomf)
  (if (null exp) ()
    (append (compilation (cadr exp) env fenv nomf)
	    (let ((var (assoc (car exp) env)))
	      (if var
		  (if (eql (cadr exp) 'loc) '((MOVE :R0 (cdr var))))
		`((VARG ,(car exp)))
		)
	      )
	    `((MOVE :R0 ,(cadar (compilation (car exp) env fenv nomf))))
	    (compil-setf (cddr exp) env fenv nomf))
    )
  )
      
(defun compil-let (exp env fenv nomf)
  (let ((nivem (assoc nomf fenv)))
    (append (compil-local (car exp) env fenv nomf)
	    (compilation (cadr exp) (local-env (car exp) env 1 (cadr nivem)) fenv nomf)
	    (depile-local (car exp) env fenv nomf))
    )
  )

(defun compil-local (exp env fenv nomf)
  (if (null exp) 
      ()
    (append (compilation (cadar exp) env fenv nomf)
	    '((PUSH :R0))
	    (compil-local (cdr exp) env fenv nomf ))
    )
  )

(defun depile-local (exp env fenv nomf)
  (if (null exp) 
      ()
    (append '((POP :R1)) (depile-local (cdr exp) env fenv nomf))
    )
  )

(defun local-env (exp env dep nivem)  
  (if (atom exp) 
      env
    (local-env (cdr exp) (cons (cons (caar exp) `(LOC ,dep ,nivem)) env) (+ 1 dep) nivem)
    )
  )


;; Compilation des labels.

(defun compil-labels (exp env fenv nomf)
  (let ((nivem (assoc nomf fenv)) (corps (gensym "CORPS")))
    (append `((JMP (@ ,corps)))
	    (compil-faux (car exp) env (fun-env (car exp) fenv (+ 1 (cadr nivem)) ) nomf)
	    `((@ ,corps))
	    (compilation (cadr exp) env (fun-env (car exp) fenv (+ 1 (cadr nivem)) ) nomf))
    )
  )

(defun compil-faux (exp env fenv nomf)
  (if (null exp) 
      ()
    (let ((nivem (assoc (caar exp) fenv)))
      (append '((FENTRY))
	      `((@ ,(caar exp)))
	      (compilation (caddar exp)  (param-env (cadar exp) env 1 (cadr nivem) ) fenv (caar exp))
	      '((RTN))
	      '((FEXIT))
	      (compil-faux (cdr exp) env fenv nomf))
      )
    )
  )

;; Fonctions de lancement du compilateur LISP -> ASM.

(defun compilation (exp &optional (env ()) (fenv ())  (nomf ()) )
  (let ((arg (if (atom exp) () (cdr exp))))
    (cond
     ((atom exp) (compil-litt exp env fenv nomf))
     ((member (car exp) '(+ - * /)) (compil-op exp env fenv nomf))
     ((member (car exp) '(< > = <= >= )) (compil-comp exp env fenv nomf))
     ((compare exp 'and) (compil-and arg (gensym "finAnd") env fenv nomf))
     ((compare exp 'or) (compil-or arg (gensym "finOr") env fenv nomf))
     ((compare exp 'if) (compil-if arg env fenv nomf))
     ((compare exp 'cond) (compil-cond arg (gensym "fincond") env fenv nomf))
     ((compare exp 'progn) (compil-progn arg env fenv nomf))
     ((compare exp 'loop) (compil-boucle arg env fenv nomf))
     ((compare exp 'setf) (compil-setf arg env fenv nomf))
     ((compare exp 'defun) (compil-defun arg env fenv nomf))
     ((compare exp 'let ) (compil-let arg env fenv nomf))
     ((compare exp 'labels) (compil-labels arg env fenv nomf))
     ((and (consp (car exp)) (eql (caar exp) 'lambda)) (compil-lambda exp env fenv nomf))
     (`(function ,(car exp)) (compil-appel exp env fenv nomf))
    )
    )
  )