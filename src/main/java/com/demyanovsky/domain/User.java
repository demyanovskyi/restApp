package com.demyanovsky.domain;

import org.springframework.stereotype.Component;

import java.util.Objects;

//Model
@Component
public class User {

    private int id;

    private String name;

    public User(int id, String firstName) {
        this.id = id;
        this.name = firstName;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() &&
                Objects.equals(getName(), user.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
