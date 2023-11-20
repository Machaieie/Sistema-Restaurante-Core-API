package com.sistemarestaurante.mz.SistemaRestaurante.repository;

import com.sistemarestaurante.mz.SistemaRestaurante.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
