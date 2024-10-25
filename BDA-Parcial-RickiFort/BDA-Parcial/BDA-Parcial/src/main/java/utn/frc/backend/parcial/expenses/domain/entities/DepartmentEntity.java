package utn.frc.backend.parcial.expenses.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "department")
public class DepartmentEntity {
    @Id
    private Integer did;

    @Column(name = "dname", nullable = false, unique = true)
    private String dname;

    public DepartmentEntity() {
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentEntity that = (DepartmentEntity) o;
        return Objects.equals(did, that.did) && Objects.equals(dname, that.dname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(did, dname);
    }

    @Override
    public String toString() {
        return "DepartmentEntity{" +
                "did=" + did +
                ", dname='" + dname + '\'' +
                '}';
    }
}
