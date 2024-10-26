package utn.frc.bda.semana03.introOOP.polimorfismoEstVsDin;

class Base {
    public void f() {
        System.out.println("Base");
    }
}

class Derivada extends Base {
    @Override
    public void f() {
        System.out.println("Derivada");
    }

}

public class Poli {
    public static void main(String[] args) {
        System.out.println("Hello World");

        Base b = new Base();
        Derivada d = new Derivada();

        b.f();
        d.f();
        ((Base) d).f();

        b = d;
        b.f();
    }
}
