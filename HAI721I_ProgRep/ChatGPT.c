#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#define MAX_VERTICES 1000
#define MAX_COLORS 1000

// Structure de données pour représenter un sommet du graphe
typedef struct vertex {
  int id;                   // Identifiant du sommet
  int degree;               // Degré du sommet
  int color;                // Couleur choisie pour le sommet (-1 si non colorié)
  int num_neighbors;        // Nombre de voisins
  int neighbors[MAX_VERTICES];  // Tableau des identifiants de voisins
  int pipes[MAX_VERTICES][2];   // Tableau de pipes pour la communication avec les voisins
} vertex;

// Fonction de comparaison pour trier les sommets par degré décroissant
int compare_vertices(const void *a, const void *b) {
  vertex *v1 = (vertex*)a;
  vertex *v2 = (vertex*)b;
  return v2->degree - v1->degree;
}

int main(int argc, char **argv) {
  // Vérifier que le nombre d'arguments est correct
  if (argc != 3) {
    fprintf(stderr, "Usage: %s num_vertices num_colors\n", argv[0]);
    exit(1);
  }

  // Lire le nombre de sommets et de couleurs à partir des arguments de la ligne de commande
  int num_vertices = atoi(argv[1]);
  int num_colors = atoi(argv[2]);

  // Tableau de sommets
  vertex vertices[MAX_VERTICES];

  // Tableau des couleurs utilisées par chaque sommet
  int used_colors[MAX_VERTICES][MAX_COLORS];

  // Vérifier que le nombre de sommets et de couleurs est valide
  if (num_vertices < 1 || num_vertices > MAX_VERTICES) {
    fprintf(stderr, "Error: invalid number of vertices\n");
    exit(1);
  }
  if (num_colors < 1 || num_colors > MAX_COLORS) {
    fprintf(stderr, "Error: invalid number of colors\n");
    exit(1);
  }

  // Initialiser les sommets et leur degré
  for (int i = 0; i < num_vertices; i++) {
    vertices[i].id = i;
    vertices[i].degree = 0;
    vertices[i].color = -1;
    vertices[i].num_neighbors = 0;
  }

  // Lire les données du graphe à partir de l'entrée standard
  for (int i = 0; i < num_vertices; i++) {
    // Lire le nombre de voisins du sommet
    scanf("%d", &vertices[i].num_neighbors);

    // Lire les identifiants des voisins du sommet
    for (int j = 0; j < vertices[i].num_neighbors; j++) {
      scanf("%d", &vertices[i].neighbors[j]);
      vertices[i].degree++;
    }
  }

  // Trier les sommets par degré décroissant
  qsort(vertices, num_vertices, sizeof(vertex), compare_vertices);

  // Créer les pipes pour la communication entre les processus
  for (int i = 0; i < num_vertices; i++) {
    vertex *v = &vertices[i];

    // Créer une pipe pour chaque voisin
    for (int j = 0; j < v->num_neighbors; j++) {
      int neighbor = v->neighbors[j];
      if (pipe(v->pipes[j]) < 0) {
        perror("Error creating pipe");
        exit(1);
      }

      // Faire en sorte que les pipes soient bidirectionnelles en dupliquant les pipes dans l'autre sens
      if (i < neighbor) {
        vertices[neighbor].pipes[v->degree][0] = v->pipes[j][0];
        vertices[neighbor].pipes[v->degree][1] = v->pipes[j][1];
      }
    }
  }

  // Pour chaque sommet, créer un processus fils pour essayer de choisir une couleur qui n'a pas été utilisée par un voisin proche
  for (int i = 0; i < num_vertices; i++) {
    vertex *v = &vertices[i];

    // Créer le processus fils
    pid_t pid = fork();
    if (pid < 0) {
      perror("Error creating child process");
      exit(1);
    }

    // Processus fils : essayer de choisir une couleur qui n'a pas été utilisée par un voisin proche
    if (pid == 0) {
      // Réinitialiser le tableau des couleurs utilisées par les voisins
      memset(used_colors[i], 0, num_colors * sizeof(int));

      // Demander à chaque voisin quelle couleur il a choisie
      for (int j = 0; j < v->num_neighbors; j++) {
        int neighbor = v->neighbors[j];
        int pipe_read = v->pipes[j][0];
        int pipe_write = v->pipes[j][1];

        // Envoyer un message demandant la couleur
        char buffer[10];
        sprintf(buffer, "color?");
        if (write(pipe_write, buffer, strlen(buffer)) < 0) {
          perror("Error writing to pipe");
          exit(1);
        }

        // Attendre la réponse du voisin
        if (read(pipe_read, buffer, 10) < 0) {
          perror("Error reading from pipe");
          exit(1);
        }

        // Mettre à jour le tableau des couleurs utilisées par les voisins
        int color = atoi(buffer);
        used_colors[i][color] = 1;
      }

      // Choisir une couleur qui n'a pas été utilisée par un voisin proche
      for (int j = 0; j < num_colors; j++) {
        if (used_colors[i][j] == 0) {
          v->color = j;
          break;
        }
      }

      // Si aucune couleur n'est disponible, envoyer un message d'erreur à tous les autres processus
      if (v->color < 0) {
        fprintf(stderr, "Error: graph is not colorable with given number of colors\n");
        char buffer[10];
        sprintf(buffer, "error");
        for (int j = 0; j < num_vertices; j++) {
          if (i != j) {
            if (write(vertices[j].pipes[v->degree][1], buffer, strlen(buffer)) < 0) {
              perror("Error writing to pipe");
              exit(1);
            }
          }
        }
        exit(1);
      }

      // Envoyer la couleur choisie à tous les voisins
      char buffer[10];
      sprintf(buffer, "%d", v->color);
      for (int j = 0; j < v->num_neighbors; j++) {
        int pipe_write = v->pipes[j][1];
        if (write(pipe_write, buffer, strlen(buffer)) < 0) {
          perror("Error writing to pipe");
          exit(1);
        }
      }

      // Quitter le processus fils
      exit(0);
    }
  }

  // Processus père : attendre la fin de tous les processus fils
  int status;
  for (int i = 0; i < num_vertices; i++) {
    wait(&status);
  }

  // Afficher les résultats
  for (int i = 0; i < num_vertices; i++) {
    printf("Vertex %d: color %d\n", vertices[i].id, vertices[i].color);
  }

  return 0;
}