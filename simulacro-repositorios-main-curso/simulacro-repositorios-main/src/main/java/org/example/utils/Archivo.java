package org.example.utils;

import org.example.daos.LanguageDAO;
import org.example.daos.RepositoryDAO;
import org.example.daos.TagDAO;
import org.example.daos.UserDAO;
import org.example.entities.Language;
import org.example.entities.Repository;
import org.example.entities.Tag;
import org.example.entities.User;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Archivo {
    public static void guardarDatosEnDB(
            String rutaArchivo,
            LanguageDAO languageDAO,
            RepositoryDAO repositoryDAO,
            TagDAO tagDAO, UserDAO userDAO
    ) throws IOException {
        List<String> lineas = Files.readAllLines(Path.of(rutaArchivo));

        for (String linea: lineas) {
            if(!linea.startsWith("ID_REPOSITORIO")){
                String[] campos = linea.split("\\|");

                // Extraer los datos de la linea
                Long repositoryId = Long.parseLong(campos[0]);
                String userName = campos[1];
                String repositoryName = campos[2];
                String description = campos[3];
                String lastUpdate = campos[4]; // Dejarlo como String
                String languagesString = campos[5];

                String[] languages = languagesString.split(",");
                Double stars = Double.parseDouble(campos[6]);

                String tagsString = campos[7];
                String[] tags = tagsString.split(",");

                String url = campos[8];

                // Crear Usuario en el caso que no se encuentre
                User userDB = userDAO.findByName(userName);

                if (userDB == null){
                    User newUser = new User();
                    newUser.setName(userName);
                    userDB = userDAO.save(newUser);
                }

                // Crear Language en el caso que no se encuentre
                List<Language> languagesDB = new ArrayList<>();

                for (String languageName: languages){
                    Language language = languageDAO.findByName(languageName);

                    if (language == null) {
                        Language newLanguage = new Language();
                        newLanguage.setName(languageName);
                        language = languageDAO.save(newLanguage);
                    }

                    languagesDB.add(language);
                }

                // Crear Tag en el caso que no se enmcuentre
                List<Tag> tagsDB = new ArrayList<>();

                for(String tagName: tags){
                    Tag tag = tagDAO.findByName(tagName);

                    if (tag == null){
                        Tag newTag = new Tag();
                        newTag.setName(tagName);
                        tag = tagDAO.save(newTag);
                    }

                    tagsDB.add(tag);
                }

                // Crear nuevo repository
                Repository newRepository = new Repository();
                newRepository.setId(repositoryId);
                newRepository.setRepositoryName(repositoryName);
                newRepository.setDescription(description);
                newRepository.setLastUpdate(lastUpdate);
                newRepository.setStars(stars);
                newRepository.setUrl(url);
                newRepository.setUser(userDB);
                newRepository.setLanguages(languagesDB);
                newRepository.setTags(tagsDB);

                // Guardar los datos
                repositoryDAO.save(newRepository);
            }
        }
    }

    public static void generarArchivo(String rutaArchivo, String datos){
        try {
            // Utilizar Files.write para escribir la cadena en el archivo con codificación UTF-8
            Files.write(Paths.get(rutaArchivo), datos.getBytes(StandardCharsets.UTF_8));
            System.out.println("Archivo guardado exitosamente en: " + rutaArchivo);

        } catch (IOException e) {
            System.out.println("Ocurrió un error al guardar el archivo: " + e.getMessage());
        }
    }
}
