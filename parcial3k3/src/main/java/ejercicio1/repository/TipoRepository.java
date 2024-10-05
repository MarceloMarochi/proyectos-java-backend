package ejercicio1.repository;

import ejercicio1.common.EntityManagerProvider;
import ejercicio1.entiti.Estudiante;
import ejercicio1.entiti.Tipo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class TipoRepository {
    private final EntityManager manager = EntityManagerProvider.getInstance();

    public TipoRepository() {

    }

    public void save(Tipo tipo) {
        manager.getTransaction().begin();
        manager.merge(tipo);
        manager.getTransaction().commit();
    }

    public Tipo findById(Long id) {
        return manager.find(Tipo.class, id);
    }

    public List<Tipo> findAll() {
        String jpql = "SELECT t FROM Tipo t";
        TypedQuery<Tipo> query = manager.createQuery(jpql, Tipo.class);
        return query.getResultList();
    }

    public void update(Tipo estudiante) {
        manager.getTransaction().begin();
        manager.merge(estudiante);
        manager.getTransaction().commit();
    }

    public void delete (Long estudianteId) {
        manager.getTransaction().begin();
        Tipo estudiante2 = findById(estudianteId);
        if (estudiante2 != null) {
            manager.remove(estudiante2);
        }
        manager.getTransaction().commit();
    }
}
