package com.demyanovsky.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

//Model
public class User {
    @JsonProperty
    private Long id;
    private String name;

    public User(Long id, String firstName) {
        this.id = id;
        this.name = firstName;
    }

    public User(){}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) &&
                Objects.equals(getName(), user.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
