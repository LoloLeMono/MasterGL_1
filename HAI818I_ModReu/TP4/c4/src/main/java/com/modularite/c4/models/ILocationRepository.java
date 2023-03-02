package com.modularite.c4.models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ILocationRepository
{
    public interface LocationRepository extends JpaRepository<Location ,Long>
    {}
}
