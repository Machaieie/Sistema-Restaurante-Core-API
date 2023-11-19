package com.sistemarestaurante.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "Pagamentos")
public class Pagamento implements Serializable {
    private static long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Date DataPagamento;

    private double ValorTotal;

    //TODO: chave estrangeira da reserva
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getDataPagamento() {
        return DataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        DataPagamento = dataPagamento;
    }

    public double getValorTotal() {
        return ValorTotal;
    }

    public void setValorTotal(double valorTotal) {
        ValorTotal = valorTotal;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
}
