#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include<arpa/inet.h> 
 
int main(int argc, char *argv[])
{
    int id = atoi(argv[3]);

    /* Etape 1 : créer une socket */   
    int ds = socket(PF_INET, SOCK_DGRAM, 0);

    int newPort = 2300 + atoi(argv[3]);

    struct sockaddr_in ad;
    ad.sin_family = AF_INET ;
    ad.sin_addr.s_addr = INADDR_ANY;
    ad.sin_port = htons(newPort);

    if (ds == -1)
    {
        printf("Processus %d :\n", ad.sin_port);
        perror("pb creation socket \n");
        exit(1);
    }

    printf("NODE %d : creation de la socket réussie \n", id);

    int res = bind(ds, (struct sockaddr*)&ad, sizeof(ad));

    if (res < 0)
    {
        printf("NODE %d :\n", id);
        perror("erreur de nommage \n");
        exit(1);
    }

    printf("NODE %d : nommage réussi \n", id);

    struct sockaddr_in adServer;
    adServer.sin_family = AF_INET;
    adServer.sin_addr.s_addr = inet_addr(argv[1]);
    adServer.sin_port = htons((short)atoi(argv[2]));
    socklen_t lgAdr = sizeof(struct sockaddr_in);

    printf("NODE %d : Je vais envoyer %d au serveur \n", id, id);
    int snd = sendto(ds, &id, sizeof(int), 0, (struct sockaddr*) &adServer, sizeof(struct sockaddr_in));

    if (snd < 0)
    {
        printf("NODE %d : \n", id);
        perror("erreur d'envoi \n");
        exit(1);
    }

    printf("NODE %d : Envoi reussi \n", id);

    close(ds);

    return 0;
}