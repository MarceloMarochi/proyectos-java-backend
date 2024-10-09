package utn.frc.bka;

public class Order {

    private Integer id;

    private String date;

    public Order(Integer id, String date) {
        this.id = id;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String name) {
        this.date = name;
    }
}
