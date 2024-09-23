package ar.edu.utn.frc.backend.simulacroparcial.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ar.edu.utn.frc.backend.simulacroparcial.model.Film;
import ar.edu.utn.frc.backend.simulacroparcial.repositories.FilmRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService{

	FilmRepository filmRepository;

	@Override public Film create(final String title,
								 final String releaseYear,
								 final Integer languageId,
								 final Integer rentalDuration,
								 final Double rentalRate,
								 final Integer length,
								 final Double replacementCost,
								 final String rating,
								 final String specialFeatures,
								 final List<Integer> actorsIds) {
		return null;
	}

	@Override public List<Film> findAll() {
		return null;
	}

	@Override public Optional<Film> findById(final Integer id) {
		return Optional.empty();
	}

	@Override public void delete(final Integer id) {

	}

	@Override public void update(final Integer id,
								 final String title,
								 final String releaseYear,
								 final Integer languageId,
								 final Integer rentalDuration,
								 final Double rentalRate,
								 final Integer length,
								 final Double replacementCost,
								 final String rating,
								 final String specialFeatures,
								 final List<Integer> actorsIds) {

	}
}
