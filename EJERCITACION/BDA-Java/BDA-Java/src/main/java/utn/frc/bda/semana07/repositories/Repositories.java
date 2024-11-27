package utn.frc.bda.semana07.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import utn.frc.bda.semana07.repositories.domain.entities.RepositoryEntity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

public class Repositories {
    private static final int REPOSITORY_ID_INDEX = 0, REPOSITORY_NAME_INDEX = 2, REPOSITORY_DESCRIPTION_INDEX = 3;
    private static final int REPOSITORY_LASTUPDATE_INDEX = 4, REPOSITORY_STARS_INDEX = 6, REPOSITORY_URL_INDEX = 8;
    private static final int USERNAME_INDEX = 1, LANGUAGE_INDEX = 5, TAGS_INDEX = 7;
    private static final String PERSISTENCE_UNIT_NAME = "h2RPU";
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    record Language(String language) {}
    record Tag(String tag) {}
    record User(String name) {}
    record Repository(
            Integer id, String name, String description,
            Date lastUpdate, Double stars, String url,
            User user, List<Language> languages, List<Tag> tags
    ) {}

    record RepositoryRow(
            Integer id, String name, String description,
            Date lastUpdate, Double stars, String url,
            String user, String languages, String tags
    ) {}

    private static List<RepositoryRow> rowList;
    private static Map<String, User> usersMap = new HashMap<>();
    private static Map<String, Language> languagesMap = new HashMap<>();
    private static Map<String, Tag> tagsMap = new HashMap<>();
    private static Map<Integer, Repository> repositoryiesMap = new HashMap<>();

    private static Function<String[], RepositoryRow> repositoryRowMapper = (arr) -> {
        Date date = null;
        try {
            DateFormat format = new SimpleDateFormat(DATE_FORMAT);
            date = format.parse(arr[REPOSITORY_LASTUPDATE_INDEX]);
        } catch (Exception e) {
            //throw new RuntimeException(e.getMessage() + " DATE: " + arr[REPOSITORY_LASTUPDATE_INDEX]);
            date = new Date();
        }
        return new RepositoryRow(
                Integer.parseInt(arr[REPOSITORY_ID_INDEX]),
                arr[REPOSITORY_NAME_INDEX],
                arr[REPOSITORY_DESCRIPTION_INDEX],
                //new SimpleDateFormat(DATE_FORMAT).parse(arr[REPOSITORY_LASTUPDATE_INDEX]),
                date,
                Double.valueOf(arr[REPOSITORY_STARS_INDEX]),
                arr[REPOSITORY_URL_INDEX],
                arr[USERNAME_INDEX],
                arr[LANGUAGE_INDEX],
                arr[TAGS_INDEX]
        );
    };


    public static void main(String[] args) throws Exception {
        //String fPath = System.getProperty("user.dir") + "/src/main/java/utn/frc/bda/semana07/repositories/Repositories.txt";
        String fPath = Repositories.class.getResource("/repositories/Repositories.txt").getFile();
        loadCollections(fPath);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        String sqlScriptPath = Repositories.class.getResource("/repositories/Repositories.sql").getFile();
        //em.getTransaction().begin();
        createDB(em, sqlScriptPath);
        //em.getTransaction().commit();

        //em.getTransaction().begin();
        populateDB(em);
        //em.getTransaction().commit();

        List<RepositoryEntity> reps = em.createNamedQuery("Repository.findAll").getResultList();
        RepositoryEntity rep = reps.get(2);
        System.out.println(rep);
        rep.getLanguages().forEach(languageEntity -> System.out.println(languageEntity.getLanguage()));
        rep.getTags().forEach(tagEntity -> System.out.println(tagEntity.getTag()));

        em.close();
        emf.close();
    }

    private static void loadCollections(String txtFilePath) {
        loadRowList(txtFilePath);
        rowList.forEach(repositoryRow -> updateCollections(repositoryRow));
    }

    private static void loadRowList(String txtFilePath) {
        String delim = "(?<!\\\\)\\|";

        //int rown = 0;
        //try {
        //    List<String> lines = readTxtFileLinesWithFilesAndPaths(txtFilePath);
        //    for (int i = 1; i < lines.size(); i++) {
        //        rown = i;
        //        if (rown >= 122) {
        //            rown = rown;
        //        }
        //        String line = lines.get(i);
        //        String[] fields = line.split(delim);
        //        RepositoryRow row = repositoryRowMapper.apply(fields);
        //    }
        //} catch (Exception e) {
        //    System.err.println("Row: " + rown + "  " + e.getMessage());
        //}


        rowList = readTxtFileLinesWithFilesAndPaths(txtFilePath)
                .stream()
                .skip(1)
                .map(l -> Arrays.stream(l.split(delim, -1))
                        .map(el -> el
                                .replaceAll("\\\\\\|", "|")
                                .trim())
                        .toArray(String[]::new))
                .map(arr -> repositoryRowMapper.apply(arr))
                .toList();
    }

