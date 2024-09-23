package ar.edu.utn.frc.backend.simulacroparcial.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = Address.TABLE_NAME)
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Address {
	public static final String TABLE_NAME = "address";

	@Id
	@Column(name = "address_id")
	Integer id;
	String address;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "city_id")
	City city;
	@Column(name = "postal_code")
	String postalCode;
	@Column(name = "last_update")
	LocalDateTime lastUpdate;

	public Address() {
		super();
	}

	public Address(Integer aId, final String aAddress, final City aCity, final String aPostalCode) {
		this();
		id = aId;
		address = aAddress;
		city = aCity;
		postalCode = aPostalCode;
		lastUpdate = LocalDateTime.now();
	}

	public void update(String aAddress, City aCity, String aPostalCode) {
		this.address = aAddress;
		this.city = aCity;
		this.postalCode = aPostalCode;
		this.lastUpdate = LocalDateTime.now();
	}

	public void update(String aAddress, String aPostalCode) {
		this.address = aAddress;
		this.postalCode = aPostalCode;
		this.lastUpdate = LocalDateTime.now();
	}
}
