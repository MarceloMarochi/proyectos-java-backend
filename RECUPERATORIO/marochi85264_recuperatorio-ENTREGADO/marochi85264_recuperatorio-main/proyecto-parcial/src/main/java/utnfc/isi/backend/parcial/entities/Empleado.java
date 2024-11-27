package utnfc.isi.backend.parcial.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Empleados")
public class Empleado {

    @Id
    @Column(name = "empleado_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empleado_id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "telefono")
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "comania_id")
    private Compania compania;

    @OneToMany(mappedBy = "empleado")
    private List<Cuenta> cuentas = new ArrayList<>();

    public Empleado() {
    }

    public Empleado(int empleado_id, String nombre, String telefono, Compania compania) {
        this.empleado_id = empleado_id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.compania = compania;
        this.cuentas = new ArrayList<>();
    }

    public int getEmpleado_id() {
        return empleado_id;
    }

    public void setEmpleado_id(int empleado_id) {
        this.empleado_id = empleado_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Compania getCompania() {
        return compania;
    }

    public void setCompania(Compania compania) {
        this.compania = compania;
    }

    public void setCuentas(Cuenta cuentas) {
        this.cuentas.add(cuentas);
    }

    public List<Cuenta> getCuentas() {
        return this.cuentas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return empleado_id == empleado.empleado_id && Objects.equals(nombre, empleado.nombre) && Objects.equals(telefono, empleado.telefono) && Objects.equals(compania, empleado.compania) && Objects.equals(cuentas, empleado.cuentas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empleado_id, nombre, telefono, compania, cuentas);
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "empleado_id=" + empleado_id +
                ", nombre='" + nombre + '\'' +
                ", telefono=" + telefono +
                ", compania=" + compania.getNombre() +
                '}';
    }
}
