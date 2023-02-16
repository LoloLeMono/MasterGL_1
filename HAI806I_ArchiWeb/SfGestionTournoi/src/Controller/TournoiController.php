<?php

namespace App\Controller;

use App\Entity\Tournoi;
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

    #[Route("/tournoi/newEvt/{nom<[0-9a-zA-Z ]+>}", name:"newEvt")]
    public function createEvt(ManagerRegistry $doctrine, $nom): Response
    {
        $stop = false;
        $evt = new Evenement();
        $evt->setNom($nom);
        $evt->setDateDeb(new \DateTime());
        $evt->setDateFin(new \DateTime());

        $allEvt = $doctrine -> getRepository(Evenement::class)->findAll();

        foreach ($allEvt as $e)
        { 
            if ($e->getNom() === $nom)
            {
                $stop = true;
                break;
            }
        }

        if (!$stop)
        {
            $em = $doctrine->getManager();
            $em->persist($evt);
            $em->flush();
        }
        

        return $this->render('tournoi/addEvenement.html.twig', [
            'controller_name' => 'TournoiController',
            'newEvt' => $evt,
            'stop'=> $stop,
        ]);
    }

    #[Route("/tournoi/newTnoi/{evtid<[0-9]+>}/{nom<[0-9a-zA-Z ]+>}/{desc?}", name:"newTnoi")]
    public function createTnoi(ManagerRegistry $doctrine, $evtid, $nom, $desc): Response
    {
        $evt = $doctrine -> getRepository(Evenement::class)->find($evtid);

        $t = new Tournoi();
        $t->setNom($nom);
        $t->setDescription($desc);
        $t->setEv($evt);

        $em = $doctrine->getManager();
        $em->persist($t);
        $em->flush();

        return $this->render('tournoi/addTournoi.html.twig', [
            'controller_name' => 'TournoiController',
            'evenement' => $evt,
            'tournoi' => $t,
        ]);
    }
}
