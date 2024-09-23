package ar.edu.utn.frc.backend.simulacroparcial.model;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "rental")
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Rental {
	@Id
	@Column(name = "rental_id")
	Integer id;
	@Column(name = "rental_date")
	LocalDateTime rentalDate;
	@OneToOne
	@JoinColumn(name = "inventory_id")
	Inventory inventory;
	@OneToOne
	@JoinColumn(name = "customer_id")
	Customer customer;
	@Column(name = "return_date")
	LocalDateTime returnDate;
	@OneToOne
	@JoinColumn(name = "staff_id")
	Staff staff;
}
