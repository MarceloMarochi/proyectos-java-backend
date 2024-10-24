package utn.frc.backend.parcial.expenses.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name="employee")
public class Employee {
    @Id
    private int empid;
    private String empname;

    @ManyToOne
    @JoinColumn(name="did")
    private Department department;

    public Employee() {}

    public Employee(int empid, String empname, Department department) {
    this.empid = empid;
        this.empname = empname;
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empid=" + empid +
                ", empname='" + empname + '\'' +
                ", department=" + department.getNombre() +
                '}';
    }
}
