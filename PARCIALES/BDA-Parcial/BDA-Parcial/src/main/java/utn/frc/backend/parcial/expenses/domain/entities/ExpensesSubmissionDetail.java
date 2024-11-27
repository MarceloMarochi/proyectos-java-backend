package utn.frc.backend.parcial.expenses.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "expense_submission_details")
public class ExpensesSubmissionDetail {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "amount")
    private double amount;

    @ManyToOne
    @JoinColumn(name="sid")
    private ExpenseSubmission expSub;

    @ManyToOne
    @JoinColumn(name="expid")
    private Expense expense;

    public ExpensesSubmissionDetail() {
    }


    public ExpensesSubmissionDetail(int id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public ExpenseSubmission getExpSub() {
        return expSub;
    }

    public void setExpSub(ExpenseSubmission expSub) {
        this.expSub = expSub;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    @Override
    public String toString() {
        return "ExpensesSubmissionDetail{" +
                "id=" + id +
                ", amount=" + amount +
                ", expSub=" + expSub.getSid() +
                ", expense=" + expense.getExpname() +
                '}';
    }
}
