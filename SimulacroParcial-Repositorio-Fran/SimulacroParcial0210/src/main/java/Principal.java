import models.Language;
import models.Repository;
import models.Tag;
import models.User;

import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Principal {
    public static void main(String[] args){
        ArrayList<Repository> repos;

        FileReaderTXT filereader = new FileReaderTXT();
        try {
            repos = filereader.readTxt();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        User usuario1 = new User("freeCodeCamp", repos);
        System.out.println(usuario1.getRepositoriesByUser());
        System.out.println(usuario1.getTotalStars());


    }
}
