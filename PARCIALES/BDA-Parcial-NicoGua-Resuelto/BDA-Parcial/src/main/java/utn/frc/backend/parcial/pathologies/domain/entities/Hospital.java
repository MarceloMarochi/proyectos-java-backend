package utn.frc.backend.parcial.pathologies.domain.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "hospital")
public class Hospital {
    @Id
    @Column(name = "hid")
    private Integer hid;
    @Column(name = "hname")
    private String hname;
    @OneToMany(mappedBy = "hospital")
    private List<Doctor> doctors;

    public Hospital() {}

    public Hospital(Integer hid, String hname) {
        this.hid = hid;
        this.hname = hname;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "hid=" + hid +
                ", hname='" + hname + '\'' +
                '}';
    }

    public static List<Hospital> findById(EntityManager em, Integer hid){
        return em.createQuery("SELECT h FROM Hospital h WHERE h.hid = :hid", Hospital.class)
                .setParameter("hid", hid)
                .getResultList();
    }

    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }
}
