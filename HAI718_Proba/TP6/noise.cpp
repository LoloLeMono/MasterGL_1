// noise.cpp : calcule la différence entre l’image originale et l’image blur

#include <stdio.h>
#include "image_ppm.h"

int main(int argc, char* argv[])
{
  char cNomImgLue[250], cNomImgLueBlur[250], cNomImgEcrite[250];
  int nH, nW, nTaille;
  double diff, som = 0;
  if (argc != 4) 
  {
    printf("Usage %s: ImageIn.pgm ImageInBlur.pgm ImageOut.pgm \n", argv[0]); 
    exit(1);
  }

  sscanf (argv[1],"%s",cNomImgLue) ;
  sscanf (argv[2],"%s",cNomImgLueBlur);
  sscanf (argv[3],"%s",cNomImgEcrite);

  OCTET *ImgIn, *ImgInBlur, *ImgOut;

  lire_nb_lignes_colonnes_image_pgm(cNomImgLue, &nH, &nW); //mm taille pour blur et normal
  nTaille = nH * nW;

  allocation_tableau(ImgIn, OCTET, nTaille);
  allocation_tableau(ImgInBlur, OCTET, nTaille);
  lire_image_pgm(cNomImgLue, ImgIn, nH * nW);
  lire_image_pgm(cNomImgLueBlur, ImgInBlur, nH * nW);

  allocation_tableau(ImgOut, OCTET, nTaille);

  for (int i=0; i < nH-1; i++)
  {
    for (int j=0; j < nW-1; j++)
    {
        diff = ImgIn[i*nW+j] - ImgInBlur[i*nW+j];
        if (diff < -128)
        {
            diff = -128;
        }
        else if(diff > 127)
        {
            diff = 127;
        }
        ImgOut[i*nW+j] = diff + 128;
        som += diff;
    }
  }
  
  ecrire_image_pgm(cNomImgEcrite, ImgOut,  nH, nW);
  free(ImgIn);free(ImgInBlur);free(ImgOut);

  printf("Somme noise = %f \n", som);
  printf("Moyenne noise = %f \n", som/(double)nTaille);

  return 1;
}
