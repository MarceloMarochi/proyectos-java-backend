package ar.edu.utn.frc.backend.simulacroparcial.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "country")
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Country {
	@Id
	@Column(name = "country_id")
	Integer id;
	@Column(name = "country")
	String name;
}
