#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <pthread.h>
#include <netdb.h>
#include <stdlib.h>
#include<arpa/inet.h>
#include<string.h>
#include "common.h"
#include "node_interconnexions.h"



int main(int argc, char *argv[]) { 
  
  // cet appel cronstruit le réseau complet
  int * lesAutresNoeuds = configuration(argc, argv);
  
  
  // algo exclusion mutuelle :
  
 
  int indice = atoi(argv[3]);
  printf("Indice = %d", indice);
   
 
  return 0; 
}
  
