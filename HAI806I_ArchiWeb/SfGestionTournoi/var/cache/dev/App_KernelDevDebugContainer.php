<?php

// This file has been auto-generated by the Symfony Dependency Injection Component for internal use.

if (\class_exists(\ContainerJ3Amc7o\App_KernelDevDebugContainer::class, false)) {
    // no-op
} elseif (!include __DIR__.'/ContainerJ3Amc7o/App_KernelDevDebugContainer.php') {
    touch(__DIR__.'/ContainerJ3Amc7o.legacy');

    return;
}

if (!\class_exists(App_KernelDevDebugContainer::class, false)) {
    \class_alias(\ContainerJ3Amc7o\App_KernelDevDebugContainer::class, App_KernelDevDebugContainer::class, false);
}

return new \ContainerJ3Amc7o\App_KernelDevDebugContainer([
    'container.build_hash' => 'J3Amc7o',
    'container.build_id' => '185441f1',
    'container.build_time' => 1676536301,
], __DIR__.\DIRECTORY_SEPARATOR.'ContainerJ3Amc7o');
