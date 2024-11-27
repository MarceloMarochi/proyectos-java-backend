package ar.edu.utnfrc.backend.services;

import ar.edu.utnfrc.backend.models.Game;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GameService {
    private final EntityManager em;

    public GameService(EntityManager em) {
        this.em = em;
    }

    public List<Game> getTopRatedGames() {
        String query = "SELECT g FROM Game g ORDER BY g.rating DESC";
        return em.createQuery(query, Game.class)
                .setMaxResults(10)
                .getResultList();
    }

    public void exportGamesByDesigner(String designerName, String filePath) throws IOException {
        String query = "SELECT g FROM Game g WHERE g.designer.name = :designerName ORDER BY g.year ASC";
        List<Game> games = em.createQuery(query, Game.class)
                .setParameter("designerName", designerName)
                .getResultList();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Name,Year,MinPlayers,MaxPlayers,Rating,Designer,Publisher");
            writer.newLine();
            for (Game game : games) {
                writer.write(String.format("%s,%d,%d,%d,%.2f,%s,%s",
                        game.getName(),
                        game.getYear(),
                        game.getMinPlayers(),
                        game.getMaxPlayers(),
                        game.getRating(),
                        game.getDesigner().getName(),
                        game.getPublisher().getName()));
                writer.newLine();
            }
        }
    }

    public List<Game> getGamesForPlayers(int playerCount) {
        String query = "SELECT g FROM Game g WHERE g.minPlayers <= :playerCount AND g.maxPlayers >= :playerCount ORDER BY g.name ASC";
        return em.createQuery(query, Game.class)
                .setParameter("playerCount", playerCount)
                .getResultList();
    }
}