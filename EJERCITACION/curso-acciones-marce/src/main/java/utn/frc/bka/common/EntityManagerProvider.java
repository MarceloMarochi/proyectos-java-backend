package utn.frc.bka.common;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerProvider {

    private static EntityManagerProvider emp;

    private EntityManager em;

    // Constructor
    private EntityManagerProvider() {
        // Iniciamos el EntityManager
        // El parámetro debe ser el nombre que pusimos en el persistence.xml
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence-unit");

        // Creamos la conexion con el entityManager
        em = entityManagerFactory.createEntityManager();
    }

    public static EntityManager getEntityManager() {
        // Si la clase misma no esta creada se crea
        if (emp == null) {
            emp = new EntityManagerProvider();
        }

        // Retornamos la conexión
        return emp.em;
    }

    // Metodo para cerrar la conexión
    public static void closeEntityManager() {
        if(emp.em != null) {
            emp.em.close();
            emp = null;
        }
    }
}
