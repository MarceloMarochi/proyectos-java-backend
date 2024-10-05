package models;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Repository {
    private String userName;
    private String repositoryName;
    private String description;
    private String lastUpdate;
    private double stars;
    private String url;
    public User user; // -< [User1, User2]
    public ArrayList<Language> languages;
    public ArrayList<Tag> tags;

    public Repository(String userName,String repositoryName, String description, String lastUpdate, double stars, String url, User user, ArrayList<Language> languages, ArrayList<Tag> tags) {
        this.userName = userName;
        this.repositoryName = repositoryName;
        this.description = description;
        this.lastUpdate = lastUpdate;
        this.stars = stars;
        this.url = url;
        this.user = user;
        this.languages = languages;
        this.tags = tags;
    }
    public Repository(String userName,String repositoryName, String description, String lastUpdate, double stars, String url, ArrayList<Language> languages, ArrayList<Tag> tags) {
        this.userName = userName;
        this.repositoryName = repositoryName;
        this.description = description;
        this.lastUpdate = lastUpdate;
        this.stars = stars;
        this.url = url;
        this.languages = languages;
        this.tags = tags;
    }
    public Repository(String userName,String repositoryName, String description, String lastUpdate, double stars, String url, ArrayList<Language> languages, ArrayList<Tag> tags, User user) {
        this.userName = userName;
        this.repositoryName = repositoryName;
        this.description = description;
        this.lastUpdate = lastUpdate;
        this.stars = stars;
        this.url = url;
        this.languages = languages;
        this.tags = tags;
        this.user = user;
    }

    public Repository(String userName, String repositoryName, String description, String lastUpdate, double stars, String url) {
        this.userName = userName;
        this.repositoryName = repositoryName;
        this.description = description;
        this.lastUpdate = lastUpdate;
        this.stars = stars;
        this.url = url;
        this.user = new User();
        this.languages = new ArrayList<>();
        this.tags = new ArrayList<>();
    }

    public Repository() {
    }



    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public double getStars() {
        return stars;
    }

    public ArrayList<Language> getLanguages() {
        return languages;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public void setTags(Tag tag) {
        this.tags.add(tag);
    }

    @Override
    public String toString() {
        return " \n Repository{" +
                "repositoryName='" + repositoryName + '\'' +
                ", description='" + description + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", stars=" + stars +
                ", url='" + url + '\'' +
                ", users=" + user +
                ", languages=" + languages +
                ", tags=" + tags +
                '}' + '\n' ;
    }
}
