package com.sistemarestaurante.mz.SistemaRestaurante.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class ReservaDTO {
    @NotBlank
    private String nomeCliente;

    @NotBlank
    private String mailCliente;

    @NotBlank
    private String telefoneCliente;

    private int NumMesas;

    private double taxaRserva;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date dia;

    @JsonFormat(pattern = "HH:mm:ss")
    @NotNull
    private Time hora;
}
