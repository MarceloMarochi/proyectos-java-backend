package ar.edu.utn.frc.alquiler_peliculas.services;

import ar.edu.utn.frc.alquiler_peliculas.dtos.FilmDTO;
import ar.edu.utn.frc.alquiler_peliculas.models.Film;
import ar.edu.utn.frc.alquiler_peliculas.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;

    public List<FilmDTO> findAll() {
        List<Film> films = filmRepository.findAll();
        return films.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public FilmDTO findById(Long id) {
        Optional<Film> film = filmRepository.findById(id);
        return film.map(this::convertToDto).orElse(null);
    }

    public FilmDTO save(FilmDTO filmDto) {
        Film film = convertToEntity(filmDto);
        Film savedFilm = filmRepository.save(film);
        return convertToDto(savedFilm);
    }

    public void deleteById(Long id) {
        filmRepository.deleteById(id);
    }

    public FilmDTO update(Long id, FilmDTO filmDto) {
        Optional<Film> existingFilm = filmRepository.findById(id);
        if (existingFilm.isPresent()) {
            Film film = convertToEntity(filmDto);
            film.setFilmId(id);
            Film updatedFilm = filmRepository.save(film);
            return convertToDto(updatedFilm);
        } else {
            return null;
        }
    }

    private FilmDTO convertToDto(Film film) {
        FilmDTO filmDto = new FilmDTO();
        filmDto.setFilmId(film.getFilmId());
        filmDto.setTitle(film.getTitle());
        filmDto.setDescription(film.getDescription());
        filmDto.setReleaseYear(film.getReleaseYear());
        filmDto.setLanguageId(film.getLanguage().getLanguageId());
        filmDto.setRentalDuration(film.getRentalDuration());
        filmDto.setRentalRate(film.getRentalRate());
        filmDto.setLength(film.getLength());
        filmDto.setReplacementCost(film.getReplacementCost());
        filmDto.setRating(film.getRating());
        filmDto.setSpecialFeatures(film.getSpecialFeatures());
        return filmDto;
    }

    private Film convertToEntity(FilmDTO filmDto) {
        Film film = new Film();
        film.setFilmId(filmDto.getFilmId());
        film.setTitle(filmDto.getTitle());
        film.setDescription(filmDto.getDescription());
        film.setReleaseYear(filmDto.getReleaseYear());
        film.setRentalDuration(filmDto.getRentalDuration());
        film.setRentalRate(filmDto.getRentalRate());
        film.setLength(filmDto.getLength());
        film.setReplacementCost(filmDto.getReplacementCost());
        film.setRating(filmDto.getRating());
        film.setSpecialFeatures(filmDto.getSpecialFeatures());
        return film;
    }

}
