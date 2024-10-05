package utn.frc.bka;

import utn.frc.bka.common.EntityManagerProvider;
import utn.frc.bka.daos.ActivoDAO;
import utn.frc.bka.daos.TransaccionDAO;
import utn.frc.bka.daos.UsuarioDAO;
import utn.frc.bka.dtos.TransaccionDTO;
import utn.frc.bka.entidades.Activo;
import utn.frc.bka.entidades.Transaccion;
import utn.frc.bka.entidades.Usuario;
import utn.frc.bka.mappers.TransaccionToTransaccionDTO;
import utn.frc.bka.util.ArchivoCSV;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ActivoDAO activoDAO = new ActivoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        TransaccionDAO transaccionDAO = new TransaccionDAO();

        try {
            ArchivoCSV.cargarDatosDesedCSV("acciones.csv", activoDAO, transaccionDAO, usuarioDAO);
        } catch (Exception e) {
            System.out.println("Error al cargar los datos a la BD: " + e.getMessage());
        }

        List<Transaccion> transacciones = transaccionDAO.findAll();

        for (Transaccion transaccion : transacciones) {
            System.out.println(transaccion.toString());
        }



        Activo activo = activoDAO.findById(1L);
        LocalDate fechaInicio = LocalDate.of(2023,1,1);
        LocalDate fechaFin = LocalDate.of(2023, 12, 31);

        List<Transaccion> transaccionesFiltradas = transaccionDAO.findByActivoAndRangoFechas(activo, fechaInicio, fechaFin);
        transaccionesFiltradas.forEach(System.out::println);


        List<Usuario> usuarios = usuarioDAO.findByNombre("an");
        usuarios.forEach(System.out::println);

        List<TransaccionDTO> transaccionDTOS = transacciones.stream().map(new TransaccionToTransaccionDTO()).toList();
        transaccionDTOS.forEach(System.out::println);

        EntityManagerProvider.closeEntityManager();
    }
}