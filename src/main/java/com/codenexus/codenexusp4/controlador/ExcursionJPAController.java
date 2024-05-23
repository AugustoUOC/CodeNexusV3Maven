package com.codenexus.codenexusp4.controlador;

import jakarta.persistence.*;
import com.codenexus.codenexusp4.modelo.Excursion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class ExcursionJPAController implements Serializable {

    private EntityManagerFactory emf = null;

    public ExcursionJPAController(){
        emf = Persistence.createEntityManagerFactory("JPA_PU");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void agregarExcursion(Excursion excursion) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(excursion);
            em.getTransaction().commit();
            System.out.println("Excursión agregada correctamente\n");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Error al insertar la excursión: " + e.getMessage());
        } finally {
            em.close();
        }
    }


    public boolean eliminarExcursion(int idExcursion) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Excursion excursion = em.find(Excursion.class, idExcursion);
            if (excursion != null) {
                em.remove(excursion);
                em.getTransaction().commit();
                System.out.println("Excursión eliminada correctamente.");
                return true;
            } else {
                System.out.println("No se encontró la excursión con ID: " + idExcursion);
                return false;
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Error al eliminar la excursión: " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }



    public ObservableList<Excursion> obtenerListaExcursionesFiltroFecha(java.sql.Date fechaInicio, java.sql.Date fechaFin) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Excursion> query = em.createQuery("SELECT e FROM Excursion e WHERE e.fechaExcursion BETWEEN :fechaInicio AND :fechaFin ORDER BY e.fechaExcursion", Excursion.class);
            query.setParameter("fechaInicio", fechaInicio, TemporalType.DATE);
            query.setParameter("fechaFin", fechaFin, TemporalType.DATE);
            return FXCollections.observableArrayList(query.getResultList());
        } finally {
            em.close();
        }
    }


    public ObservableList<Excursion> obtenerListaExcursiones() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Excursion> query = em.createQuery("SELECT e FROM Excursion e", Excursion.class);
            return FXCollections.observableArrayList(query.getResultList());
        } finally {
            em.close();
        }
    }


    public void mostrarListaExcursiones(List<Excursion> listaExcursiones) {
        if (!listaExcursiones.isEmpty()) {
            System.out.println("Lista de Excursiones:");
            for (Excursion exc : listaExcursiones) {
                System.out.println("ID: " + exc.getIdExcursion() + ", Descripción: " + exc.getDescripcion() +
                        ", Fecha: " + exc.getFechaExcursion() + ", Duración (días): " + exc.getDuracionDias() +
                        ", Precio: $" + exc.getPrecioInscripcion());
            }
        } else {
            System.out.println("No se encontraron excursiones.");
        }
    }


    public Excursion buscarExcursionPorId(int idExcursion) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Excursion.class, idExcursion);
        } finally {
            em.close();
        }
    }


}
