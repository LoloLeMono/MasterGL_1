package com.modularite.c4.models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository
{
    public interface UserRepository extends JpaRepository<User ,Long>
    { }
}
