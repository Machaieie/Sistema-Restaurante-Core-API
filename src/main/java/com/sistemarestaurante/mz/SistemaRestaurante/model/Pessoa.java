package com.sistemarestaurante.model;

import jakarta.persistence.*;

import java.io.Serializable;



@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "pessoa", discriminatorType = DiscriminatorType.STRING)
public abstract  class Pessoa implements Serializable {
    private static long serialVersionUID=1L;
    @Id
    private long id;
    private  String nome;
    private String mail;
    private String telefone;

    public Pessoa(long id, String nome, String mail, String telefone) {
        this.id = id;
        this.nome = nome;
        this.mail = mail;
        this.telefone = telefone;
    }

    public Pessoa() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
