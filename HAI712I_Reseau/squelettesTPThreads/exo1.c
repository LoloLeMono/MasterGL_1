#include <string.h>
#include <stdio.h>//perror
#include <sys/types.h>
#include <stdlib.h>
#include <unistd.h>
#include <iostream>
#include <pthread.h>


struct paramsFonctionThread {

  int idThread;

  // si d'autres paramètres, les ajouter ici.

};


void * fonctionThread (void * params){

  struct paramsFonctionThread * args = (struct paramsFonctionThread *) params;

  // a compléter
  ...
}


int main(int argc, char * argv[]){

  if (argc < 2 ){
    printf("utilisation: %s  nombre_threads  \n", argv[0]);
    return 1;
  }     

  
  pthread_t threads[atoi(argv[1])];

  ...

 
  
  // création des threards 
  for (int i = 0; i < atoi(argv[1]); i++){

    // Le passage de paramètre est fortement conseillé (éviter les
    // variables globles).

     ... // compléter pour initialiser les paramètres
    if (pthread_create(&threads[i], NULL,..., ...) != 0){
      perror("erreur creation thread");
      exit(1);
    }
  }


// garder cette saisie et modifier le code en temps venu.
  char c; 
  printf("saisir un caractère \n");
  fgets(m, 1, stdin);

 ... compléter

  return 0;
 
}
 
