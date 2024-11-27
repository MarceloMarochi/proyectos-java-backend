package utn.frc.backend.parcial.expenses.domain.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="department")
public class Department {
    @Id
    private int did;
    private String dname;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees = new ArrayList<>();

    public Department() {}

    public Department(int did, String dname) {
        this.did = did;
        this.dname = dname;
        this.employees = new ArrayList<>();
    }

    public Department(int did, String dname, List<Employee> employees) {
        this.did = did;
        this.dname = dname;
        this.employees = new ArrayList<>();
    }

    public void setDid(int did) {
        this.did = did;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public void setEmployees(Employee emp) {
        this.employees.add(emp);
    }

    @Override
    public String toString() {
        return "Departament{" +
                "did=" + did +
                ", dname='" + dname + '\'' +
                ", employees=" + employees +
                '}';
    }

    public String getNombre() {
        return this.dname;
    }
}
