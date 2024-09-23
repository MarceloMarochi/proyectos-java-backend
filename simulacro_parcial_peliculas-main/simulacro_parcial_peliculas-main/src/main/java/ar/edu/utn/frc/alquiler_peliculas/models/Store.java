package ar.edu.utn.frc.alquiler_peliculas.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "store")
@Data
@NoArgsConstructor
public class Store {

    @Id
    @GeneratedValue(generator = "store")
    @TableGenerator(name = "store", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="store_id",
            initialValue=1, allocationSize=1)
    private long storeId;

    @Column(name = "manager_staff_id")
    private long managerStaffId;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

}
