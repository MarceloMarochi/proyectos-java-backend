package ar.frc.utn.backend.connectionManager;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerProvider {

    private static EntityManagerProvider emp;
    private EntityManager manager;

    private EntityManagerProvider() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("academico");
        manager = emf.createEntityManager();
    }

    public static EntityManager getInstance(){
        if (emp == null){
            emp = new EntityManagerProvider();
        }
        return emp.manager;
    }

    public static void close(){
        if (emp.manager != null){
            emp.manager.close();
            emp = null;
        }
    }

}
