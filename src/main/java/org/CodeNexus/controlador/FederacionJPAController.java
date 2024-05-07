package org.CodeNexus.controlador;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.*;
import org.CodeNexus.modelo.Federacion;
import org.CodeNexus.utilidad.Teclado;

import java.io.Serializable;
import java.util.List;

public class FederacionJPAController implements Serializable {

    private EntityManagerFactory emf = null;

    public FederacionJPAController(){
        emf = Persistence.createEntityManagerFactory("JPA_PU");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Federacion obtenerFederacion(int idFederacion) {
        EntityManager em = getEntityManager();
        try {
            // Utilizamos el método find para obtener la Federacion directamente por su ID
            return em.find(Federacion.class, idFederacion);
        } finally {
            em.close();
        }
    }

    public Federacion obtenerFederacionPorNombre(String nombreFederacion) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Federacion> query = em.createQuery("SELECT f FROM Federacion f WHERE f.nombre = :nombre", Federacion.class);
            query.setParameter("nombre", nombreFederacion);
            List<Federacion> federaciones = query.getResultList();
            if (!federaciones.isEmpty()) {
                return federaciones.get(0);
            } else {
                if (Teclado.confirmarAccion("Federación no encontrada, ¿desea crear una nueva?")) {
                    return crearFederacion(nombreFederacion);
                } else {
                    System.out.println("Creación de la Federación cancelada.");
                    return null;
                }
            }
        } finally {
            em.close();
        }
    }

    public Federacion crearFederacion(String nombreFederacion) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            Federacion nuevaFederacion = new Federacion();
            nuevaFederacion.setNombre(nombreFederacion);
            em.persist(nuevaFederacion);
            em.getTransaction().commit();
            return nuevaFederacion;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Crear federación falló", e);
        }
    }




}
