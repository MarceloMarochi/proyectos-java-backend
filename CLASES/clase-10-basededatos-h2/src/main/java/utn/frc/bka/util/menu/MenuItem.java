package utn.frc.bka.util.menu;

public class MenuItem {
    private final String itemKey;
    private final String itemText;
    private final MenuCall itemCall;

    public MenuItem(String itemKey, String itemText, MenuCall itemCall) {
        this.itemKey = itemKey;
        this.itemText = itemText;
        this.itemCall = itemCall;
    }

    /**
     * @return the item key
     */
    public String getItemKey() {
        return itemKey;
    }

    /**
     * @return the item text
     */
    public String getItemText() {
        return itemText;
    }

    public void execute() {
        itemCall.call();
    }

}
