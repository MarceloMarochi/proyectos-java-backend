package ar.frc.utn.backend.repositorios;

import ar.frc.utn.backend.connectionManager.EntityManagerProvider;
import ar.frc.utn.backend.entidades.Alumno;
import jakarta.persistence.EntityManager;
import ar.frc.utn.backend.entidades.Tipo;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class TipoRepository {

    private final EntityManager manager = EntityManagerProvider.getInstance();

    public Tipo save(Tipo tipo){
        manager.getTransaction().begin();
        manager.persist(tipo);
        manager.getTransaction().commit();
        return tipo;
    }

    public Tipo findById(Long id){
        return manager.find(Tipo.class, id);
    }

    public Tipo findByType(String tipo){
        String jpql = "SELECT t FROM Tipo t WHERE t.nombre = :tipo";
        try {
            return manager.createQuery(jpql, Tipo.class)
                    .setParameter("tipo", tipo)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Tipo> findAll(){
        String spql = "SELECT t FROM Tipo t";
        TypedQuery<Tipo> query = manager.createQuery(spql, Tipo.class);
        return query.getResultList();
    }

    public void update(Tipo tipo){
        manager.getTransaction().begin();
        manager.merge(tipo);
        manager.getTransaction().commit();
    }

    public void delete(Long id){
        manager.getTransaction().begin();
        Tipo tipo = findById(id);
        if (tipo != null){
            manager.remove(tipo);
        }
        manager.getTransaction().commit();
    }

}
