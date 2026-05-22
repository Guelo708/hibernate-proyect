package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "property_types")

public class PropertyType {

    @Id // Define la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremental
    private Long idTipoPredio;
    private String descripcion;

    public PropertyType() {
    }

    public PropertyType(String descripcion) {

        this.descripcion = descripcion;
    }

    public Long getIdTipoPredio() {
        return idTipoPredio;
    }

    public void setIdTipoPredio(Long idTipoPredio) {
        this.idTipoPredio = idTipoPredio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "PropertyType [idTipoPredio=" + idTipoPredio + ", descripcion=" + descripcion + "]";
    }

}
