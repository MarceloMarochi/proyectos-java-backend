package ar.edu.utn.frc.backend.simulacroparcial.services;

import java.util.Optional;

import ar.edu.utn.frc.backend.simulacroparcial.model.Staff;

public interface StaffService {

	Optional<Staff> findById(Integer id);
}
