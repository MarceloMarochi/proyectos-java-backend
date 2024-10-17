package utn.frc.backend.tutor.sac.domain.entities;

import java.io.Serializable;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

//import org.apache.openjpa.persistence.jdbc.FetchMode;
//import org.apache.openjpa.persistence.jdbc.SubclassFetchMode;

/**
 *
 * @author scarafia
 */
@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
//@SubclassFetchMode(FetchMode.NONE)
@PrimaryKeyJoinColumn(name = "pid")
@Table(name = "alumno")
@NamedQueries({
  @NamedQuery(name = "Alumno.findAll", query = "SELECT a FROM Alumno a"),
  @NamedQuery(name = "Alumno.findAllById", query = "SELECT a FROM Alumno a ORDER BY a.pid"),
  @NamedQuery(name = "Alumno.findAllByApeNom", query = "SELECT a FROM Alumno a ORDER BY a.apellido, a.nombre"),
  @NamedQuery(name = "Alumno.findById", query = "SELECT a FROM Alumno a WHERE a.pid = :pid"),
  @NamedQuery(name = "Alumno.findByLegajo", query = "SELECT a FROM Alumno a WHERE a.legajo = :legajo"),
  @NamedQuery(name = "Alumno.findByLegajo", query = "SELECT a FROM Alumno a WHERE a.legajo = :legajo")
})
public class Alumno extends Persona implements Serializable {
  
  public static void insert(EntityManager em, Integer pid, String legajo) {
    String query = "insert into alumno (pid, legajo) values (?, ?)";
    em.createNativeQuery(query).setParameter(1, pid).setParameter(2, legajo).executeUpdate();
  }
  public static void delete(EntityManager em, Integer pid) {
    String query = "delete from alumno where pid = ?";
    em.createNativeQuery(query).setParameter(1, pid).executeUpdate();
  }

  private static final long serialVersionUID = 1L;
  
  @Basic(optional = false)
  @Column(name = "legajo", length = 16)
  private String legajo;

  public Alumno() {
    super();
  }

  public Alumno(Integer pid, String dni, String apellido, String nombre, String legajo) {
    super(pid, dni, apellido, nombre);
    this.legajo = legajo;
  }

  public Alumno(String dni, String apellido, String nombre, String legajo) {
    this(null, dni, apellido, nombre, legajo);
  }
  
  public Alumno(Persona persona, String legajo) {
    this(persona.pid, persona.dni, persona.apellido, persona.nombre, legajo);
  }

  public String getLegajo() {
    return legajo;
  }

  public void setLegajo(String legajo) {
    this.legajo = legajo;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (pid != null ? pid.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Alumno)) {
      return false;
    }
    Alumno other = (Alumno) object;
    if ((this.pid == null && other.pid != null) || (this.pid != null && !this.pid.equals(other.pid))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    //return super.toString() + String.format(" [%s]", legajo);
    return String.format("(%3d) %s, %s [%s]", pid, apellido, nombre, legajo);
  }
  
}
