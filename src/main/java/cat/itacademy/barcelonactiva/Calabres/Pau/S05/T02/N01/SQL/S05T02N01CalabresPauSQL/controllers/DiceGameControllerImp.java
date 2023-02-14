package cat.itacademy.barcelonactiva.Calabres.Pau.S05.T02.N01.SQL.S05T02N01CalabresPauSQL.controllers;

import cat.itacademy.barcelonactiva.Calabres.Pau.S05.T02.N01.SQL.S05T02N01CalabresPauSQL.model.domain.Game;
import cat.itacademy.barcelonactiva.Calabres.Pau.S05.T02.N01.SQL.S05T02N01CalabresPauSQL.model.domain.Role;
import cat.itacademy.barcelonactiva.Calabres.Pau.S05.T02.N01.SQL.S05T02N01CalabresPauSQL.model.dto.GameDto;
import cat.itacademy.barcelonactiva.Calabres.Pau.S05.T02.N01.SQL.S05T02N01CalabresPauSQL.model.dto.PlayerDto;
import cat.itacademy.barcelonactiva.Calabres.Pau.S05.T02.N01.SQL.S05T02N01CalabresPauSQL.model.service.DiceGameServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//tots els metodes comentats a la interfice
//s'ha de tenir en compte que qualsevol jugador té permisos per fer qualsevol acció, inclus jugar per un altre jugador
@RestController
@RequestMapping("/players")
@AllArgsConstructor
public class DiceGameControllerImp implements DiceGameController{

    private final DiceGameServiceImp diceGameServiceImp;
    private final ModelMapper modelMapper;

    @Override
    @PutMapping("/update/{id}")
    public ResponseEntity<PlayerDto> updatePlayer(int id, PlayerDto playerDto, HttpServletRequest request) {

        String userName = diceGameServiceImp.getPlayerEmailByRequest(request);
        try {
        if (diceGameServiceImp.existsByIdPlayer(id)){
            PlayerDto player = diceGameServiceImp.getPlayerbyEmail(userName);
            playerDto.setPlayer_id(diceGameServiceImp.getPlayerbyId(id).getPlayer_id());
            if (userName.equals(diceGameServiceImp.getPlayerbyId(id).getEmail()) || player.getRole().equals(Role.ADMIN)){
                if ( diceGameServiceImp.updatePlayer(playerDto) != null){
                    return new ResponseEntity<>(playerDto, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.IM_USED);
                }
        }else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
       } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PostMapping("{id}/games/add")
    public ResponseEntity<GameDto> createGame(int id, HttpServletRequest request) {
        String userName = diceGameServiceImp.getPlayerEmailByRequest(request);
        try {
            if (diceGameServiceImp.existsByIdPlayer(id)){
                PlayerDto playerDto = diceGameServiceImp.getPlayerbyId(id);
                PlayerDto player = diceGameServiceImp.getPlayerbyEmail(userName);
                if (userName.equals(diceGameServiceImp.getPlayerbyId(id).getEmail()) || player.getRole().equals(Role.ADMIN)){
                    Game game = diceGameServiceImp.createGame(playerDto);
                    GameDto gameDto = modelMapper.map(game, GameDto.class);
                    return new ResponseEntity<>(gameDto, HttpStatus.CREATED);
                } else {
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                }

            } else {
                return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @DeleteMapping("{id}/games/delete")
    public ResponseEntity<HttpStatus> deleteGames(int id, HttpServletRequest request) {
        String userName = diceGameServiceImp.getPlayerEmailByRequest(request);
        try {
            if (diceGameServiceImp.existsByIdPlayer(id)){
                PlayerDto player = diceGameServiceImp.getPlayerbyEmail(userName);
                if (userName.equals(diceGameServiceImp.getPlayerbyId(id).getEmail()) || player.getRole().equals(Role.ADMIN)){
                    diceGameServiceImp.deleteGamesFromPlayer(id);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } else {
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                }

            } else {
                return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    @GetMapping("/getAll")
    public ResponseEntity<List<PlayerDto>> getAllPlayers() {
        try {
            List<PlayerDto> players = diceGameServiceImp.getAllPlayers();
            if (players.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(players, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("{id}/games/getGames")
    public ResponseEntity<List<GameDto>> getPlayerGamesById(int id, HttpServletRequest request) {
        String userName = diceGameServiceImp.getPlayerEmailByRequest(request);
        try {
            if (diceGameServiceImp.existsByIdPlayer(id)){
                PlayerDto player = diceGameServiceImp.getPlayerbyEmail(userName);
                if (userName.equals(diceGameServiceImp.getPlayerbyId(id).getEmail()) || player.getRole().equals(Role.ADMIN)){
                    List<GameDto> games = diceGameServiceImp.getGamesByPlayer(id);
                    if (games.isEmpty()){
                        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                    } else {
                        return new ResponseEntity<>(games, HttpStatus.OK);
                    }
                } else {
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                }

            } else {
                return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    @GetMapping("/games/getTotalRanking")
    public ResponseEntity<Float> getTotalRanking() {
        try {
            Float result = (float) diceGameServiceImp.totalRanking();
            if(result > 0){
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    @GetMapping("/games/getWorstPlayer")
    public ResponseEntity<PlayerDto> getWorstPlayer() {
        try {
            List<PlayerDto> players = diceGameServiceImp.getAllPlayers();
            if (players.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            PlayerDto playerDto = diceGameServiceImp.worstPlayer();
            return new ResponseEntity<>(playerDto, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/games/getBestPlayer")
    public ResponseEntity<PlayerDto> getBestPlayer() {
        try {
            List<PlayerDto> players = diceGameServiceImp.getAllPlayers();
            if (players.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            PlayerDto playerDto = diceGameServiceImp.bestPlayer();
            return new ResponseEntity<>(playerDto, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
