(compilation '(


;;*************** FONCTIONS ********************

;; Gestion de l'analyse par cas.

(defun is-cas (exp inst)
  (eql (car exp) inst)
)

;;*************** DIFFERENTS CAS ***************

;; Compilation de littéraux.

(defun compilation-const (exp)
  `((MOVE (:DIESE ,exp) :R0))
)

(defun compilation-varg (exp)
  `((MOVE (:* :@ ,exp) :R0))
  )

(defun compilation-litt (exp env fenv nomf)
  (let ((var (assoc exp env)))
    (cond
     ((not (null var))
      (if (eql (cadr var) 'loc) 
	  `((MOVE ,(cdr var) :R0))
	(if (numberp (cadr var)) 
	    (compilation-const (cdr var)))
	)
      )
     ((and (symbolp exp) (not (null exp))) (compilation-varg exp))
     (t (compilation-const exp))
     )
    )
  )

;; Compilation d'opérations arithmétiques

(defun compilation-op (exp env fenv nomf)
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

(defun compilation-comp (exp env fenv nomf)
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

(defun compilation-and (exp etiqfin env fenv nomf)
  (if (null exp) 
      (append '((MOVE (:DIESE T) :R0)) `((@ ,etiqfin)))
    (append (compilation (car exp) env fenv nomf) 
	    '((CMP :R0 (:DIESE T)))
	    `((JNE (@ ,etiqfin)))
	    (compilation-and (cdr exp) etiqfin env fenv nomf))
    )
  )

(defun compilation-or (exp etiqfin env fenv nomf)
  (if (null exp) 
      (append '((MOVE (:DIESE NIL) :R0)) `((@ ,etiqfin)))
    (append (compilation (car exp) env fenv nomf)
	    '((CMP :R0 (:DIESE T)))
	    `((JEQ (@ ,etiqfin)))
	    (compilation-or (cdr exp) etiqfin env fenv nomf))
    )
  )


;; Compilation de structures de condition.

(defun compilation-if (exp env fenv nomf)
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

(defun compilation-cond (exp etiqfin env fenv nomf)
  (if (null exp)
      (append '((MOVE (:DIESE NIL) :R0)) `((@ , etiqfin)))      
    (let ((etiqcond (gensym "etiqcond")))                              
      (append (compilation (caar exp) env fenv nomf)                
	      '((CMP :R0 (:DIESE NIL)))                                
	      `((JEQ (@ ,etiqcond)))                             
	      (compilation (cadar exp) env fenv nomf)              
	      `((JMP (@ ,etiqfin)))                               
	      `((@ ,etiqcond))                                    
	      (compilation-cond (cdr exp) etiqfin env fenv nomf))
      )
    )
  )


;; Compilation de structures itératives.

(defun compilation-progn (exp env fenv nomf)
  (if (null exp) 
      ()
    (append (compilation (car exp) env fenv nomf) (compilation-progn (cdr exp) env fenv nomf))
    )
  )

(defun compilation-boucle (exp env fenv nomf)
  (case (car exp)
   ('while (compilation-while (cdr exp) env fenv nomf))
    ('until (compilation-until (cdr exp) env fenv nomf))
    )
  )

(defun compilation-while (exp env fenv nomf)
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

(defun compilation-until (exp env fenv nomf)  
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

(defun compilation-defun (exp env fenv nomf)
  (let ((nivem (assoc nomf fenv)))
    (append '((FENTRY))
	    `((@ ,(car exp)))
	    (compilation-progn (cddr exp)
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

(defun compilation-appel (exp env fenv nomf)
  (let ((n (length (cdr exp))) 
	(nivem (assoc (car exp) fenv)))
    (append (compilation-param (cdr exp) env fenv nomf)
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

(defun compilation-param (exp env fenv nomf)
  (if (atom exp) 
      ()
    (append (compilation (car exp) env fenv nomf)
	    `((PUSH :R0))
	    (compilation-param (cdr exp) env fenv nomf))
    )
  )

(defun compilation-lambda (exp env fenv nomf)
  (let ((lambdaexpr (gensym "lambdaexpr"))
	(n (length (cdr exp)))
	(nivem (assoc nomf fenv)))
    (append (compilation-param (cdr exp) env fenv nomf)
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

(defun compilation-setf (exp env fenv nomf)
  (if (null exp) ()
    (append (compilation (cadr exp) env fenv nomf)
	    (let ((var (assoc (car exp) env)))
	      (if var
		  (if (eql (cadr exp) 'loc) '((MOVE :R0 (cdr var))))
		`((VARG ,(car exp)))
		)
	      )
	    `((MOVE :R0 ,(cadar (compilation (car exp) env fenv nomf))))
	    (compilation-setf (cddr exp) env fenv nomf))
    )
  )
      
(defun compilation-let (exp env fenv nomf)
  (let ((nivem (assoc nomf fenv)))
    (append (compilation-local (car exp) env fenv nomf)
	    (compilation (cadr exp) (local-env (car exp) env 1 (cadr nivem)) fenv nomf)
	    (depile-local (car exp) env fenv nomf))
    )
  )

(defun compilation-local (exp env fenv nomf)
  (if (null exp) 
      ()
    (append (compilation (cadar exp) env fenv nomf)
	    '((PUSH :R0))
	    (compilation-local (cdr exp) env fenv nomf ))
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

(defun compilation-labels (exp env fenv nomf)
  (let ((nivem (assoc nomf fenv)) (corps (gensym "CORPS")))
    (append `((JMP (@ ,corps)))
	    (compilation-faux (car exp) env (fun-env (car exp) fenv (+ 1 (cadr nivem)) ) nomf)
	    `((@ ,corps))
	    (compilation (cadr exp) env (fun-env (car exp) fenv (+ 1 (cadr nivem)) ) nomf))
    )
  ) 

(defun compilation-faux (exp env fenv nomf)
  (if (null exp) 
      ()
    (let ((nivem (assoc (caar exp) fenv)))
      (append '((FENTRY))
	      `((@ ,(caar exp)))
	      (compilation (caddar exp)  (param-env (cadar exp) env 1 (cadr nivem) ) fenv (caar exp))
	      '((RTN))
	      '((FEXIT))
	      (compilation-faux (cdr exp) env fenv nomf))
      )
    )
  )

;; Fonctions de lancement du compilateur LISP -> ASM.

;; La fonction "compilation" est une fonction de compilateur LISP -> ASM qui convertit une expression LISP en une suite d'instructions ASM.

;; Arguments : "exp" est l'expression LISP à compiler. "env" est un environnement qui contient les variables locales et leurs valeurs associées. "fenv" est un environnement qui contient les noms des fonctions et leurs corps. "nomf" est le nom de la fonction en cours de compilation.

;; (let ((arg (if (atom exp) () (cdr exp)))) : Cette ligne définit une variable locale "arg" qui est la liste des arguments de "exp" si "exp" n'est pas un atome, sinon "arg" est vide.

;; (cond : Cette instruction "cond" est une forme conditionnelle qui évalue une série de conditions et exécute le premier bloc de code associé à la première condition qui est vraie.

;; Les différents cas sont gérés avec les instructions "is-cas", qui vérifient si le premier élément de "exp" est égal à un certain symbole LISP (par exemple, "and", "or", "if", "cond", etc.). Si une condition est vraie, la fonction associée est appelée avec les arguments appropriés. Par exemple, si le premier élément de "exp" est "and", la fonction "compilation-and" est appelée avec les arguments "arg" et "gensym" "finAnd", "env", "fenv" et "nomf".

;; Les fonctions appelées dans le "cond" sont des fonctions de compilateur pour des structures LISP spécifiques (par exemple, "compilation-and" pour la structure "and", "compilation-if" pour la structure "if", etc.). Elles génèrent une suite d'instructions ASM à partir de l'expression LISP.

;; Enfin, la fonction "compilation" retourne le résultat de la fonction appelée dans le "cond".


(defun compilation (exp &optional (env ()) (fenv ())  (nomf ()) )
  (let ((arg (if (atom exp) () (cdr exp))))
    (cond
     ((atom exp) (compilation-litt exp env fenv nomf))
     ((member (car exp) '(+ - * /)) (compilation-op exp env fenv nomf))
     ((member (car exp) '(< > = <= >= )) (compilation-comp exp env fenv nomf))
     ((is-cas exp 'and) (compilation-and arg (gensym "finAnd") env fenv nomf))
     ((is-cas exp 'or) (compilation-or arg (gensym "finOr") env fenv nomf))
     ((is-cas exp 'if) (compilation-if arg env fenv nomf))
     ((is-cas exp 'cond) (compilation-cond arg (gensym "fincond") env fenv nomf))
     ((is-cas exp 'progn) (compilation-progn arg env fenv nomf))
     ((is-cas exp 'loop) (compilation-boucle arg env fenv nomf))
     ((is-cas exp 'setf) (compilation-setf arg env fenv nomf))
     ((is-cas exp 'defun) (compilation-defun arg env fenv nomf))
     ((is-cas exp 'let ) (compilation-let arg env fenv nomf))
     ((is-cas exp 'labels) (compilation-labels arg env fenv nomf))
     ((and (consp (car exp)) (eql (caar exp) 'lambda)) (compilation-lambda exp env fenv nomf))
     (`(function ,(car exp)) (compilation-appel exp env fenv nomf))
    )
    )
  )

))