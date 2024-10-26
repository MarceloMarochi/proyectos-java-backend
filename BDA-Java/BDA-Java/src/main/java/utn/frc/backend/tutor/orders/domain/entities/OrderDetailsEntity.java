package utn.frc.backend.tutor.orders.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ORDER_DETAILS", schema = "PUBLIC", catalog = "ORDERSDB")
@IdClass(OrderDetailsEntityPK.class)
public class OrderDetailsEntity {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "OID", nullable = false)
    private Integer oid;
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "PID", nullable = false)
    private Integer pid;
    @Basic
    @Column(name = "CNT", nullable = false)
    private Integer cnt;
    @ManyToOne
    @JoinColumn(name = "OID", referencedColumnName = "OID", nullable = false, insertable = false, updatable = false)
    private OrderEntity orderByOid;
    @ManyToOne
    @JoinColumn(name = "PID", referencedColumnName = "PID", nullable = false, insertable = false, updatable = false)
    private ProductEntity productByPid;

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDetailsEntity that = (OrderDetailsEntity) o;

        if (oid != null ? !oid.equals(that.oid) : that.oid != null) return false;
        if (pid != null ? !pid.equals(that.pid) : that.pid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = oid != null ? oid.hashCode() : 0;
        result = 31 * result + (pid != null ? pid.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderDetailsEntity{" +
                "cnt=" + cnt +
                ", orderByOid=" + orderByOid +
                ", productByPid=" + productByPid +
                '}';
    }

    public OrderEntity getOrderByOid() {
        return orderByOid;
    }

    public void setOrderByOid(OrderEntity orderByOid) {
        this.orderByOid = orderByOid;
    }

    public ProductEntity getProductByPid() {
        return productByPid;
    }

    public void setProductByPid(ProductEntity productByPid) {
        this.productByPid = productByPid;
    }
}
