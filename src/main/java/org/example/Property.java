package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "properties") // Nombre de la tabla en la DB
public class Property {

    @Id // Define la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremental
    private Long codigoPredio;
    private Long idTipoPredio;
    private String cedulaPropietario;
    private Long idSector;

    public Property() {
    }

    public Property(Long idTipoPredio, String cedulaPropietario, Long idSector) {
        this.idTipoPredio = idTipoPredio;
        this.cedulaPropietario = cedulaPropietario;
        this.idSector = idSector;
    }

    public Long getCodigoPredio() {
        return codigoPredio;
    }

    public void setCodigoPredio(Long codigoPredio) {
        this.codigoPredio = codigoPredio;
    }

    public Long getIdTipoPredio() {
        return idTipoPredio;
    }

    public void setIdTipoPredio(Long idTipoPredio) {
        this.idTipoPredio = idTipoPredio;
    }

    public String getCedulaPropietario() {
        return cedulaPropietario;
    }

    public void setCedulaPropietario(String cedulaPropietario) {
        this.cedulaPropietario = cedulaPropietario;
    }

    public Long getIdSector() {
        return idSector;
    }

    public void setIdSector(Long idSector) {
        this.idSector = idSector;
    }

    @Override
    public String toString() {
        return "Property [codigoPredio=" + codigoPredio + ", idTipoPredio=" + idTipoPredio + ", cedulaPropietario="
                + cedulaPropietario + ", idSector=" + idSector + "]";
    }

}
