package utn.frc.backend.parcial.pathologies.domain.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Report {
    private int rid;

    private Doctor doctor;

    private Date rdate;

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
