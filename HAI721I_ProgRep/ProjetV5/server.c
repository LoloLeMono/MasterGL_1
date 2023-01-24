#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include<arpa/inet.h>

// constants for size of char arrays to store filename, the line from the file
#define FILENAME_SIZE 1024
#define MAX_LINE 2048
int **tabNodes;

void printTabInt(int* tab, int size)
{
    printf("[ ");

    for (int i = 0; i<size; i++)
    {
        if (i == size-1)
        {
            printf("%d ", tab[i]);
        }
        else
        {
            printf("%d | ", tab[i]);
        }
    }

    printf("] \n");
}

void addElement(int* tab, int el)
{
    int* buff = malloc(sizeof(int) * (sizeof(tab) + 1));
    int ii;

    for(int i=0; i<(int)sizeof(tab); i++)
    {
        buff[i] = tab[i];
        ii = i;
    }

    ii ++;

    buff[ii] = el;
}

void createLink(int firstNode, int secondNode)
{
    bool isIn = false;
    int x = 0;

    while (isIn == false && x < (int)sizeof(tabNodes[firstNode]))
    {
        if (tabNodes[firstNode][x] == secondNode)
        {
            isIn = true;
            break;
        }

        x++;
    }

    if (isIn == true)
    {
        return;
    }
    else
    {
        int *newVoisins = malloc((int)sizeof(tabNodes[firstNode]) + sizeof(int));

        for (int i = 0; i < (int)sizeof(newVoisins); i++)
        {
            newVoisins[i] = tabNodes[firstNode][i];
        }

        newVoisins[sizeof(newVoisins)] = secondNode;

        tabNodes[firstNode] = newVoisins;

        return;
    }
}

