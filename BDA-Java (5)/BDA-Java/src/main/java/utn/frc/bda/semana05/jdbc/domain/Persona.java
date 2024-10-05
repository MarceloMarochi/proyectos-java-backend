package utn.frc.bda.semana05.jdbc.domain;

/**
 *
 * @author scarafia
 */
public class Persona {

    protected Integer pid;
    protected String dni;
    protected String apellido;
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
