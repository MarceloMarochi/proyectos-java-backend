package utn.frc.backend.tutor.sac.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "materia")
public class MateriaEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mid", nullable = false)
    private Integer mid;
    @Basic
    @Column(name = "nombre", nullable = false, length = 64)
    private String nombre;
    @Basic
    @Column(name = "descripcion", nullable = true, length = -1)
    private String descripcion;
    @ManyToMany(
            //cascade = {
            //        CascadeType.PERSIST,
            //        CascadeType.MERGE,
            //        CascadeType.REFRESH
            //},
            //mappedBy = "materias"
    )
    @JoinTable(
            name = "materia_docentes",
            joinColumns = @JoinColumn(name = "mid"),
            inverseJoinColumns = @JoinColumn(name = "pid")
    )
    @JsonIgnore
    private Set<DocenteEntity> docentes = new HashSet<>();

    public MateriaEntity(Integer mid, String nombre, String descripcion) {
        this.mid = mid;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public MateriaEntity update(MateriaEntity materia) {
        nombre = materia.nombre;
        descripcion = materia.descripcion;
        return this;
    }

    public boolean addDocente(DocenteEntity docente) {
        return docentes.add(docente);
    }

    public boolean removeDocente(Integer pid) {
        DocenteEntity docente = docentes.stream().filter(d -> d.getPid() == pid).findFirst().orElse(null);
        return docente == null
                ? false
                : docentes.remove(docente);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MateriaEntity materia)) return false;

        return Objects.equals(mid, materia.mid);
    }

    @Override
    public int hashCode() {
        return mid != null ? mid.hashCode() : 0;
    }
}
