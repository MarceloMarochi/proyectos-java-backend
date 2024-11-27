package utn.frc.backend.parcial.pathologies.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "report_details")
public class ReportDetail {
    @Id
    @Column(name = "rdid")
    private Integer id;
    @Column(name = "cases")
    private Integer cases;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "rid", referencedColumnName = "rid")
    private Report report;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "pid", referencedColumnName = "pid")
    private Pathology pathology;

    public ReportDetail() {}

    public ReportDetail(Integer rdid, Integer cases, Report report, Pathology pathology) {
        this.id = rdid;
        this.cases = cases;
        this.report = report;
        this.pathology = pathology;
    }

    @Override
    public String toString() {
        return "ReportDetail{" +
                "id=" + id +
                ", cases=" + cases +
                ", report=" + report +
                ", pathology=" + pathology +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCases() {
        return cases;
    }

    public void setCases(Integer cases) {
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
}
