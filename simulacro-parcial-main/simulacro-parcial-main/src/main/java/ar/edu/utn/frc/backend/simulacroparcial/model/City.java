package ar.edu.utn.frc.backend.simulacroparcial.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Table(name = "city")
@Entity
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class City {
	@Id
	@Column(name = "city_id")
	Integer id;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "country_id")
	Country country;
	@Column(name = "city")
	String name;
}
