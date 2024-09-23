package ar.edu.utn.frc.alquiler_peliculas.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "film")
@Data
@NoArgsConstructor
public class Film {

    @Id
    @GeneratedValue(generator = "film")
    @TableGenerator(name = "film", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="film_id",
            initialValue=1, allocationSize=1)
    private long filmId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "release_year")
    private long releaseYear;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @Column(name = "rental_duration")
    private long rentalDuration;

    @Column(name = "rental_rate")
    private double rentalRate;

    @Column(name = "length")
    private long length;

    @Column(name = "replacement_cost")
    private double replacementCost;

    @Column(name = "rating")
    private String rating;

    @Column(name = "special_features")
    private String specialFeatures;

    @OneToMany(mappedBy = "film")
    private Set<Inventory> inventories;

}
