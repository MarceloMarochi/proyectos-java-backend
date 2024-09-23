package utn.frc.bda.util.menu;

import java.util.Scanner;

class SomeOptionCall implements MenuCall {
    @Override
    public void call() {
        System.out.println("Some option call");
    }
}

public class Menu {

    static class SomeOtherOptionCall implements MenuCall {
        @Override
        public void call() {
            System.out.println("Some other option call");
        }
    }

    private Scanner sc;
    private String question;
    private MenuItem[] items;
    private String exitKey, exitText;

    public Menu(Scanner sc, String question, MenuItem[] items, String exitKey, String exitText) {
        this.sc = sc;
        this.question = question;
        this.items = items;
        this.exitKey = exitKey;
        this.exitText = exitText;
    }

    public Menu(Scanner sc, String question, MenuItem[] items) {
        this(sc, question, items, "q", "Quit / Go Back");
    }

    public Menu(Scanner sc, MenuItem[] items) {
        this (sc, "Please select an option: ", items);
    }

    private void printQuestion() {
        System.out.println(question);
    }

    private void printOptions() {
        for (MenuItem item : items) {
            System.out.println(item.getItemKey() + ": " + item.getItemText());
        }

        System.out.println(exitKey + ": " + exitText);
    }

    private String getSelection() {
        return sc.nextLine();
    }

    private MenuItem getItem(String key) {
        for (MenuItem item : items) {
            if (key.equals(item.getItemKey())) {
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

            MenuItem item = getItem(op);

            if (item != null) {
                item.execute();
            } else {
                System.out.println("Option not valid.");
            }
        }
    }

    // Static functions

    private static void printQuestion(String question) {
        System.out.println(question);
    }

    private static String getAnswer(Scanner sc) {
        return sc.nextLine();
    }

    public static String ask(Scanner sc, String question) {
        printQuestion(question);
        return getAnswer(sc);
    }

    public static boolean confirm(Scanner sc, String question, String yesKey, String noKey) {
        String answer = "";

        while (!(answer.equals(yesKey) || answer.equals(noKey))) {
            answer = ask(sc, question + "(" + yesKey + "/" + noKey + ")");
        }

        return answer.equals(yesKey);
    }

    public static boolean confirm(Scanner sc, String question) {
        return confirm(sc, question, "y", "n");
    }

    // =========================================================================
    private static void opA(Scanner sc) {
        MenuItem[] ops = {
                new MenuItem("1", "Option 1", new MenuCall() {
                    @Override
                    public void call() {
                        System.out.println("You chose 1");
                    }
                }),
                new MenuItem("2", "Option 2", new MenuCall() {
                    @Override
                    public void call() {
                        System.out.println("You chose 2");
                    }
                }),
        };

        Menu m = new Menu(sc, ops);
        m.run();
    }

    private static void opB(Scanner sc) {
        MenuItem[] ops = {
                new MenuItem("3", "Option 3", new MenuCall() {
                    @Override
                    public void call() {
                        System.out.println("You chose 3");
                    }
                }),
                new MenuItem("4", "Option 4", new MenuCall() {
                    @Override
                    public void call() {
                        System.out.println("You chose 4");
                    }
                }),
        };

        Menu m = new Menu(sc, ops);
        m.run();
    }

    public static void menuRun(Scanner sc) {
        SomeOptionCall someOptionCall = new SomeOptionCall();
        SomeOtherOptionCall someOtherOptionCall = new SomeOtherOptionCall();

        MenuItem[] ops = {
                new MenuItem("s1", "Some Option Call 1", someOptionCall),
                new MenuItem("s2", "Some Option Call 2", new SomeOptionCall()),

                new MenuItem("so1", "Some Other Option Call 1", someOtherOptionCall),
                new MenuItem("so2", "Some Other Option Call 2", new SomeOtherOptionCall()),

                new MenuItem("a", "Option a", new MenuCall() {
                    @Override
                    public void call() {
                        opA(sc);
                    }
                }),
                new MenuItem("b", "Option b", () -> opB(sc)),
        };

        Menu m = new Menu(sc, ops);
        m.run();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Ejemplo static (sin instanciar)
        if (Menu.confirm(sc, "Ejemplo static... desea continuar?")) {
            menuRun(sc);
        }

        sc.close();
    }

}
