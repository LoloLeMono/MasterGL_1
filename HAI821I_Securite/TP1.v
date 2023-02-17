Parameters A B C : Prop.

Goal A -> B -> A.

Proof.
  intro.
  intro.
  assumption.
Save exo1.

Lemma exo13 : A /\ B -> B.
Proof.
  intro.
  elim H.
  intros.
  assumption.
 Qed.

Lemma exo14 : B -> A \/ B.
Proof.
  intro.
  right.
  assumption.
Qed.

Lemma exo15 : (A \/ B) -> (A -> C) -> (B -> C) -> C.
Proof.
  intros.
  elim H.
  intro.
  apply H0.
  assumption.
  intro.
  apply H1.
  assumption.
Qed.

Lemma exo16 : A -> False -> ~A.
Proof.
  intros.
  elimtype False.
  assumption.
Qed.

Lemma exo17 : False -> A.
Proof.
  intro.
  elimtype False.
  assumption.
Qed.

Lemma exo18 : (A <-> B) -> A -> B.
Proof.
  intros.
  elim H.
  intros.
  apply H1.
  assumption.
Qed.

(* PREMIER ORDRE *)

Parameters E : Set.
Parameters P Q : E -> Prop.

Lemma exo21 : forall x : E, (P x) -> exists y : E, (P y) \/ (Q y).
Proof.
  intro.
  intro.
  exists x.
  left.
  assumption.
Qed.

Lemma exo22 : (exists x : E, (P x) \/ (Q x)) -> (exists y : E, (P y)) \/ (exists z : E, (Q z)).
Proof.
  intro.
  elim H.
  intros.
  elim H0.
  intros.
  left.
  exists x.
  assumption.
  intro.
  right.
  exists x.
  assumption.
Qed.

Lemma exo23 : (forall x, (P x)) /\ (forall x, (Q x)) -> forall x, (P x) /\ (Q x).
Proof.
  intros.
  destruct H.
  split.
  apply H.
  apply H0.
Qed.

Lemma exo24 : (forall x, (P x) /\ (Q x)) -> (forall x, (P x)) /\ (forall x, (Q x)).
Proof.
  intro.
  split.
  apply H.
  apply H.
Qed.



