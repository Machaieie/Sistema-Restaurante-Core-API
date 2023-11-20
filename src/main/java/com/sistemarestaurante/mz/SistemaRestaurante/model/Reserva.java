package com.sistemarestaurante.mz.SistemaRestaurante.model;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String codigoReserva;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dia;

    @Temporal(TemporalType.TIME)
    @JsonFormat(pattern = "HH:mm:ss")
    private Time hora;
    private  String nomeCliente;
    private String mailCliente;
    private String telefoneCliente;
    
    private int NumMesas;

    private double taxaRserva;
    

   

    public int getNumMesas() {
        return NumMesas;
    }

    public void setNumMesas(int numMesas) {
        NumMesas = numMesas;
    }

    public double getTaxaRserva() {
        return taxaRserva;
    }

    public void setTaxaRserva(double taxaRserva) {
        this.taxaRserva = taxaRserva;
    }

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

    public String getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
    }
    
}
