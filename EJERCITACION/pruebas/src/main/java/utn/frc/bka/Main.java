package utn.frc.bka;

public class Main {
    public static void main(String[] args) {
        int[] numeros = {1,2,3,4,5};

        int suma = Arrays.stream(numeros)  // Convierte el arreglo en un IntStream
                .reduce(0, (a, b) -> a + b);
        System.out.println(suma);
    }
}