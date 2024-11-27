package utn.frc.bka.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import utn.frc.bka.common.EntintyMangaerProvider;
import utn.frc.bka.entity.Estudiante;
import utn.frc.bka.entity.Inasistencia;

import java.util.List;

public class EstudianteRepository {

    private final EntityManager manager = EntintyMangaerProvider.getInstance();

    public EstudianteRepository() {

    }

    public void save(Estudiante estudiante) {
        manager.getTransaction().begin();
        manager.persist(estudiante);
        manager.getTransaction().commit();
    }

    public Estudiante findById(Long id){
        return manager.find(Estudiante.class, id);
    }

    public List<Estudiante> findAll(){
        String jqpl = "SELECT e FROM Estudiante e";
        TypedQuery<Estudiante> query = manager.createQuery(jqpl, Estudiante.class);
        return query.getResultList();
    }

    public void update(Estudiante estudiante){
        manager.getTransaction().begin();
        manager.merge(estudiante);
        manager.getTransaction().commit();
    }

    public void delete(Long id){
        manager.getTransaction().begin();
        Estudiante estudiante = findById(id);
        if (estudiante != null){
            manager.remove(estudiante);
        }
        manager.getTransaction().commit();
    }

}
