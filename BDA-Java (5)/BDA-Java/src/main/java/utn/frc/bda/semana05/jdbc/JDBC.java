package utn.frc.bda.semana05.jdbc;

import utn.frc.bda.semana05.jdbc.dal.DBAlumno;
import utn.frc.bda.semana05.jdbc.domain.Alumno;
import utn.frc.bda.semana05.jdbc.domain.Persona;
import utn.frc.bda.util.StringUtil;
import utn.frc.bda.util.menu.Menu;
import utn.frc.bda.util.menu.MenuItem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class JDBC {

    // PostgreSQL
    public static final String PG_DRIVER_NAME = "org.postgresql.Driver";
    public static final String PG_URL = "jdbc:postgresql://pg16:5432/sacdb";
    public static final String PG_USR = "sacusr";
    public static final String PG_PWD = "sacpwd";

    // MySQL
    //public static final String MY_DRIVER_NAME = "com.mysql.jdbc.Driver"; // deprecated
    public static final String MY_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    public static final String MY_URL = "jdbc:mysql://mysql80:3306/sacdb";
    public static final String MY_USR = "sacusr";
    public static final String MY_PWD = "sacpwd";

    // H2 Database Engine
    public static final String H2_DRIVER_NAME = "org.h2.Driver";
    public static final String H2_URL = "jdbc:h2:file:/tmp/ddbb/sacdb";

    // Main
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        run(scanner);

        scanner.close();
    }

    public static void run(Scanner sc) {
        MenuItem[] ops = {
                new MenuItem("1", "JDBC Directo", () -> jdbcDirecto()),
                new MenuItem("2", "JDBC Con DBManager", () -> jdbcConDBManager()),
        };

        Menu menu = new Menu(sc, ops);

        menu.run();
    }

    private static void jdbcDirecto() {
        dbquery("PostgreSQL", PG_DRIVER_NAME, PG_URL, PG_USR, PG_PWD);
        dbquery("MySQL", MY_DRIVER_NAME, MY_URL, MY_USR, MY_PWD);
        dbquery("H2 Database Engine", H2_DRIVER_NAME, H2_URL, null, null);
    }

    private static void dbquery(String name, String driverName, String url, String usr, String pwd) {
        System.out.println(StringUtil.fill('-', 80));
        System.out.println("Consultando " + name + "(" + driverName + ")");
        System.out.println(StringUtil.fill('-', 80));

        Connection cn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(driverName);
            cn = DriverManager.getConnection(url, usr, pwd);

            stmt = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //stmt = cn.createStatement();

            String query = "SELECT * FROM persona ORDER BY apellido, nombre";
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                Integer id = rs.getInt("pid");
                String dni = rs.getString("dni");
                String ape = rs.getString("apellido");
                String nom = rs.getString("nombre");
                Persona p = new Persona(id, dni, ape, nom);
                System.out.println(p);
            }

        } catch (ClassNotFoundException e) {
            System.err.println("No se encontró la clase:");
            System.err.println(e.getMessage());

        } catch (Exception e) {
            System.err.println(e.getMessage());

        } finally {
            if (rs != null) try {
                rs.close();
            } catch (Exception e) {}

            if (stmt != null) try {
                stmt.close();
            } catch (Exception e) {}

            if (cn != null) try {
                cn.close();
            } catch (Exception e) {}
        }
    }

    private static void jdbcConDBManager() {
        runBatch("PostgreSQL", PG_DRIVER_NAME, PG_URL, PG_USR, PG_PWD);
        runBatch("MySQL", MY_DRIVER_NAME, MY_URL, MY_USR, MY_PWD);
        runBatch("H2 Database Engine", H2_DRIVER_NAME, H2_URL, null, null);
    }

    private static void runBatch(String name, String driverName, String url, String usr, String pwd) {
        System.out.println(StringUtil.fill('-', 80));
        System.out.println("Conectando " + name + "(" + driverName + ")");
        System.out.println(StringUtil.fill('-', 80));

        DBManager db = null;

        try {
            db = new DBManager(driverName, url, usr, pwd).connect();

            listAlumnos(DBAlumno.loadList(db));

            System.out.println("Creando nuevo alumno...");
            Alumno alumno = new Alumno("dni999", "Lojuno", "Johny", "leg999");
            System.out.println("Nuevo alumno creado: " + alumno);

            Integer pid = DBAlumno.saveDB(db, alumno);
            System.out.println("Nuevo alumno guardado: " + alumno);

            alumno = DBAlumno.loadDB(db, pid);
            System.out.println("Nuevo alumno cargado: " + alumno);

            alumno.setApellido("Quito");
            alumno.setNombre("Armando Esteban");
            System.out.println("Nuevo alumno modificado: " + alumno);

            pid = DBAlumno.saveDB(db, alumno);
            System.out.println("Nuevo alumno guardado: " + alumno);

            alumno = DBAlumno.loadDB(db, pid);
            System.out.println("Nuevo alumno cargado: " + alumno);

            DBAlumno.deleteDB(db, alumno);
            System.out.println("Nuevo alumno eliminado: " + alumno);

            alumno = DBAlumno.loadDB(db, pid);
            pid = alumno == null ? null : alumno.getPid();
            System.out.println("Se intenta cargar nuevamente: (" + pid + ") " + alumno);

        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            if (db != null) {
                db.close();
            }
        }
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
