package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sectors")
public class Sector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSector;
    private String nombreSector;
    private String descripcionSector;

    public Sector() {
    }

    public Sector(String nombreSector, String descripcionSector) {
        this.nombreSector = nombreSector;
        this.descripcionSector = descripcionSector;
    }

    public Long getIdSector() {
        return idSector;
    }

    public void setIdSector(Long idSector) {
        this.idSector = idSector;
    }

    public String getNombreSector() {
        return nombreSector;
    }

    public void setNombreSector(String nombreSector) {
        this.nombreSector = nombreSector;
    }

    public String getDescripcionSector() {
        return descripcionSector;
    }

    public void setDescripcionSector(String descripcionSector) {
        this.descripcionSector = descripcionSector;
    }

    @Override
    public String toString() {
        return "Sector [idSector=" + idSector + ", nombreSector=" + nombreSector + ", descripcionSector="
                + descripcionSector + "]";
    }

}
