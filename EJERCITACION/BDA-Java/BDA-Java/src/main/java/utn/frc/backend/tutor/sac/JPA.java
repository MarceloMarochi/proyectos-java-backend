package utn.frc.backend.tutor.sac;

import utn.frc.backend.tutor.sac.domain.entities.Alumno;
import utn.frc.backend.tutor.sac.domain.entities.Docente;
import utn.frc.backend.tutor.sac.domain.entities.Persona;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import utn.frc.bda.util.StringUtil;
import utn.frc.bda.util.menu.Menu;
import utn.frc.bda.util.menu.MenuItem;

import java.util.List;
import java.util.Scanner;

public class JPA {

    // PostgreSQL
    public static final String PG_PERSISTENCE_UNIT_NAME = "pgPU";
    // MySQL
    public static final String MY_PERSISTENCE_UNIT_NAME = "myPU";
    // H2 Database Engine
    public static final String H2_PERSISTENCE_UNIT_NAME = "h2PU";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        run(scanner);

        scanner.close();
    }

    public static void run(Scanner sc) {
        MenuItem[] ops = {
                new MenuItem("1", "Pre Tests", () -> jpaPreTests()),
                new MenuItem("2", "JPA", () -> jpaRun()),
        };

        Menu menu = new Menu(sc, ops);

        menu.run();
    }
    
    public static void jpaPreTests() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(JPA.PG_PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        runTestsBatch(em);

        em.close();
        emf.close();
    }

    public static void runTestsBatch(EntityManager em) {
        // ---------------------------------------------------------------------
        System.out.println(StringUtil.fill('=', 80));
        System.out.println("Docentes");
        System.out.println(StringUtil.fill('=', 80));
        List<Docente> docentes = em.createNamedQuery("Docente.findAllById").getResultList();
        docentes.forEach(System.out::println);
        Docente d;
        try {
            d = em.find(Docente.class, 1);
            System.out.println(d);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            d = (Docente) em.createNamedQuery("Docente.findById").setParameter("pid", 17).getSingleResult();
            System.out.println(d);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // ---------------------------------------------------------------------
        System.out.println(StringUtil.fill('=', 80));
        System.out.println("Alumnos");
        System.out.println(StringUtil.fill('=', 80));
        List<Alumno> alumnos = em.createNamedQuery("Alumno.findAllById").getResultList();
        alumnos.forEach(System.out::println);
        Alumno a;
        try {
            a = em.find(Alumno.class, 5);
            System.out.println(a);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            a = (Alumno) em.createNamedQuery("Alumno.findById").setParameter("pid", 15).getSingleResult();
            System.out.println(a);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // ---------------------------------------------------------------------
        System.out.println(StringUtil.fill('=', 80));
        System.out.println("Personas");
        System.out.println(StringUtil.fill('=', 80));
        List<Persona> personas = em.createNamedQuery("Persona.findAllById").getResultList();
        personas.forEach(System.out::println);
        Persona p;
        try {
            p = em.find(Persona.class, 8);
            System.out.println(p);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            p = (Persona) em.createNamedQuery("Persona.findById").setParameter("pid", 1).getSingleResult();
            System.out.println(p);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // ---------------------------------------------------------------------
        //System.out.println(Util.fill('=', 80));
        //System.out.println("Prueba Transaction");
        //System.out.println(Util.fill('=', 80));
        //em.getTransaction().begin();
        //Persona pp = em.find(Persona.class, 17);
        //Docente dd = em.find(Docente.class, 17);
        //pp.setApellido("QQQQQQ");
        //dd.setApellido("PPPPPPP");
        //dd.setLegajo("Leg27");
        //em.getTransaction().commit();
        // -------------------------------------------------------------------------
    }
    
    public static void jpaRun() {
        // PostgreSQL
        runDB("PostgreSQL", JPA.PG_PERSISTENCE_UNIT_NAME);

        //// H2 Database Engine
        //runDB("H2 Database Engine", JPA.H2_PERSISTENCE_UNIT_NAME);
        //
        //// MySQL
        //runDB("MySQL", JPA.MY_PERSISTENCE_UNIT_NAME);
    }

    public static void runDB(String DBName, String PUName) {
        EntityManagerFactory emf;
        EntityManager em;

        System.out.println(StringUtil.fill('-', 80));
        System.out.println("Conectando " + DBName);
        System.out.println(StringUtil.fill('-', 80));

        emf = Persistence.createEntityManagerFactory(PUName);
        em = emf.createEntityManager();

        runJPABatch(em);

        em.close();
        emf.close();
    }

    private static void runJPABatch(EntityManager em) {
        List<Alumno> alumnos = em.createNamedQuery("Alumno.findAllById").getResultList();
        listAlumnos(alumnos);

        System.out.println("Creando nuevo alumno...");
        Alumno alumno = new Alumno("leg999", "dni999", "Lojuno", "Johny");
        System.out.println("Nuevo alumno creado: " + alumno);

        em.getTransaction().begin();
        em.persist(alumno);
        em.getTransaction().commit();
        Integer pid = alumno.getPid();
        System.out.println("Nuevo alumno guardado: " + alumno);

        alumno = em.createNamedQuery("Alumno.findById", Alumno.class).setParameter("pid", pid).getSingleResult();
        System.out.println("Nuevo alumno cargado: " + alumno);

        alumno.setApellido("Quito");
        alumno.setNombre("Armando Esteban");
        System.out.println("Nuevo alumno modificado: " + alumno);

        em.getTransaction().begin();
        em.persist(alumno);
        em.getTransaction().commit();
        pid = alumno.getPid();
        System.out.println("Nuevo alumno guardado: " + alumno);

        alumno = em.createNamedQuery("Alumno.findById", Alumno.class).setParameter("pid", pid).getSingleResult();
        System.out.println("Nuevo alumno cargado: " + alumno);

        em.getTransaction().begin();
        alumno.setApellido("Chuca");
        alumno.setNombre("Kevin");
        em.getTransaction().commit();
        pid = alumno.getPid();
        System.out.println("Nuevo alumno modificado (y guardado): " + alumno);

        alumno = em.createNamedQuery("Alumno.findById", Alumno.class).setParameter("pid", pid).getSingleResult();
        System.out.println("Nuevo alumno cargado: " + alumno);

        em.getTransaction().begin();
        em.remove(alumno);
        em.getTransaction().commit();
        System.out.println("Nuevo alumno eliminado: " + alumno);

        //alumno = em.createNamedQuery("Alumno.findById", Alumno.class).setParameter("pid", pid).getSingleResult();
        alumno = em.find(Alumno.class, pid);
        pid = alumno == null ? null : alumno.getPid();
        System.out.println("Se intenta cargar nuevamente: (" + pid + ") " + alumno);
    }

    private static void listAlumnos(List<Alumno> alumnos) {
        System.out.println(StringUtil.fill('-', 80));
        System.out.println("Alumnos:");
        System.out.println(StringUtil.fill('-', 80));

        for (Alumno alumno : alumnos) {
            System.out.println(StringUtil.rPad(alumno, 80, 8));
        }
    }


}