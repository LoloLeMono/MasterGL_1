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
    if (argc != 3)
    {
        printf("Utilisation : %s port_serveur lienFichier\n", argv[0]);
        exit(1);
    }

    int portServ = atoi(argv[1]);

    int exit_status;
    char buf[256];

    if (fork() == 0)
    {
        sleep(1);

        // CREATION NODES
        for(int i = 0; i<3; i++)
        {
            snprintf(buf, sizeof(buf), "./node 127.0.0.1 %d %d &",portServ, i);
            exit_status = system(buf);

            if(exit_status==-1)
            {
                perror("Failed opening terminal node\n");
                exit(1);
            }
        }
    }
    else 
    {
        // CREATION SERVER
        snprintf(buf, sizeof(buf), "./server %d %s &",portServ, argv[2]);
        exit_status = system(buf);

        if(exit_status==-1)
        {
            perror("Failed opening terminal server\n");
            exit(1);
        }
    }
    
}