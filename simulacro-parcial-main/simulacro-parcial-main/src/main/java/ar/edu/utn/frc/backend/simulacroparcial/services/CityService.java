package ar.edu.utn.frc.backend.simulacroparcial.services;

import java.util.Optional;

import ar.edu.utn.frc.backend.simulacroparcial.model.City;

public interface CityService {

	Optional<City> findById(Integer id);
}
