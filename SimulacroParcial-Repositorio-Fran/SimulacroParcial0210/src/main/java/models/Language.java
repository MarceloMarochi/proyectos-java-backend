package models;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Language {
    private String name;
    private ArrayList<Repository> repositoriosPorLenguaje;
    private int totalRepositorios;

    public Language(String name) {
        this.name = name;
    }
    public Language() {}

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Repository> getRepositoriosPorLenguaje(ArrayList<Repository> repositorios) {
        return repositorios.stream()
                .filter(repo -> repo.getLanguages().stream()
                        .anyMatch(language -> language.getName().equals(this.name)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public String toString() {
        return "Language{" +
                "name='" + name + '\'' +
                '}';
    }
}
