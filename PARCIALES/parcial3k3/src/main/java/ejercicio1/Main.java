package ejercicio1;

import ejercicio1.archivoCSV.ArchivoCSV;
import ejercicio1.common.EntityManagerProvider;
import ejercicio1.entiti.Inasistencia;
import ejercicio1.repository.EstudianteRepository;
import ejercicio1.repository.InasistenciaRepository;
import ejercicio1.repository.TipoRepository;

import java.util.List;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args ) {
        InasistenciaRepository repo = new InasistenciaRepository();
        TipoRepository repo2 = new TipoRepository();
        EstudianteRepository repo3 = new EstudianteRepository();

        ArchivoCSV.cargarArchivosCSV("inasistencias.csv",repo, repo3, repo2);

        List<Inasistencia> inasistencia = repo.findAll();
        for (Inasistencia inasistencia1 : inasistencia) {
            System.out.println(inasistencia1);
        }
        EntityManagerProvider.close();
    }
}
