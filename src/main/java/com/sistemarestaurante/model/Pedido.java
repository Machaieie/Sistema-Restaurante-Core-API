package com.sistemarestaurante.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.security.SecureRandomParameters;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Pedido")
public class Pedido implements Serializable {
    private static long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    //TODO: relacionamento entre pedido e mesa
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mesa_id")
    private Mesa mesa;


    //TODO relacionamento entre pedido e itens de pedido
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itemPedidos;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public List<ItemPedido> getItemPedidos() {
        return itemPedidos;
    }

    public void setItemPedidos(List<ItemPedido> itemPedidos) {
        this.itemPedidos = itemPedidos;
    }
}