void comunication(int argc, char *argv[], int nbNodes)
{
    if (argc != 4)
    {
    printf("utilisation : %s ip_serveur port_serveur port_client\n", argv[0]);
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

    char *nbThreads = malloc(sizeof(char) * 2);
    sprintf(nbThreads, "%f", (double)nbNodes);
    printf("On envoi : %s", nbThreads);

    int snd = sendto(ds, nbThreads, strlen(nbThreads)+1, 0, (struct sockaddr*) &adServer, lgAdr);

    if (snd < 0)
    {
        perror("Erreur envoi \n");
        exit(1);
    }

    printf("Envoi reussi \n");

    close(ds);
}

struct sockaddr_in* receiveNodes(int ds, int nbNodes)
{
    struct sockaddr_in* adresses = malloc(nbNodes * sizeof(struct sockaddr_in));
    socklen_t lgA = sizeof(struct sockaddr_in);

    int option = 1;
    // setsockopt;

    if (ds == -1)
    {
        perror("SERVER : pb creation socket : \n");
        exit(1);
    }

    printf("SERVER : creation de la socket réussie \n");

    int compt = 0;
    struct sockaddr_in adNode;

    /* Etape 4 : recevoir l'id du client et stocker son adresse */

    while (compt < nbNodes)
    {
        int idNodeReceive;

        printf("SERVER : J'attend encore %d connexions \n", nbNodes-compt);
        int rec = recvfrom(ds, &idNodeReceive, sizeof(int), 0, (struct sockaddr*) &adNode, &lgA);

        if (rec < 0)
        {
            perror("SERVER : erreur de reception \n");
            exit(1);
        }

        printf("SERVER : Message reçu : %d \n", idNodeReceive);
        adresses[idNodeReceive] = adNode;

        compt++;
    }

    return adresses;
}

void connectNodes(int ds, int firstNode, int secondNode, struct sockaddr_in* tabSock)
{
    socklen_t lgA = sizeof(struct sockaddr_in);
    int mess = -1;

    // J'envoi au 1er -1 pour lui dire que je vais envoyer l'adresse du voisin
    if (sendto(ds, &mess, sizeof(int), 0, (struct sockaddr*) &tabSock[firstNode], lgA) < 0)
    {
        printf("SERVER : \n");
        perror("erreur d'envoi au 1er node \n");
        exit(1);
    }

    // J'envoi au 1er l'adresse du voisin
    if (sendto(ds, &tabSock[secondNode], sizeof(struct sockaddr_in), 0, (struct sockaddr*) &tabSock[firstNode], lgA) < 0)
    {
        printf("SERVER : \n");
        perror("erreur d'envoi de l'adresse du voisin au 1er node \n");
        exit(1);
    }

    mess = -2;
    // J'envoi au 2eme -2 pour lui dire d'écouter
    if (sendto(ds, &mess, sizeof(int), 0, (struct sockaddr*) &tabSock[secondNode], lgA) < 0)
    {
        printf("SERVER : \n");
        perror("erreur d'envoi au 2eme node \n");
        exit(1);
    }
}

int main(int argc, char *argv[])
{
    if (argc != 3)
    {
        printf("Utilisation : %s port_serveur lienFichier\n", argv[0]);
        exit(1);
    }

    // file pointer will be used to open/read the file
    FILE *file;

    char buffer[MAX_LINE];
    // open the the file in read mode
    file = fopen(argv[2], "r");

    // if the file failed to open, exit with an error message and status
    if (file == NULL)
    {
        printf("Error opening file.\n");
        return 1;
    }

    int nbNodes, nbLink = -1;
    int ds;
    struct sockaddr_in* tabSock;
    socklen_t lgA = sizeof(struct sockaddr_in);

    // PARSER
    do
    {
        fgets(buffer, MAX_LINE, file);

        if (buffer[0] == 'p')
        {
            char *buff2 = strtok(buffer, " ");

            buff2 = strtok(NULL, " ");
            buff2 = strtok(NULL, " ");
            nbNodes = atoi(buff2);
            printf("nbNodes = %d \n", nbNodes);
            buff2 = strtok(NULL, " ");
            nbLink = atoi(buff2);

            // On créé la socket UDP ...
            ds = socket(PF_INET, SOCK_DGRAM, 0);

            // ... avec les infos
            struct sockaddr_in ad;
            ad.sin_family = AF_INET;
            ad.sin_addr.s_addr = INADDR_ANY;
            ad.sin_port = htons(atoi(argv[1]));
            socklen_t lgAdr = sizeof(struct sockaddr_in);

            if (bind(ds, (struct sockaddr*)&ad, sizeof(ad)) < 0)
            {
                perror("SERVER : Erreur de nommage \n");
                exit(1);
            }

            tabSock = receiveNodes(ds, nbNodes);

            // On envoi le nb total nodes à tout le monde
            for(int i=0; i<nbNodes; i++)
            {
                if (sendto(ds, &nbNodes, sizeof(int), 0, (struct sockaddr*) &tabSock[i], lgA) < 0)
                {
                    printf("SERVER : \n");
                    perror("erreur d'envoi du nb de nodes \n");
                    exit(1);
                }
            }
        }

        if (buffer[0] == 'e')
        {
            int firstNode, secondNode;

            char *buff2 = strtok(buffer, " ");

            buff2 = strtok(NULL, " ");
            firstNode = atoi(buff2);
            buff2 = strtok(NULL, " ");
            secondNode = atoi(buff2);
            printf("Node1 = %d, Node2 = %d \n", firstNode, secondNode);

            connectNodes(ds, firstNode, secondNode, tabSock);

            // STOP
            fclose(file);
            free(tabSock);
            close(ds); // ferme la socket UDP

            printf("SERVER : FIN \n");
            return 0;
        }

        if (buffer[0] == feof(file))
        {
            printf("EOF");
        }

    } while (feof(file) == 0);

    // close our access to the file
    fclose(file);
    free(tabSock);
    close(ds); // ferme la socket UDP

    printf("SERVER : FIN \n");
    
    return 0;
}