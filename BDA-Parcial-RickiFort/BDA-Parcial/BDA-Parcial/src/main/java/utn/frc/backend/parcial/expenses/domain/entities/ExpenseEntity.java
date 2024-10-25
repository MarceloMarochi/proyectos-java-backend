package utn.frc.backend.parcial.expenses.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "expense")
public class ExpenseEntity {
    @Id
    private Integer expid;
    @Column(name = "expname" , nullable = false)
    private String expname;

    public ExpenseEntity() {
    }

    public Integer getExpid() {
        return expid;
    }

    public void setExpid(Integer expid) {
        this.expid = expid;
    }

    public String getExpname() {
        return expname;
    }

    public void setExpname(String expname) {
        this.expname = expname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpenseEntity that = (ExpenseEntity) o;
        return Objects.equals(expid, that.expid) && Objects.equals(expname, that.expname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expid, expname);
    }

    @Override
    public String toString() {
        return "ExpenseEntity{" +
                "expid=" + expid +
                ", expname='" + expname + '\'' +
                '}';
    }
}
