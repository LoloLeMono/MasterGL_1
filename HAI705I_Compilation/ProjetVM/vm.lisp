;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; 
;;;;;;;;;;;;;;;;;;;;;;;;;;;  __   __ __  __   ;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;  \ \ / /|  \/  |  ;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;   \ V / | |\/| |  ;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;    \_/  |_|  |_|  ;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;                   ;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;



;; ********** FONCTIONS DE GESTION DE LA MACHINE VIRTUELLE **********

;; ********** Gestion des attributs de la machine.

(defun get-prop (vm prop)
  (get vm prop)
  )

(defun set-prop (vm prop val)
  (setf (get vm prop) val)
  )

(defun inc-prop (vm prop)
  (set-prop vm prop (+ (get-prop vm prop) 1))
  )

(defun dec-prop (vm prop)
  (set-prop vm prop (- (get-prop vm prop) 1))
  )

(defun set-tab (tab cle val)
  (setf (aref tab cle) val)
  )


;; ********** Gestion de la mémoire.

(defun get-taille (&optional (vm 'vm))
  (get-prop vm :taille)
  )

(defun set-taille (vm tmem)
  (set-prop vm :taille tmem)
  )

(defun get-mem (vm adr)
  (aref (get vm :memtab) adr)
  )

(defun set-mem (vm adr val)
  (set-tab (get vm :memtab) adr val)
  )


;; ********** Gestion des registres.

(defun get-reg (vm reg)
  (get-prop vm reg)
  )

(defun set-reg (vm reg val)
  (set-prop vm reg val)
  )

;; ********** Gestion des tables de hashage des étiquettes.

(defun get-hash (tab cle)
  (gethash cle tab)
  )

(defun set-hash (tab cle val)
  (setf (gethash cle tab) val)
  )

(defun inc-hash (tab)
  (set-hash tab 'nb (+ (get-hash tab 'nb) 1))
  )

(defun get-etiq (vm cle)
  (get-hash (get-prop vm :etiq) cle)
  )

(defun set-etiq (vm cle val)
  (set-hash (get-prop vm :etiq) cle val)
  )

(defun inc-etiq (vm)
  (inc-hash (get-prop vm :etiq))
  )

(defun get-etiqNR (vm cle)
  (get-hash (get-prop vm :etiqNR) cle)
  )

(defun set-etiqNR (vm cle val)
  (set-hash (get-prop vm :etiqNR) cle val)
  )

(defun inc-etiqNR (vm)
  (inc-hash (get-prop vm :etiqNR))
  )

(defun res-etiq (vm exp adr)
  (if (null exp) 
      ()
    (progn
      (set-mem vm (car exp) (list (car (get-mem vm (car exp))) adr))
      (res-etiq vm (cdr exp) adr)
      )
    )
  )

;; ********** Gestion des compteurs ordinaux PC et LC.
;; Manipulation des compteurs PC et LC de la machine virtuelle.

