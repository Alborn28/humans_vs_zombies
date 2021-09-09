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

    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping
    public ResponseEntity<List<Kill>> getAllKills(@PathVariable Long gameId) {
        List<Kill> returnKills = killRepository.getByGameId(gameId);
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(returnKills, status);
    }

    @GetMapping("/{killId}")
    public ResponseEntity<Kill> getSpecificKill(@PathVariable Long gameId, @PathVariable Long killId) {
        Kill returnKill = new Kill();
        HttpStatus status;

        if (!killRepository.existsById(killId)) {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(returnKill, status);
        }

        returnKill = killRepository.findById(killId).get();

        if (returnKill.getGame().getId() != gameId) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(status);
        }

        status = HttpStatus.OK;
        return new ResponseEntity<>(returnKill, status);
    }

    @PostMapping
    public ResponseEntity<Kill> addKill(@PathVariable Long gameId, @RequestBody Kill kill) {
        Kill returnKill = new Kill();
        HttpStatus status;

        if (!gameRepository.existsById(gameId) || kill.getGame().getId() != gameId) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnKill, status);
        }

        if(!playerRepository.existsByBiteCodeAndGameId(kill.getBiteCode(), gameId)) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnKill, status);
        }

        kill.setVictim(playerRepository.getByBiteCodeAndGameId(kill.getBiteCode(), gameId));
        returnKill = killRepository.save(kill);

        Player victim = playerRepository.getByBiteCodeAndGameId(kill.getBiteCode(), gameId);
        victim.setHuman(false);
        playerRepository.save(victim);
        
        status = HttpStatus.OK;
        return new ResponseEntity<>(returnKill, status);
    }

    @PutMapping("/{killId}")
    public ResponseEntity<Kill> updateKill(@RequestBody Kill kill, @PathVariable Long gameId, @PathVariable Long killId) {
        Kill returnKill = new Kill();
        HttpStatus status;

        if (killId != kill.getId() || (killRepository.findById(killId).get().getGame().getId() != gameId) || kill.getGame()==null) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnKill, status);
        }

        returnKill = killRepository.save(kill);
        status = HttpStatus.OK;
        return new ResponseEntity<>(returnKill, status);
    }

    @DeleteMapping("/{killId}")
    public ResponseEntity<Kill> deleteKill(@PathVariable Long gameId, @PathVariable Long killId) {
        HttpStatus status = null;

        if (!killRepository.existsById(killId) || (killRepository.findById(killId).get().getGame().getId() != gameId)) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(status);
        }

        status = HttpStatus.OK;
        killRepository.deleteById(killId);
        return new ResponseEntity<>(status);
    }
}
