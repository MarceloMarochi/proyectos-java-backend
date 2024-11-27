package ar.edu.utn.frc.backend.simulacroparcial.model;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Table(name = Store.TABLE_NAME)
@Entity
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Store {
	public static final String TABLE_NAME = "store";
	@Id
	@Column(name = "store_id")
	Integer id;
	@OneToOne
	@JoinColumn(name = "manager_staff_id")
	Staff manager;
	@OneToMany(mappedBy = "store")
	List<Staff> staff;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	Address address;
	@OneToMany(mappedBy = "store")
	List<Inventory> inventories;
	@Column(name = "last_update")
	LocalDateTime lastUpdated;

	public Store() {
		super();
	}

	public Store(Integer aId, Staff aManager, Address aAddress) {
		this();
		id = aId;
		manager = aManager;
		address = aAddress;
		inventories = new LinkedList<>();
		staff = new LinkedList<>();
		lastUpdated = LocalDateTime.now();
	}

	public void changeManager(Staff aManager) {
		manager = aManager;
		lastUpdated = LocalDateTime.now();
	}
}
