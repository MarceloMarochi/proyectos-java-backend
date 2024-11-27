package utn.frc.backend.parcial.pathologies.domain.entities;


import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pathology")
public class Pathology {
    @Id
    @Column(name = "pid")
    private Integer pid;
    @Column(name = "pname")
    private String pname;
    @OneToMany(mappedBy = "pathology")
    private List<ReportDetail> reportDetail;

    public Pathology() {

    }
    public Pathology(Integer pid, String pname) {
        this.pid = pid;
        this.pname = pname;
    }

    @Override
    public String toString() {
        return "Pathology{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                '}';
    }

    public static List<Pathology> findById(EntityManager em, Integer pId){
        return em.createQuery("SELECT p FROM Pathology p WHERE p.pid = :pid", Pathology.class)
                .setParameter("pid", pId)
                .getResultList();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public List<ReportDetail> getReportDetail() {
        return reportDetail;
    }

    public void setReportDetail(List<ReportDetail> reportDetail) {
        this.reportDetail = reportDetail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pathology pathology = (Pathology) o;
        return Objects.equals(pid, pathology.pid) && Objects.equals(pname, pathology.pname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid, pname);
    }
}
