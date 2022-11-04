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


int main(int argc, char *argv[])
{ 
  
  // cet appel cronstruit le réseau complet
  int * lesAutresNoeuds = configuration(argc, argv);
  int h,j,typ;
  
  // algo exclusion mutuelle :
  int indice = atoi(argv[3]);

  struct sockaddr_in my_addr, peer_addr;
  socklen_t peer_addr_size;
  int sd, b, l, acc;
  my_addr.sin_family = AF_INET;
  
  // Creating socket
  sd = socket(AF_INET, SOCK_STREAM, 0);
    
  // Binding
  b = bind(sd, (struct sockaddr *)&my_addr, sizeof(struct sockaddr_in));
  
  if(b > 0)
  {
    printf("Binded Successfully\n"); 
  }                                                                                                            
  else
  {
    printf("Binding Error\n");
  }

  // Listening
  l = listen(sd, 5);

  if(l > 0)
  {
    printf("Listening...\n");
  }
  else
  {
    printf("Not listening..\n");
  }

  peer_addr_size = sizeof(struct sockaddr_in);
    
  // Accept system call
  acc = accept(sd, (struct sockaddr *)&peer_addr, &peer_addr_size);

  if(acc > 0)
  {
    printf("Accepted \n");
  }
  else
  {
    printf("Not accepted \n");
  }

  
}

int findNoeud(int* tabNoeud, struct sockaddr_in addr)
{
  for (int i=0; i<sizeof(tabNoeud); i++)
  {
    if (tabNoeud[i] == addr)
    {
      
    }
  }
}