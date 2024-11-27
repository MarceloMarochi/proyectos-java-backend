package utn.frc.backend.tutor.orders.domain.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;

@Entity
@NamedQueries({
        @NamedQuery(name = "Order.findAll", query = "select o from OrderEntity o"),
        @NamedQuery(name = "Order.findAllByCustomerId", query = "select o from OrderEntity o where o.cid = :cid")
})
@Table(name = "ORDERS", schema = "PUBLIC", catalog = "ORDERSDB")
public class OrderEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "OID", nullable = false)
    private Integer oid;
    @Basic
    @Column(name = "CID", nullable = false)
    private Integer cid;
    @Basic
    @Column(name = "ODATE", nullable = false)
    private Date odate;
    @ManyToOne
    @JoinColumn(name = "CID", referencedColumnName = "CID", nullable = false, insertable = false, updatable = false)
    private CustomerEntity customerByCid;
    @OneToMany(mappedBy = "orderByOid", fetch = FetchType.LAZY)
    private Collection<OrderDetailsEntity> orderDetailsByOid;

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Date getOdate() {
        return odate;
    }

    public void setOdate(Date odate) {
        this.odate = odate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        if (oid != null ? !oid.equals(that.oid) : that.oid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = oid != null ? oid.hashCode() : 0;
        return result;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "oid=" + oid +
                ", odate=" + odate +
                ", customerByCid=" + customerByCid +
                '}';
    }

    public CustomerEntity getCustomerByCid() {
        return customerByCid;
    }

    public void setCustomerByCid(CustomerEntity customerByCid) {
        this.customerByCid = customerByCid;
    }

    public Collection<OrderDetailsEntity> getOrderDetailsByOid() {
        return orderDetailsByOid;
    }

    public void setOrderDetailsByOid(Collection<OrderDetailsEntity> orderDetailsByOid) {
        this.orderDetailsByOid = orderDetailsByOid;
    }
}
