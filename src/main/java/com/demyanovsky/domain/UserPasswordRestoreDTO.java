package com.demyanovsky.domain;

import java.util.Objects;

public class UserPasswordRestoreDTO {
    private String password;
    private String confirmPassword;

    public UserPasswordRestoreDTO() {
    }

    public UserPasswordRestoreDTO(String password, String confirmPassword) {
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPasswordRestoreDTO that = (UserPasswordRestoreDTO) o;
        return Objects.equals(password, that.password) &&
                Objects.equals(confirmPassword, that.confirmPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password, confirmPassword);
    }

    @Override
    public String toString() {
        return "UserPasswordRestoreDTO{" +
                "password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
