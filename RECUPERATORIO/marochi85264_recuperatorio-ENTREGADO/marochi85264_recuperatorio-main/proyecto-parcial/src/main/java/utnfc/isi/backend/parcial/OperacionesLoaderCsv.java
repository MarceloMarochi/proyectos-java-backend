package utnfc.isi.backend.parcial;

import jakarta.persistence.EntityManager;
import utnfc.isi.backend.parcial.entities.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class OperacionesLoaderCsv {
    public void loadData(String filePath, EntityManager em) throws Exception {
        Map<String, Compania> companias = new HashMap<>();
        Map<String, Empleado> empleados = new HashMap<>();
        Map<String, Operador_Tarjeta> operadoresTarjetas = new HashMap<>();
        Map<String, Moneda> monedas = new HashMap<>();

        em.getTransaction().begin();

        String line;
        boolean isFirstLine = true;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String separador = Pattern.quote("|");
                String[] tokens = line.split(separador);

                System.out.println(Arrays.toString(tokens));

                //Compania
                String companiaNombre = tokens[0].trim();
                System.out.println("NOMBRE COMPANIA " + companiaNombre);
                Float comisionCompania = Float.parseFloat(tokens[1].trim());


                //Empleado
                String empleadoIdentificacion = tokens[2].trim();
                String telefonoEmpleado = tokens[3].trim();
                String nombreEmpleado = tokens[4].trim();

                //Cuenta
                String numeroCuenta = tokens[5].trim();
                String numeroTarjeta = tokens[6].trim();
                String operadorTarjetaNombre = tokens[7].trim();

                //Tarjeta
                String vencimientoTarjeta = tokens[8].trim();
                Float saldoCuenta = Float.parseFloat(tokens[9].trim());

                //Moneda
                String monedaNombre = tokens[10].trim();
                Float cotizacionUsd = Float.parseFloat(tokens[11].trim());


                Compania compania = companias.get(companiaNombre);
                if (compania == null) {
                    compania = new Compania();
                    compania.setNombre(companiaNombre);
                    compania.setComision(comisionCompania);
                    em.persist(compania);
                    companias.put(companiaNombre, compania);
                }

                Empleado empleado = empleados.get(empleadoIdentificacion);
                if (empleado == null) {
                    empleado = new Empleado();
                    empleado.setNombre(nombreEmpleado);
                    empleado.setTelefono(telefonoEmpleado);
                    empleado.setCompania(compania);
                    em.persist(empleado);
                    empleados.put(empleadoIdentificacion, empleado);
                }

                Operador_Tarjeta tarjeta = operadoresTarjetas.get(operadorTarjetaNombre);
                if (tarjeta == null) {
                    tarjeta = new Operador_Tarjeta();
                    tarjeta.setNombre(operadorTarjetaNombre);
                    em.persist(tarjeta);
                    operadoresTarjetas.put(operadorTarjetaNombre, tarjeta);
                }

                Moneda moneda = monedas.get(monedaNombre);
                if (moneda == null) {
                    moneda = new Moneda();
                    moneda.setNombre(monedaNombre);
                    moneda.setConversion_usd(cotizacionUsd);
                    em.persist(moneda);
                    monedas.put(monedaNombre, moneda);
                }

                Cuenta cuenta = new Cuenta();
                cuenta.setNumero_cuenta(numeroCuenta);
                cuenta.setEmpleado(empleado);
                cuenta.setNumero_tarjeta(numeroTarjeta);
                cuenta.setVencimiento_tarjeta(vencimientoTarjeta);
                cuenta.setOperadorTarjeta(tarjeta);
                cuenta.setMoneda(moneda);
                cuenta.setSaldo(saldoCuenta);

                em.persist(cuenta);

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        em.getTransaction().commit();

    }

    public void limpiarDB(EntityManager em) {
        em.getTransaction().begin();
        em.createNativeQuery("DELETE FROM Cuentas").executeUpdate();
        em.createNativeQuery("DELETE FROM Empleados").executeUpdate();
        em.createNativeQuery("DELETE FROM OperadoresTarjetas").executeUpdate();
        em.createNativeQuery("DELETE FROM Monedass").executeUpdate();
        em.createNativeQuery("DELETE FROM Companias").executeUpdate();

        em.createNativeQuery("DELETE FROM sqlite_sequence WHERE name = 'Cuentas'").executeUpdate();
        em.createNativeQuery("DELETE FROM sqlite_sequence WHERE name = 'Empleados'").executeUpdate();
        em.createNativeQuery("DELETE FROM sqlite_sequence WHERE name = 'OperadoresTarjetas'").executeUpdate();
        em.createNativeQuery("DELETE FROM sqlite_sequence WHERE name = 'Monedass'").executeUpdate();
        em.createNativeQuery("DELETE FROM sqlite_sequence WHERE name = 'Companias'").executeUpdate();

        em.getTransaction().commit();
    }
}
