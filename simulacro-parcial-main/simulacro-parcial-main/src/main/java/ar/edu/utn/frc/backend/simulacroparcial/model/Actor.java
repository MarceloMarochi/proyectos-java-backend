package ar.edu.utn.frc.backend.simulacroparcial.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "actor")
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Actor {
	@Id
	@Column(name = "actor_id")
	Integer id;
	@Column(name = "first_name")
	String firstName;
	@Column(name = "last_name")
	String lastName;
}
