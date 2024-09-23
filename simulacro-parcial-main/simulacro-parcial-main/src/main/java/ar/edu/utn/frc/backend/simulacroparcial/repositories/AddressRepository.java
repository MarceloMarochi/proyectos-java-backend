package ar.edu.utn.frc.backend.simulacroparcial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.utn.frc.backend.simulacroparcial.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
