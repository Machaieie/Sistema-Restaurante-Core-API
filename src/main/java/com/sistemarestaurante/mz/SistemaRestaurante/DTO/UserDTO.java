package com.sistemarestaurante.mz.SistemaRestaurante.DTO;

import com.sistemarestaurante.mz.SistemaRestaurante.model.Role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record UserDTO(@NotBlank String usuario, @NotBlank String senha, @NotEmpty Role role) {

    
}
