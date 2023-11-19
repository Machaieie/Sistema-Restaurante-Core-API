package com.sistemarestaurante.repository;

import com.sistemarestaurante.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
