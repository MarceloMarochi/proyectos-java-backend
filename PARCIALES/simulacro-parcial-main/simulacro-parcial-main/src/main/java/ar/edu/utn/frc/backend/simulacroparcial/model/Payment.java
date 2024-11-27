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
@Table(name = "payment")
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Payment {
	@Id
	@Column(name = "payment_id")
	Integer id;
	@OneToOne
	@JoinColumn(name = "customer_id")
	Customer customer;
	@OneToOne
	@JoinColumn(name = "staff_id")
	Staff staff;
	@OneToOne
	@JoinColumn(name = "rental_id")
	Rental rental;
	@Column(name = "payment_date")
	LocalDateTime paymentDate;
	Double amount;
}
