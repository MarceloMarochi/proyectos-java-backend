package utn.frc.backend.parcial.pathologies.domain.entities;

public class ReportDetail {
    private int rdid;

    private int cases;

    private Report report;

    private Pathology pathology;

    public ReportDetail() {
    }

    public ReportDetail(int rdid, int cases, Report report, Pathology pathology) {
        this.rdid = rdid;
        this.cases = cases;
        this.report = report;
        this.pathology = pathology;
    }

    public int getRdid() {
        return rdid;
    }

    public void setRdid(int rdid) {
        this.rdid = rdid;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public Pathology getPathology() {
        return pathology;
    }

    public void setPathology(Pathology pathology) {
        this.pathology = pathology;
    }

    @Override
    public String toString() {
        return "ReportDetail{" +
                "rdid=" + rdid +
                ", cases=" + cases +
                ", report=" + report.getRid() +
                ", pathology=" + pathology.getPname() +
                '}';
    }
}
