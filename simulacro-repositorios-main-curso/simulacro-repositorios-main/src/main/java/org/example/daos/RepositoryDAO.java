package org.example.daos;

import jakarta.persistence.EntityManager;
import org.example.common.EntityManagerProvider;
import org.example.dtos.LanguageStatistics;
import org.example.dtos.RepositoryStatistics;
import org.example.entities.Language;
import org.example.entities.Repository;
import org.example.entities.Tag;
import org.example.entities.User;

import java.util.List;

public class RepositoryDAO {
    private final EntityManager entityManager = EntityManagerProvider.getEntityManager();

    public RepositoryDAO(){

    }

    public Repository save(Repository repository){
        entityManager.getTransaction().begin();
        entityManager.persist(repository);
        entityManager.getTransaction().commit();
        return repository;
    }

    public List<Repository> findAll(){
        return entityManager.createQuery("SELECT r FROM Repository r", Repository.class).getResultList();
    }

    public Repository findById(Long id){
        return entityManager.find(Repository.class, id);
    }

    public Repository findByName(String name){
        String query = "SELECT r FROM Repository r WHERE r.repositoryName = :name";

        try {
            return entityManager.createQuery(query, Repository.class)
                    .setParameter("name", name)
                    .getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Repository> findAllByUser(User user){
        String query = "SELECT r FROM Repository r WHERE r.user = :user";

        return entityManager.createQuery(query, Repository.class)
                .setParameter("user", user)
                .getResultList();
    }

    public List<Repository> findAllByLanguage(Language language){
        String query = "SELECT r FROM Repository r WHERE :language MEMBER OF r.languages";

        return entityManager.createQuery(query, Repository.class)
                .setParameter("language", language)
                .getResultList();
    }

    public List<Repository> findAllByTag(Tag tag){
        String query = "SELECT r FROM Repository r WHERE :tag MEMBER OF r.tags";

        return entityManager.createQuery(query, Repository.class)
                .setParameter("tag", tag)
                .getResultList();
    }


    // Metodo para obtener la cantidad total de repositorios y la suma total de estrellas
    public RepositoryStatistics getTotalRepositoriesAndStars() {
        // Consulta JPQL para obtener la cantidad total de repositorios y la suma total de estrellas
        String query = "SELECT new org.example.dtos.RepositoryStatistics(COUNT(r), COALESCE(SUM(r.stars), 0)) FROM Repository r";

        return entityManager.createQuery(query, RepositoryStatistics.class).getSingleResult();
    }
}