    private static List<String> readTxtFileLinesWithFilesAndPaths(String txtFilePath) {
        try (Stream<String> s = Files.lines(Paths.get(txtFilePath))) {
            return s.toList();
        } catch (IOException ioe) {
            return new ArrayList<>();
        }
    }

    private static void updateCollections(RepositoryRow row) {
        User user = usersMap.get(row.user);
        if (user == null) {
            user = new User(row.user);
            usersMap.put(user.name, user);
        }

        List<Language> languageList = new ArrayList<>();
        Arrays.stream(row.languages.split(",")).forEach(lang -> {
            if (!lang.isBlank()) {
                Language language = languagesMap.get(lang);
                if (language == null) {
                    language = new Language(lang);
                    languagesMap.put(language.language, language);
                }
                languageList.add(language);
            }
        });

        List<Tag> tagList = new ArrayList<>();
        Arrays.stream(row.tags.split(",")).forEach(tg -> {
            if (!tg.isBlank()) {
                Tag tag = tagsMap.get(tg);
                if (tag == null) {
                    tag = new Tag(tg);
                    tagsMap.put(tag.tag, tag);
                }
                tagList.add(tag);
            }
        });

        Repository repository = new Repository(
                row.id, row.name, row.description,
                row.lastUpdate, row.stars, row.url,
                user, languageList, tagList
        );
        repositoryiesMap.put(repository.id, repository);
    }

    private static void createDB(EntityManager em, String sqlScriptPath) throws Exception {
        String script = String.join("\n", Files.lines(Paths.get(sqlScriptPath)).toList());
        String ss = Files.readString(Paths.get(sqlScriptPath));

        em.createNativeQuery(script).executeUpdate();
    }


    private static void populateDB(EntityManager em) {
        cleanDB(em);

        usersMap.keySet().forEach(name ->
                em.createNativeQuery("insert into usr(uname) values ?")
                        .setParameter(1, name)
                        .executeUpdate()
        );

        languagesMap.keySet().forEach(language ->
                em.createNativeQuery("insert into language(language) values ?")
                        .setParameter(1, language)
                        .executeUpdate()
        );

        tagsMap.keySet().forEach(tag ->
                em.createNativeQuery("insert into tag(tag) values ?")
                        .setParameter(1, tag)
                        .executeUpdate()
        );

        repositoryiesMap.values().forEach(repository -> {
            try {
                em.createNativeQuery(
                                "insert into repository(rid, uname, rname, description, last_update, stars, url)" +
                                        " values (?, ?, ?, ?, ?, ?, ?)"
                        ).setParameter(1, repository.id)
                        .setParameter(2, repository.user.name)
                        .setParameter(3, repository.name)
                        .setParameter(4, repository.description)
                        .setParameter(5, repository.lastUpdate)
                        .setParameter(6, repository.stars)
                        .setParameter(7, repository.url)
                        .executeUpdate();
            } catch (Exception e) {
                System.out.println("==============================================");
                System.out.println("ERR: " + repository.id);

            }

            repository.languages.forEach(lang ->
                    em.createNativeQuery("insert into repository_languages(rid, language) values (?, ?)")
                            .setParameter(1, repository.id)
                            .setParameter(2, lang.language)
                            .executeUpdate()
            );

            repository.tags.forEach(tag ->
                    em.createNativeQuery("insert into repository_tags(rid, tag) values(?, ?)")
                            .setParameter(1, repository.id)
                            .setParameter(2, tag.tag)
                            .executeUpdate()
            );
        });
        
    }

    private static void cleanDB(EntityManager em) {
        em.createNativeQuery("delete from repository_tags").executeUpdate();
        em.createNativeQuery("delete from repository_languages").executeUpdate();
        em.createNativeQuery("delete from repository").executeUpdate();
        em.createNativeQuery("delete from tag").executeUpdate();
        em.createNativeQuery("delete from language").executeUpdate();
        em.createNativeQuery("delete from usr").executeUpdate();
    }



}
