<?php

/**
 * This file has been auto-generated
 * by the Symfony Routing Component.
 */

return [
    false, // $matchHost
    [ // $staticRoutes
        '/fact' => [[['_route' => 'app_fact', '_controller' => 'App\\Controller\\FactController::index'], null, null, null, false, false, null]],
        '/master' => [[['_route' => 'app_master', '_controller' => 'App\\Controller\\MasterController::master'], null, null, null, false, false, null]],
    ],
    [ // $regexpList
        0 => '{^(?'
                .'|/_error/(\\d+)(?:\\.([^/]++))?(*:35)'
                .'|/fact/([^/]++)(*:56)'
                .'|/combi/([^/]++)/([^/]++)(*:87)'
            .')/?$}sDu',
    ],
    [ // $dynamicRoutes
        35 => [[['_route' => '_preview_error', '_controller' => 'error_controller::preview', '_format' => 'html'], ['code', '_format'], null, null, false, true, null]],
        56 => [[['_route' => 'app_fact_n', '_controller' => 'App\\Controller\\FactController::showFact'], ['n'], null, null, false, true, null]],
        87 => [
            [['_route' => 'app_combi_n_p', '_controller' => 'App\\Controller\\FactController::showCombi'], ['n', 'p'], null, null, false, true, null],
            [null, null, null, null, false, false, 0],
        ],
    ],
    null, // $checkCondition
];
