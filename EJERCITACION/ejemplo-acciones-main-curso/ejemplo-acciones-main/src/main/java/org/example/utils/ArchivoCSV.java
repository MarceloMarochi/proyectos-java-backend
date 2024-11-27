package org.example.utils;

import org.example.daos.ActivoDAO;
import org.example.daos.TransaccionDAO;
import org.example.daos.UsuarioDAO;
import org.example.entities.Activo;
import org.example.entities.Transaccion;
import org.example.entities.Usuario;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ArchivoCSV {
    public static void cargarDatosDesdeCSV(String rutaArchivo, ActivoDAO activoDAO, UsuarioDAO usuarioDAO, TransaccionDAO transaccionDAO)  {
        try {
            List<String> lineas = Files.readAllLines(Paths.get(rutaArchivo));
            List<Activo> activos = new ArrayList<>();
            List<Usuario> usuarios = new ArrayList<>();
            List<String> transaccionesCSV = new ArrayList<>();

            // Variable de control para la sección actual
            String seccionActual = "";

            // Procesar las lineas del archivo .CSV
            for (String linea: lineas){
                if(linea.startsWith("# Tabla:")){
                    seccionActual = linea;
                } else if (!linea.isEmpty()){
                    switch (seccionActual){
                        case "# Tabla: Activos":
                            if (!linea.startsWith("activo_id")){
                                activos.add(new Activo(linea));
                            }
                            break;
                        case "# Tabla: Usuarios":
                            if (!linea.startsWith("usuario_id")){
                                usuarios.add(new Usuario(linea));
                            }
                            break;
                        case "# Tabla: Transacciones":
                            if (!linea.startsWith("transaccion_id")){
                                transaccionesCSV.add(linea);
                            }
                            break;
                    }
                }
            }

            // Guardar los activos y usuarios en la BD
            for (Activo activo: activos){
                activoDAO.save(activo);
            }

            for (Usuario usuario: usuarios){
                usuarioDAO.save(usuario);
            }

            // Procesar y guardar las transacciones
            for (String linea: transaccionesCSV){
                String[] valores = linea.split(",");

                Usuario usuario = usuarioDAO.findById(Long.parseLong(valores[1]));
                Activo activo = activoDAO.findById(Long.parseLong(valores[2]));

                Transaccion transaccion = new Transaccion(linea, usuario, activo);

                transaccionDAO.save(transaccion);
            }

            System.out.println("Datos cargados a la BD correctamente");

        } catch (IOException e){
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
