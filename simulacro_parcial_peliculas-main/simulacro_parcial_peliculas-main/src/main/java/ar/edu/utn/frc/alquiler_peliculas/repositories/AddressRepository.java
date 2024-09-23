package ar.edu.utn.frc.alquiler_peliculas.repositories;

import ar.edu.utn.frc.alquiler_peliculas.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    // ... otros métodos de consulta personalizados según sea necesario

}
