package utn.frc.backend.parcial.pathologies.domain.entities;

import java.util.Date;

public class PathView {

    private int did;
    private String dname;
    private int hid;
    private String hname;
    private int rid;
    private Date rdate;
    private int pid;
    private String pname;
    private int rdid;
    private int cases;

    public PathView() {
    }

    public PathView(int did, String dname, int hid, String hname, int rid, Date rdate, int pid, String pname, int rdid, int cases) {
        this.did = did;
        this.dname = dname;
        this.hid = hid;
        this.hname = hname;
        this.rid = rid;
        this.rdate = rdate;
        this.pid = pid;
        this.pname = pname;
        this.rdid = rdid;
        this.cases = cases;
    }
}
