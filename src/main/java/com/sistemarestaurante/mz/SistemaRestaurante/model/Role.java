package com.sistemarestaurante.mz.SistemaRestaurante.model;

public enum Role {
    ROLE_ADMIN("admin"),

    ROLE_EMPLOYEE("employee");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
