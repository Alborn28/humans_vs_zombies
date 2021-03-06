package se.experis.humans_vs_zombies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.experis.humans_vs_zombies.models.*;
import se.experis.humans_vs_zombies.repositories.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/game/{gameId}/squad")
public class SquadController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private SquadRepository squadRepository;

    @Autowired
    private SquadMemberRepository squadMemberRepository;

    @Autowired
    private SquadCheckinRepository squadCheckinRepository;

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

        returnSquad = squadRepository.findById(squadId).get();

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

        if (!gameRepository.existsById(gameId) || squadRepository.existsByNameAndGameId(squad.getName(), gameId)) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnSquad, status);
        }

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

        if(squadMemberRepository.existsByPlayer(squadMember.getPlayer())) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnSquad, status);
        }

        returnSquad = squadMemberRepository.save(squadMember);
        status = HttpStatus.OK;
        return new ResponseEntity<>(returnSquad, status);
    }

    @DeleteMapping("/{squadId}/leave/{playerId}")
    public ResponseEntity<SquadMember> deleteSquadMember(@PathVariable Long gameId, @PathVariable Long squadId, @PathVariable Long playerId) {
        SquadMember returnSquad = new SquadMember();
        HttpStatus status;

        if (!gameRepository.existsById(gameId) || !squadRepository.existsById(squadId) || !playerRepository.existsById(playerId)) {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(returnSquad, status);
        }

        Player player = playerRepository.findById(playerId).get();
        SquadMember squadMember = player.getSquadMember();

        if(!squadMemberRepository.existsByPlayer(player)) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnSquad, status);
        }

        player.setSquadMember(null);
        playerRepository.save(player);

        squadMemberRepository.deleteById(squadMember.getId());
        status = HttpStatus.OK;
        return new ResponseEntity<>(status);
    }

    @PutMapping("/{squadId}")
    public ResponseEntity<Squad> updateSquad(@PathVariable Long gameId, @PathVariable Long squadId, @RequestBody Squad squad) {
        Squad returnSquad = new Squad();
        HttpStatus status;

        if (squadId != squad.getId() || (squadRepository.getById(squadId).getGame().getId() != gameId) || squad.getGame()==null) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnSquad, status);
        }

        squadRepository.save(squad);
        status = HttpStatus.OK;
        return new ResponseEntity<>(returnSquad, status);
    }

    @PatchMapping("/{squadId}")
    public ResponseEntity<Squad> patchSquad(@RequestBody Squad updatedSquad, @PathVariable Long gameId, @PathVariable Long squadId) {
        Squad returnSquad = new Squad();
        HttpStatus status;

        if (squadId != updatedSquad.getId()) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnSquad, status);
        }

        Squad squad = squadRepository.findById(squadId).get();
        squad.setName(updatedSquad.getName());

        returnSquad = squadRepository.save(squad);
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

    @GetMapping("/{squadId}/check-in")
    public ResponseEntity<List<SquadCheckin>> getAllCheckins(@PathVariable Long gameId, @PathVariable Long squadId) {
        List<SquadCheckin> returnCheckins = new ArrayList<>();
        HttpStatus status;

        if (!squadRepository.existsById(squadId) || (squadRepository.getById(squadId).getGame().getId() != gameId)) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnCheckins, status);
        }

        status = HttpStatus.OK;
        returnCheckins = squadCheckinRepository.getByGameIdAndSquadId(gameId, squadId);
        return new ResponseEntity<>(returnCheckins, status);
    }

    @PostMapping("/{squadId}/check-in")
    public ResponseEntity<SquadCheckin> addCheckin(@PathVariable Long gameId, @PathVariable Long squadId, @RequestBody SquadCheckin squadCheckin) {
        SquadCheckin returnSquadCheckin = new SquadCheckin();
        HttpStatus status;

        if (!squadRepository.existsById(squadId)) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnSquadCheckin, status);
        }

        if (squadCheckin.getGame().getId() != gameId || squadCheckin.getSquad().getId() != squadId) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnSquadCheckin, status);
        }

        SquadMember squadMember = squadMemberRepository.findById(squadCheckin.getSquadMember().getId()).get();

        //If the squad member already has a checkin, remove it and save the new one.
        if(squadMember.getSquadCheckin() != null) {
            Long squadCheckinId = squadMember.getSquadCheckin().getId();
            squadMember.setSquadCheckin(null);
            squadMemberRepository.save(squadMember);
            squadCheckinRepository.deleteById(squadCheckinId);
        }

        status = HttpStatus.CREATED;
        returnSquadCheckin = squadCheckinRepository.save(squadCheckin);
        return new ResponseEntity<>(returnSquadCheckin, status);
    }
}
