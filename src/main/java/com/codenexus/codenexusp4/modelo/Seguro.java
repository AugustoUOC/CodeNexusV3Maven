package com.codenexus.codenexusp4.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "seguro") // Asume que la tabla en la base de datos se llama 'seguros'
public class Seguro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSeguro;

    @Column(name = "seguroContratado", nullable = false)
    private String tipo;

    @Column(name = "precio", nullable = false)
    private double precio;

    // Constructor vacío necesario para JPA
    public Seguro() {
    }

    // Constructor completo
    public Seguro(int idSeguro, String tipo, double precio) {
        this.idSeguro = idSeguro;
        this.tipo = tipo;
        this.precio = precio;
    }

    // Constructor sin ID para crear nuevas instancias sin un ID predefinido
    public Seguro(String tipo, double precio) {
        this.tipo = tipo;
        this.precio = precio;
    }

    // Getters y Setters
    public int getIdSeguro() {
        return idSeguro;
    }

    public void setIdSeguro(int idSeguro) {
        this.idSeguro = idSeguro;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Seguro{" +
                "idSeguro=" + idSeguro +
                ", tipo='" + tipo + '\'' +
                ", precio=" + precio +
                '}';
    }
}

