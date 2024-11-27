package ar.edu.utn.frc.alquiler_peliculas.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "language")
@Data
@NoArgsConstructor
public class Language {

    @Id
    @GeneratedValue(generator = "language")
    @TableGenerator(name = "language", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="language_id",
            initialValue=1, allocationSize=1)
    private long languageId;

    @Column(name = "name")
    private String name;

}
