package utn.frc.bka.semana03.menuItems;

import utn.frc.bka.util.menu.MenuCall;
import utn.frc.bka.util.menu.MenuItem;

public class MenuCallNPrimos extends MenuItem {

    public MenuCallNPrimos(String key, String title) {
        super(key, title);
    }

    @Override
    public void ejecutar() {
        System.out.println("N Primos");
        //NPrimos.run();
    }
}
