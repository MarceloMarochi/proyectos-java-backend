package ar.edu.utn.frc.alquiler_peliculas.repositories;

import ar.edu.utn.frc.alquiler_peliculas.models.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    // ... otros métodos de consulta personalizados según sea necesario

}
