package ar.edu.utn.frc.alquiler_peliculas.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "country")
@Data
@NoArgsConstructor
public class Country {

    @Id
    @GeneratedValue(generator = "country")
    @TableGenerator(name = "country", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="country_id",
            initialValue=1, allocationSize=1)
    private long countryId;

    @Column(name = "country")
    private String country;

}
