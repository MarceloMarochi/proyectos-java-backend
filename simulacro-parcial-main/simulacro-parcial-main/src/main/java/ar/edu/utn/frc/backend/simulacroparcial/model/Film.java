package ar.edu.utn.frc.backend.simulacroparcial.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "film")
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Film {
	@Id
	@Column(name = "film_id")
	Integer id;
	String title;
	@Column(name = "release_year")
	String releaseYear;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "language_id")
	Language language;
	@Column(name = "rental_duration")
	Integer rentalDuration;
	@Column(name = "rental_rate")
	Double rentalRate;
	Integer length;
	@Column(name = "replacement_cost")
	Double replacementCost;
	String rating;
	@Column(name = "special_features")
	String specialFeatures;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinTable(name = "film_actor",
		joinColumns = @JoinColumn(name = "film_id"),
		inverseJoinColumns = @JoinColumn(name = "actor_id"))
	private List<Actor> actors;
}
