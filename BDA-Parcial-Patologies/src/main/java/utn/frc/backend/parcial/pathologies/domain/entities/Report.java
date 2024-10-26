package utn.frc.backend.parcial.pathologies.domain.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="report")
public class Report {
    @Id
    @Column(name="rid")
    private int rid;

    @ManyToOne
    @JoinColumn(name="did")
    private Doctor doctor;

    @Column(name="rdate")
    private Date rdate;

    @OneToMany(mappedBy = "report")
    private List<ReportDetail> reportDetails = new ArrayList<ReportDetail>();

    public Report() {
    }

    public Report(int rid, Doctor doctor, Date rdate) {
        this.rid = rid;
        this.doctor = doctor;
        this.rdate = rdate;
        this.reportDetails = new ArrayList<ReportDetail>();
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Date getRdate() {
        return rdate;
    }

    public void setRdate(Date rdate) {
        this.rdate = rdate;
    }

    @Override
    public String toString() {
        return "Report{" +
                "rid=" + rid +
                ", doctor=" + doctor.getDname() +
                ", rdate=" + rdate +
                '}';
    }
}
