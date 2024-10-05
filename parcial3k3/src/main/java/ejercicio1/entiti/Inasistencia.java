package ejercicio1.entiti;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "transferencias")
public class Inasistencia {

    // PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

    @OneToOne
    @JoinColumn(name = "tipo_id")
    private Tipo tipo;

    @Column(name = "justificado", nullable = false)
    private int justificado;

    @Column(name = "cantidad", nullable = false)
    private double cantidad;

    public Inasistencia(Long id, Estudiante estudiante, Tipo tipo, int justificado, double cantidad) {
        this.id = id;
        this.estudiante = estudiante;
        this.tipo = tipo;
        this.justificado = justificado;
        this.cantidad = cantidad;
    }

    public Inasistencia(String linea, Estudiante estudiante, Tipo tipo) {
        String[] valores = linea.split(",");
        this.estudiante = estudiante;
        this.tipo = tipo;
        this.justificado = Integer.parseInt(valores[2]);
        this.cantidad = Double.parseDouble(valores[3]);
    }

    public Inasistencia() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inasistencia that = (Inasistencia) o;
        return justificado == that.justificado && Double.compare(cantidad, that.cantidad) == 0 && Objects.equals(id, that.id) && Objects.equals(estudiante, that.estudiante) && Objects.equals(tipo, that.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, estudiante, tipo, justificado, cantidad);
    }

    @Override
    public String toString() {
        return "Inasistencia{" +
                "id=" + id +
                ", estudiante=" + estudiante +
                ", tipo=" + tipo +
                ", justificado=" + justificado +
                ", cantidad=" + cantidad +
                '}';
    }
}
