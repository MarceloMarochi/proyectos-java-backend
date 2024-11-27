package utn.frc.backend.parcial.pathologies.domain.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="pathology")
public class Pathology {
    @Id
    @Column(name="pid")
    private int pid;

    @Column(name="pname")
    private String pname;

    @OneToMany(mappedBy = "pathology")
    private List<ReportDetail> reportsDetail = new ArrayList<ReportDetail>();

    public Pathology() {
    }

    public Pathology(int pid, String pname) {
        this.pid = pid;
        this.pname = pname;
        this.reportsDetail = new ArrayList<>();
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setReportsDetail(ReportDetail repoDetail) {
        this.reportsDetail.add(repoDetail);
    }

    @Override
    public String toString() {
        return "Pathology{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                '}';
    }
}
