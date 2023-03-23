Require Export ZArith.
Open Scope Z.

Inductive expr : Set :=
| Cte : Z -> expr
| Plus : expr -> expr -> expr
| Moins : expr -> expr -> expr
| Mult : expr -> expr -> expr
| Div : expr -> expr -> expr.

Inductive eval : expr -> Z -> Prop :=
| ECte : forall c : Z , eval (Cte c) c

| EPlus : forall (e1 e2 : expr) (v1 v2 v : Z),
eval e1 v1 -> eval e2 v2 -> v = v1 + v2 -> eval (Plus e1 e2) v

| EMoins : forall (e1 e2 : expr) (v1 v2 v : Z),
eval e1 v1 -> eval e2 v2 -> v = v1 - v2 -> eval (Moins e1 e2) v

| EMult : forall (e1 e2 : expr) (v1 v2 v : Z),
eval e1 v1 -> eval e2 v2 -> v = v1 * v2 -> eval (Mult e1 e2) v

| EDiv : forall (e1 e2 : expr) (v1 v2 v : Z),
eval e1 v1 -> eval e2 v2 -> v = v1 / v2 -> eval (Div e1 e2) v.

Goal eval (Plus (Cte 1) (Cte 1)) 2.
Proof.
  apply (EPlus _ _ 1 1 _).
  apply ECte.
  apply ECte.
  auto.
Qed.

Lemma eval1 : eval (Mult (Plus (Cte 4) (Cte 2)) (Moins (Cte 9) (Cte 2))) 42.
Proof.
  eapply EMult.
  eapply EPlus.
  apply ECte.
  apply ECte.
  auto.
  eapply EMoins.
  apply ECte.
  apply ECte.
  auto.
  auto.
Qed.

Fixpoint f_eval (e : expr) : Z :=
  match e with
  | Cte c => c

  | Plus e1 e2 =>
    let v1 := f_eval e1 in
    let v2 := f_eval e2 in
    v1 + v2

  | Moins e1 e2 =>
    let v1 := f_eval e1 in
    let v2 := f_eval e2 in
    v1 - v2

  | Mult e1 e2 =>
    let v1 := f_eval e1 in
    let v2 := f_eval e2 in
    v1 * v2

  | Div e1 e2 =>
    let v1 := f_eval e1 in
    let v2 := f_eval e2 in
    v1 / v2