package org.example;

import org.example.common.EntityManagerProvider;
import org.example.daos.ActivoDAO;
import org.example.daos.TransaccionDAO;
import org.example.daos.UsuarioDAO;
import org.example.dtos.TransaccionDTO;
import org.example.entities.Activo;
import org.example.entities.Transaccion;
import org.example.entities.Usuario;
import org.example.mappers.TransaccionToTransaccionDTO;
import org.example.utils.ArchivoCSV;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Iniciamos los DAOs
        ActivoDAO activoDAO = new ActivoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        TransaccionDAO transaccionDAO = new TransaccionDAO();

        // ===================================================================================
        // Cargar acciones.csv a la BD
        try {
            ArchivoCSV.cargarDatosDesdeCSV("acciones.csv", activoDAO, usuarioDAO, transaccionDAO);
        }catch (Exception e){
            System.out.println("Error al cargar los datos: " + e.toString());
        }


        // ===================================================================================
        // Realizamos unas busqueda de todas las transacciones registradas en la BD
        List<Transaccion> transacciones = transaccionDAO.findAll();

        for (Transaccion transaccion: transacciones){
            System.out.println(transaccion.toString());
        }


        // ===================================================================================
        // Consulta por id del activo
        System.out.println(activoDAO.findById(2L).toString());


        // ===================================================================================
        // Ejemplo de activo para la consulta
        Activo activo = activoDAO.findById(1L); // Suponemos que buscamos el activo con ID 1

        // Definir rango de fechas para la consulta
        LocalDate fechaInicio = LocalDate.of(2023, 1, 1);
        LocalDate fechaFin = LocalDate.of(2023, 12, 31);

        // Ejecutar la consulta
        List<Transaccion> transaccionesFiltradas = transaccionDAO.findByActivoAndRangoFechas(activo, fechaInicio, fechaFin);

        // Imprimir los resultados
        transaccionesFiltradas.forEach(System.out::println);

        // ==================================================================================
        // Buscar usuarios que tenga la cadena de caracteres ingresada (por nombre)
        List<Usuario> usuarios = usuarioDAO.findByNombre("an");
        usuarios.forEach(System.out::println);


        // ==================================================================================
        // Patron DTO

        /*
        A las transacciones obtenidas anteriormente aplico el mappers en un .map()
        para convertir la lista de entidades a una lista de dtos
        */
        List<TransaccionDTO> transaccionDTOS = transacciones.stream()
                .map(new TransaccionToTransaccionDTO()).toList();

        transaccionDTOS.forEach(System.out::println);


        // Cerramos conexion
        EntityManagerProvider.closeEntityManager();
    }
}