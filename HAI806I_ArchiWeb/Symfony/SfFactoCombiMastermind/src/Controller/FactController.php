<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Routing\Annotation\Route;

class FactController extends AbstractController
{
    #[Route('/fact', name: 'app_fact')]
    public function index(): JsonResponse
    {
        return $this->json([
            'message' => 'Welcome to your new controller!',
            'path' => 'src/Controller/FactController.php',
        ]);
    }

    #[Route('/fact/{n}', name: 'app_fact_n')]
    public function showFact(int $n): JsonResponse
    {
        return $this->json([
            'factorielle('.$n.')' => fact($n),
        ]);
    }

    #[Route('/combi/{n}/{p}', name: 'app_combi_n_p')]
    public function showCombi(int $n, int $p): JsonResponse
    {
        return $this->json([
            'Combinaison('.$n.', '.$p.')' => combi($n, $p),
        ]);
    }
}

function fact($n)
{
    if ($n <= 1)
    {
        return 1;
    }
    else
    {
        return $n * fact($n-1);
    }
}

function combi($n, $p)
{
    return fact($n)/(fact($p)*fact($n-$p));
}