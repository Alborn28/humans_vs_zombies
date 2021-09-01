package se.experis.humans_vs_zombies.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.experis.humans_vs_zombies.models.Chat;
import se.experis.humans_vs_zombies.models.Game;
import se.experis.humans_vs_zombies.repositories.ChatRepository;
import se.experis.humans_vs_zombies.repositories.GameRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/game")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ChatRepository chatRepository;


    @GetMapping()
    public ResponseEntity<List<Game>> getAllGames(){
        List<Game> games=gameRepository.findAll();
        HttpStatus status= HttpStatus.OK;
        return new ResponseEntity<>(games, status);
    }
    @GetMapping("/{gameId}")
    public ResponseEntity<Game> getSpecificGame(@PathVariable Long gameId){
        Game returnGame = new Game();
        HttpStatus status;
        if (!gameRepository.existsById(gameId)){
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(returnGame,status);
        }
        status = HttpStatus.OK;
        returnGame=gameRepository.findById(gameId).get();
        return  new ResponseEntity<>(returnGame,status);
    }
    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game){
        Game newGame= gameRepository.save(game);
        HttpStatus status=HttpStatus.CREATED;
        return new ResponseEntity<>(newGame,status);
    }
    @PutMapping("/{gameId}")
    public ResponseEntity<Game> updateGame(@RequestBody Game game,@PathVariable Long gameId){
        Game returnGame=new Game();
        HttpStatus status;

        if (gameId != game.getId()){
            status=HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnGame, status);
        }
        returnGame=gameRepository.save(game);
        status=HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(returnGame,status);
    }
    @DeleteMapping("/{gameId}")
    public ResponseEntity<Game> deleteGame(@PathVariable Long gameId){
        HttpStatus status=null;

        if (!gameRepository.existsById(gameId)){
            status=HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(status);
        }
        status = HttpStatus.OK;
        gameRepository.deleteById(gameId);
        return new ResponseEntity<>(status);
    }
    @GetMapping("/{gameId}/chat")
    public ResponseEntity<Chat> getChatMessages(@PathVariable Long gameId){
        // date syntax "2008-03-02T23:00:00.000+00:00" || "'2008-03-03'"
        Chat returnChat = new Chat();
        HttpStatus status;
        if (!chatRepository.existsById(gameId)){
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(returnChat,status);
        }
        status = HttpStatus.OK;
        returnChat=chatRepository.findById(gameId).get();
        return  new ResponseEntity<>(returnChat,status);
    }

    @PostMapping("/{gameId}/chat")
    public ResponseEntity<Chat> addMessage(@PathVariable Long gameId, @RequestBody Chat chat){
        HttpStatus status;

        if (!gameRepository.existsById(gameId)){
            status=HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(status);
        }

        chat.setGame(gameRepository.getById(gameId));
        Chat returnChat= chatRepository.save(chat);
        status=HttpStatus.CREATED;
        return new ResponseEntity<>(returnChat,status);
    }
}
