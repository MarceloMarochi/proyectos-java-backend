package org.example.daos;

import jakarta.persistence.EntityManager;
import org.example.common.EntityManagerProvider;
import org.example.dtos.LanguageStatistics;
import org.example.entities.Language;
import org.example.entities.Repository;
import org.example.entities.User;

import java.util.List;

public class LanguageDAO {
    private final EntityManager entityManager = EntityManagerProvider.getEntityManager();

    public LanguageDAO(){

    }

    public Language save(Language language){
        entityManager.getTransaction().begin();
        entityManager.persist(language);
        entityManager.getTransaction().commit();
        return language;
    }

    public List<Language> findAll(){
        return entityManager.createQuery("SELECT l FROM Language l", Language.class).getResultList();
    }

    public Language findById(Long id){
        return entityManager.find(Language.class, id);
    }

    public Language findByName(String name){
        String query = "SELECT l FROM Language l WHERE l.name = :name";

        try {
            return entityManager.createQuery(query, Language.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    // Metodo para obtener estadísticas de lenguajes
    public List<LanguageStatistics> getLanguageStatistics() {
        String query = "SELECT new org.example.dtos.LanguageStatistics(l.name, COUNT(r), COALESCE(SUM(r.stars), 0)) " +
                "FROM Repository r JOIN r.languages l " +
                "GROUP BY l.name";

        return entityManager.createQuery(query, LanguageStatistics.class).getResultList();
    }

    public List<Language> findLanguagesByUser(User user) {
        String query = "SELECT DISTINCT l FROM Repository r JOIN r.languages l WHERE r.user = :user";
        return entityManager.createQuery(query, Language.class)
                .setParameter("user", user)
                .getResultList();
    }

}
