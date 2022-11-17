#include <stdio.h> 
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include <stdlib.h>
#include<arpa/inet.h>
#include<string.h>

/* Programme serveur */

int main(int argc, char *argv[])
{

  if (argc != 2)
  {
    printf("utilisation : %s port_serveur\n", argv[0]);
    exit(1);
  }

  /* Etape 1 : créer une socket */   
  int ds = socket(PF_INET, SOCK_DGRAM, 0);
  
  if (ds == -1)
  {
    perror("Serveur : pb creation socket :");
    exit(1);
  }

  printf("Serveur : creation de la socket réussie \n");
  
  /* Etape 2 : Nommer la socket du seveur */

  struct sockaddr_in ad;
  ad.sin_family = AF_INET;
  ad.sin_addr.s_addr = INADDR_ANY;
  ad.sin_port = htons(atoi(argv[1]));

  int res = bind(ds, (struct sockaddr*)&ad, sizeof(ad));

   if (res < 0)
   {
      perror("Erreur de nommage");
   }
 
  /* Etape 4 : recevoir l'id du client et stocker son adresse */

   int compt = 0;
   struct sockaddr_in adProc;
   socklen_t lgAdr = sizeof(struct sockaddr_in);

   char* nbEdge;
   nbEdge = malloc(sizeof(char) * 2); 

   printf("J'attend une connexion \n");
   int rec = recvfrom(ds, nbEdge, sizeof(nbEdge), 0, (struct sockaddr*) &adProc, &lgAdr);

   if (rec < 0)
   {
        perror("Erreur reception \n");
        exit(1);
    }

    printf("Message reçu : %s \n", nbEdge);

   close(ds);
   printf("Fermage de socket \n");

   printf("Serveur : je termine \n");
   return 0;
}