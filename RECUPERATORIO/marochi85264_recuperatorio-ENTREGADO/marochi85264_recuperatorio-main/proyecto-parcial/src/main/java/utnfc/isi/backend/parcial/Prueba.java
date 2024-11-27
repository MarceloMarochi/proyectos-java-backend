package utnfc.isi.backend.parcial;

import utnfc.isi.backend.parcial.entities.*;

public class Prueba {
    public static void main(String[] args) {
        Compania compania1 = new Compania(1, "Compania 1", 15);
        Empleado empleado1 = new Empleado(1, "Marce", "35145", compania1);
        Cuenta cuenta1 = new Cuenta(1, "AAA123", empleado1, "123", "20/05/2025", 1578);
        Operador_Tarjeta operador1 = new Operador_Tarjeta(1, "Operador1");
        Moneda moneda1 = new Moneda(1, "Moneda1", 50);

        cuenta1.setOperadorTarjeta(operador1);
        cuenta1.setMoneda(moneda1);


        System.out.println(compania1.toString());
        System.out.println(empleado1.toString());
        System.out.println(cuenta1.toString());
        System.out.println(operador1.toString());
        System.out.println(moneda1.toString());
    }
}
