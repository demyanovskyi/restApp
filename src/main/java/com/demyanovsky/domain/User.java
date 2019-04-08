package com.demyanovsky.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "Users")
@Table(name = "users")
public class User {
    @JsonProperty
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    @Id
    private UUID id;
    @JsonProperty
    private String name;
    @JsonIgnore
    private String password;
    @JsonProperty
    private Role role;
    @JsonIgnore
    private String salt;
    @JsonProperty
    private String email;
    @JsonIgnore
    private String restoreHash;
    @JsonIgnore
    private OffsetDateTime validityPeriod;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(String name) {

        this.name = name;
    }

    public User() {
    }

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public User(String name, String password, Role role, String salt, String email, String restoreHash, OffsetDateTime validityPeriod) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.salt = salt;
        this.email = email;
        this.restoreHash = restoreHash;
        this.validityPeriod = validityPeriod;
    }

    public User(UUID id, String name) {
        this.id = id;
        this.name = name;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRestoreHash() {
        return restoreHash;
    }

    public void setRestoreHash(String restoreHash) {
        this.restoreHash = restoreHash;
    }

    public OffsetDateTime getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(OffsetDateTime validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(password, user.password) &&
                role == user.role &&
                Objects.equals(salt, user.salt) &&
                Objects.equals(email, user.email) &&
                Objects.equals(restoreHash, user.restoreHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, role, salt, email, restoreHash);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", salt='" + salt + '\'' +
                ", email='" + email + '\'' +
                ", restoreHash='" + restoreHash + '\'' +
                '}';
    }
}
