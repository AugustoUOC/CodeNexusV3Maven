package com.codenexus.codenexusp4.controlador;

import jakarta.persistence.*;
import com.codenexus.codenexusp4.modelo.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.io.Serializable;
import java.util.List;

public class SocioJPAController implements Serializable {


    private EntityManagerFactory emf = null;

    public SocioJPAController(){
        emf = Persistence.createEntityManagerFactory("JPA_PU");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void agregarSocio(Socio socio) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(socio);
            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            em.close();
        }
    }

    public boolean eliminarSocioPorId(int idSocio) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Socio socio = em.find(Socio.class, idSocio);
            if (socio == null) {
                return false;
            }
            em.remove(socio);
            tx.commit();
            return true;
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            em.close();
        }
    }

    public Socio buscarSocioPorId(int idSocio) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Socio.class, idSocio);
        } finally {
            em.close();
        }
    }

    public List<Socio> obtenerListaSocios() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Socio> query = em.createQuery("SELECT s FROM Socio s", Socio.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public ObservableList<Socio> obtenerListaSociosPorTipo(String tipoSocio) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Socio> query = em.createQuery("SELECT s FROM Socio s WHERE s.tipoSocio = :tipoSocio", Socio.class);
            query.setParameter("tipoSocio", tipoSocio);
            List<Socio> resultList = query.getResultList();
            return FXCollections.observableArrayList(resultList);
        } finally {
            em.close();
        }
    }




    public void mostrarListaSociosPorTipo(List<Socio> listaSocios) {
        if (!listaSocios.isEmpty()) {
            System.out.println("Lista de Socios:");
            for (Socio socio : listaSocios) {
                if (socio != null) {
                    System.out.println("ID: " + socio.getIdSocio() + ", Nombre: " + socio.getNombre() +
                            ", Tipo de Socio: " + socio.getTipoSocio());

                    if (socio instanceof Estandar) {
                        Estandar estandar = (Estandar) socio;
                        System.out.println("       NIF: " + estandar.getNif() +
                                ", Seguro: " + (estandar.getSeguroContratado() != null ? estandar.getSeguroContratado().getTipo() : "N/A") +
                                ", Precio del Seguro: " + (estandar.getSeguroContratado() != null ? estandar.getSeguroContratado().getPrecio() : "N/A"));
                    } else if (socio instanceof Federado) {
                        Federado federado = (Federado) socio;
                        System.out.println("       NIF: " + federado.getNif() +
                                ", Federación: " + (federado.getFederacion() != null ? federado.getFederacion().getNombre() : "N/A"));
                    } else if (socio instanceof Infantil) {
                        Infantil infantil = (Infantil) socio;
                        System.out.println("       ID Tutor: " + infantil.getIdTutor());
                    }
                } else {
                    System.out.println("Se encontró un socio null en la lista");
                }
            }
        } else {
            System.out.println("No se encontraron Socios");
        }
    }

}

