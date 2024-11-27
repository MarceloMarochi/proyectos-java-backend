package utnfc.isi.backend.parcial.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Companias")
public class Compania {
    @Id
    @Column(name = "compania_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int compania_id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "comision")
    private double comision;

    @OneToMany(mappedBy = "compania")
    private List<Empleado> empleados = new ArrayList<>();

    public Compania() {
    }

    public Compania(int compania_id, String nombre, double comision) {
        this.compania_id = compania_id;
        this.nombre = nombre;
        this.comision = comision;
        this.empleados = new ArrayList<>();
    }

    public int getCompania_id() {
        return compania_id;
    }

    public void setCompania_id(int compania_id) {
        this.compania_id = compania_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(float comision) {
        this.comision = comision;
    }

    public void setEmpleados(Empleado empleados) {
        this.empleados.add(empleados);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compania compania = (Compania) o;
        return compania_id == compania.compania_id && Double.compare(comision, compania.comision) == 0 && Objects.equals(nombre, compania.nombre) && Objects.equals(empleados, compania.empleados);
    }

    @Override
    public int hashCode() {
        return Objects.hash(compania_id, nombre, comision, empleados);
    }

    @Override
    public String toString() {
        return "Compania{" +
                "compania_id=" + compania_id +
                ", nombre='" + nombre + '\'' +
                ", comision=" + comision +
                '}';
    }
}
