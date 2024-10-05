package utn.frc.bka.daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import utn.frc.bka.common.EntityManagerProvider;
import utn.frc.bka.entidades.Activo;

import java.util.List;

public class ActivoDAO {
    // final es como const de javascript
    private final EntityManager em = EntityManagerProvider.getEntityManager();

    public ActivoDAO() {
    }

    public void save(Activo activo) {
        // Prepara la clase para una transaccion
        em.getTransaction().begin();

        // Metodo para guardar la base de datos persist()
        em.persist(activo);

        // Commit si esta tod o correcto
        em.getTransaction().commit();
    }

    // Buscar un activo por Id
    public Activo findById(Long activoId) {
        return em.find(Activo.class, activoId);
    }

    // Buscar todos los activos
    // Hacer una query, es decir una consulta personalizada
    public List<Activo> findAll() {
        String jpql = "SELECT a FROM Activo a";
        TypedQuery<Activo> query = em.createQuery(jpql, Activo.class);

        return query.getResultList();
    }

    // Obtener por parametro el activo
    public void update(Activo activo) {
        // Obtengo la transaccion
        em.getTransaction().begin();
        // Llama a merge en vez de persist. Es como que mergea los dato (actualiza)
        em.merge(activo);
        //commit
        em.getTransaction().commit();
    }

    // Obtengo el id para el delte
    public void delete(Long activoId) {
        // Obtengo la transaccion
        em.getTransaction().begin();
        // Reutilizo el metodo anterior
        Activo activo = findById(activoId);

        // Borro el activo
        if (activo != null) {
            em.remove(activo);
        }

        // commit
        em.getTransaction().commit();
    }
}
