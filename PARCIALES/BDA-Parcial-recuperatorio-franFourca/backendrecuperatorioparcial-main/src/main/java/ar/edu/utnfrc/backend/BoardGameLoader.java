package ar.edu.utnfrc.backend;

import ar.edu.utnfrc.backend.models.Designer;
import ar.edu.utnfrc.backend.models.Game;
import ar.edu.utnfrc.backend.models.Publisher;
import jakarta.persistence.EntityManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BoardGameLoader {
    public void loadData(String filePath, EntityManager em) throws Exception {
        Map<String, Designer> designers = new HashMap<>();
        Map<String, Publisher> publishers = new HashMap<>();

        em.getTransaction().begin();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {

                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] data = line.split(",");

                String name = data[0].trim();
                int year = Integer.parseInt(data[1].trim());
                int minPlayers = Integer.parseInt(data[2].trim());
                int maxPlayers = Integer.parseInt(data[3].trim());
                double rating = Double.parseDouble(data[4].trim());
                String designerName = data[5].trim();
                String publisherName = data[6].trim();


                Designer designer = designers.get(designerName);
                if (designer == null) {
                    designer = new Designer();
                    designer.setName(designerName);
                    em.persist(designer);
                    designers.put(designerName, designer);
                }

                Publisher publisher = publishers.get(publisherName);
                if (publisher == null) {
                    publisher = new Publisher();
                    publisher.setName(publisherName);
                    em.persist(publisher);
                    publishers.put(publisherName, publisher);
                }


                Game game = new Game();
                game.setName(name);
                game.setYear(year);
                game.setMinPlayers(minPlayers);
                game.setMaxPlayers(maxPlayers);
                game.setRating(rating);
                game.setDesigner(designer);
                game.setPublisher(publisher);

                em.persist(game);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            throw e;
        }

        em.getTransaction().commit();
    }
}
