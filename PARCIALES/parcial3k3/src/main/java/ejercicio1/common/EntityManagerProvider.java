package ejercicio1.common;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerProvider {

    private static EntityManagerProvider emp;
    private EntityManager manager;

    private EntityManagerProvider() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2ExpPU");
        manager = emf.createEntityManager();
    }

    // Singleton
    public static EntityManager getInstance() {
        if (emp == null) {
            emp = new EntityManagerProvider();
        }
        return emp.manager;
    }

    public static void close() {
        if (emp.manager != null) {
            emp.manager.close();
            emp = null;
        }
    }
}
