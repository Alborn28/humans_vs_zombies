package se.experis.humans_vs_zombies.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.experis.humans_vs_zombies.models.Chat;
import se.experis.humans_vs_zombies.models.Game;
import se.experis.humans_vs_zombies.repositories.ChatRepository;
import se.experis.humans_vs_zombies.repositories.GameRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/game")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ChatRepository chatRepository;

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
        returnGame = gameRepository.getById(gameId);
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

    @GetMapping("/{gameId}/chat")
    public ResponseEntity<List<Chat>> getChatMessages(@PathVariable Long gameId) {
        List<Chat> returnChat = new ArrayList<>();
        HttpStatus status;

        if (!gameRepository.existsById(gameId)) {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(returnChat, status);
        }

        status = HttpStatus.OK;
        returnChat = chatRepository.getByGameId(gameId);
        return new ResponseEntity<>(returnChat, status);
    }

    @PostMapping("/{gameId}/chat")
    public ResponseEntity<Chat> addMessage(@PathVariable Long gameId, @RequestBody Chat chat) {
        HttpStatus status;

        if (!gameRepository.existsById(gameId)) {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(status);
        }

        if (chat.getGame().getId() != gameId) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(status);
        }

        status = HttpStatus.CREATED;
        Chat returnChat = chatRepository.save(chat);
        return new ResponseEntity<>(returnChat, status);
    }
}
