package com.sistemarestaurante.mz.SistemaRestaurante.repository;

import com.sistemarestaurante.mz.SistemaRestaurante.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
