package se.experis.humans_vs_zombies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.experis.humans_vs_zombies.models.Game;
import se.experis.humans_vs_zombies.models.Player;
import se.experis.humans_vs_zombies.repositories.GameRepository;
import se.experis.humans_vs_zombies.repositories.PlayerRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/game/{gameId}/player")
public class PlayerController {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GameRepository gameRepository;

    @GetMapping()
    public ResponseEntity<List<Player>> getAllPlayers(@PathVariable Long gameId){
        List<Player> players = playerRepository.getByGameId(gameId);
        HttpStatus status= HttpStatus.OK;
        return new ResponseEntity<>(players, status);
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<Player> getSpecificPlayer(@PathVariable Long gameId, @PathVariable Long playerId){
        Player returnPlayer = new Player();
        HttpStatus status;

        if (!playerRepository.existsById(playerId)){
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(returnPlayer,status);
        }
        status = HttpStatus.OK;
        returnPlayer = playerRepository.findById(playerId).get();

        if(returnPlayer.getGame().getId() != gameId) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(status);
        }
        return  new ResponseEntity<>(returnPlayer,status);
    }

    @PostMapping
    public ResponseEntity<Player> createPlayer(@PathVariable Long gameId, @RequestBody Player player){
        HttpStatus status = HttpStatus.CREATED;
        if(!gameRepository.existsById(gameId)) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(status);
        }
        Player newPlayer = playerRepository.save(player);
        return new ResponseEntity<>(newPlayer, status);
    }

    @PutMapping("/{playerId}")
    public ResponseEntity<Player> updatePlayer(@RequestBody Player player,@PathVariable Long gameId, @PathVariable Long playerId){
        HttpStatus status;

        if (playerId != player.getId() || (playerRepository.getById(playerId).getGame().getId() != gameId)){
            status=HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(status);
        }
        playerRepository.save(player);
        status=HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(status);
    }

    @DeleteMapping("/{playerId}")
    public ResponseEntity<Player> deletePlayer(@PathVariable Long gameId, @PathVariable Long playerId){
        HttpStatus status=null;

        if (!playerRepository.existsById(playerId) || (playerRepository.getById(playerId).getGame().getId() != gameId)){
            status=HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(status);
        }
        status=HttpStatus.OK;
        playerRepository.deleteById(playerId);
        return new ResponseEntity<>(status);
    }
}
