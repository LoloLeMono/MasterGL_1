import numpy as np
import random

# n obstacle pour une matrice n*n
#ToDO : Corriger dépassement tab

#1 = caca
#2 = obstacle
#3 = recipe

# Priorité = haut > droite > gauche > bas

h = 7
nbCacas = 5
nbObstacles = 10
#capacity = 3
container = 0
end = False
pos = np.array([0, 0])


# Retourne la direction d'un caca
def check(pos):
  scanL = pos[0]
  scanC = pos[1]
  #print("scanL = ", scanL)
  #print("scanC = ", scanC)

  # tant qu'on ne tombe pas sur un obstacle && on depasse pas en haut
  while scanL >= 0 and mape[scanL][scanC] != 2:
    print("Je scanne en ",scanL, scanC,)
    if mape[scanL][scanC] == 1:  # si on trouve un caca
      print("Caca scanné vers le haut")
      return "up"
    else:  # on reagrde vers le haut
      scanL -= 1
      print("Je regarde en ",scanL, scanC,)

  scanL = pos[0]
  scanC = pos[1]

  # tant qu'on ne tombe pas sur un obstacle && on dépasse pas à droite
  while scanC <= h - 1 and mape[scanL][scanC] != 2:
    print("Je scanne en ",scanL, scanC,)
    if mape[scanL][scanC] == 1:  # si on trouve un caca
      print("Caca scanné vers la droite")
      return "right"
      # go to
    else:  # on regarde vers la droite
      scanC += 1
      print("Je regarde en ",scanL, scanC,)

  scanL = pos[0]
  scanC = pos[1]

  # tant qu'on ne tombe pas sur un obstacle et on dépasse pas à gauche
  while scanC >= 0 and mape[scanL][scanC] != 2:
    print("Je scanne en ",scanL, scanC,)
    if mape[scanL][scanC] == 1:  # si on trouve un caca
      print("Caca scanné vers la gauche")
      return "left"
      # go to
    else:  # on regarde vers la gauche
      scanC -= 1
      print("Je regarde en ",scanL, scanC,)

  scanL = pos[0]
  scanC = pos[1]

  # tant qu'on ne tombe pas sur un obstacle et on depasse pas en bas
  while scanL <= h - 1 and mape[scanL][scanC] != 2:
    print("Je scanne en ",scanL, scanC,)
    if mape[scanL][scanC] == 1:  # si on trouve un caca
      print("Caca scanné vers le bas")
      return "down"
      # go to
    else:  # on regarde vers le bas
      scanL += 1
      print("Je regarde en ",scanL, scanC,)

  print("retour de check = null")
  return "null"


# move : Incremente pos dans une direction sinon avance en suivant les priorités
def move(direction):
  if (direction == "up"):
    pos[0] -= 1
    print("Je monte et la nouvelle pos = ", pos)

  if (direction == "right"):
    pos[1] += 1
    print("Je droite et la nouvelle pos = ", pos)

  if (direction == "left"):
    pos[1] -= 1
    print("Je gauche et la nouvelle pos = ", pos)

  if (direction == "down"):
    pos[0] += 1
    print("Je descend et la nouvelle pos = ", pos)

  #else : on trouve pas caca => avance ou droite ou gauche ou recule
  if (direction == "null"):
    if pos[0] - 1 >= 0 and mape[pos[0] - 1, pos[1]] != 2:
      if mape[pos[0] - 1, pos[1]] != 5:
        move("up")
        return 0
    elif pos[1] + 1 < h and mape[pos[0], pos[1] + 1] != 2:
      if mape[pos[0], pos[1] + 1] != 5:
        move("right")
        return 0
    elif pos[1] - 1 >= 0 and mape[pos[0], pos[1] - 1] != 2:
      if mape[pos[0], pos[1] - 1] != 5:
        move("left")
        return 0  
    elif pos[0] + 1 < h and mape[pos[0] + 1, pos[1]] != 2:
      if mape[pos[0] + 1, pos[1]] != 5:
        move("down")
        return 0
    
    # Si on est déjà passé partout

    if pos[0] - 1 >= 0 and mape[pos[0] - 1, pos[1]] != 2:
      move("up")
    elif pos[1] + 1 < h and mape[pos[0], pos[1] + 1] != 2:
      move("right")
    elif pos[1] - 1 >= 0 and mape[pos[0], pos[1] - 1] != 2:
      move("left")     
    elif pos[0] + 1 < h and mape[pos[0] + 1, pos[1]] != 2:
      move("down")
  
  return 0


# spawn : Initialise pos au recipe le plus proche et retourne 0
def spawn():
    for li in range(h):
        for col in range(h):
            if mape[li][col] == 3:
                pos[0] = li
                pos[1] = col
                print("Spawn en ", pos)
                return 0

    print("Erreur spawn")


# fillMap : Construit la map
def fillMape():
  # Remplir les obstacle (2)
  for i in range(nbObstacles):

    randX = random.randint(0, h - 1)
    randY = random.randint(0, h - 1)

    mape[randX][randY] = 2

  # Ajoute les cacas (1)
  for i in range(nbCacas):

    randX = random.randint(0, h - 1)
    randY = random.randint(0, h - 1)

    while mape[randX][randY] != 0:
      randX = random.randint(0, h - 1)
      randY = random.randint(0, h - 1)

    mape[randX][randY] = 1

  # Ajoute le recipe (3)
  randX = random.randint(0, h - 1)
  randY = random.randint(0, h - 1)

  while mape[randX][randY] != 0:
    randX = random.randint(0, h - 1)
    randY = random.randint(0, h - 1)

  mape[randX][randY] = 3

  print(mape)

mape = np.zeros([h, h])

#
#------------EXECUTION---------------
fillMape()
spawn()

# 1 recherche
#print(check(pos))

def main():
  while (nbCacas>0):
      print("DEPLACEMENT SUIVANT")
      if mape[pos[0], pos[1]] == 1:
          print("CACA trouvé !")
          nbCacas -= 1
      move(check(pos))
      print(mape)

