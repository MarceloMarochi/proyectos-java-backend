package utn.frc.bka.POO;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class Persona implements Nombrable {
    // Estos métodos son protegidos para que puedan ser accedodos
    // por las clases que lo heredan
    protected String nombre;

    protected String apellido;

    public Persona(String nombre, String apellido) {
        this.nombre = nombre;
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
    public void nombrar(String s1, String s2) {
        setNombre(s1);
        setApellido(s2);
    }

    @Override
    public String obtenerNombre() {
        return nombre +  " " + apellido;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
}
