<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Routing\Annotation\Route;

class MasterController extends AbstractController
{
    #[Route('/master', name: 'app_master')]
    public function master(SessionInterface $session, Request $request)
    {
        if(!$session->has('mastermind'))
        {
            $session->set('mastermind',new Mastermind()); // créé
        }

        $jeu=$session->get('mastermind',null); // on a un jeu !
        $prop = $request->query->get('prop');

        if (null !== $prop) // une proposition
        { 
            $r=$jeu->test($prop); // résultat

            if ($r===false) // proposition invalide
            {
                $message="$prop : proposition invalide !";
            } else if ($r['bon']!=$jeu->getTaille()) // valide !
            {
                $message="$prop : proposition valide : {$r['bon']} bien placé(s), {$r['mal']} mal placé(s)";
            } else {
                $message="Félicitations, vous avez gagné !";
            }

            $session->set('mastermind', $jeu); // sauve le jeu
        } else // pas de proposition !
        {
            $message = "Veuillez saisir une proposition S.V.P. !";
            $nouv = $request->query->get('nouveauJeu');
 
            if (null != $nouv) // Nouveau Jeu
            {
                $taille = intval($request->query->get('taille'));

                if ($taille<1 || $taille>6)
                {
                    $taille=4; // par défaut
                }

                $session->set('mastermind',new Mastermind($taille)); // créé
                $jeu=$session->get('mastermind',null); // à supprimer
            }
        }
        
        return $this->render('mastermind.twig',[
            'jeu' => $jeu,
            'message' => $message
        ]);
    }
}
