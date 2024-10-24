package utn.frc.backend.parcial.expenses.domain.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="expense")
public class Expense {

    @Id
    @Column(name = "expid")
    private int expid;

    @Column(name = "expname")
    private String expname;

    @OneToMany( mappedBy = "expense")
    private List<ExpensesSubmissionDetail> expSubDet = new ArrayList<>();

//    @ManyToOne
//    @JoinColumn(name="empid")
//    private Employee employee;

    public Expense() {
    }

    public Expense(int expid, String expname) {
        this.expid = expid;
        this.expname = expname;
        this.expSubDet = new ArrayList<ExpensesSubmissionDetail>();
    }

    public Expense(int expid, String expname, List<ExpensesSubmissionDetail> expSubDet) {
        this.expid = expid;
        this.expname = expname;
        this.expSubDet = new ArrayList<ExpensesSubmissionDetail>();
    }

    public int getExpid() {
        return expid;
    }

//    public Employee getEmployee() {
//        return employee;
//    }

//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//    }


    public void setExpid(int expid) {
        this.expid = expid;
    }

    public String getExpname() {
        return expname;
    }

    public void setExpname(String expname) {
        this.expname = expname;
    }

    public List<ExpensesSubmissionDetail> getExpSubDet() {
        return expSubDet;
    }

    public void setExpSubDet(ExpensesSubmissionDetail expSubDet) {
        this.expSubDet.add(expSubDet);
    }

    @Override
    public String toString() {
        return "Expense{" +
                "expid=" + expid +
                ", expname='" + expname + '\'' +
                ", expSubDet=" + expSubDet +
                '}';
    }
}
