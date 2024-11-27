package utn.frc.bka.util;

import jdk.swing.interop.SwingInterOpUtils;
import utn.frc.bka.daos.ActivoDAO;
import utn.frc.bka.daos.TransaccionDAO;
import utn.frc.bka.daos.UsuarioDAO;
import utn.frc.bka.entidades.Activo;
import utn.frc.bka.entidades.Transaccion;
import utn.frc.bka.entidades.Usuario;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ArchivoCSV {

    public static void cargarDatosDesedCSV(String rutaArchivo, ActivoDAO activoDAO, TransaccionDAO transaccionDAO, UsuarioDAO usuarioDAO) {
        try {
            List<String> lineas = Files.readAllLines(Paths.get(rutaArchivo));
            List<Activo> activos = new ArrayList<>();
            List<Usuario> usuarios = new ArrayList<>();
            List<String> transaccionesCSV = new ArrayList<>();

            // Para saber en que tabla estoy
            String seccionActual = "";

            for(String linea : lineas) {
                if (linea.startsWith("# Tabla:")) {
                    seccionActual = linea;
                } else if (!linea.isEmpty()) {

                    switch (seccionActual) {

                        case "# Tabla: Activos":
                            // aca se obvia el primer renglon que son
                            // los titulos de las columnas
                            if (!linea.startsWith("activo_id")) {
                                activos.add(new Activo(linea));
                            }
                            break;
                        case "# Tabla: Usuarios":
                            if (!linea.startsWith("usuario_id")){
                                usuarios.add(new Usuario(linea));
                            }
                            break;

                        // Como esta es una tabla compuesta con foreing key, lo que tenemos que hacer
                        // es guardar como tipo string al array
                        case "# Tabla: Transacciones":
                            if (!linea.startsWith("transaccion_id")){
                                transaccionesCSV.add(linea);
                            }
                            break;
                    }
                }
            }

            // Guardamos en la BD
            for(Activo activo : activos) {
                activoDAO.save(activo);
            }

            for(Usuario usuario : usuarios) {
                usuarioDAO.save(usuario);
            }

            // Como las tablas transacciones tiene dos FK
            // El entitymanager no puede asignar de una los id
            // Primero debe conocer al objeto para lugo sacar su id.
            for(String linea : transaccionesCSV) {
                String[] valores = linea.split(",");

                Usuario usuario = usuarioDAO.findById(Long.parseLong(valores[1]));
                Activo activo = activoDAO.findById(Long.parseLong(valores[2]));

                Transaccion transaccion = new Transaccion(linea, usuario, activo);

                transaccionDAO.save(transaccion);
            }

            System.out.println("Datos cargados correctamente");


        } catch (IOException e) {
            System.out.println("Error al cargar el archivo" + e.getMessage());
        }
    }
}
