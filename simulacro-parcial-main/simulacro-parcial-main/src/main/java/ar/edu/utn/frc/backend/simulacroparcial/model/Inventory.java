package ar.edu.utn.frc.backend.simulacroparcial.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "inventory")
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Inventory {
	@Id
	@Column(name = "inventory_id")
	Integer id;
	@OneToOne
	@JoinColumn(name = "film_id")
	Film film;
	@ManyToOne
	@JoinColumn(name = "store_id")
	Store store;
}
