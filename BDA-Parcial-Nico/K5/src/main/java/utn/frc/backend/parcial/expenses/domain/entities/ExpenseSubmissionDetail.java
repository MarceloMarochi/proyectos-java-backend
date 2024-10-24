package utn.frc.backend.parcial.expenses.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name="expense_submission_details")
public class ExpenseSubmissionDetail {
    @Id
    private int id;
    private double amount;

    @ManyToOne
    @JoinColumn(name="sid")
    private ExpenseSubmission expSub;

    @ManyToOne
    @JoinColumn(name="expid")
    private Expense exp;

    public ExpenseSubmissionDetail() {}

    public ExpenseSubmissionDetail(int id, double amount, ExpenseSubmission expSub) {
        this.id = id;
        this.amount = amount;
        this.expSub = expSub;
    }

    public ExpenseSubmissionDetail(int id, double amount, ExpenseSubmission expSub, Expense exp) {
        this.id = id;
        this.amount = amount;
        this.expSub = expSub;
        this.exp = exp;
    }

    public void setExpesense(Expense expense) {
        this.exp = expense;
    }

    @Override
    public String toString() {
        return "ExpenseSubmissionDetail{" +
                "id=" + id +
                ", amount=" + amount +
                ", expSub=" + expSub.getIdExpSub() +
                ", exp=" + exp +
                '}';
    }


}
