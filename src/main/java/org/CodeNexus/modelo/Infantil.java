package org.CodeNexus.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "infantil")
public class Infantil extends Socio {
    @Column(name = "idTutor")
    private int idTutor;

    public Infantil() {
        super();
    }

    public Infantil(int idSocio, String nombre, int idTutor) {
        super(idSocio, nombre, "Infantil");
        this.idTutor = idTutor;
    }

    public int getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(int idTutor) {
        this.idTutor = idTutor;
    }

    @Override
    public String toString() {
        return "Socio Infantil con id número: " + getIdSocio() + ", llamado: " + getNombre() + ".\n" +
                "Tiene un tutor asociado con el id número " + idTutor + ".";
    }
}
