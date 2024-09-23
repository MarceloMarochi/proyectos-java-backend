package ar.edu.utn.frc.backend.simulacroparcial.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ar.edu.utn.frc.backend.simulacroparcial.model.City;
import ar.edu.utn.frc.backend.simulacroparcial.repositories.CityRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CityServiceImpl implements CityService{

	CityRepository cityRepository;

	@Override public Optional<City> findById(final Integer id) {
		return cityRepository.findById(id);
	}
}
