package utn.frc.backend.parcial.expenses.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="expense")
public class Expense {
    @Id
    private int expid;
    private String expname;

    @OneToMany(mappedBy = "exp")
    private List<ExpenseSubmissionDetail> expSubDet = new ArrayList<>();

    public Expense() {}

    public Expense(int expid, String expname) {
        this.expid = expid;
        this.expname = expname;
        this.expSubDet = new ArrayList<>();
    }

    public Expense(int expid, String expname, List<ExpenseSubmissionDetail> expSubDet) {
        this.expid = expid;
        this.expname = expname;
        this.expSubDet = new ArrayList<ExpenseSubmissionDetail>();
    }

    public void setdetalle(ExpenseSubmissionDetail detalle) {
        this.expSubDet.add(detalle);
    }

    @Override
    public String toString() {
        return "Expense{" +
                "expid=" + expid +
                ", expname='" + expname + '\'' +
                '}';
    }


}
