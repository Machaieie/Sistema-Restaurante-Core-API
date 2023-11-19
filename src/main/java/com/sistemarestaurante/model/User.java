package com.sistemarestaurante.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "Usuario")
public class User extends  Pessoa{

    @Column(nullable = false, length = 50)
    private String username;

    @Column(length = 64)
    private String password;

    @Column(length = 50)
    private String sessionId;

    @Column(length = 64)
    private String textPassword;

    private String confirmationToken;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<UserRole> roles = new HashSet<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getTextPassword() {
        return textPassword;
    }

    public void setTextPassword(String textPassword) {
        this.textPassword = textPassword;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }
}
