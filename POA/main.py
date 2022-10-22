import numpy as np
import random

#ToDO : Corriger dépassement tab

# Priorité = haut > droite > gauche > back

h = 5
nbCacas = 3
#capacity = 3
container = 0
end = False

pos = np.array([0,0])
map = np.array([[0,0,0,0,0],[0,0,0,0,0]])

# Remplir les obstacle (2)
for i in range(h) : 

    randX = random.randint(0, h)
    randY = random.randint(0, h)
    
    map[randX][randY] = 2

# Ajoute les cacas (1)
for i in range(nbCacas) :

    randX = random.randint(0, h)
    randY = random.randint(0, h)

    while map[randX][randY] != 0 :
        map[randX][randY] = 1

nbRecipes = ((h*h)/25)+1

# Ajoute les recipes (3)
for i in range(nbRecipes) :
    randX = random.randint(0, h)
    randY = random.randint(0, h)
    
    while map[randX][randY] != 0 :
        map[randX][randY] = 3

print (map)

# Check : Retourne la direction d'un caca

def check(pos) : 
    scanX = pos[0]
    scanY = pos[1]

    while map[scanX][scanY] != 2 : # tant qu'on ne tombe pas sur un obstacle
        if map[scanX][scanY] == 1 : # si on trouve un caca
            return "up"
        else: # on reagrde vers le haut
            scanY -= 1

    scanX = pos[0]
    scanY = pos[1]

    while map[scanX][scanY] != 2 : # tant qu'on ne tombe pas sur un obstacle
        if map[scanX][scanY] == 1 : # si on trouve un caca
            return "right"
            # go to
        else: # on regarde vers la droite
            scanX += 1

    scanX = pos[0]
    scanY = pos[1]

    while map[scanX][scanY] : # tant qu'on ne tombe pas sur un obstacle
        if map[scanX][scanY] : # si on trouve un caca
            return "left"
            # go to
        else: # on regarde vers la gauche
            scanX -= 1

    scanX = pos[0]
    scanY = pos[1]

    while map[scanX][scanY] != 2 : # tant qu'on ne tombe pas sur un obstacle
        if map[scanX][scanY] == 1 : # si on trouve un caca
            return "down"
            # go to
        else: # on regarde vers le bas
            scanY += 1



# Avancer

def move(direction) :
    match direction:
        case "up":
            pos[1] -= 1
        case "right":
            pos[0] += 1
        case "left":
            pos[0] -= 1
        case "down":
            pos[1] += 1
        case _:
            return "ERROR"

for i in range(0, 10) :
    move(check(pos))
    print(pos)
    
