# hello_world.asm

.data
hello:.asciiz "hello world\n"

.text
main: li $v0, 4 #initialise $v0 avec le mode 4
      la $a0, hello #initialise $a0 avec hello
      syscall #lis $v0 en premier