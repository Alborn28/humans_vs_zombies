package se.experis.humans_vs_zombies.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.experis.humans_vs_zombies.models.Game;
import se.experis.humans_vs_zombies.models.enumerators.GameState;
import se.experis.humans_vs_zombies.repositories.GameRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/game")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    /**
     * Get a list of all games
     */
    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> games = gameRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(games, status);
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<Game> getSpecificGame(@PathVariable Long gameId) {
        Game returnGame = new Game();
        HttpStatus status;

        if (!gameRepository.existsById(gameId)) {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(returnGame, status);
        }

        status = HttpStatus.OK;
        returnGame = gameRepository.findById(gameId).get();
        return new ResponseEntity<>(returnGame, status);
    }

    @PostMapping
    public ResponseEntity<Game> addGame(@RequestBody Game game) {
        Game newGame = gameRepository.save(game);
        HttpStatus status = HttpStatus.CREATED;
        return new ResponseEntity<>(newGame, status);
    }

    @PutMapping("/{gameId}")
    public ResponseEntity<Game> updateGame(@RequestBody Game game, @PathVariable Long gameId) {
        Game returnGame = new Game();
        HttpStatus status;

        if (gameId != game.getId()) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnGame, status);
        }

        returnGame = gameRepository.save(game);
        status = HttpStatus.OK;
        return new ResponseEntity<>(returnGame, status);
    }

    @PatchMapping("/{gameId}")
    public ResponseEntity<Game> patchGame(@RequestBody Game updatedGame, @PathVariable Long gameId) {
        Game returnGame = new Game();
        HttpStatus status;

        if (gameId != updatedGame.getId()) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnGame, status);
        }

        Game game = gameRepository.findById(gameId).get();
        game.setName(updatedGame.getName());
        game.setDescription(updatedGame.getDescription());
        game.setGameState(updatedGame.getGameState());
        game.setStartDate(updatedGame.getStartDate());
        game.setEndDate(updatedGame.getEndDate());

        returnGame = gameRepository.save(game);
        status = HttpStatus.OK;
        return new ResponseEntity<>(returnGame, status);
    }

    @PutMapping("/{gameId}/start")
    public ResponseEntity<Game> startGame(@PathVariable Long gameId) {
        Game updatedGame = new Game();
        HttpStatus status;

        if (!gameRepository.existsById(gameId)) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(updatedGame, status);
        }

        updatedGame = gameRepository.findById(gameId).get();
        updatedGame.setGameState(GameState.IN_PROGRESS);
        updatedGame = gameRepository.save(updatedGame);
        status = HttpStatus.OK;
        return new ResponseEntity<>(updatedGame, status);
    }

    @PutMapping("/{gameId}/end")
    public ResponseEntity<Game> endGame(@PathVariable Long gameId) {
        Game updatedGame = new Game();
        HttpStatus status;

        if (!gameRepository.existsById(gameId)) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(updatedGame, status);
        }

        updatedGame = gameRepository.findById(gameId).get();
        updatedGame.setGameState(GameState.COMPLETE);
        updatedGame = gameRepository.save(updatedGame);
        status = HttpStatus.OK;
        return new ResponseEntity<>(updatedGame, status);
    }

    @DeleteMapping("/{gameId}")
    public ResponseEntity<Game> deleteGame(@PathVariable Long gameId) {
        HttpStatus status = null;

        if (!gameRepository.existsById(gameId)) {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(status);
        }

        status = HttpStatus.OK;
        gameRepository.deleteById(gameId);
        return new ResponseEntity<>(status);
    }
}
