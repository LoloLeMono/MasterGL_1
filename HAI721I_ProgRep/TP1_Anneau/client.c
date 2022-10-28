#include <stdio.h> 
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include <stdlib.h>
#include<arpa/inet.h>
#include<string.h>

/* Programme client */

int main(int argc, char *argv[])
{

  if (argc != 5)
  {
    printf("utilisation : %s ip_serveur port_serveur port_client idClient\n", argv[0]);
    exit(1);
  }

  /* Etape 1 : créer une socket */   
  int ds = socket(PF_INET, SOCK_DGRAM, 0);

  /* /!\ : Il est indispensable de tester les valeurs de retour de
     toutes les fonctions et agir en fonction des valeurs
     possibles. Voici un exemple */
  if (ds == -1)
  {
    perror("Client : pb creation socket :");
    exit(1); // je choisis ici d'arrêter le programme car le reste
         // dépendent de la réussite de la création de la socket.
  }
  
  /* J'ajoute des traces pour comprendre l'exécution et savoir
     localiser des éventuelles erreurs */
  printf("Client : creation de la socket réussie \n");

  struct sockaddr_in adPrec;
  struct sockaddr_in adSuiv;

  struct sockaddr_in ad;
  ad.sin_family = AF_INET ;
  ad.sin_addr.s_addr = INADDR_ANY ;
  ad.sin_port = htons(atoi(argv[3]));

  int res = bind(ds, (struct sockaddr*)&ad, sizeof(ad));

  if (res < 0)
  {
    printf("Erreur de nommage");
  }

  struct sockaddr_in adServer;
  adServer.sin_family = AF_INET;
  adServer.sin_addr.s_addr = inet_addr(argv[1]);
  adServer.sin_port = htons((short)atoi(argv[2]));
  socklen_t lgAdr = sizeof(struct sockaddr_in);
  
  char *idProc = malloc(sizeof(char) * 2);
  idProc = argv[4];

  int snd = sendto(ds, idProc, strlen(idProc)+1, 0, (struct sockaddr*) &adServer, sizeof(struct sockaddr_in));

  if (snd < 0)
  {
    perror("Erreur envoi \n");
    exit(1);
  }

  printf("Envoi reussi \n");


  // RECEPTION DU SUIVANT

  printf("J'attend que le serveur renvoi mon suivant \n");

  int rec = recvfrom(ds, &adSuiv, sizeof(struct sockaddr_in), 0, (struct sockaddr*) &adServer, &lgAdr);
  
  printf("Je recois IP %s \n", inet_ntoa(adSuiv.sin_addr));
  printf("Je recois port %d \n", ntohs(adSuiv.sin_port));


  if (rec < 0)
  {
    perror("Erreur de reception du client");
    exit(1);
  }

  printf("Client : J'ai bien stocké la socket de mon suivant \n");



  // RECEPTION DU PRECEDENT

  printf("J'attend que le serveur renvoi mon precedent \n");

  rec = recvfrom(ds, &adPrec, sizeof(struct sockaddr_in), 0, (struct sockaddr*) &adServer, &lgAdr);
  
  printf("Je recois IP %s \n", inet_ntoa(adPrec.sin_addr));
  printf("Je recois port %d \n", ntohs(adPrec.sin_port));


  if (rec < 0)
  {
    perror("Erreur de reception du client");
    exit(1);
  }

  printf("Client : J'ai bien stocké la socket de mon precedent \n");
  
  close(ds);

  /* Etape x : recréer une socket TCP pour la communication entre processus*/   

  ds = socket(PF_INET, SOCK_STREAM, 0);

  if (ds == -1)
  {
    perror("Client : pb creation socket :");
    exit(1);
  }
  
  printf("Client : creation de la socket réussie \n");


  




  printf("Client : je termine \n");

  return 0;
}