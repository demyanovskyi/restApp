package com.demyanovsky.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.annotation.Generated;
import java.util.Objects;
import java.util.UUID;

//Model
@Table(value = "users")
 public  class User  {

    @Id
    @Generated(value = "true")
    @JsonProperty
    private UUID id;
    @JsonProperty
    private String name;

    public User(UUID id, String firstName) {
        this.id = id;
        this.name = firstName;
    }
    public User( String firstName) {

        this.name = firstName;
    }

    public User() {
    }

    public UUID getId() {
        return id;
    }


    public void setId(UUID id) {
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
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
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
