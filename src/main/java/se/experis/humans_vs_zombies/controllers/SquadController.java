package se.experis.humans_vs_zombies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.experis.humans_vs_zombies.models.*;
import se.experis.humans_vs_zombies.repositories.GameRepository;
import se.experis.humans_vs_zombies.repositories.PlayerRepository;
import se.experis.humans_vs_zombies.repositories.SquadMemberRepository;
import se.experis.humans_vs_zombies.repositories.SquadRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/game/{gameId}/squad")
public class SquadController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private SquadRepository squadRepository;

    @Autowired
    private SquadMemberRepository squadMemberRepository;

    @GetMapping
    public ResponseEntity<List<Squad>> getAllSquads(@PathVariable Long gameId) {
        List<Squad> returnSquads = squadRepository.getByGameId(gameId);
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(returnSquads, status);
    }

    @GetMapping("/{squadId}")
    public ResponseEntity<Squad> getSpecificSquad(@PathVariable Long gameId, @PathVariable Long squadId) {
        Squad returnSquad = new Squad();
        HttpStatus status;

        if (!squadRepository.existsById(squadId)) {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(returnSquad, status);
        }

        returnSquad = squadRepository.getById(squadId);

        if (returnSquad.getGame().getId() != gameId) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnSquad, status);
        }

        status = HttpStatus.OK;
        return new ResponseEntity<>(returnSquad, status);
    }

    @PostMapping
    public ResponseEntity<Squad> addSquad(@PathVariable Long gameId, @RequestBody Squad squad) {
        Squad returnSquad = new Squad();
        HttpStatus status;

        if (!gameRepository.existsById(gameId)) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnSquad, status);
        }

        //Testa ifall man kan lägga till squadmember när man skapar squad via front-end, annars här.

        returnSquad = squadRepository.save(squad);
        status = HttpStatus.OK;
        return new ResponseEntity<>(returnSquad, status);
    }

    @PostMapping("/{squadId}/join")
    public ResponseEntity<SquadMember> addSquadMember(@PathVariable Long gameId, @PathVariable Long squadId, @RequestBody SquadMember squadMember) {
        SquadMember returnSquad = new SquadMember();
        HttpStatus status;

        if (!gameRepository.existsById(gameId) || !squadRepository.existsById(squadId)) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnSquad, status);
        }
        returnSquad = squadMemberRepository.save(squadMember);
        status = HttpStatus.OK;
        return new ResponseEntity<>(returnSquad, status);
    }

    @PutMapping("/{squadId}")
    public ResponseEntity<Squad> updateSquad(@PathVariable Long gameId, @PathVariable Long squadId, @RequestBody Squad squad) {
        Squad returnSquad = new Squad();
        HttpStatus status;

        if (squadId != squad.getId() || (squadRepository.getById(squadId).getGame().getId() != gameId)) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnSquad, status);
        }

        squadRepository.save(squad);
        status = HttpStatus.OK;
        return new ResponseEntity<>(returnSquad, status);
    }

    @DeleteMapping("/{squadId}")
    public ResponseEntity<Squad> deleteSquad(@PathVariable Long gameId, @PathVariable Long squadId) {
        HttpStatus status;

        if (!squadRepository.existsById(squadId) || (squadRepository.getById(squadId).getGame().getId() != gameId)) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(status);
        }

        squadRepository.deleteById(squadId);
        status = HttpStatus.OK;
        return new ResponseEntity<>(status);
    }
}
