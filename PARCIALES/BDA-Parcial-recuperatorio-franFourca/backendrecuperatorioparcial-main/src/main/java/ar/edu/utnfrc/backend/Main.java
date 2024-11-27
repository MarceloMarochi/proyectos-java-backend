package ar.edu.utnfrc.backend;


import ar.edu.utnfrc.backend.models.Game;
import ar.edu.utnfrc.backend.services.GameService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("boardgames");
        EntityManager em = emf.createEntityManager();

        BoardGameLoader loader = new BoardGameLoader();
        loader.loadData("/Users/franfourcade/Developer/Facultad2024/Backend/ideaProjects/recuperatorioBackend/boardgames.csv", em);

        System.out.println("Datos cargados exitosamente.");

        GameService gameService = new GameService(em);

        System.out.println("Top 10 juegos mejor puntuados:");
        List<Game> topRatedGames = gameService.getTopRatedGames();
        for (Game game : topRatedGames) {
            System.out.println(game.getName() + " - Rating: " + game.getRating());
        }

        System.out.println("\nExportando juegos del diseñador 'Klaus Teuber'...");
        gameService.exportGamesByDesigner("Klaus Teuber", "output_klaus_teuber.csv");
        System.out.println("Archivo exportado exitosamente.");

        System.out.println("\nJuegos para 4 jugadores:");
        List<Game> gamesForFourPlayers = gameService.getGamesForPlayers(4);
        for (Game game : gamesForFourPlayers) {
            System.out.println(game.getName() + " - MinPlayers: " + game.getMinPlayers() + ", MaxPlayers: " + game.getMaxPlayers());
        }
        em.close();
        emf.close();
    }
}