#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// constants for size of char arrays to store filename, the line from the file
#define FILENAME_SIZE 1024
#define MAX_LINE 2048
int **tabNodes;

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

// createLink :
void createLink(int firstNode, int secondNode) {
  bool isIn = false;
  int x = 0;

  while (isIn == false && x < (int)sizeof(tabNodes[firstNode])) {
    if (tabNodes[firstNode][x] == secondNode) {
      isIn = true;
      break;
    }
    x++;
  }

  if (isIn == true) {
    return;
  } else {
    int *newVoisins = malloc((int)sizeof(tabNodes[firstNode]) + sizeof(int));

    for (int i = 0; i < (int)sizeof(newVoisins); i++) {
      newVoisins[i] = tabNodes[firstNode][i];
    }
    newVoisins[sizeof(newVoisins)] = secondNode;

    tabNodes[firstNode] = newVoisins;

    return;
  }
}

int main() {
  // file pointer will be used to open/read the file
  FILE *file;

  char buffer[MAX_LINE];
  int **tabNodes = NULL; // Chaques indince de tabNodes représentent un node et
                         // il contient sa liste de voisins

  // open the the file in read mode
  file = fopen("exmplMod.txt", "r");

  // if the file failed to open, exit with an error message and status
  if (file == NULL) {
    printf("Error opening file.\n");
    return 1;
  }

  int nbNodes, nbLink = -1;
  int ind = 0;

  do {
    fgets(buffer, MAX_LINE, file);

    if (buffer[0] == 'p') {

      char *buff2 = strtok(buffer, " ");

      buff2 = strtok(NULL, " ");
      buff2 = strtok(NULL, " ");
      nbNodes = atoi(buff2);
      printf("nbEdge = %d \n", nbNodes);
      buff2 = strtok(NULL, " ");
      nbLink = atoi(buff2);
      printf("nbLink = %d \n", nbLink);

      tabNodes = malloc(sizeof(int *) * nbLink +
                        sizeof(int));
    }

    if (buffer[0] == 'e') {
      int firstNode;
      int secondNode;

      char *buff2 = strtok(buffer, " ");

      buff2 = strtok(NULL, " ");
      firstNode = atoi(buff2);
      buff2 = strtok(NULL, " ");
      secondNode = atoi(buff2);
      printf("Node1 = %d, Node2 = %d \n", firstNode, secondNode);

      int tabBuf[2] = {firstNode, secondNode};
      tabNodes[ind] = tabBuf;
      ind++;
    }

    if (feof(file)) {
      printf("EOF");
    }

    
  } while (feof(file) == NULL);

  // close our access to the file
  printf("%ls et %ls", tabNodes[0], tabNodes[1]);
  fclose(file);

  return 0;
}