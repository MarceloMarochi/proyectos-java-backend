package ar.edu.utn.frc.alquiler_peliculas.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "city")
@Data
@NoArgsConstructor
public class City {

    @Id
    @GeneratedValue(generator = "city")
    @TableGenerator(name = "city", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="city_id",
            initialValue=1, allocationSize=1)
    private long cityId;

    @Column(name = "city")
    private String city;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;


}
