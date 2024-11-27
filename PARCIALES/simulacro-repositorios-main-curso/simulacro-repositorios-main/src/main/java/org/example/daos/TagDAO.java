package org.example.daos;

import jakarta.persistence.EntityManager;
import org.example.common.EntityManagerProvider;
import org.example.entities.Language;
import org.example.entities.Tag;

import java.util.List;

public class TagDAO {
    private final EntityManager entityManager = EntityManagerProvider.getEntityManager();

    public TagDAO(){

    }

    public Tag save(Tag tag){
        entityManager.getTransaction().begin();
        entityManager.persist(tag);
        entityManager.getTransaction().commit();
        return tag;
    }

    public List<Tag> findAll(){
        return entityManager.createQuery("SELECT t FROM Tag t", Tag.class).getResultList();
    }

    public Tag findById(Long id){
        return entityManager.find(Tag.class, id);
    }

    public Tag findByName(String name){
        String query = "SELECT t FROM Tag t WHERE t.name = :name";

        try {
            return entityManager.createQuery(query, Tag.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
