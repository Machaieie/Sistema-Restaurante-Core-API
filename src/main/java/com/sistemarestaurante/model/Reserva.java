package com.sistemarestaurante.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "Reserva")
public class Reserva implements Serializable {
    private static long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Date dia;
    private Time hora;
    private  String nomeCliente;
    private String mailCliente;
    private String telefoneCliente;

    //TODO: Relacionamento entre Reserva e mesa
    // lista de mesas associadas a reserva
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reserva", cascade = CascadeType.ALL)
    private List<Mesa> mesas;

    //TODO: Relacionamento entre pagamento e reserva
    // Uma reserva pode ter varios pagamentos
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reserva", cascade = CascadeType.ALL)
    private List<Pagamento> pagamentos;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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



    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getMailCliente() {
        return mailCliente;
    }

    public void setMailCliente(String mailCliente) {
        this.mailCliente = mailCliente;
    }

    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }
}
