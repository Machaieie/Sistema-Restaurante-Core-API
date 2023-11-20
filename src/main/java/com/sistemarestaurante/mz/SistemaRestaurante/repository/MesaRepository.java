package com.sistemarestaurante.repository;

import com.sistemarestaurante.model.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {
}
