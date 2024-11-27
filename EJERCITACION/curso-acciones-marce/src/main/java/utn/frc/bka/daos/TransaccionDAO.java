package utn.frc.bka.daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import utn.frc.bka.common.EntityManagerProvider;
import utn.frc.bka.entidades.Activo;
import utn.frc.bka.entidades.Transaccion;

import java.time.LocalDate;
import java.util.List;

public class TransaccionDAO {
    private final EntityManager em = EntityManagerProvider.getEntityManager();

    public TransaccionDAO(){

    }

    public void save(Transaccion transaccion){
        em.getTransaction().begin();
        em.persist(transaccion);
        em.getTransaction().commit();
    }

    public Transaccion findById(Long transaccionId){
        return em.find(Transaccion.class, transaccionId);
    }
    public List<Transaccion> findAll(){
        TypedQuery<Transaccion> query = em.createQuery("SELECT t FROM Transaccion t", Transaccion.class);
        return query.getResultList();
    }

    public void update(Transaccion transaccion){
        em.getTransaction().begin();
        em.merge(transaccion);
        em.getTransaction().commit();
    }

    public void delete(Long transaccionId){
        em.getTransaction().begin();

        Transaccion transaccion = this.findById(transaccionId);

        if(transaccion != null){
            em.remove(transaccion);
        }

        em.getTransaction().commit();
    }


    // Consulta personalizada.
    public List <Transaccion> findByActivoAndRangoFechas(Activo activo, LocalDate fechaInicio, LocalDate fechaFin) {
        String jpql = "SELECT t FROM Transaccion t WHERE t.activo = :activoT AND t.fecha BETWEEN :fechaInicioT AND :fechaFinT";

        TypedQuery<Transaccion> query = em.createQuery(jpql, Transaccion.class);
        query.setParameter("activoT", activo);
        query.setParameter("fechaInicioT", fechaInicio);
        query.setParameter("fechaFinT", fechaFin);

        return query.getResultList();
    }
}
