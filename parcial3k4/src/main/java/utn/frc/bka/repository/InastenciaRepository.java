package utn.frc.bka.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import utn.frc.bka.common.EntintyMangaerProvider;
import utn.frc.bka.entity.Inasistencia;

import java.util.List;

public class    InastenciaRepository {

    private final EntityManager manager = EntintyMangaerProvider.getInstance();

    public InastenciaRepository() {

    }

    public void save(Inasistencia inasistencia){
        manager.getTransaction().begin();
        manager.persist(inasistencia);
        manager.getTransaction().commit();
    }

    public Inasistencia findById(Long id){
        return manager.find(Inasistencia.class, id);
    }

    public List<Inasistencia> findAll(){
        String jqpl = "SELECT i FROM Inasistencia i";
        TypedQuery<Inasistencia> query = manager.createQuery(jqpl, Inasistencia.class);
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
        if (inasistencia != null){
            manager.remove(inasistencia);
        }
        manager.getTransaction().commit();
    }

}
