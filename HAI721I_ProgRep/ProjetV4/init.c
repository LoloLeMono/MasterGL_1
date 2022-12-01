#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include<arpa/inet.h>

#define FILENAME_SIZE 1024
#define MAX_LINE 2048
int **tabNodes;

int main(int argc, char *argv[])
{
    if (argc != 4)
    {
        printf("Utilisation : %s port_serveur port_client_reference lienFichier\n", argv[0]);
        exit(1);
    }

    int portServ = atoi(argv[1]);

    int exit_status;
    char buf[256];


    // CREATION SERVER
    snprintf(buf, sizeof(buf), "./server %d %s &",portServ, argv[3]);
    exit_status = system(buf);

    if(exit_status==-1)
    {
        perror("Failed opening terminal server\n");
        exit(1);
    }

    int portClient = atoi(argv[2]);

    // CREATION NODES (ATTENTION 3 BRUT ICI)
    for(int i = 0; i<3; i++)
    {
        snprintf(buf, sizeof(buf), "./node 127.0.0.1 %d %d %d &",portServ, portClient, i);
        exit_status = system(buf);

        if(exit_status == -1)
        {
            perror("Failed opening terminal node\n");
            exit(1);
        }

        portClient++;
    }

    printf("INITIALISATION : FIN");

    return 0;
}