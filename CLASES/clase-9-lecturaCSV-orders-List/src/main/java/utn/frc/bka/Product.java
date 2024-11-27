package utn.frc.bka;

public class Product {
// Clase para asignarle los valores obtenidos en el CSV a una instancia


    // Cuando el tipo de dato empieza con mayúscula es de tipo envoltorio y aadmite nulos.
    private Integer id;

    private String nombre;

    private Integer count;

    private Double price;

    // Constructor
    public Product(Integer id, String nombre, Integer count, Double price) {
        this.id = id;
        this.nombre = nombre;
        this.count = count;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
