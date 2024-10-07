package utn.frc.bka.common;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntintyMangaerProvider {

    private static EntintyMangaerProvider emp;
    private EntityManager manager;

    private EntintyMangaerProvider() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("academico");
        manager = emf.createEntityManager();
    }

    public static EntityManager getInstance() {
        if (emp == null) {
            emp = new EntintyMangaerProvider();
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
