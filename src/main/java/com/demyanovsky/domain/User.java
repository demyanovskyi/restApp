package com.demyanovsky.domain;


import com.fasterxml.jackson.annotation.JsonProperty;

//Model


public class User {


    @JsonProperty
    private Long id;


    private String name;

    public User(Long id, String firstName) {
        this.id = id;
        this.name = firstName;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return String.format(
                "User[id=%d, name='%s']",
                id, name);
    }
}
