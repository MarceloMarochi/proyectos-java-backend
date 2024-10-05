package org.example;

import org.example.common.EntityManagerProvider;
import org.example.daos.LanguageDAO;
import org.example.daos.RepositoryDAO;
import org.example.daos.TagDAO;
import org.example.daos.UserDAO;
import org.example.dtos.LanguageStatistics;
import org.example.dtos.RepositoryStatistics;
import org.example.dtos.UserStatistics;
import org.example.entities.Language;
import org.example.entities.Repository;
import org.example.entities.User;
import org.example.utils.Archivo;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        // Inicializar DAOs
        LanguageDAO languageDAO = new LanguageDAO();
        RepositoryDAO repositoryDAO = new RepositoryDAO();
        TagDAO tagDAO = new TagDAO();
        UserDAO userDAO = new UserDAO();

        // Guardar datos en la BD
        try {
            Archivo.guardarDatosEnDB("repositories.txt", languageDAO, repositoryDAO, tagDAO, userDAO);
            System.out.println("Datos guardados exitosamente");
        } catch (Exception e){
            System.out.println("No se pudo cargar los datos a la BD");
        }

        // Mostrar todos los repostories cargados en BD
        List<Repository> repositories = repositoryDAO.findAll();
        repositories.forEach(System.out::println);

        // Consulta por language
        Language language = languageDAO.findById(1L);
        List<Repository> repositoriesByTag = repositoryDAO.findAllByLanguage(language);
        repositoriesByTag.forEach(System.out::println);

        // Consultar la lista de repositorios de un usuario específico y
        // la lista de lenguajes que utiliza en todos sus repositorios.
        User user = userDAO.findById(10L);
        List<Language> languagesByUser = languageDAO.findLanguagesByUser(user);
        languagesByUser.forEach(System.out::println);

        // 4) Generar un archivo de texto separado por comas con la lista de lenguajes y
        // la cantidad de repositorios que utilizan cada uno de ellos y la
        // suma de estrellas del conjunto de repositorios que utilizan el lenguaje
        List<LanguageStatistics> languagesStatistics = languageDAO.getLanguageStatistics();
        languagesStatistics.forEach(System.out::println);
        String datosString = "";

        for(LanguageStatistics languageStatistics: languagesStatistics){
            datosString += languageStatistics.toString() + "\n";
        }
        Archivo.generarArchivo("languages_statistics.txt", datosString);

        // 5)  Mostrar por pantalla la lista de usuarios ordenados alfabéticamente junto con
        // la cantidad de repositorios que tienen y el número total de estrellas que han recibido.
        List<UserStatistics> usersStatistics = userDAO.getUserStatistics();
        usersStatistics.forEach(System.out::println);

        // 3)  Determinar e informar la cantidad total de repositorios importados y el número
        // total de estrellas acumuladas por todos los repositorios
        RepositoryStatistics repositoryStatistics = repositoryDAO.getTotalRepositoriesAndStars();
        System.out.println(repositoryStatistics.toString());

        // Cerrar conexion
        EntityManagerProvider.closeEntityManager();
    }
}