package utn.frc.backend.parcial.pathologies.domain.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    @Column(name = "did")
    private Integer did;
    @Column(name = "dname")
    private String dname;
    @ManyToOne
    @JoinColumn(name = "hid", referencedColumnName = "hid")
    private Hospital hospital;
    @OneToMany(mappedBy = "doctor")
    private List<Report> reports;

    public Doctor() {}

    public Doctor(Integer did, String dname, Hospital hospital) {
        this.did = did;
        this.dname = dname;
        this.hospital = hospital;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "did=" + did +
                ", dname='" + dname + '\'' +
                ", hospital=" + hospital +
                '}';
    }

    public static List<Doctor> findById(EntityManager em, Integer dId){
        return em.createQuery("SELECT d FROM Doctor d WHERE d.did = :dId", Doctor.class)
                .setParameter("dId", dId)
                .getResultList();
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
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

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }
}
