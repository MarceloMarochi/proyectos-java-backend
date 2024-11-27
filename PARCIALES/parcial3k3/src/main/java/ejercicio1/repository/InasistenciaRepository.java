package ejercicio1.repository;

import ejercicio1.common.EntityManagerProvider;
import ejercicio1.entiti.Inasistencia;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class InasistenciaRepository {
    private final EntityManager manager = EntityManagerProvider.getInstance();

    public InasistenciaRepository() {
    }

    public void save(Inasistencia inasistencia) {
        manager.getTransaction().begin();
        manager.merge(inasistencia);
        manager.getTransaction().commit();
    }

    public Inasistencia findById(Long id) {
        return manager.find(Inasistencia.class, id);
    }

    public List<Inasistencia> findAll() {
        String jpql = "SELECT i FROM Inasistencia i";
        TypedQuery<Inasistencia> query = manager.createQuery(jpql, Inasistencia.class);
        return query.getResultList();
    }

    public void update(Inasistencia inasistencia) {
        manager.getTransaction().begin();
        manager.merge(inasistencia);
        manager.getTransaction().commit();
    }

    public void delete (Long inasistenciaId) {
        manager.getTransaction().begin();
        Inasistencia inasistencia2 = findById(inasistenciaId);
        if (inasistencia2 != null) {
            manager.remove(inasistencia2);
        }
        manager.getTransaction().commit();
    }


}
