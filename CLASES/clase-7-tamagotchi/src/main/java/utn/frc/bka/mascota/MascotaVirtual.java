package utn.frc.bka.mascota;
import java.util.Scanner;

public class MascotaVirtual {
    private static final int  ENERGIA_MIN = 0, ENERGIA_MAX = 100;

    private static final int  HUMOR_MIN = 0, HUMOR_MAX = 100;

    private String nombre;

    private int energia;

    private int humor;

    private String estado;

    private int consecutivoComer = 0;

    // Constructores
    public MascotaVirtual(String nombre, int energia, int humor, String estado, int consecutivoComer) {
        this.nombre = nombre;
        this.energia = energia;
        this.humor = humor;
        this.estado = estado;
        this.consecutivoComer = consecutivoComer;
    }

    public MascotaVirtual(String nombre) {
        this(nombre, ENERGIA_MAX, HUMOR_MAX, "Despierta", 0);
    }

    private void resetearComer() {
        this.consecutivoComer = 0;
    }

    // toString()
    @Override
    public String toString() {
        return String.format(
                "%s (Energía %d/%d, Humor %d/%d, %s",
                nombre, energia, ENERGIA_MAX, humor, HUMOR_MAX, estado
        );
    }

    // Getters and Setters
    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        if (0 <= energia && energia <= 100) {
            this.energia = energia;
        } else {
            System.out.println("La energia no puede ser mayor que 100 ni menor a 0.");
        }

    }

    public int getHumor() {
        return humor;
    }

    public void setHumor(int humor) {
        if (1 <= humor && humor <= 5) {
            this.humor = humor;
        } else {
            System.out.println("El humor no puede ser mayor que 5 ni menor a 1.");
        }
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void getEstado(String estado) {
        if (estado.equalsIgnoreCase("Durmiendo") || estado.equalsIgnoreCase( "Despierta")) {
            this.estado = estado;
        } else {
            System.out.println("No existe el estado tipado.");
        }

    }

    // COMPORTAMIENTOS

    // Ingesta
    public void comer() {
        int energia = getEnergia();
        int humor = getHumor();
        int diezPorciento = (energia * 10) / 100;

        if (!estado.equalsIgnoreCase("Durmiendo")) {
            if (energia <= 90) {
                setEnergia(energia + diezPorciento);
            } else {
                setEnergia(100);
            }
            setHumor(humor + 1);
        } else {
            System.out.println("La mascota esta durmiendo.");
        }
    }

    public void beber() {
        int energia = getEnergia();
        int humor = getHumor();
        int cincoPorciento = (energia * 5) / 100;

        if (!estado.equalsIgnoreCase("Durmiendo")) {
            if (energia <= 90) {
                setEnergia(energia + cincoPorciento);
            } else {
                setEnergia(100);
            }
            setHumor(humor + 1);
        } else {
            System.out.println("La mascota esta durmiendo.");
        }
    }

    // Actividades
    public void correr() {
        int energia = getEnergia();
        int humor = getHumor();

        if (!estado.equalsIgnoreCase("Durmiendo")) {
            int treintYCinco = (energia * 35) / 100;
            if (energia >= 35) {
                setEnergia(energia - treintYCinco);
            } else {
                setEnergia(0);
            }
            if (humor >= 2) {
                setHumor(humor - 2);
            } else {
                setHumor(0);
            }
        } else {
            System.out.println("La mascota esta durmiendo.");
        }
    }

    public void saltar() {
        int energia = getEnergia();
        int humor = getHumor();

        int quince = (energia * 15) / 100;
        if (!estado.equalsIgnoreCase("Durmiendo")) {
            if (energia >= 35) {
                setEnergia(energia - quince);
            } else {
                setEnergia(0);
            }
            if (humor >= 2) {
                setHumor(humor - 2);
            } else {
                setHumor(0);
            }
        } else {
            System.out.println("La mascota esta durmiendo.");
        }
    }

    // Otros comportamientos
    public void dormir() {
        setEstado("Durmiendo");
        if (energia <= 75) {
            setEnergia(energia + 25);
        } else {
            setEnergia(100);
        }
        if (humor <= 3) {
            setHumor(humor + 2);
        } else {
            setHumor(5);
        }
    }

    public void despertar() {
        setEstado("Despierta");
        if (humor >= 1) {
            setHumor(humor - 1);
        } else {
            setHumor(0);
        }
    }


    // Menú de prueba
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MascotaVirtual mascota = new MascotaVirtual("Fido");

        boolean salir = false;

        while (!salir) {
            System.out.println("\n=== Menú de Mascota Virtual ===");
            System.out.println("1. Comer");
            System.out.println("2. Beber");
            System.out.println("3. Correr");
            System.out.println("4. Saltar");
            System.out.println("5. Dormir");
            System.out.println("6. Despertar");
            System.out.println("7. Ver estado de la mascota");
            System.out.println("8. Salir");
            System.out.print("Elige una opción: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    mascota.comer();
                    break;
                case 2:
                    mascota.beber();
                    break;
                case 3:
                    mascota.correr();
                    break;
                case 4:
                    mascota.saltar();
                    break;
                case 5:
                    mascota.dormir();
                    break;
                case 6:
                    mascota.despertar();
                    break;
                case 7:
                    System.out.println(mascota);
                    break;
                case 8:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }

        scanner.close();
        System.out.println("¡Gracias por jugar con tu mascota virtual!");
    }

}
