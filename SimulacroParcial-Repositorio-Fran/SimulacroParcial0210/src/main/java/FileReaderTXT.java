import models.Language;
import models.Repository;
import models.Tag;
import models.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReaderTXT {


    public ArrayList<Repository> readTxt() throws FileNotFoundException {
        String path = "src/test/resources/REPOSITORIES.txt";
        String line = ""; // linea que estoy iterando
        boolean isFirstLine = true;
        String mensaje = "No funciono";
        ArrayList<Repository> repositories = new ArrayList<>();
        int cont = 0;
        Repository repo = new Repository();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                try {
                    repo = processLine(line);
                } catch (NumberFormatException error){
                    System.out.println(cont);
                }
                repositories.add(repo);
                cont++;


            }
        } catch (IOException e) {
            throw new FileNotFoundException(e.toString());
        }
        return repositories;
    }

    private Repository processLine(String line) {
        String[] splitLine = line.split("\\|");
        String userName = splitLine[1];
        String repositoryName = splitLine[2];
        String description = splitLine[3];
        String lastUpdated = splitLine[4];
        ArrayList<Language> languages = getLanguages(splitLine[5].split(","));
        double star = Double.parseDouble(splitLine[6]); // "6.4"
        ArrayList<Tag> tags = getTags(splitLine[7].split(","));
        String url = splitLine[8];
        User user = new User(userName);
        return new Repository(userName, repositoryName, description, lastUpdated, star, url, languages, tags, user);
    }

    private ArrayList<Language> getLanguages(String[] linea) {
        return Arrays.stream(linea)
                .map(Language::new)
                .collect(Collectors.toCollection(ArrayList::new));
    }
    private ArrayList<Tag> getTags(String[] linea) {
        return Arrays.stream(linea).map(Tag::new).collect(Collectors.toCollection(ArrayList::new));
    }

}
