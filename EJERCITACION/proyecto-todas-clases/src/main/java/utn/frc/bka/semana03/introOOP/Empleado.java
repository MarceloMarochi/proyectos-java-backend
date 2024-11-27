package utn.frc.bka.semana03.introOOP;

public class Empleado extends Persona {
    private double salario;

    public Empleado(String nombre, String apellido, double salario) {
        super(nombre, apellido);
        this.salario = salario;
    }

    @Override
    public String toString() {
        //return super.toString() + " (" + salario + ")";

        //return apellido + ", " + nombre + " (" + salario + ")";

        return String.format("%s, %s (%.2f)", apellido, nombre, salario);
    }


    public static void main(String[] args) {
        Empleado e = new Empleado("n", "a", 999);

        System.out.println(e);

    }

}
