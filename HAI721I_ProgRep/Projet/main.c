#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// constants for size of char arrays to store filename, the line from the file
#define FILENAME_SIZE 1024
#define MAX_LINE 2048
int **tabNodes;

<<<<<<< HEAD
// createLink :
void createLink(int firstNode, int secondNode) {
  bool isIn = false;

  while (isIn == false && i < sizeof(tabNodes[firstNode])) {
    if (tabNodes[firstNode][i] == secondNode) {
      isIn = true;
      break;
    }
  }

  if (isIn == true) {
    return;
  } else {
    int *newVoisins = malloc(sizeof(tabNodes[firstNode]) + sizeof(int));

    for (int i = 0; i < sizeof(newVoisins); i++) {
      newVoisins[i] = tabNodes[firstNode][i];
    }
    newVoisins[sizeof(newVoisins)] = secondNode;

    tabNodes[firstNode] = newVoisins;

    return;
  }
}

=======
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

>>>>>>> ce1596355c9637fa674f3d68083b7d47c3193aad
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
<<<<<<< HEAD
=======
  int ind = 0;
>>>>>>> ce1596355c9637fa674f3d68083b7d47c3193aad

  do {
    fgets(buffer, MAX_LINE, file);

    if (buffer[0] == 'p') {

      char *buff2 = strtok(buffer, " ");

      buff2 = strtok(NULL, " ");
      buff2 = strtok(NULL, " ");
      nbNodes = atoi(buff2);
<<<<<<< HEAD
      printf("nbEdge = %d \n", nbEdge);
=======
      printf("nbEdge = %d \n", nbNodes);
>>>>>>> ce1596355c9637fa674f3d68083b7d47c3193aad
      buff2 = strtok(NULL, " ");
      nbLink = atoi(buff2);
      printf("nbLink = %d \n", nbLink);

<<<<<<< HEAD
      tabNodes = malloc(sizeof(int *) * nbNodes +
                        sizeof(int)); //+ sizeof(int) pour la premiere case
                                      //jamais prise en compte
      // printf("tabSize = %d \n", (int)sizeof(tabNodes));
=======
      tabNodes = malloc(sizeof(int *) * nbLink +
                        sizeof(int));
>>>>>>> ce1596355c9637fa674f3d68083b7d47c3193aad
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

<<<<<<< HEAD
      createLink(firstNode, secondNode);
=======
      int tabBuf[2] = {firstNode, secondNode};
      tabNodes[ind] = tabBuf;
      ind++;
>>>>>>> ce1596355c9637fa674f3d68083b7d47c3193aad
    }

    if (feof(file)) {
      printf("EOF");
    }

<<<<<<< HEAD
=======
    
>>>>>>> ce1596355c9637fa674f3d68083b7d47c3193aad
  } while (feof(file) == NULL);

  // close our access to the file
  printf("%ls et %ls", tabNodes[0], tabNodes[1]);
  fclose(file);

  return 0;
}