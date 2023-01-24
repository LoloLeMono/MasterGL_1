########################################
#~ définitions
########################################

# Executables à générer
BIN=server  node  init

# liste des fichiers sources 
SRCS0=server.c
SRCS1=node.c
SRCS2=init.c

default: $(BIN)

########################################
#~ regles pour l'executable
########################################

obj/%.o: %.c
	gcc -Wall -Iinclude -c $< -o $@

server: $(SRCS0:%.c=obj/%.o)
	gcc -o $@ $+

node: $(SRCS1:%.c=obj/%.o)
	gcc -o $@ $+

init: $(SRCS2:%.c=obj/%.o)
	gcc -o $@ $+

clean:
	rm -f $(BIN) obj/*.o *~