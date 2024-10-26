package utn.frc.backend.tutor.sac.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter @Setter @NoArgsConstructor @ToString
@Entity
@Table(name = "docente")
public class DocenteEntity {
    @Id @Column(name = "pid", nullable = false)
    private Integer pid;

    @Basic @Column(name = "legajo", nullable = false, length = 16)
    private String legajo;

    @OneToOne @JoinColumn(name = "pid")
    private PersonaEntity persona;

    @ManyToMany(
            //cascade = {
            //        CascadeType.PERSIST,
            //        CascadeType.MERGE,
            //        CascadeType.REFRESH
            //},
            mappedBy = "docentes"
    )
    private Set<MateriaEntity> materias = new HashSet<>();

    public DocenteEntity(Integer pid, String legajo) {
        this.pid = pid;
        this.legajo = legajo;
    }

    public DocenteEntity(PersonaEntity persona, String legajo) {
        this.pid = persona.getPid();
        this.legajo = legajo;
        this.persona = persona;
    }

    public DocenteEntity update(DocenteEntity docente) {
        legajo = docente.legajo;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DocenteEntity that)) return false;

        return Objects.equals(pid, that.pid);
    }

    @Override
    public int hashCode() {
        return pid != null ? pid.hashCode() : 0;
    }
}
