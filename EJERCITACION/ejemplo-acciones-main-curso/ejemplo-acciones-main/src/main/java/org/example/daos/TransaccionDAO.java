package org.example.daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.common.EntityManagerProvider;
import org.example.entities.Activo;
import org.example.entities.Transaccion;

import java.time.LocalDate;
import java.util.List;

public class TransaccionDAO {
    final private EntityManager em = EntityManagerProvider.getEntityManager();

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

    /*
    Esta consulta realiza una búsqueda en la base de datos para obtener una lista de transacciones que
    cumplen con los siguientes criterios:

    Filtrar por activo: La consulta busca todas las transacciones que están asociadas con un activo
    específico, pasado como parámetro (activo). Este filtro se aplica usando t.activo = :activo,
    donde :activo es el parámetro que se inyecta en la consulta.

    Filtrar por rango de fechas: La consulta también filtra las transacciones según un rango de fechas.
    Este rango está definido por dos parámetros: fechaInicio y fechaFin. El filtro se aplica con la condición t.
    fecha BETWEEN :fechaInicio AND :fechaFin, que selecciona las transacciones cuya fecha cae dentro
    del rango especificado, incluidas las fechas de inicio y fin.
     */
    public List<Transaccion> findByActivoAndRangoFechas(Activo activo, LocalDate fechaInicio, LocalDate fechaFin) {
        // Usar JPQL para hacer la consulta con filtros de activo y fecha
        String jpql = "SELECT t FROM Transaccion t WHERE t.activo = :activo AND t.fecha BETWEEN :fechaInicio AND :fechaFin";

        TypedQuery<Transaccion> query = em.createQuery(jpql, Transaccion.class);
        query.setParameter("activo", activo);
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);

        return query.getResultList();
    }
}
