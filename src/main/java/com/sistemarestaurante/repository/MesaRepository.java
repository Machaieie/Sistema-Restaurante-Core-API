package com.sistemarestaurante.repository;

import com.sistemarestaurante.model.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MesaRepository extends JpaRepository<Mesa, Long> {
}
