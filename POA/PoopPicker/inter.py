import time
import pygame, sys
from constant import *

pygame.init()

screen = pygame.display.set_mode([largeur,longueur])

def board(mape):
    for x in range(0, h):
        for y in range(0,h):
            case = mape[x][y]

            match case:
                case 0.:
                    pygame.draw.rect(screen, color_floor, (y*67, x*67, 67,67))
                case 1.:
                    #pygame.draw.rect(screen, caca, (y*67, x*67, 67,67))
                    screen.blit(caca, (y*67, x*67))
                case 2.:
                    pygame.draw.rect(screen, color_wall, (y*67, x*67, 67,67))
                case 5.:
                    pygame.draw.rect(screen, color_floor, (y*67, x*67, 67,67))

screen.fill(color_floor)
running = True

time.sleep(2)

while running :
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False

    if (nbCacas>0):
      print("DEPLACEMENT SUIVANT")
      pygame.draw.rect(screen, color_floor, (pos[1]*67, pos[0]*67, 67,67))
      pygame.display.update()
      move(check(pos))
      screen.blit(robot, (pos[1]*67, pos[0]*67))
      pygame.display.update()

      if mape[pos[0], pos[1]] == 1:
          print("CACA trouvé !")
          mape[pos[0], pos[1]] = 0
          nbCacas -= 1
      print(mape)
    
    time.sleep(1)
    
    board(mape)

    pygame.display.update()
    pygame.display.flip()
    
pygame.quit()