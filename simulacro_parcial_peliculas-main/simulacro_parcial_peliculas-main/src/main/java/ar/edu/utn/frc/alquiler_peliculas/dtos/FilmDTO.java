package ar.edu.utn.frc.alquiler_peliculas.dtos;

import lombok.Data;

@Data
public class FilmDTO {

    private long filmId;
    private String title;
    private String description;
    private long releaseYear;
    private long languageId;
    private long rentalDuration;
    private double rentalRate;
    private long length;
    private double replacementCost;
    private String rating;
    private String specialFeatures;

}
