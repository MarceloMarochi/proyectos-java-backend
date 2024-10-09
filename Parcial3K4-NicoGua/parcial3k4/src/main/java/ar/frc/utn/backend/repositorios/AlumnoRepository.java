package ar.frc.utn.backend.repositorios;

import ar.frc.utn.backend.connectionManager.EntityManagerProvider;
import ar.frc.utn.backend.entidades.Alumno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class AlumnoRepository {

    private final EntityManager manager = EntityManagerProvider.getInstance();

    public Alumno save(Alumno alumno){
        manager.getTransaction().begin();
        manager.persist(alumno);
        manager.getTransaction().commit();
        return alumno;
    }

    public Alumno findById(Long id){
        return manager.find(Alumno.class, id);
    }

    public Alumno findByName(String nombre){
        String jpql = "SELECT a FROM Alumno a WHERE a.nombre = :nombre";
        try {
            return manager.createQuery(jpql, Alumno.class)
                    .setParameter("nombre", nombre)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Alumno> findAll(){
        String jpql = "SELECT a FROM Alumno a";
        TypedQuery<Alumno> query = manager.createQuery(jpql, Alumno.class);
        return query.getResultList();
    }

    public void update(Alumno alumno){
        manager.getTransaction().begin();
        manager.merge(alumno);
        manager.getTransaction().commit();
    }

    public void delete(Long id){
        manager.getTransaction().begin();
        Alumno alumno = findById(id);
        if (id != null){
            manager.remove(alumno);
        }
        manager.getTransaction().commit();
    }

}
