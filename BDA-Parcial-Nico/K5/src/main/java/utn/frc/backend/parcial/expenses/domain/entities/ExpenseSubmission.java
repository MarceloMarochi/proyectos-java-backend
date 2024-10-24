package utn.frc.backend.parcial.expenses.domain.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="expense_submission")
public class ExpenseSubmission {
    @Id
    private int sid;
    private Date sdate;

    @OneToOne
    @JoinColumn(name="empid")
    private Employee employee;

    @OneToMany(mappedBy = "expSub")
    private List<ExpenseSubmissionDetail> expSubDet;

    public ExpenseSubmission() {}

    public ExpenseSubmission(int sid, Date sdate, Employee employee) {
        this.sid = sid;
        this.sdate = sdate;
        this.employee = employee;
        this.expSubDet = new ArrayList<>();
    }

    public ExpenseSubmission(int sid, Date sdate, Employee employee, List<ExpenseSubmissionDetail> expSubDet) {
        this.sid = sid;
        this.sdate = sdate;
        this.employee = employee;
        this.expSubDet = new ArrayList<>();
    }

    public void setExpsubdet(ExpenseSubmissionDetail detalle) {
        this.expSubDet.add(detalle);
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

    public int getIdExpSub() {
        return this.sid;
    }
}
