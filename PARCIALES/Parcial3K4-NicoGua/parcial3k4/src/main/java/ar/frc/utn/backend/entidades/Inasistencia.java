package ar.frc.utn.backend.entidades;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "inasistencias")
public class Inasistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "justificado", nullable = false)
    private int justificado;

    @Column(name = "cantidad", nullable = false)
    private Double cantidad;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "alumno_id")
    private Alumno alumno;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tipo_id")
    private Tipo tipo;

    public Inasistencia(){

    }

    public Inasistencia(int justificado, Double cantidad, Alumno alumno, Tipo tipo) {
        this.justificado = justificado;
        this.cantidad = cantidad;
        this.alumno = alumno;
        this.tipo = tipo;
    }

    public Inasistencia(Long id, int justificado, Double cantidad, Alumno alumno, Tipo tipo) {
        this.id = id;
        this.justificado = justificado;
        this.cantidad = cantidad;
        this.alumno = alumno;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getJustificado() {
        return justificado;
    }

    public void setJustificado(int justificado) {
        this.justificado = justificado;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inasistencia that = (Inasistencia) o;
        return justificado == that.justificado && Objects.equals(id, that.id) && Objects.equals(cantidad, that.cantidad) && Objects.equals(alumno, that.alumno) && Objects.equals(tipo, that.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, justificado, cantidad, alumno, tipo);
    }

    @Override
    public String toString() {
        return "Inasistencia{" +
                "id=" + id +
                ", justificado=" + justificado +
                ", cantidad=" + cantidad +
                ", alumno=" + alumno +
                ", tipo=" + tipo +
                '}';
    }
}
