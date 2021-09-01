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
    public ResponseEntity<List<Squad>> getAllSquads(@PathVariable Long gameId){
        HttpStatus status;
        List<Squad> returnSquadList=new ArrayList<>();

        if (!gameRepository.existsById(gameId)){
            status=HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(status);
        }

        returnSquadList=squadRepository.getByGameId(gameId);
        status=HttpStatus.OK;
        return new ResponseEntity<>(returnSquadList,status);
    }

    @GetMapping("/{squadId}")
    public ResponseEntity<Squad> getSpecificSquad(@PathVariable Long gameId, @PathVariable Long squadId){
        Squad returnSquad = new Squad();
        HttpStatus status;
        if (!gameRepository.existsById(gameId)){
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(returnSquad,status);
        }
        status = HttpStatus.OK;
        returnSquad=squadRepository.findById(squadId).get();
        return  new ResponseEntity<>(returnSquad,status);
    }

    @PostMapping
    public ResponseEntity <Squad> addSquad(@PathVariable Long gameId, @RequestBody Squad squad){
        HttpStatus status=null;

        if (!gameRepository.existsById(gameId)){
            status=HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(status);
        }

        //Testa ifall man kan lägga till squad och squad member via front-end.
        squadRepository.save(squad);
        status=HttpStatus.OK;
        return new ResponseEntity<>(status);
    }
    @PostMapping("/{squadId}/join")
    public ResponseEntity <Squad> addSquadMember(@PathVariable Long gameId, @RequestBody SquadMember squadMember, @PathVariable Long squadId){
        HttpStatus status=null;

        if (!gameRepository.existsById(gameId) || !squadRepository.existsById(squadId)){
            status=HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(status);
        }
        squadMemberRepository.save(squadMember);
        status=HttpStatus.OK;
        return new ResponseEntity<>(status);
    }
    @PutMapping("/{squadId}")
    public ResponseEntity<Squad> updateSquad(@RequestBody Squad squad, @PathVariable Long gameId, @PathVariable Long squadId){
        //Admin only
        HttpStatus status;

        if (squadId != squad.getId() || (squadRepository.getById(squadId).getGame().getId() != gameId)){
            status=HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(status);
        }

        squadRepository.save(squad);
        status=HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(status);
    }
    @DeleteMapping("/{squadId}")
    public ResponseEntity<Squad> deleteSquad(@PathVariable Long gameId, @PathVariable Long squadId){
        //Admin only
        HttpStatus status=null;

        if (!squadRepository.existsById(squadId) || (squadRepository.getById(squadId).getGame().getId() != gameId)){
            status=HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(status);
        }
        status=HttpStatus.OK;
        squadRepository.deleteById(squadId);
        return new ResponseEntity<>(status);
    }


}
