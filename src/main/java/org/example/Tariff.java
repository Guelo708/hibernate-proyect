package org.example;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tariffs") // Nombre de la tabla en la DB
public class Tariff {

    @Id // Define la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremental
    private Long idTarifa;
    private Long idTipoPredio;
    private Double valor;
    private Date fechaInicio;
    private Date fechaFin;



    public Tariff() {
    }
public Tariff(Long idTipoPredio, Double valor, Date fechaInicio, Date fechaFin) {
    this.idTipoPredio = idTipoPredio;
    this.valor = valor;
    this.fechaInicio = fechaInicio;
    this.fechaFin = fechaFin;
}

    public Long getIdTarifa() {
        return idTarifa;
    }

    public void setIdTarifa(Long idTarifa) {
        this.idTarifa = idTarifa;
    }

    public Long getIdTipoPredio() {
        return idTipoPredio;
    }

    public void setIdTipoPredio(Long idTipoPredio) {
        this.idTipoPredio = idTipoPredio;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return "Tariff [idTarifa=" + idTarifa + ", idTipoPredio=" + idTipoPredio + ", valor=" + valor + ", fechaInicio="
                + fechaInicio + ", fechaFin=" + fechaFin + "]";
    }

  

}
