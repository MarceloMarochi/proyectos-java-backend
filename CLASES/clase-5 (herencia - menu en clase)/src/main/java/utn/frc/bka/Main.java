package utn.frc.bka;

import utn.frc.bka.POO.Persona;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Persona p = new Persona("Marce", "Marochi");
        System.out.println(p.toString());
    }
}