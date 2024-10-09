package utn.frc.bda.semana04;

import utn.frc.bda.util.menu.Menu;
import utn.frc.bda.util.menu.MenuCall;
import utn.frc.bda.util.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Pet {
    private static final int ENERGY_MIN = 0, ENERGY_MAX = 100;
    private static final int ENERGY_INC_SLEEP = 25, ENERGY_INC_WAKEUP = 0;
    private static final float ENERGY_INC_EAT = 1.1f, ENERGY_INC_DRINK = 1.05f;
    private static final float ENERGY_INC_RUN = .65f, ENERGY_INC_JUMP = .85f;

    private static final int HUMOR_MIN = 0, HUMOR_MAX = 5;
    private static final int HUMOR_INC_SLEEP = 2, HUMOR_INC_WAKEUP = -1;
    private static final int HUMOR_INC_EAT = 1, HUMOR_INC_DRINK = 1, HUMOR_INC_CONSECUTIVE_INTAKE = -1;
    private static final int HUMOR_INC_RUN = -2, HUMOR_INC_JUMP = -2;

    private static final int CONSECUTIVE_INTAKE_COUNT_TO_CHANGE_HUMOR = 3,  CONSECUTIVE_INTAKE_COUNT_TO_DIE = 5;
    private static final int CONSECUTIVE_ACTIVITY_COUNT_TO_SLEEP = 3;

    private final String name;
    private int energy;
    private int humor;
    private boolean awake;

    private int consecutiveIntakeCount = 0;
    private int consecutiveActivityCount = 0;

    public Pet(String name) {
        this(name, ENERGY_MAX, HUMOR_MAX, true);
    }

    public Pet(String name, int energy, int humor, boolean awake) {
        this.name = name;
        setEnergy(energy);
        setHumor(humor);
        this.awake = awake;
    }

    public String getName() {
        return name;
    }

    public int getEnergy() {
        return energy;
    }

    private void setEnergy(int energy) {
        if (energy < ENERGY_MIN) {
            energy = ENERGY_MIN;
        } else if (energy > ENERGY_MAX) {
            energy = ENERGY_MAX;
        }
        this.energy = energy;
    }

    private void incEnergy(int inc) {
        setEnergy(energy + inc);
    }

    // Se castea a INT el calculo energy * inc
    private void incEnergy(float inc) {
        setEnergy((int) (energy * inc));
    }

    public int getHumor() {
        return humor;
    }

    private void setHumor(int humor) {
        if (humor < HUMOR_MIN) {
            humor = HUMOR_MIN;
        } else if (humor > HUMOR_MAX) {
            humor = HUMOR_MAX;
        }
        this.humor = humor;
    }

    private void incHumor(int inc) {
        setHumor(humor + inc);
    }

    private void incHumor(float inc) {
        setHumor((int) (humor * inc));
    }

    public boolean isAwake() {
        return awake;
    }

    private void setAwake(boolean awake) {
        this.awake = awake;
    }

    public boolean isAlive() {
        return energy > ENERGY_MIN;
    }

    private void resetIntakes() {
        consecutiveIntakeCount = 0;
    }

    private void resetActivities() {
        consecutiveActivityCount = 0;
    }

    private boolean hasToSleep() {
        return consecutiveActivityCount == CONSECUTIVE_ACTIVITY_COUNT_TO_SLEEP || humor == HUMOR_MIN;
    }

    private void die() {
        setEnergy(ENERGY_MIN);
        setHumor(HUMOR_MIN);
        setAwake(false);
    }

    public boolean sleep() {
        if (!isAlive() || !isAwake()) { return false; }

        setAwake(false);

        incEnergy(ENERGY_INC_SLEEP);
        incHumor(HUMOR_INC_SLEEP);

        resetIntakes();
        resetActivities();

        return true;
    }

    public boolean wakeUp() {
        if (!isAlive() || isAwake()) { return false; }

        setAwake(true);

        incEnergy(ENERGY_INC_WAKEUP);
        incHumor(HUMOR_INC_WAKEUP);

        return hasToSleep() ? sleep() : true;
    }

    private boolean intake(float energyInc, int humorInc) {
        if (!isAlive() || !isAwake()) {
            return false;
        }

        if (++consecutiveIntakeCount == CONSECUTIVE_INTAKE_COUNT_TO_DIE) {
            die();
            return true; // or false?
        }

        resetActivities();

        incEnergy(energyInc);

        if (consecutiveIntakeCount >= CONSECUTIVE_INTAKE_COUNT_TO_CHANGE_HUMOR) {
            humorInc = HUMOR_INC_CONSECUTIVE_INTAKE;
        }
        incHumor(humorInc);

        return hasToSleep() ? sleep() : true;
    }

    public boolean eat() {
        return intake(ENERGY_INC_EAT, HUMOR_INC_EAT);
    }

    public boolean drink() {
        return intake(ENERGY_INC_DRINK, HUMOR_INC_DRINK);
    }

    private boolean performActivity(float energyInc, int humorInc) {
        if (!isAlive() || !isAwake()) {
            return false;
        }

        resetIntakes();
        consecutiveActivityCount++;

        incEnergy(energyInc);
        incHumor(humorInc);

        return hasToSleep() ? sleep() : true;
    }

    public boolean run() {
        return performActivity(ENERGY_INC_RUN, HUMOR_INC_RUN);
    }

    public boolean jump() {
        return performActivity(ENERGY_INC_JUMP, HUMOR_INC_JUMP);
    }

    @Override
    public String toString() {
        return String.format(
                "%s  (Energy: %d/%d,  Humor: %d/%d, %s, %s)",
                name, energy, ENERGY_MAX, humor, HUMOR_MAX,
                (awake ? "Awake" : "Asleep"),
                (isAlive() ? "Alive" : "Dead")
        );
    }

}


