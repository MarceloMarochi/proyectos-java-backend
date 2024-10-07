package utn.frc.bka.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "transferencias")
public class Inasistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "justificado", nullable = false)
    private int justificado;

    @Column(name = "cantidad" , nullable = false)
    private double cantidad;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

    @OneToOne
    @JoinColumn(name = "tipo_id")
    private Tipo tipo;

    public Inasistencia(){

    }

    public Inasistencia(String linea, Estudiante estudiante, Tipo tipo) {
        String[] valores = linea.split(",");
        this.estudiante = estudiante;
        this.tipo = tipo;
        this.justificado = Integer.parseInt(valores[2]);
        this.cantidad = Double.parseDouble(valores[3]);
    }

    public Inasistencia(Long id, int justificado, double cantidad, Estudiante estudiante, Tipo tipo) {
        this.id = id;
        this.justificado = justificado;
        this.cantidad = cantidad;
        this.estudiante = estudiante;
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

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
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
        return justificado == that.justificado && Double.compare(cantidad, that.cantidad) == 0 && Objects.equals(id, that.id) && Objects.equals(estudiante, that.estudiante) && Objects.equals(tipo, that.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, justificado, cantidad, estudiante, tipo);
    }

    @Override
    public String toString() {
        return "Inasistencia{" +
                "id=" + id +
                ", justificado=" + justificado +
                ", cantidad=" + cantidad +
                ", estudiante=" + estudiante +
                ", tipo=" + tipo +
                '}';
    }
}
