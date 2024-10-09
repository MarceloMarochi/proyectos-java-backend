package ar.frc.utn.backend.archivoCSV;

import ar.frc.utn.backend.Main;
import ar.frc.utn.backend.entidades.Alumno;
import ar.frc.utn.backend.entidades.Inasistencia;
import ar.frc.utn.backend.entidades.Tipo;
import ar.frc.utn.backend.repositorios.AlumnoRepository;
import ar.frc.utn.backend.repositorios.InnasistenciaRepository;
import ar.frc.utn.backend.repositorios.TipoRepository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ArchivoCSV {

    public static void lecturaArchivoCSV(InnasistenciaRepository inasistenciaRepository, AlumnoRepository alumnoRepository, TipoRepository tipoRepository) {

        URL locationFile = Main.class.getResource("/inasistencias.csv");

        try {

            List<String> lineas = Files.readAllLines(Paths.get(locationFile.toURI()));

            for (String linea : lineas){
                if (!linea.startsWith("Name,Type")){
                    String[] valores = linea.split(",");
                    String nombre = valores[0];
                    String type = valores[1];
                    Integer justificado = Integer.parseInt(valores[2]);
                    Double cantidad = Double.parseDouble(valores[3]);

                    Alumno alumno = alumnoRepository.findByName(nombre);
                    if (alumno == null){
                        Alumno new_alumno = new Alumno(nombre);
                        alumno = alumnoRepository.save(new_alumno);
                    }

                    Tipo tipo = tipoRepository.findByType(type);
                    if (tipo == null){
                        Tipo new_tipo = new Tipo(type);
                        tipo = tipoRepository.save(new_tipo);
                    }

                    Inasistencia new_inasistencia = new Inasistencia(justificado, cantidad, alumno, tipo);
                    inasistenciaRepository.save(new_inasistencia);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

}
