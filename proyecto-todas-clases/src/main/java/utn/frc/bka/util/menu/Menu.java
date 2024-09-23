package utn.frc.bka.util.menu;

import utn.frc.bka.semana03.menuItems.MenuCallNPrimos;

import java.util.Scanner;

public class Menu {

    private Scanner sc;

    private String question;

    private MenuItem[] items;

    private String exitKey, exitTitle;


    // Constructor
    public Menu(Scanner sc, String question, String exitKey, MenuItem[] items, String exitTitle) {
        this.sc = sc;
        this.question = question;
        this.exitKey = exitKey;
        this.items = items;
        this.exitTitle = exitTitle;
    }

    // Segundo constructor que llama al primero (sirve para cuando no le pasemos como salir del menu)
    public Menu(Scanner sc, String question, MenuItem[] items) {
        this(sc, question, "q",items, "q - Quit / Salir." );
    }

    private void printQuestion() {
        System.out.println(question);
    }

    private void printOptions() {
        for (MenuItem item : items) {
            System.out.println(item.getKey() + ": " + item.getTitle());
        }
        System.out.println(exitKey + ": " + exitTitle);
    }

    private String getSelection() {
        return sc.nextLine();
    }

    private MenuItem getItem(String key) {
        for (MenuItem item : items) {
            if (item.getKey().equals(key)) {
                return item;
            }
        }
        return null;
    }

    public void run() {
        while (true) {
            printQuestion();

            printOptions();

            String op = getSelection();

            if (op.equals(exitKey)) {
                break;
            }

            MenuItem task = getItem(op);

            if (task != null) {
                task.ejecutar();
            } else {
                System.out.println("Opcion inválida.");
            }
        }
    }

    private static void opA(Scanner sc) {
        MenuItem[] ops = {
                new MenuItem("1", "Opción 1") {
                    @Override
                    public void ejecutar() {
                        System.out.println("Elegiste la opción 1.");
                    }
                },
                new MenuItem("2", "Opcion 2") {
                    @Override
                    public void ejecutar() {
                        System.out.println("Elegiste la opción 2.");
                    }
                }
        };
        Menu m = new Menu(sc, "Elige una opcion: ",ops);
        m.run();
    }

    private static void opB(Scanner sc) {
        MenuItem[] ops = {
                new MenuItem("3", "Opción 3") {
                    @Override
                    public void ejecutar() {
                        System.out.println("Elegiste la opción 3.");
                    }
                },
                new MenuItem("4", "Opcion 4") {
                    @Override
                    public void ejecutar() {
                        System.out.println("Elegiste la opción 4.");
                    }
                }
        };
        Menu m = new Menu(sc, "Elige una opcion: ",ops);
        m.run();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        MenuItem[] ops = {
                new MenuCallNPrimos("p", "Nros primos"),
                new MenuItem("a", "Opcion a") {
                    @Override
                    public void ejecutar() { opA(sc); }
                },
                new MenuItem("b", "Opcion b") {
                    @Override
                    public void ejecutar() { opB(sc); }
                },
        };
        Menu m = new Menu(sc, "Elige una opcion: ",ops);
        m.run();
        sc.close();

    }
}
