package utnfc.isi.backend.parcial;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import utnfc.isi.backend.parcial.entities.Compania;
import utnfc.isi.backend.parcial.entities.Cuenta;
import utnfc.isi.backend.parcial.entities.Empleado;
import utnfc.isi.backend.parcial.services.OperacionesService;

import java.util.List;
import java.util.Set;

public class Main {



    public static void main(String[] args) throws Exception {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("parcial-pu");
        EntityManager em = emf.createEntityManager();

        OperacionesLoaderCsv loader = new OperacionesLoaderCsv();
        loader.limpiarDB(em);
        loader.loadData("D:\\Documentos\\INGENIERÍA EN SISTEMAS\\3° Año\\BACKEND\\Proyectos\\marochi85264_recuperatorio-ENTREGADO\\marochi85264_recuperatorio-main\\proyecto-parcial\\data\\operaciones.csv", em);

        System.out.println("Datos cargados exitosamente");


        OperacionesService service = new OperacionesService(em);


        System.out.println("\n ------------------------- Punto 2. MOSTRAR POR MONEDAS (Nombre, Cantidad cuentas y saldo acumulado) -------------------------");
        service.mostrarResumenMonedas(em);

        System.out.println("\n ------------------------- Punto 3. MOSTRAR POR COMPANIA (Nombre, Total comisiones en dolares) -------------------------");
        List<Compania> companias = service.getAllCompania();
        for (Compania compania : companias) {
            String line = service.getNombreYTotalComisionesPorCompania(compania.getCompania_id(), em);
            System.out.println(line);
        }

        System.out.println("\n ------------------------- Punto 4. MOSTRAR POR CUENTAS CON MAYOR SALDO Y MENOR SALDO EN DOLARES -------------------------");
        Cuenta cuentaMenorId = service.getCuentaMenorSaldo();
        Cuenta cuentaMayorId = service.getCuentaMayorSaldo();
        System.out.println("Id de la cuenta con mayor saldo en dolares: " + cuentaMayorId.getCuenta_id() + " Saldo en dolares: " + cuentaMayorId.conversionSaldoUSD() );
        System.out.println("Id de la cuenta con menor saldo en dolares: " + cuentaMenorId.getCuenta_id() + " Saldo en dolares: " + cuentaMenorId.conversionSaldoUSD() );

        em.close();
        emf.close();

    }
}
