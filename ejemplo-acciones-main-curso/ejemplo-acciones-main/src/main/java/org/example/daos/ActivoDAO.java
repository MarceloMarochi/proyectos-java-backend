package org.example.daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.common.EntityManagerProvider;
import org.example.entities.Activo;

import java.util.List;

public class ActivoDAO {
    final private EntityManager em = EntityManagerProvider.getEntityManager();

    public ActivoDAO(){

    }

    public void save(Activo activo){
        em.getTransaction().begin();
        em.persist(activo);
        em.getTransaction().commit();
    }

    public Activo findById(Long activoId){
        return em.find(Activo.class, activoId);
    }
    public List<Activo> findAll(){
        TypedQuery<Activo> query = em.createQuery("SELECT a FROM Activo a", Activo.class);
        return query.getResultList();
    }

    public void update(Activo activo){
        em.getTransaction().begin();
        em.merge(activo);
        em.getTransaction().commit();
    }

    public void delete(Long activoId){
        em.getTransaction().begin();

        Activo activo = this.findById(activoId);

        if(activo != null){
            em.remove(activo);
        }

        em.getTransaction().commit();
    }
}
