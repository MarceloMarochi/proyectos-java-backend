package utn.frc.backend.parcial.pathologies.domain.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="hospital")
public class Hospital {
    @Id
    @Column(name = "hid")
    private int hid;

    @Column(name = "hname")
    private String hname;

    @OneToMany( mappedBy="hospital")
    private List<Doctor> doctors = new ArrayList<>();

    public Hospital() {}

    public Hospital(int hid, String hname) {
        this.hid = hid;
        this.hname = hname;
        this.doctors = new ArrayList<>();
    }

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }

    public void setDoctors(Doctor doctor) {
        this.doctors.add(doctor);
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "hid=" + hid +
                ", hname='" + hname + '\'' +
                '}';
    }
}
