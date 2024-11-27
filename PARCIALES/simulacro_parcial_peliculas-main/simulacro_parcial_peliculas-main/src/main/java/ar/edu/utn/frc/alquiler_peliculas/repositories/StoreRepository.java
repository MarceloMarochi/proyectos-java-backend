package ar.edu.utn.frc.alquiler_peliculas.repositories;

import ar.edu.utn.frc.alquiler_peliculas.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    // ... otros métodos de consulta personalizados según sea necesario

}
