package ar.edu.utn.frc.backend.simulacroparcial.services;

import java.util.List;
import java.util.Optional;

import ar.edu.utn.frc.backend.simulacroparcial.model.Film;

public interface FilmService {

	Film create(String title,
				String releaseYear,
				Integer languageId,
				Integer rentalDuration,
				Double rentalRate,
				Integer length,
				Double replacementCost,
				String rating,
				String specialFeatures,
				List<Integer> actorsIds);

	List<Film> findAll();

	Optional<Film> findById(Integer id);

	void delete(Integer id);

	void update(Integer id, String title,
				String releaseYear,
				Integer languageId,
				Integer rentalDuration,
				Double rentalRate,
				Integer length,
				Double replacementCost,
				String rating,
				String specialFeatures,
				List<Integer> actorsIds);


}
