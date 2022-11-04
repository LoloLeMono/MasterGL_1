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

  if (argc != 3)
  {
    printf("utilisation : %s port_serveur nbProc\n", argv[0]);
    exit(1);
  }

  int nbProc = atoi(argv[2]);
  struct sockaddr_in tabSock[nbProc];

  /* Etape 1 : créer une socket UDP*/   
  int ds = socket(PF_INET, SOCK_DGRAM, 0);
  
  if (ds == -1)
  {
    perror("Serveur : pb creation socket :");
    exit(1);
  }

  printf("Serveur : creation de la socket réussie \n");
  
  /* Etape 2 : Nommer la socket du seveur */

  struct sockaddr_in adUDP;
  adUDP.sin_family = AF_INET;
  adUDP.sin_addr.s_addr = INADDR_ANY;
  adUDP.sin_port = htons(atoi(argv[1]));

  int res = bind(ds, (struct sockaddr*)&adUDP, sizeof(adUDP));

   if (res < 0)
   {
      perror("Erreur de nommage");
   }
 
  /* Etape 4 : recevoir l'id du client et stocker son adresse */

   int compt = 0;
   struct sockaddr_in adProc;
   socklen_t lgAdr = sizeof(struct sockaddr_in);

   char* idProc;
   idProc = malloc(sizeof(char) * 3);

   while (compt < nbProc)
   {
      printf("J'attend une connexion \n");
      int rec = recvfrom(ds, idProc, sizeof(idProc), 0, (struct sockaddr*) &adProc, &lgAdr);

      if (rec < 0)
      {
         perror("Erreur reception \n");
         exit(1);
      }

      printf("Message reçu : %s \n", idProc);
      tabSock[atoi(idProc)] = adProc;
      compt++;
   }

   /* Etape 5 : On informe tout les processus de leur suivant */

   int snd;

   for (int i = 0; i < nbProc; i++)
   {
      snd = sendto(ds, &tabSock[(i+1) % nbProc], sizeof(struct sockaddr_in), 0, (struct sockaddr*) &tabSock[i], sizeof(struct sockaddr_in));
      printf("J'envoi IP %s au proc n°%d\n", inet_ntoa(tabSock[(i+1) % nbProc].sin_addr), i);
      printf("J'envoi port %d au proc n°%d\n", ntohs(tabSock[(i+1) % nbProc].sin_port), i);
      
      if(snd < 0)
      {
         perror("Erreur envoi \n");
         exit(1);
      }

      // printf("Envoi reussi au processeur %d \n", i);
   }
   

   /* Etape 6 : ferme la socket */

   close(ds);
   printf("Fermage de socket \n");

   printf("Serveur : je termine \n");
   return 0;
}