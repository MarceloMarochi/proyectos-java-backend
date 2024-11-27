package utnfc.isi.backend.parcial.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Monedass")
public class Moneda {
    @Id
    @Column(name = "moneda_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int moneda_id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "conversion_usd")
    private double conversion_usd;

    public Moneda() {
    }

    public Moneda(int moneda_id, String nombre, double conversion_usd) {
        this.moneda_id = moneda_id;
        this.nombre = nombre;
        this.conversion_usd = conversion_usd;
    }

    public int getMoneda_id() {
        return moneda_id;
    }

    public void setMoneda_id(int moneda_id) {
        this.moneda_id = moneda_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getConversion_usd() {
        return conversion_usd;
    }

    public void setConversion_usd(double conversion_usd) {
        this.conversion_usd = conversion_usd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Moneda moneda = (Moneda) o;
        return moneda_id == moneda.moneda_id && Double.compare(conversion_usd, moneda.conversion_usd) == 0 && Objects.equals(nombre, moneda.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moneda_id, nombre, conversion_usd);
    }

    @Override
    public String toString() {
        return "Moneda{" +
                "moneda_id=" + moneda_id +
                ", nombre='" + nombre + '\'' +
                ", conversion_usd=" + conversion_usd + '\'' +
                '}';
    }
}
