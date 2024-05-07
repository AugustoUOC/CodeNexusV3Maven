package org.CodeNexus.controlador;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.CodeNexus.modelo.Estandar;
import org.CodeNexus.modelo.Seguro;

public class SeguroJPAController {

    private EntityManagerFactory emf = null;

    public SeguroJPAController(){
        emf = Persistence.createEntityManagerFactory("JPA_PU");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Seguro obtenerSeguro(int idSeguro) {
        EntityManager em = getEntityManager();
        try {
            // Utilizamos el método find para obtener el Seguro directamente por su ID
            // Devuelve el seguro encontrado
            return em.find(Seguro.class, idSeguro);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void actualizarSeguroDeSocio(Estandar estandar) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            // Aseguramos que estandar y su seguro asociado estén gestionados
            Estandar managedEstandar = em.merge(estandar);
            Seguro seguroContratado = managedEstandar.getSeguroContratado();
            if (!em.contains(seguroContratado)) {
                seguroContratado = em.merge(seguroContratado);
            }
            managedEstandar.setSeguroContratado(seguroContratado);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Actualización del seguro fallida: " + e.getMessage(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
