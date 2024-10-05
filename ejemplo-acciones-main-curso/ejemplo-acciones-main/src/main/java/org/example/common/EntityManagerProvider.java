package org.example.common;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

// Aplicando el patron singleton
public class EntityManagerProvider {
    private static EntityManagerProvider emp;
    private EntityManager em;

    private EntityManagerProvider(){
        // Iniciar EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-unit");
        em = emf.createEntityManager();
    }

    // Crear la instancia
    public static EntityManager getEntityManager(){
        if(emp == null){
            emp = new EntityManagerProvider();
        }

        return emp.em;
    }

    // Cerrar conexion
    public static void closeEntityManager(){
        if (emp.em != null){
            emp.em.close();
            emp = null;
        }
    }
}