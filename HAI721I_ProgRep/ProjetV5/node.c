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

void agrandirTabInt(int* tab, int* size)
{
  // On redimensionne le tableau pour pouvoir y ajouter un élément
  tab = realloc(tab, sizeof(int) * (*size + 1));

  // On met à jour la taille du tableau
  *size = *size + 1;
}

void agrandirTabSocket(struct sockaddr_in* tab, int* size)
{
  // On redimensionne le tableau pour pouvoir y ajouter un élément
  tab = realloc(tab, sizeof(struct sockaddr_in) * (*size + 1));

  // On met à jour la taille du tableau
  *size = *size + 1;
}
 
int main(int argc, char *argv[])
{
    if (argc != 5)
    {
        printf("Utilisation : %s ip_server port_serveur port_client_reference id\n", argv[0]);
        exit(1);
    }

    int id = atoi(argv[4]);
    int nbNodes;
    char bufError[256];

    // On créé une socket UDP pour communiquer avec le serveur...
    int dsServer = socket(PF_INET, SOCK_DGRAM, 0);
    if (dsServer == -1)
    {
        snprintf(bufError, sizeof(bufError), "NODE %d : Erreur creation socket \n", id);
        perror(bufError);
        exit(1);
    }

    // ... avec son adresse passé en parametre
    struct sockaddr_in ad;
    ad.sin_family = AF_INET ;
    ad.sin_addr.s_addr = INADDR_ANY;
    ad.sin_port = htons((short)atoi(argv[3]));
    socklen_t lgAdr = sizeof(struct sockaddr_in);

    printf("NODE %d : creation de la socket réussie \n", id);

    // On nomme la socket avec son adresse
    if (bind(dsServer, (struct sockaddr*)&ad, sizeof(ad)) < 0)
    {
        snprintf(bufError, sizeof(bufError), "NODE %d : Erreur de nommage \n", id);
        perror(bufError);
        exit(1);
    }

    printf("NODE %d : nommage réussi \n", id);

    // On créé une nouvelle adresse pour la socket du serveur 
    struct sockaddr_in adServer;
    adServer.sin_family = AF_INET;
    adServer.sin_addr.s_addr = inet_addr(argv[1]);
    adServer.sin_port = htons((short)atoi(argv[2]));

    // On envoi l'ID au serveur
    printf("NODE %d : Je vais envoyer mon id %d au serveur \n", id, id);
    if (sendto(dsServer, &id, sizeof(int), 0, (struct sockaddr*) &adServer, sizeof(struct sockaddr_in)) < 0)
    {
        snprintf(bufError, sizeof(bufError), "NODE %d : Erreur de d'envoi \n", id);
        perror(bufError);
        exit(1);
    }

    // Il nous envoi le nb de nodes total
    if (recvfrom(dsServer, &nbNodes, sizeof(int), 0, (struct sockaddr*) &adServer, &lgAdr) < 0)
    {
        perror("SERVER : erreur de reception du nb de nodes \n");
        exit(1);
    }
    printf("NODE %d : J'ai recu nbNodes = %d \n", id, nbNodes);

    // WORK_HERE

    struct sockaddr_in* tabAdV = NULL;
    int* tabSockV = NULL;
    int rep, sizeTabAdV, sizeTabSockV;
    sizeTabAdV = 0;
    sizeTabSockV = 0;
    bool init = true;
    int dsTCP;

    // On créé une nouvelle socket pour le node courant en TCP
    int ds = socket(PF_INET, SOCK_STREAM, 0);
    if (ds == -1)
    {
        snprintf(bufError, sizeof(bufError), "NODE %d : Erreur creation socket \n", id);
        perror(bufError);
        exit(1);
    }

    printf("NODE %d : creation de la socket TCP réussie \n", id);

    // On nomme la socket TCP du node avec son adresse déjà connu
    if (bind(ds, (struct sockaddr*)&ad, sizeof(ad)) < 0)
    {
        snprintf(bufError, sizeof(bufError), "NODE %d : Erreur de nommage \n", id);
        perror(bufError);
        exit(1);
    }

    printf("NODE %d : nommage réussi \n", id);

    // Tant qu'on ne recoit pas le message de fin, on écoute le serveur
    // Message :
        // -3 = fin
        // -2 = accepter une connexion
        // -1 = se connecter à un voisin

    while (init)
    {
        // J'attend un message du server
        if (recvfrom(dsServer, &rep, sizeof(int), 0, (struct sockaddr*) &ad, &lgAdr) < 0)
        {
            perror("NODE : erreur de reception \n");
            exit(1);
        }

        printf("NODE %d : Je recoit %d du serveur \n", id, rep);

        struct sockaddr_in adOut;

        // En fonction du message du serveur, on continue
        switch (rep)
        {
            case -3: //fin
                init = false;
            break;


            case -2: // Accepter une connexion

                // On agrandit le tab de sockets pour ajouter le nouveau voisin à l'indice sizeTabAdv-1
                agrandirTabSocket(tabAdV, &sizeTabAdV);

                listen(ds, 5);
                
                int dsTCP = accept(ds, (struct sockaddr *) &tabAdV[sizeTabAdV-1], &lgAdr);
                if (dsTCP < 0)
                {
                    snprintf(bufError, sizeof(bufError), "NODE %d : Erreur accept de la socket TCP \n", id);
                    perror(bufError);
                    exit(1);
                }

                // On stocke la nouvelle socket dans le tableau
                agrandirTabInt(tabSockV, &sizeTabSockV);
                tabSockV[sizeTabSockV-1] = dsTCP;

                printf("NODE %d : J'accepte la connexion de %d \n", id, htons(tabAdV[sizeTabAdV-1].sin_port));
            break;



            case -1: // Se connecter à un voisin

                // On stocke l'adresse du voisin
                if (recvfrom(dsServer, &adOut, sizeof(struct sockaddr_in), 0, (struct sockaddr*) &ad, &lgAdr) < 0)
                {
                    perror("NODE : erreur de reception \n");
                    exit(1);
                }

                printf("NODE %d : J'ai recu l'adresse du node : %d \n", id, htons(adOut.sin_port));

                if (connect(ds, (struct sockaddr *) &adOut, lgAdr) < 0)
                {
                    perror("NODE : erreur de connection \n");
                    exit(1);
                }

                printf("NODE %d : Je suis connecté au node : %d \n", id, htons(adOut.sin_port));
            break;
            
            default:
                printf("NODE %d : Default du switch \n", id);
            break;
        }

        printf("NODE %d : FIN DE RECEPTION \n", id);
        close(ds);
        return 0;
        
    }

    printf("NODE %d : FIN DE RECEPTION \n", id);
    close(ds);
    return 0;
}