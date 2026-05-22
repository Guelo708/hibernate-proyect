package org.example;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremental
    private Long idFactura;
    private Long codigoPredio;
    private Date fechaEmision;
    private Double valorTarifa;
    private Date mesFacturado;
    private Date añoFacturado;
    private Long idEstado;

    public Bill() {
    }

    public Bill(Long codigoPredio, Date fechaEmision, Double valorTarifa, Date mesFacturado, Date añoFacturado,
            Long idEstado) {
        this.codigoPredio = codigoPredio;
        this.fechaEmision = fechaEmision;
        this.valorTarifa = valorTarifa;
        this.mesFacturado = mesFacturado;
        this.añoFacturado = añoFacturado;
        this.idEstado = idEstado;
    }

    public Long getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Long idFactura) {
        this.idFactura = idFactura;
    }

    public Long getCodigoPredio() {
        return codigoPredio;
    }

    public void setCodigoPredio(Long codigoPredio) {
        this.codigoPredio = codigoPredio;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Double getValorTarifa() {
        return valorTarifa;
    }

    public void setValorTarifa(Double valorTarifa) {
        this.valorTarifa = valorTarifa;
    }

    public Date getMesFacturado() {
        return mesFacturado;
    }

    public void setMesFacturado(Date mesFacturado) {
        this.mesFacturado = mesFacturado;
    }

    public Date getAñoFacturado() {
        return añoFacturado;
    }

    public void setAñoFacturado(Date añoFacturado) {
        this.añoFacturado = añoFacturado;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }

    @Override
    public String toString() {
        return "Bill [idFactura=" + idFactura + ", codigoPredio=" + codigoPredio + ", fechaEmision=" + fechaEmision
                + ", valorTarifa=" + valorTarifa + ", mesFacturado=" + mesFacturado + ", añoFacturado=" + añoFacturado
                + ", idEstado=" + idEstado + "]";
    }

}
