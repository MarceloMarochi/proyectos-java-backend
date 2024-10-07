package utn.frc.bka.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import utn.frc.bka.common.EntintyMangaerProvider;
import utn.frc.bka.entity.Estudiante;
import utn.frc.bka.entity.Inasistencia;
import utn.frc.bka.entity.Tipo;

import java.util.List;

public class TipoRepository {

    private final EntityManager manager = EntintyMangaerProvider.getInstance();

    public TipoRepository() {

    }

    public void save(Tipo tipo) {
        manager.getTransaction().begin();
        manager.persist(tipo);
        manager.getTransaction().commit();
    }

    public Tipo findById(Long id){
        return manager.find(Tipo.class, id);
    }

    public List<Tipo> findAll(){
        String jqpl = "SELECT t FROM Tipo t";
        TypedQuery<Tipo> query = manager.createQuery(jqpl, Tipo.class);
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
