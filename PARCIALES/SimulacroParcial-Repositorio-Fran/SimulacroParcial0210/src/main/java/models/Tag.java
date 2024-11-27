package models;

public class Tag {
    private String name;

    public Tag(String name) {
        this.name = name;
    }
    public Tag(){}

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "name='" + name + '\'' +
                '}';
    }
}
