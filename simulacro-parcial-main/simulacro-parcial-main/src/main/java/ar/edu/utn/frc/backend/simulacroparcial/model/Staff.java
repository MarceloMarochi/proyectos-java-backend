package ar.edu.utn.frc.backend.simulacroparcial.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Table(name = "staff")
@Entity
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Staff {
	@Id
	@Column(name = "staff_id")
	Integer id;
	@Column(name = "first_name")
	String firstName;
	@Column(name = "last_name")
	String lastName;
	@JoinColumn(name = "address_id")
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	Address address;
	String email;
	boolean active;
	String username;
	String password;
	@ManyToOne
	@JoinColumn(name = "store_id")
	Store store;
}
