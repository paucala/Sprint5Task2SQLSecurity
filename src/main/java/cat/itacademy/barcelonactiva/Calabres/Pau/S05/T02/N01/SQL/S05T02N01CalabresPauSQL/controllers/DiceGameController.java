package cat.itacademy.barcelonactiva.Calabres.Pau.S05.T02.N01.SQL.S05T02N01CalabresPauSQL.controllers;

import cat.itacademy.barcelonactiva.Calabres.Pau.S05.T02.N01.SQL.S05T02N01CalabresPauSQL.model.dto.GameDto;
import cat.itacademy.barcelonactiva.Calabres.Pau.S05.T02.N01.SQL.S05T02N01CalabresPauSQL.model.dto.PlayerDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.token.Token;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface DiceGameController {
    //PUT /players: modifica el nom del jugador/a.
    ResponseEntity<PlayerDto> updatePlayer(@PathVariable("id") int id, @RequestBody PlayerDto playerDto, HttpServletRequest request);
    //POST /players/{id}/games/ : un jugador/a específic realitza una tirada dels daus.
    ResponseEntity<GameDto> createGame(@PathVariable("id") int id,HttpServletRequest request);
    //DELETE /players/{id}/games: elimina les tirades del jugador/a.
    ResponseEntity<HttpStatus> deleteGames(@PathVariable("id") int id, HttpServletRequest request);
    //GET /players/: retorna el llistat de tots els jugadors/es del sistema amb el seu percentatge mitjà d’èxits.
    ResponseEntity<List<PlayerDto>> getAllPlayers();
    //GET /players/{id}/games: retorna el llistat de jugades per un jugador/a.
    ResponseEntity<List<GameDto>> getPlayerGamesById(@PathVariable("id") int id, HttpServletRequest request);
    //GET /players/ranking: retorna el ranking mig de tots els jugadors/es del sistema. És a dir, el  percentatge mitjà d’èxits.
    ResponseEntity<Float> getTotalRanking();
    //GET /players/ranking/loser: retorna el jugador/a  amb pitjor percentatge d’èxit.
    ResponseEntity<PlayerDto> getWorstPlayer();
    //GET /players/ranking/winner: retorna el  jugador amb pitjor percentatge d’èxit.
    ResponseEntity<PlayerDto> getBestPlayer();










}
