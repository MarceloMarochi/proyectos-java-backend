package utn.frc.backend.tutor.sac.domain.entities;

import java.io.Serializable;
import java.util.Collection;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

/**
 *
 * @author scarafia
 */
@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "pid")
@Table(name = "docente")
@NamedQueries({
  @NamedQuery(name = "Docente.findAll", query = "SELECT d FROM Docente d"),
  @NamedQuery(name = "Docente.findAllById", query = "SELECT d FROM Docente d ORDER BY d.pid"),
  @NamedQuery(name = "Docente.findAllByApeNom", query = "SELECT d FROM Docente d ORDER BY d.apellido, d.nombre"),
  @NamedQuery(name = "Docente.findById", query = "SELECT d FROM Docente d WHERE d.pid = :pid"),
  @NamedQuery(name = "Docente.findByLegajo", query = "SELECT d FROM Docente d WHERE d.legajo = :legajo")})
public class Docente extends Persona implements Serializable {

  private static final long serialVersionUID = 1L;

  @Basic(optional = false)
  @Column(name = "legajo", length = 16)
  private String legajo;
  
  // No hace falta del lado del docente => se cambia por relacion many to many mapped.
  // Sí hará falta del lado del curso, pues para poder asignar un docente, éste
  // tiene que estar relacionado con la materia a dicho curso.
  //@OneToMany(cascade = CascadeType.ALL, mappedBy = "docente")
  //private Collection<MateriaDocentes> materiaDocentesCollection;
  @ManyToMany(mappedBy = "docentes")
  private Collection<Materia> materias;
  
  public Docente() {
    super();
  }

  public Docente(Integer pid, String dni, String apellido, String nombre, String legajo) {
    super(pid, dni, apellido, nombre);
    this.legajo = legajo;
  }

  public Docente(String dni, String apellido, String nombre, String legajo) {
    this(null, dni, apellido, nombre, legajo);
  }
  
  public Docente(Persona persona, String legajo) {
    this(persona.pid, persona.dni, persona.apellido, persona.nombre, legajo);
  }

  public String getLegajo() {
    return legajo;
  }

  public void setLegajo(String legajo) {
    this.legajo = legajo;
  }

  public Collection<Materia> getMaterias() {
    return materias;
  }
  
  public void setMaterias(Collection<Materia> materias) {
    this.materias = materias;
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
    if (!(object instanceof Docente)) {
      return false;
    }
    Docente other = (Docente) object;
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
