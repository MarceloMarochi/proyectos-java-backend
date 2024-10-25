package utn.frc.backend.parcial.expenses.domain.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "expense_submission")
public class ExpenseSubmissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sid;
    @Column(name="sdate", nullable=false)
    @Temporal(TemporalType.DATE)
    private Date sdate;
    @ManyToOne
    @JoinColumn(name = "empid",nullable = false)
    private EmployeeEntity employee;

    public ExpenseSubmissionEntity() {
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpenseSubmissionEntity that = (ExpenseSubmissionEntity) o;
        return Objects.equals(sid, that.sid) && Objects.equals(sdate, that.sdate) && Objects.equals(employee, that.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid, sdate, employee);
    }

    @Override
    public String toString() {
        return "ExpenseSubmissionEntity{" +
                "sid=" + sid +
                ", sdate=" + sdate +
                ", employee=" + employee.toString() +
                '}';
    }
}
