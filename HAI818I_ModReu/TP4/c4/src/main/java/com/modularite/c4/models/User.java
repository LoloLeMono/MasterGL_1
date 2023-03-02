package com.modularite.c4.models;

import jakarta.persistence.*;

import java.util.List;

@Entity(name="users") @Access(AccessType.FIELD)
public class User
{
    @Id
    @GeneratedValue( strategy = GenerationType . IDENTITY )
    private long user_id;

    @ManyToMany
    @JoinTable (name=" user_locations ",
            joinColumns = @JoinColumn (name=" user_id "),
            inverseJoinColumns = @JoinColumn(name="location_id"))
    private List<Location> locations;

    public List<Location> getLocations()
    {
        return locations;
    }

    public void setLocations(List<Location> locations)
    {
        this.locations = locations;
    }

    public long getUser_id()
    {
        return user_id;
    }

    public void setUser_id(long user_id)
    {
        this.user_id = user_id;
    }
}
