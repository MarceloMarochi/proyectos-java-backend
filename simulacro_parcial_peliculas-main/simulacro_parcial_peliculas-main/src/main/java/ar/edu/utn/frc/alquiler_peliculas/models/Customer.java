package ar.edu.utn.frc.alquiler_peliculas.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(generator = "customer")
    @TableGenerator(name = "customer", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="customer_id",
            initialValue=1, allocationSize=1)
    private long customerId;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "active")
    private boolean active;

    @Column(name = "create_date")
    private LocalDateTime createDate;

}
