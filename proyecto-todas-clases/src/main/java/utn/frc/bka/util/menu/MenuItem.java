package utn.frc.bka.util.menu;

public abstract class MenuItem implements MenuCall {

    private String key;

    private String title;

    public MenuItem(String key, String title) {
        this.key = key;
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
