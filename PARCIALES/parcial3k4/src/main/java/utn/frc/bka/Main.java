package utn.frc.bka;

import utn.frc.bka.archivoCSV.ArchivoCSV;
import utn.frc.bka.common.EntintyMangaerProvider;
import utn.frc.bka.entity.Inasistencia;
import utn.frc.bka.entity.Tipo;
import utn.frc.bka.repository.EstudianteRepository;
import utn.frc.bka.repository.InastenciaRepository;
import utn.frc.bka.repository.TipoRepository;

import java.util.List;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args ) {

        InastenciaRepository inastencia = new InastenciaRepository();
        EstudianteRepository estudianteRepository = new EstudianteRepository();
        TipoRepository tipoRepository = new TipoRepository();

        ArchivoCSV.cargarArchivoCSV(inastencia, estudianteRepository, tipoRepository);

        List<Inasistencia> inasistencias = inastencia.findAll();

        for(Inasistencia inasistencia : inasistencias){
            System.out.println(inasistencia);
        }

        EntintyMangaerProvider.close();
    }
}
