package com.codenexus.codenexusp4.modelo;

import jakarta.persistence.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "excursion")
public class Excursion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idExcursion;

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    @Temporal(TemporalType.DATE)
    @Column(name = "fechaExcursion")
    private Date fechaExcursion;

    @Column(name = "duracionDias")
    private int duracionDias;

    @Column(name = "precioInscripcion")
    private double precioInscripcion;

    // Constructor vacío
    public Excursion() {
    }

    // Constructor con todos los atributos
    public Excursion(int idExcursion, String descripcion, Date fechaExcursion, int duracionDias, double precioInscripcion) {
        this.idExcursion = idExcursion;
        this.descripcion = descripcion;
        this.fechaExcursion = fechaExcursion;
        this.duracionDias = duracionDias;
        this.precioInscripcion = precioInscripcion;
    }

    // Getters y setters
    public int getIdExcursion() {
        return idExcursion;
    }

    public void setIdExcursion(int idExcursion) {
        this.idExcursion = idExcursion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaExcursion() {
        return fechaExcursion;
    }

    public void setFechaExcursion(Date fechaExcursion) {
        this.fechaExcursion = fechaExcursion;
    }

    public int getDuracionDias() {
        return duracionDias;
    }

    public void setDuracionDias(int duracionDias) {
        this.duracionDias = duracionDias;
    }

    public double getPrecioInscripcion() {
        return precioInscripcion;
    }

    public void setPrecioInscripcion(double precioInscripcion) {
        this.precioInscripcion = precioInscripcion;
    }

    // Método toString para imprimir los detalles de la excursión
    @Override
    public String toString() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaTransformada = formatoFecha.format(fechaExcursion);
        return "Excursión con id : " + idExcursion + ".\n" +
                "Descripción = " + descripcion + ".\n" +
                "Fecha de la excursión = " + fechaTransformada + ", con una duración de " + duracionDias + " días.\n" +
                "El precio de inscripción es de " + precioInscripcion + " Euros.\n";
    }
}
