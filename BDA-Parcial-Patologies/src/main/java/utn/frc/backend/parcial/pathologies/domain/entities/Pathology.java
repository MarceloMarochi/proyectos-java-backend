package utn.frc.backend.parcial.pathologies.domain.entities;

import java.util.ArrayList;
import java.util.List;

public class Pathology {

    private int pid;

    private String pname;

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
