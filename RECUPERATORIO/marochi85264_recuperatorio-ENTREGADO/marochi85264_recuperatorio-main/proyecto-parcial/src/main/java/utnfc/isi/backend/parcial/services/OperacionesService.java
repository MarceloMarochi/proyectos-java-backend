package utnfc.isi.backend.parcial.services;

import jakarta.persistence.EntityManager;
import utnfc.isi.backend.parcial.entities.Compania;
import utnfc.isi.backend.parcial.entities.Cuenta;
import utnfc.isi.backend.parcial.entities.Empleado;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class OperacionesService {

    private final EntityManager em;

    public OperacionesService(EntityManager em) {
        this.em = em;
    }

    public List<Compania> getAllCompania() {
        String query = "SELECT c FROM Compania c ORDER BY c.compania_id ASC";
        return em.createQuery(query, Compania.class)
                .getResultList();
    }


    public List<Object[]> getResumenMonedas(EntityManager em) {
        String query = "SELECT m.nombre, COUNT(c.numero_cuenta), SUM(c.saldo) " +
                "FROM Cuenta c " +
                "JOIN c.moneda m " +
                "GROUP BY m.nombre " +
                "ORDER BY SUM(c.saldo) ASC";

        return em.createQuery(query, Object[].class)
                .getResultList();
    }

    public void mostrarResumenMonedas(EntityManager em) {
        List<Object[]> resultados = getResumenMonedas(em);

        for (Object[] resultado : resultados) {
            String nombreMoneda = (String) resultado[0];
            Long cantidadCuentas = (Long) resultado[1];
            Double saldoAcumulado = (Double) resultado[2];

            System.out.println("Moneda: " + nombreMoneda +
                    ", Cantidad de Cuentas: " + cantidadCuentas +
                    ", Saldo Acumulado: " + saldoAcumulado);
        }
    }



//     Por cada compañía, determinar el nombre y el total de comisiones en dolares que esa compañia debe
//pagar. Por ejemplo la compania A tiene un comision del 10%, y tiene 10 cuentas asociadas, se debe
//convertir el saldo en la moneda de la cuenta a dolares para acumular la comision.

    public Compania findCompaniaById(int compania_id, EntityManager em) {
        String query = "SELECT c FROM Compania c WHERE c.compania_id = :compania_id";
        Compania compania = em.createQuery(query, Compania.class)
                .setParameter("compania_id", compania_id)
                .getSingleResult();

        return compania;
    }

    public List<Cuenta> getCuentaPorEmpleadp(int empleado_id, EntityManager em) {
        String query = "SELECT c FROM Cuenta c WHERE c.empleado.empleado_id = :empleado_id";
        List<Cuenta> cuentasPorEmpleado = em.createQuery(query, Cuenta.class)
                .setParameter("empleado_id", empleado_id)
                .getResultList();
        return cuentasPorEmpleado;
    }

    public List<Empleado> getEmpleadosPorCompania(int compania_id, EntityManager em) {
        String query = "SELECT e FROM Empleado e WHERE e.compania.compania_id = :compania_id";
        List<Empleado> empleadosPorCompania = em.createQuery(query, Empleado.class)
                .setParameter("compania_id", compania_id)
                .getResultList();
        return empleadosPorCompania;
    }


    // Arreglado cambio empleado.getCuentas() por la query getCuentaPorEmpleado()
    public String getNombreYTotalComisionesPorCompania(int compania_id, EntityManager em) {
        Compania compania = findCompaniaById(compania_id, em);
        List<Empleado> empleadosPorCompania =getEmpleadosPorCompania(compania_id, em);

        int totalSaldo = 0;

        for (Empleado empleado : empleadosPorCompania) {
            List<Cuenta> cuentasPorEmpleado = getCuentaPorEmpleadp(empleado.getEmpleado_id(), em);
            for (Cuenta cuenta : cuentasPorEmpleado){

                totalSaldo += cuenta.conversionSaldoUSD();
            }
        }

        double calculoComision = totalSaldo * compania.getComision();

        String line = "Compania: " + compania.getNombre() + " - Total comisiones usd: " + calculoComision;
        return line;
    }

    // QUERY ARREGLADA: Cambie getSingleResult() y le puse maximo un resultado
    public Cuenta getCuentaMayorSaldo() {
        String query = "SELECT c FROM Cuenta c ORDER BY c.saldo DESC";
        Cuenta cuentaMay = em.createQuery(query, Cuenta.class)
                .setMaxResults(1)
                .getSingleResult();
        return cuentaMay;
    }

    // QUERY ARREGLADA: Cambie getSingleResult() y le puse maximo un resultado
    public Cuenta getCuentaMenorSaldo() {
        String query = "SELECT c FROM Cuenta c ORDER BY c.saldo ASC";
        Cuenta cuentaMen = em.createQuery(query, Cuenta.class)
                .setMaxResults(1)
                .getSingleResult();
        return cuentaMen;
    }
}
