package com.codenexus.codenexusp4.modelo;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "socio")
@Inheritance(strategy = InheritanceType.JOINED)
public class Socio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSocio")
    private int idSocio;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "tipoSocio")
    public String tipoSocio;

    // Constructor vacío
    public Socio() {
        this(0, "", "");
    }

    // Constructor con todos los atributos
    public Socio(int idSocio, String nombre, String tipoSocio) {
        this.idSocio = idSocio;
        this.nombre = nombre;
        this.tipoSocio = tipoSocio;
    }

    // Getters y setters
    public int getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoSocio() {
        return tipoSocio;
    }

    public void setTipoSocio(String tipoSocio) {
        this.tipoSocio = tipoSocio;
    }

    // Métodos equals y hashCode basados en idSocio
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Socio socio = (Socio) o;
        return idSocio == socio.idSocio;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSocio);
    }

    // Método toString para imprimir los detalles del socio
    @Override
    public String toString() {
        return "Socio " + tipoSocio + " creado.\n" +
                "Su id es el número " + idSocio +
                " y se llama " + nombre + ".";
    }
}

