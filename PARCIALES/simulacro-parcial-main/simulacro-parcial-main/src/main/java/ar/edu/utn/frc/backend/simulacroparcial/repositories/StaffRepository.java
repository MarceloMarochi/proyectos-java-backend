package ar.edu.utn.frc.backend.simulacroparcial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.utn.frc.backend.simulacroparcial.model.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
}
