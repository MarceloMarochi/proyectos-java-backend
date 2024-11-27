package utn.frc.backend.parcial.pathologies.domain.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "report")
public class Report {
    @Id
    @Column(name = "rid")
    private Integer rid;
    @Column(name = "rdate")
    private Date rdate;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "did", referencedColumnName = "did")
    private Doctor doctor;
    @OneToMany(mappedBy = "report")
    private List<ReportDetail> details;

    public Report() {}

    public Report(Integer rid, Date rdate, Doctor doctor) {
        this.rid = rid;
        this.rdate = rdate;
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "Report{" +
                "rid=" + rid +
                ", rdate=" + rdate +
                ", doctor=" + doctor +
                '}';
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Date getRdate() {
        return rdate;
    }

    public void setRdate(Date rdate) {
        this.rdate = rdate;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<ReportDetail> getDetails() {
        return details;
    }

    public void setDetails(List<ReportDetail> details) {
        this.details = details;
    }
}
