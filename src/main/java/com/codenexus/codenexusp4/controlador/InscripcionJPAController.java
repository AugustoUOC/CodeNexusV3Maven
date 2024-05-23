package com.codenexus.codenexusp4.controlador;

import jakarta.persistence.*;
import com.codenexus.codenexusp4.modelo.Inscripcion;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class InscripcionJPAController implements Serializable {

    private EntityManagerFactory emf = null;

    public InscripcionJPAController(){
        emf = Persistence.createEntityManagerFactory("JPA_PU");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void agregarInscripcion(Inscripcion inscripcion) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(inscripcion);
            em.getTransaction().commit();
            System.out.println("La Inscripción se ha insertado correctamente.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al insertar Inscripción: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public Inscripcion buscarInscripcionPorID(int idInscripcion) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Inscripcion.class, idInscripcion);
        } finally {
            em.close();
        }
    }

    public void eliminarInscripcion(int idInscripcion) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Inscripcion inscripcion = em.find(Inscripcion.class, idInscripcion);
            if (inscripcion != null) {
                em.remove(inscripcion);
                em.getTransaction().commit();
                System.out.println("Inscripción eliminada correctamente.");
            } else {
                System.out.println("No se ha podido eliminar la Inscripción con ID: " + idInscripcion);
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al eliminar la inscripción: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Inscripcion> obtenerListaInscripciones() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Inscripcion> query = em.createQuery("SELECT i FROM Inscripcion i", Inscripcion.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Inscripcion> obtenerInscripcionesporSocio(int idSocio) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Inscripcion> query = em.createQuery("SELECT i FROM Inscripcion i WHERE i.idSocio = :idSocio", Inscripcion.class);
            query.setParameter("idSocio", idSocio);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Inscripcion> obtenerInscripcionesFiltroFechas(Date fechaInicio, Date fechaFin) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Inscripcion> query = em.createQuery("SELECT i FROM Inscripcion i WHERE i.fechaInscripcion BETWEEN :fechaInicio AND :fechaFin ORDER BY i.fechaInscripcion", Inscripcion.class);
            query.setParameter("fechaInicio", fechaInicio, TemporalType.DATE);
            query.setParameter("fechaFin", fechaFin, TemporalType.DATE);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Inscripcion> obtenerInscripcionesPorSocioYFechas(int idSocio, java.sql.Date fechaInicio, java.sql.Date fechaFin) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT i FROM Inscripcion i WHERE i.idSocio = :idSocio AND i.fechaInscripcion BETWEEN :fechaInicio AND :fechaFin", Inscripcion.class)
                    .setParameter("idSocio", idSocio)
                    .setParameter("fechaInicio", fechaInicio)
                    .setParameter("fechaFin", fechaFin)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public void mostrarListaInscripciones(List<Inscripcion> ListaInscripcion) {
        if (!ListaInscripcion.isEmpty()) {
            System.out.println("Lista de Inscripciones:");
            for (Inscripcion ins : ListaInscripcion) {
                System.out.println("ID: " + ins.getIdInscripcion() + ", ID Excursion asociada: " + ins.getIdExcursion() +
                        ", Fecha: " + ins.getFechaInscripcion() + ", ID Socio participante: " + ins.getIdSocio());
            }
        } else {
            System.out.println("No se encontraron excursiones.");
        }
    }


}