(defun get-pc (&optional (vm 'vm))
  (get-prop vm :PC)
  )

(defun set-pc (vm val)
  (set-prop vm :PC val)
)

(defun inc-pc (vm)
  (inc-prop vm :PC)
  )

(defun dec-pc (vm)
  (dec-prop vm :PC)
  )

(defun get-lc (&optional (vm 'vm))
  (get-prop vm :LC)
  )

(defun set-lc (vm val)
  (set-prop vm :LC val)
)

(defun inc-lc (vm)
  (inc-prop vm :LC)
  )

(defun dec-lc (vm)
  (dec-prop vm :LC)
  )

;; ********** Gestion de la pile.
;; Fonctions pour manipuler la pile.

(defun get-sp (&optional (vm 'vm))
  (get-prop vm :SP)
  )

(defun set-sp (vm val)
  (set-prop vm :SP val)
)

(defun inc-sp (vm)
  (inc-prop vm :SP)
  )

(defun dec-sp (vm)
  (dec-prop vm :SP)
  )

(defun get-fp (&optional (vm 'vm))
  (get-prop vm :FP)
  )

(defun set-fp (vm val)
  (set-prop vm :FP val)
)

;; ********** Gestion des instructions en mémoire.
;; Récupération des instructions en mémoire et exécution.

(defun get-mem-pc (&optional (vm 'vm))
  (get-mem vm (get-pc vm))
  )

(defun set-mem-pc (vm val)
  (set-mem vm (get-pc vm) val)
  )

(defun get-mem-lc (&optional (vm 'vm))
  (get-mem vm (get-lc vm))
  )

(defun set-mem-lc (vm val)
  (set-mem vm (get-lc vm) val)
  )

(defun is-inst (vm inst)
  (eql (car (get-mem-pc vm)) inst)
  )

(defun in-fun (vm)
  (is-inst vm 'FENTRY)
  )

(defun out-fun (vm)
  (is-inst vm 'FEXIT)
  )

(defun saut-fonction (vm nbfun)
  (setf nbfun (+ nbfun 1))
  (loop while (< 0 nbfun)
	do 
	(dec-pc vm)
	(cond
	 ((in-fun vm) (setf nbfun (+ nbfun 1)))
	 ((out-fun vm) (setf nbfun (- nbfun 1)))
	 )
	)
  )

(defun vm-op (vm op src dst)
  (let ((adr (get-dst vm dst))
	(res (apply op (list (get-src vm (get-dst vm dst)) (get-src vm src)))))
    (set-reg vm adr res)
    )
  )

(defun vm-jcond (vm dst cond)
  (if (apply cond (list vm)) 
      (vm-jmp vm dst))
  )

;; ********** Gestion du chargement du code en mémoire.
;; Fonctions de gestion du chargement du code assembleur en mémoire.

(defun is-saut (inst)
  (member (car inst) '(JMP JEQ JL JG JLE JGE JNE))
  )

(defun case-adr (vm exp inst etiqLoc etiqLocNR)
  (if (get-hash etiqLoc (cadadr exp))
      (error "Étiquettes multiples : ~S" (cadr inst))
    (progn
      (set-hash etiqLoc (cadr inst) (+ (get-lc vm) 1))
      (res-etiq vm (get-hash etiqLocNR (cadr inst)) (+ (get-lc vm) 1))
      (if (get-hash etiqLocNR (cadr inst))
	  (progn
	    (set-hash etiqLocNR (cadr inst) ())
	    (inc-hash etiqLocNR)
	    )
	)
      )
    )
  )

(defun case-varg (vm exp inst)
  (if (null (get-etiq vm (cadr inst)))
      (progn
	(set-etiq vm (cadr inst) (get-prop vm :VP))
	(if (< (get-prop vm :VP) (- (get-prop vm :BP) 1)) 
	    (inc-prop vm :VP)
	  )
	)
    )
  )

(defun case-saut (vm exp inst)
  (if (get-etiq vm (cadadr inst))
      (set-mem-lc vm (list (car inst) (get-etiq vm (cadadr inst))))
    (progn                                    
      (if (null (get-etiqNR vm (cadadr inst)))
	  (inc-etiqNR vm)
	)
      (set-etiqNR vm (cadadr inst) (list* (get-lc vm) (get-etiqNR vm (cadadr inst))))
      (set-mem-lc vm inst)
      )
    )
  (dec-lc vm)
  )

(defun case-fonction (vm exp inst)
  (if (get-etiq vm (cadadr exp))
      (error "Fonction déjà définie : ~S" (cadadr exp))
    (progn
      (set-mem-lc vm inst)
      (set-etiq vm (cadadr exp) (get-lc vm))
      (res-etiq vm (get-etiqNR vm (cadadr exp)) (get-lc vm))
      (if (get-etiqNR vm (cadadr exp))
	  (progn
	    (set-etiqNR (cadadr exp) ())
	    (inc-etiqNR vm)
	    )
	)
      (dec-lc vm)
      )
    )
  )

(defun case-other (vm exp inst etiqLoc etiqLocNR)
  (if (is-saut inst)
      (progn
	(if (get-hash etiqLoc (cadadr inst))
	    (set-mem-lc vm (list (car inst) (get-hash etiqLoc (cadadr inst))))
	  (progn
	    (if (null (get-hash etiqLocNR (cadadr inst)))
		(inc-hash etiqLocNR)
	      )
	    (set-hash etiqLocNR (cadadr inst) (list* (get-lc vm) (get-hash etiqLocNR (cadadr inst))))
	    (set-mem-lc vm inst)
	    )
	  )
	)
    (set-mem-lc vm inst)
    )
  (dec-lc vm)
  )


;; ********** Gestion des drapeaux de comparaison.
;; Fonctions de mise à jour des drapeaux de la machine virtuelle.

(defun set-drapeaux (vm dpp de dpg)
  (set-prop vm :DPP dpp)
  (set-prop vm :DE de)
  (set-prop vm :DPG dpg)
  )

(defun is-egal (vm)
  (eql (get-prop vm :DE) 1)
  )

(defun is-not-egal (vm)
  (not (is-egal vm))
  )

(defun is-pluspetit (vm)
  (eql (get-prop vm :DPP) 1)
  )

(defun is-not-pluspetit (vm)
  (not (is-pluspetit vm))
  )

(defun is-plusgrand (vm)
  (eql (get-prop vm :DPG) 1)
  )

(defun is-not-plusgrand (vm)
  (not (is-plusgrand vm))
  )

;; ********** Gestion de l'adressage.
;; Fonctions de résolution des adresses.


;; Exemples d'adressage dans l'ordre :
;; (), 598, :R3, (:DIESE 5), (6 :R1), (:@ :etiqfin), (:* 2 :R2)

(defun is-prop (e) 
  (member e (list :R0 :R1 :R2 :R3 :SP :VP :FP :DPP :DE :DPG :PC :LC))
  )

(defun is-const (e)
  (eql (car e) :DIESE)
  )

(defun is-index (e)
  (numberp (car e))
  )

(defun get-index (vm e)
  (get-mem vm (+ (car e) (get-src vm (cadr e))))
  )

(defun is-etiq (e) 
  (eql (car e) :@)
  )

(defun is-indir (e) 
  (eql (car e) :*)
  )

(defun get-indir-src (vm e)
  (get-mem vm (if (null (cddr e)) 
		  (get-src vm (cadr e)) 
		(get-src vm (cdr e)))
	   )
  )

(defun get-indir-dst (vm e)
  (if (null (cddr e))
      (get-src vm (cadr e))
    (if (is-index (cdr e))
	(+ (cadr e) (get-src vm (caddr e)))
      (if (is-etiq (cdr e)) 
	  (get-src vm (cdr e))
	)
      )
    )
  )

(defun is-loc (e)
  (eql (car e) 'LOC)
  )

(defun get-newFP (vm e)
  (let ((newFP (get-fp vm)))
    (loop while (> (get-mem vm (+ 3 newFP)) (caddr e))
	  do (setf newFP (get-mem vm (+ 2 newFP)))
	  )
    newFP
    )
  )

(defun get-loc-src (vm e)
  (if (eql (caddr e) ()) (setf (caddr e) 0))
  (let ((newFP (get-newFP vm e)))
    (if (< (cadr e) 0)
	(get-mem vm (- newFP (get-mem vm newFP) 1 (cadr e)))
      (get-mem vm ( + 4 newFP (cadr e)))
      )
    )
  )

(defun get-loc-dst (vm e)
  (let ((newFP (get-newFP vm e)))
    (if (< (cadr e) 0)
	(- newFP (get-mem vm newFP) 1 (cadr e))  
      (+  4 newFP  (cadr e))
      )
    )
  )


;; Renvoie les contenus pour src / les adresses pour dst.
 
(defun get-src (vm exp)
  (if (atom exp)
      (cond
       ((null exp) 0)
       ((numberp exp) (get-mem vm exp))
       ((is-prop exp) (get-prop vm exp))
       )
    (if (consp exp)
	(cond
	 ((is-const exp) (cadr exp))
	 ((is-index exp) (get-index vm exp))
	 ((is-etiq exp) (get-etiq vm (cadr exp))) 
	 ((is-indir exp) (get-indir-src vm exp))
	 ((is-loc exp) (get-loc-src vm exp))
	 )
      )
    )
  )

(defun get-dst (vm exp)
  (if (atom exp)
      (cond
       ((null exp) 0)
       ((numberp exp) exp)
       ((is-prop exp) exp)
       )
    (if (consp exp)
	(cond
	 ((is-index exp) exp)
	 ((is-etiq exp) (get-etiq vm (cadr exp)))
	 ((is-const exp) (cadr exp))
	 ((is-indir exp) (get-indir-dst vm exp))
	 ((is-loc exp) (get-loc-dst vm exp))
	 )
      )
    )
  )

;; ********** Etat de la machine : contenus mémoire et pile.
;; Fonctions d'affichage du contenu de la mémoire.

(defun aff-zone (vm ind fin)
  (if (and (<= ind fin) (< ind (get-taille vm)) (>= ind 0))
      (progn (aff-case vm ind)
	     (aff-zone vm (+ ind 1) fin))
    )
  )

(defun aff-case (vm ind)
  (if (= (mod ind 5) 0) (format t "~%~D " ind))
  (format t ".~S" (get-mem vm ind))
  )

(defun aff-globals (&optional (vm 'vm))
  (aff-zone vm 0 100)
  )

(defun aff-all (&optional (vm 'vm))
  (aff-zone vm 0 (get-taille vm))
  )

;; ********** Etat de la machine : lancée ou éteinte.
;; Gestion de l'exécution de la machine.

(defun inst-ok (vm)
  (consp (get-mem-pc vm))
  )

(defun not-halt (vm)
  (not (is-inst vm 'HALT))
  )

(defun vm-running (&optional (vm 'vm))
  (and (inst-ok vm)  (not-halt vm))
  )

(defun vm-overflow (vm)
  (>= (get-sp vm) (get-pc vm))
  )


;; ********** INSTRUCTIONS UTILISABLES PAR LA MACHINE VIRTUELLE **********
;;
;; MOVE source cible
;;
;; PUSH source
;; POP cible
;;
;; ADD source cible
;; SUB source cible
;; MULT source cible
;; DIV source cible
;;
;; INC cible
;; DEC cible
;;
;; JMP cible
;; JSR etiquette
;;
;; CMP un deux
;; JL cible
;; JEQ cible
;; JG cible
;; JLE cible
;; JGE cible
;; JNE cible
;;
;; RTN
;; NOP
;; ERR


;; ********** Instructions de transfert registres <-> mémoire.

(defun vm-move (vm src dst)
  (let ((adr (get-dst vm dst))
	(res (get-src vm src)))
    (if (numberp adr)
	(set-mem vm adr res)
      (set-prop vm adr res)
      )
    )
  )


;; ********** Instructions de gestion de la pile.

(defun vm-push (vm src)
  (inc-sp vm)
  (vm-move vm src '(:* :SP))
  )

(defun vm-pop (vm dst)
  (vm-move vm '(:* :SP) dst)
  (dec-sp vm)
  )

;; ********** Instructions arithmétiques entre registres.

(defun vm-add (vm src dst)
  (vm-op vm '+ src dst)
  )

(defun vm-sub (vm src dst)
  (vm-op vm '- src dst)
  )

(defun vm-mult (vm src dst)
  (vm-op vm '* src dst)
  )

(defun vm-div (vm src dst)
  (vm-op vm '/ src dst)
  )


;; ********** Instructions arithmétiques registre <-> valeur.

(defun vm-inc (vm dst)
  (vm-add vm '(:DIESE 1) dst)
  )

(defun vm-dec (vm dst)
  (vm-sub vm '(:DIESE 1) dst)
  )

;; ********** Instructions de saut.

(defun vm-jmp (vm dst)
  (if (numberp dst)  
      (set-pc vm dst)
    (vm-move vm dest :PC)
    )
  )

(defun vm-jsr (vm etq)
  (vm-push vm :PC)
  (vm-jmp vm etq)
  ) 


;; ********** Instructions de saut conditionnel.

(defun vm-cmp (vm recto verso)
  (let ((r (get-src vm recto))
	(v (get-src vm verso)))
    (if (and (numberp r) (numberp v))
	(cond
	 ((eql r v) (set-drapeaux vm 0 1 0))
	 ((< r v) (set-drapeaux vm 1 0 0))
	 ((> r v) (set-drapeaux vm 0 0 1))
	 )
      (if (eql r v) 
	  (set-drapeaux vm 0 1 0)
	(set-drapeaux vm 0 0 0)
	)
      )
    )
  )

(defun vm-jl (vm dst)
  (vm-jcond vm dst 'is-pluspetit)
  )

(defun vm-jeq (vm dst)
  (vm-jcond vm dst 'is-egal)
  )
      
(defun vm-jg (vm dst)
  (vm-jcond vm dst 'is-plusgrand)
  )

(defun vm-jle (vm dst)
  (vm-jcond vm dst 'is-not-plusgrand)
  )


(defun vm-jge (vm dst)
  (vm-jcond vm dst 'is-not-pluspetit)
  )

(defun vm-jne (vm dst)
  (vm-jcond vm dst 'is-not-egal)
  )


;; ********** Instructions de gestion de la machine.

(defun vm-rtn (vm)
  (vm-move vm '( 1 :FP) :SP)
  (vm-move vm '( 4 :FP) :PC)
  (vm-move vm '( 2 :FP)  :FP)
  )

(defun vm-nop (vm)
  )

(defun vm-err (vm exp)
  (format t "Erreur : ~S~%" exp)
  )

;; Fonctions structurelles de la machine virtuelle.

;; nom : Nom de la machine.
;; R0, R1, R2, R3 : Registres.
;; BP : Base Pointer initialisé à 100, pile montante.
;; SP : Stack Pointer, si pile vide SP = BP.
;; FP : Frame Pointer
;; DPP : Drapeau de comparaison "plus petit".
;; DE : Drapeau de comparaison "égalité".
;; DPG : Drapeau de comparaison "plus grand".
;; taille : Taille allouée à la mémoire (pile + tas + code).
;; memtab : Mémoire de la machine.
;; PC : Program Counter, compteur ordinal, position dans le code.
;; LC : Load Counter, position du chargement du code.
;; etiq : Table de hashage pour les étiquettes.
;; etiqNR : Table de hashage des étiquettes non résolues


;; ********** Création d'une machine virtuelle.

(defun make-machine (&optional (nom 'vm) (tmem 10000) (aff ()))
  (set-prop nom :nom nom)
  (set-prop nom :R0 0)
  (set-prop nom :R1 0)
  (set-prop nom :R2 0)
  (set-prop nom :R3 0)
  (set-prop nom :BP 100)
  (set-prop nom :SP 100)
  (set-prop nom :VP 1)
  (set-prop nom :FP 0)
  (set-prop nom :DPP 0)
  (set-prop nom :DE 0)
  (set-prop nom :DPG 0)
  (reset-memoire nom tmem)
  (if (not (null aff)) (print-machine nom))
  )
  
;; ********** Vidage mémoire d'une machine virtuelle.

(defun reset-memoire (&optional (nom 'vm) (tmem 10000))
  (let ((taille (max tmem 1000)))
    (set-taille nom taille)
    (set-prop nom :memtab (make-array taille))
    (set-pc nom (- taille 1))
    (set-lc nom (- taille 1))
    (set-prop nom :etiq (make-hash-table :size taille))
    (set-prop nom :etiqNR (make-hash-table :size taille))
    (set-etiqNR nom 'nb 0)
    )
  )


;; ********** Chargement de code dans la mémoire d'une machine virtuelle.

(defun load-machine (vm asm)
  (let ((exp asm)
	(inst (car asm))
	(etiqLoc (make-hash-table :size (get-taille vm)))
	(etiqLocNR (make-hash-table :size (get-taille vm))))
    (set-hash etiqLocNR 'nb 0)
    (loop while exp
	  do
	  (case (car inst)
	    ('@ (case-adr vm exp inst etiqLoc etiqLocNR))
	    ('VARG (case-varg vm exp inst))
	    ('JSR (case-saut vm exp inst))
	    ('FEntry (case-fonction vm exp inst))
	    (otherwise (case-other vm exp inst etiqLoc etiqLocNR))
	    )
	  do (setf exp (cdr exp))
	  do (setf inst (car exp))
	  )
    )
  )

  
;; ********** Lancement d'une machine virtuelle.
  
(defun run-machine (&optional (nom 'vm) (aff ()))
  (set-mem-lc nom '(HALT))
  (let ((nbfun 0))
  (loop while (vm-running nom)
	do
	(if (in-fun nom) 
	    (saut-fonction nom nbfun)
	  (exec-inst nom (get-mem-pc nom) aff)
	  )
	)
  )
  (if (vm-overflow nom)
      (error "Débordement de pile")
    (get-reg nom :R0))
  )

(defun exec-inst (vm exp &optional (aff ()))
  (let ((inst (car exp))
	(param (cadr exp))
	(param-bis (caddr exp)))
    (if (null exp)
	(vm-nop vm)
      (case inst
	('MOVE (vm-move vm param param-bis))
	('ADD (vm-add vm param param-bis))
	('SUB (vm-sub vm param param-bis))
	('MULT (vm-mult vm param param-bis))
	('DIV (vm-div vm param param-bis))
	('PUSH (vm-push vm param))
	('POP (vm-pop vm param))
	('INCR (vm-inc vm param))
	('DECR (vm-dec vm param))
	('JMP  (vm-jmp vm param))
	('CMP  (vm-cmp vm param param-bis))
	('JEQ (vm-jeq vm param))
	('JL (vm-jl vm param))
	('JLE (vm-jle vm param))
	('JG (vm-jg vm param))
	('JGE (vm-jge vm param))
	('JNE (vm-jne  vm param))
	('JSR (vm-jsr vm param))
	('RTN (vm-rtn vm))
	('FENTRY (vm-nop vm))
	('FEXIT (vm-nop vm))
	('ERR (vm-err vm))
	(otherwise (vm-err vm exp))
	)
      )
    (if (not (null aff)) (format t "~S~%" (get-mem-pc vm)))
    (dec-pc vm)
    )
  )


;; ********** Affichage de tous les paramètres d'une machine virtuelle.

(defun print-machine (&optional (nom 'vm))
  (format t "~%Machine virtuelle : ~%--- Nom : ~S ~%--- Taille : ~D" nom (get-taille nom))
  (format t "~%- Registres : ~%--- R0 : ~D ~%--- R1 : ~D ~%--- R2 : ~D ~%--- R3 : ~D" 
	  (get-reg nom :R0) (get-reg nom :R1) (get-reg nom :R2) (get-reg nom :R3))
  (format t "~%- Pointeurs : ~%--- BP : ~D ~%--- SP : ~D ~%--- VP : ~D ~%--- FP : ~D"
	  (get-prop nom :BP) (get-prop nom :SP) (get-prop nom :VP) (get-prop nom :FP))
  (format t "~%- Drapeaux : ~%--- DPP : ~D ~%--- DE : ~D ~%--- DPG : ~D"
	  (get-prop nom :DPP) (get-prop nom :DE) (get-prop nom :DPG))
  (format t "~%- Compteurs : ~%--- PC : ~D ~%--- LC : ~D ~%"
	  (get-pc nom) (get-lc nom))
)
