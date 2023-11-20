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
    private long id;

    private int lugares;
    private boolean estado;

    //TODO: Relacionamento entre mesa e pedidos (Lista de pedidos)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mesa", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;

    //TODO: id da reserva
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumLugares() {
        return lugares;
    }

    public void setNumLugares(int numLugares) {
        this.lugares = numLugares;
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
