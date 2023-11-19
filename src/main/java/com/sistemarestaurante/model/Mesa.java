package com.sistemarestaurante.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Mesa")
public class Mesa implements Serializable {
    private static long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private int numLugares;
    private boolean estado;

    //TODO: Relacionamento entre mesa e pedidos (Lista de pedidos)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mesa", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;

    //TODO: id da reserva
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getNumLugares() {
        return numLugares;
    }

    public void setNumLugares(int numLugares) {
        this.numLugares = numLugares;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
}
