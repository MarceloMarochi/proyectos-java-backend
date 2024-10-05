package utn.frc.bka.semana03.menuItems;

import utn.frc.bka.util.menu.MenuCall;
import utn.frc.bka.util.menu.MenuItem;


// Esta clase extiende de MenuItem y representa una opción del
// menú que ejecuta una acción específica (en este caso, la
// impresión de "N Primos").
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
