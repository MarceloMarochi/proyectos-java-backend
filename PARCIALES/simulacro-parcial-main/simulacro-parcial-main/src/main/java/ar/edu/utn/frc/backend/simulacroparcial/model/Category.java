package ar.edu.utn.frc.backend.simulacroparcial.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Table(name = "category")
@Entity
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category {
	@Id
	@Column(name = "category_id")
	Integer id;
	String name;
}
