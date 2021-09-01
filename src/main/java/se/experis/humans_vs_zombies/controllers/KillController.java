package se.experis.humans_vs_zombies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import se.experis.humans_vs_zombies.models.Kill;
import se.experis.humans_vs_zombies.models.Player;
import se.experis.humans_vs_zombies.repositories.GameRepository;
import se.experis.humans_vs_zombies.repositories.KillRepository;
import se.experis.humans_vs_zombies.repositories.PlayerRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/game/{gameId}/kill")
public class KillController {


    @Autowired
    private KillRepository killRepository;
    @Autowired
    private GameRepository gameRepository;

    @GetMapping
    @PreAuthorize("hasRole('Administrator')")
    public ResponseEntity<List<Kill>> getAllKills(@PathVariable Long gameId){
        HttpStatus status;

        List<Kill> returnKills= new ArrayList<>();

        if (!gameRepository.existsById(gameId)){
            status=HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(status);
        }

        returnKills=killRepository.getByGameId(gameId);
        status=HttpStatus.OK;
        return new ResponseEntity<>(returnKills,status);
    }

    @GetMapping("/{killId}")
    public ResponseEntity<Kill> getSpecificKill(@PathVariable Long gameId, @PathVariable Long killId){
        Kill returnKill = new Kill();
        HttpStatus status;

        if (!killRepository.existsById(killId)){
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(returnKill,status);
        }
        status = HttpStatus.OK;
        returnKill = killRepository.findById(killId).get();

        if(returnKill.getGame().getId() != gameId) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(status);
        }
        return  new ResponseEntity<>(returnKill,status);
    }

    @PostMapping
    public ResponseEntity <Kill> addKill(@PathVariable Long gameId, @RequestBody Kill kill){
        HttpStatus status=null;

        if (!gameRepository.existsById(gameId)){
            status=HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(status);
        }

        killRepository.save(kill);
        status=HttpStatus.OK;
        return new ResponseEntity<>(status);
    }
    @PutMapping("/{killId}")

    public ResponseEntity<Kill> updateKill(@RequestBody Kill kill, @PathVariable Long gameId, @PathVariable Long killId){
        HttpStatus status;

        if (killId != kill.getId() || (killRepository.getById(killId).getGame().getId() != gameId)){
            status=HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(status);
        }

        killRepository.save(kill);
        status=HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(status);
    }
    @DeleteMapping("/{killId}")
    public ResponseEntity<Kill> deleteKill(@PathVariable Long gameId, @PathVariable Long killId){
        HttpStatus status=null;

        if (!killRepository.existsById(killId) || (killRepository.getById(killId).getGame().getId() != gameId)){
            status=HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(status);
        }
        status=HttpStatus.OK;
        killRepository.deleteById(killId);
        return new ResponseEntity<>(status);
    }
}
