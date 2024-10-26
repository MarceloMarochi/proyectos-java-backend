package utn.frc.backend.tutor.orders.domain.entities;

import jakarta.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Customer.findAll", query = "select c from CustomerEntity c")
})
@Table(name = "CUSTOMER", schema = "PUBLIC", catalog = "ORDERSDB")
public class CustomerEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CID", nullable = false)
    private Integer cid;
    @Basic
    @Column(name = "CNAME", nullable = false, length = 128)
    private String cname;
    @Basic
    @Column(name = "CITY_ID", nullable = false)
    private Integer cityId;
    @ManyToOne
    @JoinColumn(name = "CITY_ID", referencedColumnName = "CID", nullable = false, updatable = false, insertable = false)
    private CityEntity cityByCityId;

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

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerEntity that = (CustomerEntity) o;

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
        return "CustomerEntity{" +
                "cid=" + cid +
                ", cname=" + cname +
                ", cityByCityId=" + cityByCityId +
                '}';
    }

    public CityEntity getCityByCityId() {
        return cityByCityId;
    }

    public void setCityByCityId(CityEntity cityByCityId) {
        this.cityByCityId = cityByCityId;
    }
}
