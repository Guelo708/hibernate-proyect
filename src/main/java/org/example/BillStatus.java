package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bill_statuses")
public class BillStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstadoFactura;
    private String Descripcion;

    public BillStatus() {
    }

    public BillStatus(String descripcion) {
        Descripcion = descripcion;
    }

    public Long getIdEstadoFactura() {
        return idEstadoFactura;
    }

    public void setIdEstadoFactura(Long idEstadoFactura) {
        this.idEstadoFactura = idEstadoFactura;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    @Override
    public String toString() {
        return "BillStatus [idEstadoFactura=" + idEstadoFactura + ", Descripcion=" + Descripcion + "]";
    }

}
