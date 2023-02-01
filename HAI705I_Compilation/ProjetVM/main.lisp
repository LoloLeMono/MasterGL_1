;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;  __  __   _   ___ _  _   ;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;; |  \/  | /_\ |_ _| \| |  ;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;; | |\/| |/ _ \ | || .` |  ;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;; |_|  |_/_/ \_\___|_|\_|  ;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;                          ;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;



(require "vm.lisp")
(require "compilateur.lisp")


(defun run (mv code &optional (code-bis ()))
  (if (not (null code-bis)) (load-machine mv (compilation code-bis))) ;; Compile et Charge la définition de la fonction dans la machine "vm" si elle n'existe pas
    (load-machine mv (compilation code)) ;; Compile et Charge la fonction dans la machine "vm"
    (run-machine mv)
)

(make-machine)