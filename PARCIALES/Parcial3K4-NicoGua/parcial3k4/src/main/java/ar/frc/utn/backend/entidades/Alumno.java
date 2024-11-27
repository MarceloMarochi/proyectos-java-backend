package ar.frc.utn.backend.entidades;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "alumnos")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "alumno",fetch = FetchType.LAZY)
    private List<Inasistencia> inasistencias;

    public Alumno(){

    }

    public Alumno(String nombre){
        this.nombre = nombre;
    }

    public Alumno(Long id, String nombre, List<Inasistencia> inasistencias) {
        this.id = id;
        this.nombre = nombre;
        this.inasistencias = inasistencias;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alumno alumno = (Alumno) o;
        return Objects.equals(id, alumno.id) && Objects.equals(nombre, alumno.nombre) && Objects.equals(inasistencias, alumno.inasistencias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, inasistencias);
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
