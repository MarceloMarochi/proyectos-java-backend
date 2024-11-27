package utn.frc.backend.tutor.sac.domain.entities;

import java.io.Serializable;
import java.util.Collection;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 *
 * @author scarafia
 */
@Entity
@Table(name = "materia")
@NamedQueries({
  @NamedQuery(name = "Materia.findAll", query = "SELECT m FROM Materia m"),
  @NamedQuery(name = "Materia.findAllById", query = "SELECT m FROM Materia m ORDER BY m.mid"),
  @NamedQuery(name = "Materia.findAllByNombre", query = "SELECT m FROM Materia m ORDER BY m.nombre"),
  @NamedQuery(name = "Materia.findById", query = "SELECT m FROM Materia m WHERE m.mid = :id"),
  @NamedQuery(name = "Materia.findByNombre", query = "SELECT m FROM Materia m WHERE m.nombre = :nombre")
})
public class Materia implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "mid")
  private Integer mid;
  @Basic(optional = false)
  @Column(name = "nombre", length = 64)
  private String nombre;
  @Column(name = "descripcion", length = 2147483647)
  private String descripcion;
  
  // Se cambia por relación many to many, evitando mapear tabla intermedia.
  //@OneToMany(cascade = CascadeType.ALL, mappedBy = "materia")
  //private Collection<MateriaDocentes> materiaDocentesCollection;
  @JoinTable(
    name = "materia_docentes",
    joinColumns = {
      @JoinColumn(name = "mid", referencedColumnName = "mid")
    },
    inverseJoinColumns = {
      @JoinColumn(name = "pid", referencedColumnName = "pid")
    }
  )
  @ManyToMany
  private Collection<Docente> docentes;

  public Materia() {
    super();
  }

  public Materia(Integer mid, String nombre, String descripcion) {
    this();
    this.mid = mid;
    this.nombre = nombre;
    this.descripcion = descripcion;
  }
  
  public Materia(String nombre, String descripcion) {
    this(null, nombre, descripcion);
  }

  public Integer getMid() {
    return mid;
  }

  public void setMid(Integer id) {
    this.mid = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public Collection<Docente> getDocentes() {
    return docentes;
  }

  public void setDocentes(Collection<Docente> docentes) {
    this.docentes = docentes;
  }
  
  public int getCantDocentes() {
    return docentes.size();
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (mid != null ? mid.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Materia)) {
      return false;
    }
    Materia other = (Materia) object;
    if ((this.mid == null && other.mid != null) || (this.mid != null && !this.mid.equals(other.mid))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return String.format("(%2d) %s", mid, nombre);
  }
  
}
