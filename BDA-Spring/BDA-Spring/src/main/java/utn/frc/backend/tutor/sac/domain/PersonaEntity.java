package utn.frc.backend.tutor.sac.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter @Setter @NoArgsConstructor @ToString
@Entity
@Table(name = "persona")
public class PersonaEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pid", nullable = false)
    private Integer pid;
    @Basic
    @Column(name = "dni", nullable = false, length = 64)
    private String dni;
    @Basic
    @Column(name = "apellido", nullable = false, length = 64)
    private String apellido;
    @Basic
    @Column(name = "nombre", nullable = false, length = 64)
    private String nombre;

    public PersonaEntity(Integer pid, String dni, String apellido, String nombre) {
        this.pid = pid;
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
    }

    public PersonaEntity update(PersonaEntity persona) {
        dni = persona.dni;
        apellido = persona.apellido;
        nombre = persona.nombre;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonaEntity that)) return false;

        return Objects.equals(pid, that.pid);
    }

    @Override
    public int hashCode() {
        return pid != null ? pid.hashCode() : 0;
    }
}
