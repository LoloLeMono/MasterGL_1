<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Doctrine\Persistence\ManagerRegistry;

use App\Entity\Evenement;


class TournoiController extends AbstractController
{
    #[Route('/tournoi', name: 'app_tournoi')]
    public function index(ManagerRegistry $doctrine): Response
    {
        $e = $doctrine -> getRepository(Evenement::class)->findAll();

        return $this->render('tournoi/index.html.twig', [
            'controller_name' => 'TournoiController',
            'evenements' => $e,
        ]);
    }
}
