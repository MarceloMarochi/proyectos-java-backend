package utn.frc.backend.tutor.orders;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import utn.frc.backend.tutor.orders.domain.entities.CityEntity;
import utn.frc.backend.tutor.orders.domain.entities.CustomerEntity;
import utn.frc.backend.tutor.orders.domain.entities.OrderEntity;
import utn.frc.backend.tutor.orders.domain.entities.ProductEntity;
import utn.frc.backend.tutor.sac.JPA;
import utn.frc.bda.semana05.jdbc.OrdersDB;
import utn.frc.bda.util.menu.Menu;
import utn.frc.bda.util.menu.MenuItem;

import java.util.List;
import java.util.Scanner;

public class OrdersJPA {
    private static final String H2_PERSISTENCE_UNIT_NAME = "h2OPU";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        run(scanner);

        scanner.close();
    }

    public static void run(Scanner sc) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(H2_PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        MenuItem[] ops = {
                new MenuItem("1", "Populate", () -> populate(em)),
                new MenuItem("2", "Query", () -> query(em)),
        };
        Menu menu = new Menu(sc, ops);
        menu.run();

        em.close();
        emf.close();
    }

    private static void populate(EntityManager em) {
        // Usar OrdersDB.populate para probar inicialmente.
        // todo
    }

    private static void query(EntityManager em) {
        //List<CityEntity> cities = em.createNamedQuery("City.findAll").getResultList();
        //cities.forEach(System.out::println);

        //List<CustomerEntity> customers = em.createNamedQuery("Customer.findAll").getResultList();
        //customers.forEach(System.out::println);

        //List<ProductEntity> products = em.createNamedQuery("Product.findAll").getResultList();
        //products.forEach(System.out::println);

        //List<OrderEntity> orders = em.createNamedQuery("Order.findAll").getResultList();
        //orders.forEach(System.out::println);

        // ---------------------------------------------------------------------
        // Orders of customer 15
        List<OrderEntity> orders = em.createNamedQuery("Order.findAllByCustomerId").setParameter("cid", 15).getResultList();
        orders.forEach(System.out::println);

        System.out.println("================================================================================");
        orders.get(0).getOrderDetailsByOid().forEach(System.out::println);

    }

}
