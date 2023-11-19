package com.sistemarestaurante.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Reserva")
public class Reserva implements Serializable {
    private static long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Date dia;
    private Time hora;

    //TODO: Relacionamento entre Reserva e mesa
    // lista de mesas associadas a reserva
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reserva", cascade = CascadeType.ALL)
    private List<Mesa> mesas;

    //TODO:  Relacionamento entre cliente e reserva
    // Um Clinte pode ter varias reservas
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    //TODO: Relacionamento entre pagamento e reserva
    // Uma reserva pode ter varios pagamentos
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reserva", cascade = CascadeType.ALL)
    private List<Pagamento> pagamentos;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public List<Mesa> getMesas() {
        return mesas;
    }

    public void setMesas(List<Mesa> mesas) {
        this.mesas = mesas;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }
}
