package utn.frc.backend.tutor.orders.domain.entities;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@NamedQueries({
        @NamedQuery(name = "Product.findAll", query = "select p from ProductEntity p")
})
@Table(name = "PRODUCT", schema = "PUBLIC", catalog = "ORDERSDB")
public class ProductEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "PID", nullable = false)
    private Integer pid;
    @Basic
    @Column(name = "PNAME", nullable = false, length = 64)
    private String pname;
    @Basic
    @Column(name = "PRICE", nullable = false, precision = 0)
    private Double price;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductEntity that = (ProductEntity) o;

        if (pid != null ? !pid.equals(that.pid) : that.pid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pid != null ? pid.hashCode() : 0;
        return result;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "pid=" + pid +
                ", pname=" + pname +
                ", price=" + price +
                '}';
    }
}
