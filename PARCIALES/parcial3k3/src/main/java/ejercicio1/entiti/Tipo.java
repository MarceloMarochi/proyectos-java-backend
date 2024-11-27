package ejercicio1.entiti;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tipos")
public class Tipo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @OneToOne(cascade = CascadeType.ALL)
    private Inasistencia inasistencia;


    public Tipo() {

    }

    public Tipo(Long id, String nombre, Inasistencia inasistencia) {
        this.id = id;
        this.nombre = nombre;
        this.inasistencia = inasistencia;
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

    public Inasistencia getInasistencia() {
        return inasistencia;
    }

    public void setInasistencia(Inasistencia inasistencia) {
        this.inasistencia = inasistencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tipo tipo = (Tipo) o;
        return Objects.equals(id, tipo.id) && Objects.equals(nombre, tipo.nombre) && Objects.equals(inasistencia, tipo.inasistencia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, inasistencia);
    }

    @Override
    public String toString() {
        return "Tipo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", inasistencia=" + inasistencia +
                '}';
    }
}
