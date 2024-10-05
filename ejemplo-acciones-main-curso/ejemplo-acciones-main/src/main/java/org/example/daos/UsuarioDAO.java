package org.example.daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.common.EntityManagerProvider;
import org.example.entities.Usuario;

import java.util.List;

public class UsuarioDAO {
    final private EntityManager em = EntityManagerProvider.getEntityManager();

    public UsuarioDAO(){

    }

    public void save(Usuario usuario){
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
    }

    public Usuario findById(Long usuarioId){
        return em.find(Usuario.class, usuarioId);
    }
    public List<Usuario> findAll(){
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
        return query.getResultList();
    }

    public void update(Usuario usuario){
        em.getTransaction().begin();
        em.merge(usuario);
        em.getTransaction().commit();
    }

    public void delete(Long usuarioId){
        em.getTransaction().begin();

        Usuario usuario = this.findById(usuarioId);

        if(usuario != null){
            em.remove(usuario);
        }

        em.getTransaction().commit();
    }
    /*
    Este método realiza una consulta en la base de datos para obtener una lista de usuarios
    cuyos nombres contienen una subcadena específica. Utiliza JPQL para buscar coincidencias
    en el campo nombre de la entidad Usuario, empleando el operador LIKE para permitir búsquedas
    parciales. Se añade el comodín % alrededor del valor de nombre para que coincida con cualquier
    parte del nombre (al principio, en medio o al final). Finalmente, devuelve una lista de
    usuarios que cumplen con este criterio.
     */
    public List<Usuario> findByNombre(String nombre){
        String jpql = "SELECT u FROM Usuario u WHERE u.nombre LIKE :nombre";

        TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
        query.setParameter("nombre", "%" + nombre + "%");

        return query.getResultList();
    }
}
