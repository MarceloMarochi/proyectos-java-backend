package org.example.daos;

import jakarta.persistence.EntityManager;
import org.example.common.EntityManagerProvider;
import org.example.dtos.UserStatistics;
import org.example.entities.Language;
import org.example.entities.User;

import java.util.List;

public class UserDAO {
    private final EntityManager entityManager = EntityManagerProvider.getEntityManager();

    public UserDAO(){

    }

    public User save(User user){
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        return user;
    }

    public List<User> findAll(){
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    public User findById(Long id){
        return entityManager.find(User.class, id);
    }

    public User findByName(String name){
        String query = "SELECT u FROM User u WHERE u.name = :name";

        try {
            return entityManager.createQuery(query, User.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    // Método para obtener estadísticas de usuarios ordenados alfabéticamente
    public List<UserStatistics> getUserStatistics() {
        // Consulta JPQL para obtener la cantidad de repositorios y estrellas por usuario
        String query = "SELECT new org.example.dtos.UserStatistics(u.name, COUNT(r), COALESCE(SUM(r.stars), 0)) " +
                "FROM User u LEFT JOIN u.repositories r " +
                "GROUP BY u.name " +
                "ORDER BY u.name ASC";

        return entityManager.createQuery(query, UserStatistics.class).getResultList();
    }
}
