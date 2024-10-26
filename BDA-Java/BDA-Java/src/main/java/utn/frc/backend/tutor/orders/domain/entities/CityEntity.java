package utn.frc.backend.tutor.orders.domain.entities;

import jakarta.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "City.findAll", query = "select c from CityEntity c")
})
@Table(name = "CITY", schema = "PUBLIC", catalog = "ORDERSDB")
public class CityEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CID", nullable = false)
    private Integer cid;
    @Basic
    @Column(name = "CNAME", nullable = false, length = 64)
    private String cname;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CityEntity that = (CityEntity) o;

        if (cid != null ? !cid.equals(that.cid) : that.cid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cid != null ? cid.hashCode() : 0;
        return result;
    }

    @Override
    public String toString() {
        return "CityEntity{" +
                "cid=" + cid +
                ", cname=" + cname +
                '}';
    }

}
