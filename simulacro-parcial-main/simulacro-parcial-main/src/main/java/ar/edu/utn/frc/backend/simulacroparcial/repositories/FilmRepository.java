package ar.edu.utn.frc.backend.simulacroparcial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.utn.frc.backend.simulacroparcial.model.Film;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {
}
