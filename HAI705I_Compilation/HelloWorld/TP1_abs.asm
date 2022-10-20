# val_abs.asm

.text
main: li $v0, 5
      syscall #initialise $v0
      abs $a0, $v0
      li $v0, 1
      syscall


# CORRECTION
#
#main:  li $v0, 4
#	la $a0, msg
#	syscall
#	li $v0, 5
#	syscall
#	li $t0, 0
#	bge $v0, st0, disp
#	neg $v0, $v0
#	
#disp:  move $a0, $v0
#	li $v0, 1
#	syscall	
      