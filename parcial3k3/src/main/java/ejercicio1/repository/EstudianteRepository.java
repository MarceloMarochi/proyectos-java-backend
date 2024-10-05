package ejercicio1.repository;

import ejercicio1.common.EntityManagerProvider;
import ejercicio1.entiti.Estudiante;
import ejercicio1.entiti.Inasistencia;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class EstudianteRepository {
    private final EntityManager manager = EntityManagerProvider.getInstance();

    public EstudianteRepository() {

    }

    public void save(Estudiante estudiante) {
        manager.getTransaction().begin();
        manager.merge(estudiante);
        manager.getTransaction().commit();
    }

    public Estudiante findById(Long id) {
        return manager.find(Estudiante.class, id);
    }

    public List<Estudiante> findAll() {
        String jpql = "SELECT e FROM Estudiante e";
        TypedQuery<Estudiante> query = manager.createQuery(jpql, Estudiante.class);
        return query.getResultList();
    }

    public void update(Estudiante estudiante) {
        manager.getTransaction().begin();
        manager.merge(estudiante);
        manager.getTransaction().commit();
    }

    public void delete (Long estudianteId) {
        manager.getTransaction().begin();
        Estudiante estudiante2 = findById(estudianteId);
        if (estudiante2 != null) {
            manager.remove(estudiante2);
        }
        manager.getTransaction().commit();
    }
}
