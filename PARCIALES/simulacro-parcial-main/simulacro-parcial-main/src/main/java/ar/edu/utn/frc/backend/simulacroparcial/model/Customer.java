package ar.edu.utn.frc.backend.simulacroparcial.model;



import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
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

@Table(name = Customer.TABLE_NAME)
@Entity
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {

	public static final String TABLE_NAME = "customer";
	@Id
	@Column(name = "customer_id")
	Integer id;
	@Column(name = "first_name")
	String firstName;
	@Column(name = "last_name")
	String lastName;
	String email;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	Address address;
	boolean active;
	@Column(name = "create_date")
	LocalDateTime createdDate;
	//No me interesa traer el Store cuando busco al Customer
	@Column(name = "store_id")
	Integer storeId;
	@Column(name = "last_update")
	LocalDateTime lastUpdate;

	public Customer() {
		super();
	}

	public Customer(Integer aId, String aFirstName, String aLastName, String aEmail, Address aAddress, Integer aStoreId) {
		super();
		id = aId;
		firstName = aFirstName;
		lastName = aLastName;
		email = aEmail;
		address = aAddress;
		active = true;
		storeId = aStoreId;
		createdDate = LocalDateTime.now();
		lastUpdate = LocalDateTime.now();
	}

	public void update(String aFirstName, String aLastName, String aEmail, String aAddress, String aPostalCode) {
		this.firstName = aFirstName;
		this.lastName = aLastName;
		this.email = aEmail;
		this.address.update(aAddress, aPostalCode);
		this.lastUpdate = LocalDateTime.now();
	}
}
