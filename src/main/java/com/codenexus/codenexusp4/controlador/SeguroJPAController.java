package com.codenexus.codenexusp4.controlador;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import com.codenexus.codenexusp4.modelo.Estandar;
import com.codenexus.codenexusp4.modelo.Seguro;
import jakarta.persistence.TypedQuery;

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


    public Seguro obtenerSeguroPorTipo(String tipoSeguro) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Seguro> query = em.createQuery("SELECT s FROM Seguro s WHERE s.tipo = :tipo", Seguro.class);
            query.setParameter("tipo", tipoSeguro);
            return query.getSingleResult();
        } catch (Exception e) {
            return null; // Maneja la excepción según tus necesidades
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
