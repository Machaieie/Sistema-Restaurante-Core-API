package com.sistemarestaurante.repository;

import com.sistemarestaurante.model.Cliente;
import com.sistemarestaurante.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReservaRepository extends JpaRepository<Reserva, UUID> {
}
