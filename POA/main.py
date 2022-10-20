import numpy as np
import random

h = 5
nbCacas = 3

map = np.array([[0,0,0,0,0],[0,0,0,0,0]])

for i in range(h) : # Remplir les obstacle (2)

    randX = random.randint(0, h)
    randY = random.randint(0, h)
    
    map[randX][randY] = 2

for i in range(nbCaca) : # Ajoute les cacas (1)

    randX = random.randint(0, h)
    randY = random.randint(0, h)

    while map[randX][randY] != 0 :
        map[randX][randY] = 1

nbRecipes = ((h*h)/25)+1

for i in range(nbRecipes) : # Ajoute les recipes
    randX = random.randint(0, h)
    randY = random.randint(0, h)
    
    while map[randX][randY] != 0 :
        map[randX][randY] = 3
