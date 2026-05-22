package org.example;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremental
private Long idPago;
private Long idFactura;
private Date fechaPago;
private Double valorPagado;
private Long idMetodoPago;

public Payment() {
}

public Payment(Long idFactura, Date fechaPago, Double valorPagado, Long idMetodoPago) {
    this.idFactura = idFactura;
    this.fechaPago = fechaPago;
    this.valorPagado = valorPagado;
    this.idMetodoPago = idMetodoPago;

}

public Long getIdPago() {
    return idPago;
}

public void setIdPago(Long idPago) {
    this.idPago = idPago;
}

public Long getIdFactura() {
    return idFactura;
}

public void setIdFactura(Long idFactura) {
    this.idFactura = idFactura;
}

public Date getFechaPago() {
    return fechaPago;
}

public void setFechaPago(Date fechaPago) {
    this.fechaPago = fechaPago;
}

public Double getValorPagado() {
    return valorPagado;
}

public void setValorPagado(Double valorPagado) {
    this.valorPagado = valorPagado;
}

public Long getIdMetodoPago() {
    return idMetodoPago;
}

public void setIdMetodoPago(Long idMetodoPago) {
    this.idMetodoPago = idMetodoPago;
}

@Override
public String toString() {
    return "Payment [idPago=" + idPago + ", idFactura=" + idFactura + ", fechaPago=" + fechaPago + ", valorPagado="
            + valorPagado + ", idMetodoPago=" + idMetodoPago + "]";
}




}
