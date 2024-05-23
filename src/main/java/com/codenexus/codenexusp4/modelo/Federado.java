package com.codenexus.codenexusp4.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "federado")
public class Federado extends Socio {
    @Column(name = "nif")
    private String nif;

    @ManyToOne
    @JoinColumn(name = "idFederacion", referencedColumnName = "idFederacion")
    private Federacion federacion;

    // Constructor vacío requerido por JPA
    public Federado() {
        super();
    }

    // Constructor con todos los atributos
    public Federado(int idSocio, String nombre, Federacion federacion, String nif) {
        super(idSocio, nombre, "Federado");
        this.federacion = federacion;
        this.nif = nif;
    }

    // Getters y setters
    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public Federacion getFederacion() {
        return federacion;
    }

    public void setFederacion(Federacion federacion) {
        this.federacion = federacion;
    }

    @Override
    public String toString() {
        return "Socio Federado con id número: " + getIdSocio() + ", llamado: " + getNombre() + ", con NIF: " + nif + ".\n" +
                "Pertenece a la federación: " + (federacion != null ? federacion.getNombre() : "Sin federación") + ".";
    }
}
