package utn.frc.bda.semana03.introOOP;

public class Persona implements Nombrable {
    protected String nombre;
    protected String apellido;

    public Persona() {
        super();
    }

    public Persona(String nombre) {
        this.nombre = nombre;
    }

    public Persona(String nombre, String apellido) {
        this(nombre);
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }


    public static void main(String[] args) {
        Persona p = new Persona("Johny", "Lojuno");

        Object o = new Persona("Armando Estéban", "Quito");

        System.out.println(p);
        System.out.println(o);
    }

    @Override
    public void nombrar(String s1, String s2) {
        setNombre(s1);
        setApellido(s2);
    }

    @Override
    public String obtenerNombre() {
        return nombre + " " + apellido;
    }
}
