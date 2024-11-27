package utn.frc.bka.util.menu;

// Es una clase abstracta que implementa la interfaz MenuCall. Representa un ítem genérico del menú.
public abstract class MenuItem implements MenuCall {

    private String key;

    private String title;

    public MenuItem(String key, String title) {
        // La clave o número que se debe ingresar para seleccionar esta opción.
        this.key = key;

        //El texto que describe la opción en el menú.
        this.title = title;
    }

    public String getKey() {
        return key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setKey(String key) {
        this.key = key;
    }


    @Override
    public abstract void ejecutar();

}
