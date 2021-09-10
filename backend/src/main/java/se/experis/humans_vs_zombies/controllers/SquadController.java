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
    private SquadRepository squadRepository;

    @Autowired
    private SquadMemberRepository squadMemberRepository;

    @Autowired
    private ChatRepository chatRepository;

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

        if(squadMemberRepository.existsByPlayer(squadMember.getPlayer())) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnSquad, status);
        }

        returnSquad = squadMemberRepository.save(squadMember);
        status = HttpStatus.OK;
        return new ResponseEntity<>(returnSquad, status);
    }

    @PutMapping("/{squadId}")
    public ResponseEntity<Squad> updateSquad(@PathVariable Long gameId, @PathVariable Long squadId, @RequestBody Squad squad) {
        //Cascading kan ta bort koppling till Game, går inte att lägga till kopplingen i efterhand
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

    @GetMapping("/{squadId}/chat")
    public ResponseEntity<List<Chat>> getChatMessages(@PathVariable Long gameId, @PathVariable Long squadId) {
        List<Chat> returnChat = new ArrayList<>();
        HttpStatus status;

        if (!squadRepository.existsById(squadId) || (squadRepository.getById(squadId).getGame().getId() != gameId)) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnChat, status);
        }

        status = HttpStatus.OK;
        returnChat = chatRepository.getByGameIdAndSquadId(gameId, squadId);
        return new ResponseEntity<>(returnChat, status);
    }

    @PostMapping("/{squadId}/chat")
    public ResponseEntity<Chat> addMessage(@PathVariable Long gameId, @PathVariable Long squadId, @RequestBody Chat chat) {
        Chat returnChat = new Chat();
        HttpStatus status;

        if (!squadRepository.existsById(squadId)) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnChat, status);
        }

        if (chat.getGame().getId() != gameId || chat.getSquad().getId() != squadId) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnChat, status);
        }

        status = HttpStatus.CREATED;
        returnChat = chatRepository.save(chat);
        return new ResponseEntity<>(returnChat, status);
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

        status = HttpStatus.CREATED;
        returnSquadCheckin = squadCheckinRepository.save(squadCheckin);
        return new ResponseEntity<>(returnSquadCheckin, status);
    }
}
