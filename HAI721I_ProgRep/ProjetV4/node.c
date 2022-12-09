#include <netinet/in.h>
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
    if (argc != 5)
    {
        printf("Utilisation : %s ip_server port_serveur port_client_reference id\n", argv[0]);
        exit(1);
    }

    int id = atoi(argv[4]);
    char bufError[256];

    /* Etape 1 : créer une socket */   
    int ds = socket(PF_INET, SOCK_DGRAM, 0);

    struct sockaddr_in ad;
    ad.sin_family = AF_INET ;
    ad.sin_addr.s_addr = INADDR_ANY;
    ad.sin_port = htons((short)atoi(argv[3]));
    socklen_t lgAdr = sizeof(struct sockaddr_in);

    if (ds == -1)
    {
        snprintf(bufError, sizeof(bufError), "NODE %d : Erreur creation socket \n", id);
        perror(bufError);
        exit(1);
    }

    printf("NODE %d : creation de la socket réussie \n", id);

    int res = bind(ds, (struct sockaddr*)&ad, sizeof(ad));

    if (res < 0)
    {
        snprintf(bufError, sizeof(bufError), "NODE %d : Erreur de nommage \n", id);
        perror(bufError);
        exit(1);
    }

    printf("NODE %d : nommage réussi \n", id);

    struct sockaddr_in adServer;
    adServer.sin_family = AF_INET;
    adServer.sin_addr.s_addr = inet_addr(argv[1]);
    adServer.sin_port = htons((short)atoi(argv[2]));

    printf("NODE %d : Je vais envoyer mon id %d au serveur \n", id, id);
    int snd = sendto(ds, &id, sizeof(int), 0, (struct sockaddr*) &adServer, sizeof(struct sockaddr_in));

    if (snd < 0)
    {
        snprintf(bufError, sizeof(bufError), "NODE %d : Erreur de d'envoi \n", id);
        perror(bufError);
        exit(1);
    }

    printf("NODE %d : Envoi reussi \n", id);

    /* J'attend de recevoir le nb d'entrés */

    int nbSocksIn;

    printf("NODE %d : J'attend une connexion \n", id);
    int rec = recvfrom(ds, &nbSocksIn, sizeof(int), 0, (struct sockaddr*) &adServer, &lgAdr);

    if (rec < 0)
    {
        snprintf(bufError, sizeof(bufError), "NODE %d : Erreur de reception In \n", id);
        perror(bufError);
        exit(1);
    }

    printf("NODE %d : J'ai %d nodes à écouter \n", id, nbSocksIn);

    /* J'attend de recevoir le nb de sortis */

    int nbSocksOut;

    printf("NODE %d : J'attend une connexion \n", id);
    rec = recvfrom(ds, &nbSocksOut, sizeof(int), 0, (struct sockaddr*) &adServer, &lgAdr);

    if (rec < 0)
    {
        snprintf(bufError, sizeof(bufError), "NODE %d : Erreur de reception Out \n", id);
        perror(bufError);
        exit(1);
    }

    printf("NODE %d : J'ai %d nodes à contacter \n", id, nbSocksOut);

    struct sockaddr_in tabSocksOut[nbSocksOut];

    /* J'attend de recevoir les sockets des voisins */

    for (int i=0; i<nbSocksOut; i++)
    {
        rec = recvfrom(ds, &tabSocksOut[i], lgAdr, 0, (struct sockaddr*) &adServer, &lgAdr);
        if (rec < 0)
        {
            snprintf(bufError, sizeof(bufError), "NODE %d : Erreur des voisins \n", id);
            perror(bufError);
            exit(1);
        }

        printf("NODE %d : Je recois l'adresse de %d \n", id, htons(tabSocksOut[i].sin_port));
    }

    close(ds);



    /* INITIALISATION TERMINE, DEBUT COMMUNICATION NODES */



    int dsTCP = socket(PF_INET, SOCK_STREAM, 0);
    
    if (bind(dsTCP, (struct sockaddr*)&ad, sizeof(ad)) < 0)
    {
        snprintf(bufError, sizeof(bufError), "NODE %d : Erreur de nommage de la socket TCP In\n", id);
        perror(bufError);
        exit(1);
    }

    if (listen(dsTCP, 10) < 0)
    {
        perror("Erreur listen");
        exit(1);
    }

    //int tabDs[nbSocksOut];

    // BOUCLE DES CONNEXIONS
    for (int i=0; i<nbSocksOut; i++)
    {
        //tabDs[i] = socket(PF_INET, SOCK_STREAM, 0);

        int dsBuf = socket(PF_INET, SOCK_STREAM, 0);

        if (bind(dsBuf, (struct sockaddr*)&tabSocksOut[i], sizeof(tabSocksOut[i])) < 0)
        {
            snprintf(bufError, sizeof(bufError), "NODE %d : Erreur de nommage de la socket TCP Out\n", id);
            perror(bufError);
            exit(1);
        }

        struct sockaddr_in add = tabSocksOut[i];

        int res = connect(dsBuf, (struct sockaddr*) &add, sizeof(add));
        if (res == -1)
        {
            perror("Probleme connect");
            exit(1);
        }
        /*
        if(connect(tabDs[i], (struct sockaddr*) &tabSocksOut[i], lgAdr) < 0)
        {
            snprintf(bufError, sizeof(bufError), "NODE %d : Erreur de de connexion de la socket TCP \n", id);
            perror(bufError);
            exit(1);
        }
        */

        printf("NODE %d : Je me connecte à %d \n", i, htons(tabSocksOut[i].sin_port));
        close(dsBuf);
    }

    printf("NODE %d : Fin de connexions aux voisins In \n", id);

    // BOUCLE DES ACCEPTS
    struct sockaddr_in tabAdV[nbSocksIn];
    socklen_t lg = sizeof(struct sockaddr_in);

    for (int i=0; i<nbSocksIn; i++)
    {
        if (accept(dsTCP, (struct sockaddr*) &tabAdV[i], &lg) < 0)
        {
            snprintf(bufError, sizeof(bufError), "NODE %d : Erreur accept de la socket TCP \n", id);
            perror(bufError);
            exit(1);
        }

        printf("NODE %d : J'accepte la connexion de %d \n", id, htons(tabAdV[i].sin_port));
    }

    close(dsTCP);

    printf("NODE %d : FIN \n", id);

    return 0;
}