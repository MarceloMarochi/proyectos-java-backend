package utn.frc.backend.parcial.expenses.domain.entities;

import jakarta.persistence.*;

import java.util.Objects;
@Entity
@Table(name = "employee")
public class EmployeeEntity {
    @Id
    private Integer empid;
    @Column(name = "empname", nullable = false)
    private String empname;

    @ManyToOne
    @JoinColumn(name = "did", nullable = false)
    private DepartmentEntity department;

    public EmployeeEntity() {
    }

    public Integer getEmpid() {
        return empid;
    }

    public void setEmpid(Integer empid) {
        this.empid = empid;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeEntity that = (EmployeeEntity) o;
        return Objects.equals(empid, that.empid) && Objects.equals(empname, that.empname) && Objects.equals(department, that.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empid, empname, department);
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "empid=" + empid +
                ", empname='" + empname + '\'' +
                ", department=" + department.toString() +
                '}';
    }
}
