package ejercicio1.archivoCSV;

import ejercicio1.Main;
import ejercicio1.entiti.Estudiante;
import ejercicio1.entiti.Inasistencia;
import ejercicio1.entiti.Tipo;
import ejercicio1.repository.EstudianteRepository;
import ejercicio1.repository.InasistenciaRepository;
import ejercicio1.repository.TipoRepository;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;


public class ArchivoCSV {

    public static void cargarArchivosCSV(String rutaArchivo, InasistenciaRepository inasistenciaRepository, EstudianteRepository estudianteRepository, TipoRepository tipoRepository) {

        URL locationFile = Main.class.getResource("inasistencias.csv");
        try {

            List<String> lineas = Files.readAllLines(Paths.get(rutaArchivo));
            // List<String> lineas = Files.readAllLines(Paths.get(locationFile.toURI()));

            List<String> inasistenciasCSV = new ArrayList<>();

            for (String linea : lineas) {
                if (linea.startsWith("Name")) {
                    continue;
                } else if (linea.isEmpty()) {
                    inasistenciasCSV.add(linea);

                }
            }

            for (String linea : inasistenciasCSV) {
                String[] valores = linea.split(",");
                Estudiante estudiante = new Estudiante();
                estudiante.setNombre(valores[0]);

                Tipo tipo = new Tipo();
                tipo.setNombre(valores[1]);

                Inasistencia inasistencia = new Inasistencia(linea, estudiante, tipo);

                inasistenciaRepository.save(inasistencia);
            }


        } catch (IOException e){
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
