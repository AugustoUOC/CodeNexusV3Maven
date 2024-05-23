package com.codenexus.codenexusp4.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "federacion")
public class Federacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFederacion;

    @Column(name = "nombreFederacion", nullable = false)
    private String nombre;

    // Constructor vacío necesario para JPA
    public Federacion() {
    }

    // Constructor con todos los atributos
    public Federacion(int idFederacion, String nombre) {
        this.idFederacion = idFederacion;
        this.nombre = nombre;
    }

    // Getters y setters
    public int getIdFederacion() {
        return idFederacion;
    }

    public void setIdFederacion(int idFederacion) {
        this.idFederacion = idFederacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Método toString para imprimir los detalles de la federación
    @Override
    public String toString() {
        return "La federación se llama " + nombre + " y su id es el número " + idFederacion + ".";
    }
}

