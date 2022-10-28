#include <stdio.h>
#include <stdbool.h>

// constants for size of char arrays to store filename, the line from the file
#define FILENAME_SIZE 1024
#define MAX_LINE 2048

int main()
{
  // file pointer will be used to open/read the file
  FILE *file;

  char buffer[MAX_LINE];

  // open the the file in read mode
  file = fopen("exmpl.txt", "r");

  // if the file failed to open, exit with an error message and status
  if (file == NULL)
  {
    printf("Error opening file.\n");
    return 1;
  }

  // we'll keep reading the file so long as keep_reading is true, and we'll 
  // keep track of the current line of the file using current_line

  do 
  {
    // read the next line from the file, store it into buffer
    fgets(buffer, MAX_LINE, file);

    // if we've reached the end of the file, we didn't find the line
    if (feof(file))
    {
      // stop reading from the file, and tell the user the number of lines in 
      // the file as well as the line number they were trying to read as the 
      // file is not large enough
      printf("EOF");
    }

  } while (buffer[0] == 'c');

  printf("La derniere ligne : %s", buffer);

  // close our access to the file
  fclose(file);

  return 0;
}