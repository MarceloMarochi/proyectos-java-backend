package utn.frc.backend.tutor.sac.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter @Setter @NoArgsConstructor @ToString
@Entity
@Table(name = "alumno")
public class AlumnoEntity {
    @Id
    @Column(name = "pid", nullable = false)
    private Integer pid;

    @Basic
    @Column(name = "legajo", nullable = false, length = 16)
    private String legajo;

    @OneToOne
    @JoinColumn(name = "pid")
    private PersonaEntity persona;

    //@OneToMany(mappedBy = "alumno")
    //@JsonIgnore
    //private Set<Inscripcion> inscripciones = new HashSet<>();

    public AlumnoEntity(Integer pid, String legajo) {
        this.pid = pid;
        this.legajo = legajo;
    }

    public AlumnoEntity(PersonaEntity persona, String legajo) {
        this.pid = persona.getPid();
        this.legajo = legajo;
        this.persona = persona;
    }

    public AlumnoEntity update(AlumnoEntity alumno) {
        legajo = alumno.legajo;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AlumnoEntity alumno)) return false;

        return Objects.equals(pid, alumno.pid);
    }

    @Override
    public int hashCode() {
        return pid != null ? pid.hashCode() : 0;
    }
}
