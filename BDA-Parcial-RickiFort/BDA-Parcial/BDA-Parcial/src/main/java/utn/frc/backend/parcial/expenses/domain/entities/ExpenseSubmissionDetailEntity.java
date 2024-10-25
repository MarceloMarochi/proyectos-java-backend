package utn.frc.backend.parcial.expenses.domain.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "expense_submission_details")
public class ExpenseSubmissionDetailEntity {
    @Id
    private Integer id;
    @Column(name = "amount")
    private Double amount;
    @ManyToOne
    @JoinColumn(name = "expid", nullable = false)
    private ExpenseEntity expense;
    @ManyToOne
    @JoinColumn(name = "sid", nullable = false)
    private ExpenseSubmissionEntity expenseSubmission;

    public ExpenseSubmissionDetailEntity() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public ExpenseEntity getExpense() {
        return expense;
    }

    public void setExpense(ExpenseEntity expense) {
        this.expense = expense;
    }

    public ExpenseSubmissionEntity getExpenseSubmission() {
        return expenseSubmission;
    }

    public void setExpenseSubmission(ExpenseSubmissionEntity expenseSubmission) {
        this.expenseSubmission = expenseSubmission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpenseSubmissionDetailEntity that = (ExpenseSubmissionDetailEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(amount, that.amount) && Objects.equals(expense, that.expense) && Objects.equals(expenseSubmission, that.expenseSubmission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, expense, expenseSubmission);
    }

    @Override
    public String toString() {
        return "ExpenseSubmissionDetailEntity{" +
                "id=" + id +
                ", amount=" + amount +
                ", expense=" + expense.toString() +
                ", expenseSubmission=" + expenseSubmission.toString() +
                '}';
    }
}
