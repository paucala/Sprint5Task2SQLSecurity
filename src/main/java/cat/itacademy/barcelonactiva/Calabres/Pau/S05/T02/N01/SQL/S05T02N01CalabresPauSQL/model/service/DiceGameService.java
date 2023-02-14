package cat.itacademy.barcelonactiva.Calabres.Pau.S05.T02.N01.SQL.S05T02N01CalabresPauSQL.model.service;

import cat.itacademy.barcelonactiva.Calabres.Pau.S05.T02.N01.SQL.S05T02N01CalabresPauSQL.model.domain.Game;
import cat.itacademy.barcelonactiva.Calabres.Pau.S05.T02.N01.SQL.S05T02N01CalabresPauSQL.model.domain.Player;
import cat.itacademy.barcelonactiva.Calabres.Pau.S05.T02.N01.SQL.S05T02N01CalabresPauSQL.model.dto.GameDto;
import cat.itacademy.barcelonactiva.Calabres.Pau.S05.T02.N01.SQL.S05T02N01CalabresPauSQL.model.dto.PlayerDto;

import java.util.List;

public interface DiceGameService {

    //Metodes perr crear partides i jugadors/es
    Game createGame(PlayerDto playerDto);
    //Metodes de comprobació
    boolean existsByIdPlayer (int id);
    boolean existingPlayerbyName (String name);
    boolean existingPlayerbyEmail (String email);
    boolean existsByIdGame (int id);
    //Metodes per obtenir informació
    PlayerDto getPlayerbyId(int id);
    List<PlayerDto> getAllPlayers();
    List<GameDto> getAllGames();
    List<GameDto> getGamesByPlayer(int id);
    //Metodes d'actualització
    PlayerDto updatePlayer (PlayerDto playerDto);
    //Metodes de eliminació
    void deleteGamesFromPlayer(int id);
    //Ranking
    double totalRanking();
    PlayerDto worstPlayer();
    PlayerDto bestPlayer();
    //Metodes auxiliars
    int diceRoll();
    float setPlayerExitPercent(int id);


}
