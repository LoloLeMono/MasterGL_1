# val_abs.asm

.data
vrai: .asciiz "pair\n"
faux: .asciiz "impair\n"

.text
main: li $v0, 5
      syscall #initialise $v0
      li $t0, 2
      div $v0, $t0
      mfhi $t0
      beqz $t0, disp
      li $v0, 4
      la $a0, faux
      syscall
      li $v0 10 # exit
      syscall
	
disp:   li $v0, 4
        la $a0, vrai
        syscall
        li $v0 10 # exit
	syscall