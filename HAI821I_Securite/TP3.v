Fixpoint plus (n m : nat) {struct n} : nat :=
  match n with
  | 0 => m
  | ( S p ) => S ( plus p m)
  end .

Fixpoint mult (n m : nat) {struct n} : nat :=
  match n with
  | 0 => 0
  | (S p) => (plus (mult p m) m)
  end.

Eval compute in (mult 2 3).

Goal forall n : nat , (mult 2 n) = (plus n n).
Proof.
  intros.
  simpl.
  reflexivity.
Qed.

Lemma l1 : forall m : nat, (plus m 2) = (S (S m)).
Proof.
  induction m.
  simpl.
  reflexivity.
  simpl.
  rewrite IHm.
  reflexivity.
Qed.

Lemma l2 : forall n m : nat, (plus n (S m)) = (S (plus n m)).
Proof.
  induction n.
  intros.
  simpl.
  reflexivity.
  intros.
  simpl.
  rewrite IHn.
  reflexivity.
Qed.

Goal forall n : nat , (mult n 2) = (plus n n).
Proof.
  induction n.
  simpl.
  reflexivity.
  simpl.
  rewrite IHn.
  rewrite l1.
  rewrite l2.
  reflexivity.
Qed.
  .