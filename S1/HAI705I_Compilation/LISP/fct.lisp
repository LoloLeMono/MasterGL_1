(defun taille (t)
    (if (atom t)
        0
        (+ 1 (taille(cart)) (taille(cdrt))
    )
)

(defun member (x l) (if (eq (car l) x) l (member x (cdr l))))