package com.modularite.c4.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.ManyToMany;

import java.util.List;

public class Location
{
    @ManyToMany(mappedBy = "locations")
    // Pour ne pas produire des cycles
    @JsonIgnore
    private List<User> users;

    public List<User> getUsers()
    {
        return users;
    }

    public void setUsers(List<User> users)
    {
        this.users = users;
    }
}
