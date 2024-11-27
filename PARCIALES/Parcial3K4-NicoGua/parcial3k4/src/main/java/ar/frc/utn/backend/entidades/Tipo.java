package ar.frc.utn.backend.entidades;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tipos")
public class Tipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "tipo", fetch = FetchType.LAZY)
    private List<Inasistencia> inasistencias;

    public Tipo(){

    }

    public Tipo(String nombre){
        this.nombre = nombre;
    }

    public Tipo(Long id, String nombre, List<Inasistencia> inasistencias) {
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
        Tipo tipo = (Tipo) o;
        return Objects.equals(id, tipo.id) && Objects.equals(nombre, tipo.nombre) && Objects.equals(inasistencias, tipo.inasistencias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, inasistencias);
    }

    @Override
    public String toString() {
        return "Tipo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
