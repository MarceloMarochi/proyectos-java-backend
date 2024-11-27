package utn.frc.backend.parcial.expenses.domain.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="department")
public class Departament {
    @Id
    @Column(name = "did")
    private int did;

    @Column(name = "dname")
    private String dname;

    @OneToMany( mappedBy = "departament")
    private List<Employee> employees = new ArrayList<>();

    public Departament() {
    }

    public Departament(int did, String dname, List<Employee> employees) {
        this.did = did;
        this.dname = dname;
        this.employees = new ArrayList<Employee>();
    }

    public Departament(int did, String dname) {
        this.did = did;
        this.dname = dname;
        this.employees = new ArrayList<Employee>();

    }

    public void setDid(int did) {
        this.did = did;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public int getDid() {
        return did;
    }

    public String getDname() {
        return dname;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Employee employees) {
        this.employees.add(employees);
    }

    @Override
    public String toString() {
        return "Departament{" +
                "did=" + did +
                ", dname='" + dname + '\'' +
                ", employees=" + employees +
                '}';
    }


}
