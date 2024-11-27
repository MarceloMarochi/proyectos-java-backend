package ejercicio1.entiti;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name="estudiantes")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "estudiante", fetch = FetchType.LAZY)
    private List<Inasistencia> inasistencias;

    public Estudiante(Long id, String nombre, List<Inasistencia> inasistencias) {
        this.id = id;
        this.nombre = nombre;
        this.inasistencias = inasistencias;
    }

    public Estudiante() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Inasistencia> getInasistencias() {
        return inasistencias;
    }

    public void setInasistencias(List<Inasistencia> inasistencias) {
        this.inasistencias = inasistencias;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", inasistencias=" + inasistencias +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estudiante that = (Estudiante) o;
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre) && Objects.equals(inasistencias, that.inasistencias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, inasistencias);
    }
}
