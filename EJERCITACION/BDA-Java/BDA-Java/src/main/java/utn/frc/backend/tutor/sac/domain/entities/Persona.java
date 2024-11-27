package utn.frc.backend.tutor.sac.domain.entities;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 *
 * @author scarafia
 */
@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Inheritance(strategy = InheritanceType.JOINED)
//@SubclassFetchMode(FetchMode.NONE)
@Table(name = "persona")
@NamedQueries({
  @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"),
  @NamedQuery(name = "Persona.findAllById", query = "SELECT p FROM Persona p ORDER BY p.pid"),
  @NamedQuery(name = "Persona.findAllByApeNom", query = "SELECT p FROM Persona p ORDER BY p.apellido, p.nombre"),
  @NamedQuery(name = "Persona.findById", query = "SELECT p FROM Persona p WHERE p.pid = :pid"),
  @NamedQuery(name = "Persona.findByDni", query = "SELECT p FROM Persona p WHERE p.dni = :dni"),
  @NamedQuery(name = "Persona.findByApellido", query = "SELECT p FROM Persona p WHERE p.apellido = :apellido")
})
public class Persona implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "pid")
  protected Integer pid;
  @Basic(optional = false)
  @Column(name = "dni", length = 64)
  protected String dni;
  @Basic(optional = false)
  @Column(name = "apellido", length = 64)
  protected String apellido;
  @Basic(optional = false)
  @Column(name = "nombre", length = 64)
  protected String nombre;

  public Persona() {
    super();
  }

  public Persona(Integer pid, String dni, String apellido, String nombre) {
    this();
    this.pid = pid;
    this.dni = dni;
    this.apellido = apellido;
    this.nombre = nombre;
  }

  public Persona(String dni, String apellido, String nombre) {
    this(null, dni, apellido, nombre);
  }

  public Integer getPid() {
    return pid;
  }

  public void setPid(Integer pid) {
    this.pid = pid;
  }

  public String getDni() {
    return dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
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
    if (!(object instanceof Persona)) {
      return false;
    }
    Persona other = (Persona) object;
    if ((this.pid == null && other.pid != null) || (this.pid != null && !this.pid.equals(other.pid))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return String.format("(%3d) %s, %s", pid, apellido, nombre);
  }

}
