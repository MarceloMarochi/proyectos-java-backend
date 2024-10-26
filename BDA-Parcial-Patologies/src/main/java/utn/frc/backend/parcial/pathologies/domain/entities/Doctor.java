package utn.frc.backend.parcial.pathologies.domain.entities;

import java.util.ArrayList;
import java.util.List;

public class Doctor {
    private int did;

    private String dname;

    private Hospital hospital;

    private List<Report> reports = new ArrayList<Report>();

    public Doctor() {
    }

    public Doctor(int did, String dname, Hospital hospital) {
        this.did = did;
        this.dname = dname;
        this.hospital = hospital;
        this.reports = new ArrayList<Report>();
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public void setReports(Report report) {
        this.reports.add(report);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "did=" + did +
                ", dname='" + dname + '\'' +
                ", hospital=" + hospital.getHname() +
                '}';
    }
}
