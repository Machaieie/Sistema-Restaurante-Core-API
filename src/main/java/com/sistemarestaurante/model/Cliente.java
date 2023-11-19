package com.sistemarestaurante.model;


import jakarta.persistence.*;
import org.springframework.data.repository.cdi.Eager;

import java.util.List;

@Entity
@Table(name = "Cliente")
public class Cliente extends  Pessoa{


    //TODO: Lista de reservas que provavelmente podem estar  associadas ao cliente
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Reserva> reservas;

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
}
