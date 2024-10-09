package ar.frc.utn.backend.repositorios;

import ar.frc.utn.backend.connectionManager.EntityManagerProvider;
import ar.frc.utn.backend.entidades.Inasistencia;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class InnasistenciaRepository {

    private final EntityManager manager = EntityManagerProvider.getInstance();

    public void save(Inasistencia inasistencia){
        manager.getTransaction().begin();
        manager.persist(inasistencia);
        manager.getTransaction().commit();
    }

    public Inasistencia findById(Long id){
        return manager.find(Inasistencia.class, id);
    }

    public List<Inasistencia> findAll(){
        String spql = "SELECT i FROM Inasistencia i";
        TypedQuery<Inasistencia> query = manager.createQuery(spql, Inasistencia.class);
        return query.getResultList();
    }

    public void update(Inasistencia inasistencia){
        manager.getTransaction().begin();
        manager.merge(inasistencia);
        manager.getTransaction().commit();
    }

    public void delete(Long id){
        manager.getTransaction().begin();
        Inasistencia inasistencia = findById(id);
        if (inasistencia != null) {
            manager.remove(inasistencia);
        }
        manager.getTransaction().commit();
    }

}
