package utn.frc.bka.archivoCSV;

import jakarta.persistence.criteria.CriteriaBuilder;
import utn.frc.bka.Main;
import utn.frc.bka.entity.Estudiante;
import utn.frc.bka.entity.Inasistencia;
import utn.frc.bka.entity.Tipo;
import utn.frc.bka.repository.EstudianteRepository;
import utn.frc.bka.repository.InastenciaRepository;
import utn.frc.bka.repository.TipoRepository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ArchivoCSV {

    public static void cargarArchivoCSV(InastenciaRepository inasistenciaRepository, EstudianteRepository estudianteRepository, TipoRepository tipoRepository) {

        URL locationFile = Main.class.getResource("/inasistencias.csv");

        try {

            List<String> lineas = Files.readAllLines(Paths.get(locationFile.toURI()));

            List<String> inasistenciasCSV = new ArrayList<>();

            for (String linea : lineas){
                if (linea.startsWith("Name")){
                    continue;
                } else if (!linea.isEmpty()){
                    inasistenciasCSV.add(linea);
                }
            }

            // Base de datos

            for (String linea : inasistenciasCSV) {
                String[] valores = linea.split(",");
                Estudiante estudiante = new Estudiante();
                estudiante.setNombre(valores[0]);
                Tipo tipo = new Tipo();
                tipo.setNombre(valores[1]);

                Inasistencia inasistencia = new Inasistencia(linea, estudiante, tipo);

                inasistenciaRepository.save(inasistencia);

            }

            System.out.println("Datos cargados correctamente");

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

}
