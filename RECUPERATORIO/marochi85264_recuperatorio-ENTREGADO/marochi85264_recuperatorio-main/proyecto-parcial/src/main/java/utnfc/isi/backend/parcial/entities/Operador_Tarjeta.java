package utnfc.isi.backend.parcial.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "OperadoresTarjetas")
public class Operador_Tarjeta {

    @Id
    @Column(name = "operador_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int operador_tarjeta_id;

    @Column(name = "nombre")
    private String nombre;

    public Operador_Tarjeta() {
    }

    public Operador_Tarjeta( int operador_tarjeta_id, String nombre) {
        this.operador_tarjeta_id = operador_tarjeta_id;
        this.nombre = nombre;
    }

    public int getOperador_tarjeta_id() {
        return operador_tarjeta_id;
    }

    public void setOperador_tarjeta_id(int operador_tarjeta_id) {
        this.operador_tarjeta_id = operador_tarjeta_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operador_Tarjeta that = (Operador_Tarjeta) o;
        return operador_tarjeta_id == that.operador_tarjeta_id && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operador_tarjeta_id, nombre);
    }

    @Override
    public String toString() {
        return "Operador_Tarjeta{" +
                "operador_tarjeta_id=" + operador_tarjeta_id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
