package utn.frc.backend.parcial.expenses.domain.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "expense_submission")
public class ExpenseSubmission {

    @Id
    @Column(name = "sid")
    private int sid;

    @Column(name = "sdate")
    private Date sdate;

    @ManyToOne
    @JoinColumn(name = "empid")
    private Employee employee;

    @OneToMany( mappedBy = "expSub")
    private List<ExpensesSubmissionDetail> expSubDet;

    public ExpenseSubmission() {
    }

    public ExpenseSubmission(int sid, Date sdate, Employee employee) {
        this.sid = sid;
        this.sdate = sdate;
        this.employee = employee;
        this.expSubDet = new ArrayList<ExpensesSubmissionDetail>();
    }

    public ExpenseSubmission(int sid, Date sdate, Employee employee, List<ExpensesSubmissionDetail> expSubDet) {
        this.sid = sid;
        this.sdate = sdate;
        this.employee = employee;
        this.expSubDet = new ArrayList<ExpensesSubmissionDetail>();
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<ExpensesSubmissionDetail> getExpSubDet() {
        return expSubDet;
    }

    public void setExpSubDet(ExpensesSubmissionDetail expSubDet) {
        this.expSubDet.add(expSubDet);
    }

    @Override
    public String toString() {
        return "ExpenseSubmission{" +
                "sid=" + sid +
                ", sdate=" + sdate +
                ", employee=" + employee +
                ", expSubDet=" + expSubDet +
                '}';
    }
}
