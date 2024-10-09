package ar.frc.utn.backend;


import ar.frc.utn.backend.archivoCSV.ArchivoCSV;
import ar.frc.utn.backend.connectionManager.EntityManagerProvider;
import ar.frc.utn.backend.entidades.Inasistencia;
import ar.frc.utn.backend.repositorios.AlumnoRepository;
import ar.frc.utn.backend.repositorios.InnasistenciaRepository;
import ar.frc.utn.backend.repositorios.TipoRepository;

import java.util.List;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args ) {

        AlumnoRepository alumnoRepository = new AlumnoRepository();
        InnasistenciaRepository inasitenciRepository = new InnasistenciaRepository();
        TipoRepository tipoRepository = new TipoRepository();

        ArchivoCSV.lecturaArchivoCSV(inasitenciRepository, alumnoRepository, tipoRepository);

        List<Inasistencia> inasistencias = inasitenciRepository.findAll();

        for (Inasistencia inasistencia : inasistencias){
            System.out.println(inasistencia);
        }

        System.out.println("------------------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------------------");

        inasistencias.stream()
                        .filter(estudiante -> estudiante.getCantidad() < 5 && estudiante.getJustificado() == 1)
                                . forEach(estudiante -> System.out.println(estudiante));

        EntityManagerProvider.close();

    }
}
