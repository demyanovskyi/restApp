package com.demyanovsky.domain;

import java.util.UUID;

public class UserDTO {
    private UUID id;
    private String name;
    private String password;

    public UserDTO(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public UserDTO(String name) {
        this.name = name;
    }

    public UserDTO() {
    }

    public UserDTO(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserDTO(UUID id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
