package com.demyanovsky.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

public class UserPasswordRestoreDTO {
    @NotNull
    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#*&$%!_=+^<>-]).{6,20})", message = "Password have to At least 8 chars, " +
            "contains at least one digit, contains at least one lower alpha char and one upper alpha char " +
            "contains at least one char within a set of special chars (@#%$^ etc.), does not contain space, tab, etc.")
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
