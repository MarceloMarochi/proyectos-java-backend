package utn.frc.backend.tutor.orders.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class OrderDetailsEntityPK implements Serializable {
    @Column(name = "OID", nullable = false)
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer oid;
    @Column(name = "PID", nullable = false)
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDetailsEntityPK that = (OrderDetailsEntityPK) o;

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
}