public class Tamagotchi {

    private enum Command {
        Sleep, WakeUp, Eat, Drink, Run, Jump
    }

    private static Pet pet;

    private static Pet newPet(Scanner sc) {
        String name = Menu.ask(sc, "Enter pet's name:");
        return new Pet(name);
    }

    private static void perform(Command command) {
        if (pet != null) {
            boolean done = false;

            switch (command) {
                case Sleep:
                    done = pet.sleep();
                    break;
                case WakeUp:
                    done = pet.wakeUp();
                    break;
                case Eat:
                    done = pet.eat();
                    break;
                case Drink:
                    done = pet.drink();
                    break;
                case Run:
                    done = pet.run();
                    break;
                case Jump:
                    done = pet.jump();
                    break;
            }

            String result = pet.getName() + " could" + (done ? "" : " NOT") + " " + (command == Command.WakeUp ? "wake up" : command.toString().toLowerCase());
            System.out.println(result);
            System.out.println(pet);
        } else {
            System.out.println("No pet here");
        }
    }

    public static void main(String[] args) {
        List<Pet> pets = new ArrayList<>();


        Scanner sc = new Scanner(System.in);
        MenuItem[] items = {
                new MenuItem("n", "New pet", new MenuCall() {
                    @Override
                    public void call() {
                        pet = newPet(sc);
                        System.out.println(pet);
                    }
                }),
                new MenuItem("s", "Sleep", () -> perform(Command.Sleep)),
                new MenuItem("w", "Wake Up", () -> perform((Command.WakeUp))),
                new MenuItem("e", "Eat", () -> perform(Command.Eat)),
                new MenuItem("d", "Drink", () -> perform(Command.Drink)),
                new MenuItem("r", "Run", () -> perform(Command.Run)),
                new MenuItem("j", "Jump", () -> perform(Command.Jump)),
        };
        Menu m = new Menu(sc, items);
        m.run();

        sc.close();
    }

}
