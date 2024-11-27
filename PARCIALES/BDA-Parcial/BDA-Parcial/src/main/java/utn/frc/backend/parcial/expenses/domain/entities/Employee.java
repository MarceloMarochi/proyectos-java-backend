package utn.frc.backend.parcial.expenses.domain.entities;

import jakarta.persistence.*;
import utn.frc.backend.parcial.expenses.Expenses;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="employee")
public class Employee {

    @Id
    @Column(name = "empid")
    private int empid;

    @Column(name = "empname")
    private String empname;

    @ManyToOne
    @JoinColumn(name="did")
    private Departament departament;

//    @OneToMany( mappedBy = "employee")
//    private List<Expense> expenses = new ArrayList<>();



    public Employee() {
    }

    public Employee(int empid, String empname, Departament departament) {
        this.empid = empid;
        this.empname = empname;
        this.departament = departament;
    }

    public int getEmpid() {
        return empid;
    }

//    public List<Expense> getExpenses() {
//        return expenses;
//    }

//    public void setExpenses(Expense expenses) {
//        this.expenses.add(expenses);
//    }

    public void setEmpid(int empid) {
        this.empid = empid;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public Departament getDepartament() {
        return departament;
    }

    public void setDepartament(Departament departament) {
        this.departament = departament;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empid=" + empid +
                ", empname='" + empname + '\'' +
                ", departament=" + departament.getDname() +
                '}';
    }
}
