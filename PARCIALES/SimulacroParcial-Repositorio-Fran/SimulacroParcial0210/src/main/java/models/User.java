package models;

import java.util.ArrayList;
import java.util.Objects;

public class User {
    private String name;
    private ArrayList<Repository> repositoriesByUser;
    private double stars;

    public User(String name, ArrayList<Repository> repositoriesByUser) {
        this.name = name;
        this.repositoriesByUser = this.getRepositoriesByUser(repositoriesByUser);
    }
    public User(ArrayList<Repository> repositoriesByUser) {
        this.repositoriesByUser = repositoriesByUser;
    }
    public User(String name) {
        this.name = name;
    }
    public User() {

    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Repository> getRepositoriesByUser(ArrayList<Repository> repositories) {
        ArrayList<Repository> repositoriesByUser = new ArrayList<>();
        for (Repository repository : repositories) {
            if (Objects.equals(repository.user.getName(), this.name)){
                repositoriesByUser.add(repository);
            }
        }
        return repositoriesByUser;
    }

    public ArrayList<Repository> getRepositoriesByUser() {
        return repositoriesByUser;
    }
    public double getTotalStars() {
        double totalStars = 0;
        for (Repository repository : this.repositoriesByUser) {
            totalStars += repository.getStars();
        }
        return totalStars;
        //return this.repositoriesByUser.stream().map(Repository::getStars).reduce(0d, Double::sum);

    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(repositoriesByUser, user.repositoriesByUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, repositoriesByUser);
    }
}
