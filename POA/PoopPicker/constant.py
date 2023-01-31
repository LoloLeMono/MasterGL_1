import pygame
import os
from main import *

largeur, longueur = h*67,h*67
case = h*67/12


emerald_green = (0,201,87) #emeraldgreen
forest_green = (34,139,34) #forestgreen
cobalt_green = (61,145,64) #cobaltgreen

color_caca = (139,69,19)
color_floor = (0,153,153)
color_robot = (0,0,255)
color_wall = (64,64,64)

Path = "img/"
caca = pygame.transform.scale(pygame.image.load(os.path.join(Path,"newCaca.png")),(67, 67))
robot = pygame.transform.scale(pygame.image.load(os.path.join(Path,"newRobot.png")),(67, 67))
#mur = pygame.transform.scale(pygame.image.load(os.path.join(Path,"mur.jpg")),(case, case))