Inductive bin : Set :=
| C0 : bin
| C1 : bin
| C0S : bin -> bin
| C1S : bin -> bin.

Inductive is_even : bin -> Prop :=
| even_C0 : is_even C0
| even_plus : forall b, is_even (C0S b).


Lemma exo1 : is_even ( C0S ( C1S C1)).
Proof.
  apply even_plus.
Qed.

Fixpoint head_0 (b : bin) : bin :=
match b with
| C0 => C0S (C0)
| C1 => C1S (C0)
| C0S (b0) => C0S (head_0 (b0))
| C1S (b1) => C1S (head_0 (b1))
end .

Definition prog1 := C0S (C1S C1).

Definition prog2 := C0S (C0S (C0S (C1S C1))).

Eval compute in (head_0 prog1).

Inductive is_equal : bin -> bin -> Prop :=
| eq_head0 : forall b1, is_equal b1 (head_0 b1)
| eq_C0S :  forall b1 b2, is_equal b1 b2  -> is_equal (C0S b1) (C0S b2)
| eq_C1S :  forall b1 b2, is_equal b1 b2  -> is_equal (C1S b1) (C1S b2)
| eq_refl : forall b, is_equal b b
| eq_sym : forall b1 b2, is_equal b1 b2 -> is_equal b2 b1
| eq_trans : forall b1 b2 b3, is_equal b1 b2 -> is_equal b2 b3 -> is_equal b1 b3.

Eval compute in (is_equal prog1 prog2).


Goal is_equal (C0S (C1S C1)) (C0S (C1S (C1S (C0S C0)))). (respectivement 110 et 00110)
Proof.
  (Elimination des premiers termes communs)
  apply eq_C0S.
  apply eq_C1S.
  (Transitivité)
  apply (eq_trans C1 (C1S (C0)) (C1S (C0S C0))).

    (Sous-Cas 1)
    apply eq_head0.

    (Sous-Cas 2)
    apply eq_C1S.
    apply eq_head0.
Qed.


Fixpoint mult_2 (b : bin) : bin :=
  match b with
  | C0 => C0
  | b0 => C0S b0
  end.

Eval compute in (mult_2 (C0S (C1S C0))).
Lemma preuve : forall b, (is_even b) -> (exists b', (is_equal b (mult_2 b'))).
Proof.
  intros.
  elim H.

 

    (Sous-Cas 1)
    exists C0.
    simpl.
    apply eq_refl.

    (Sous-Cas 2)
    intros.
    exists b0.
    elim b0.

      (Sous-Cas 2.1)
      apply eq_sym.
      apply eq_head0.

      (Sous-Cas 2.2)
      apply eq_C0S.

      (Sous-Cas 2.3)
      apply eq_refl.

      (Sous-Cas 2.4)
      intros.
      simpl.
      apply eq_refl.

      (Sous-Cas 2.5)
      intros.
      simpl.
      apply eq_refl.
Qed.