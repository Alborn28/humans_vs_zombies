package se.experis.humans_vs_zombies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.experis.humans_vs_zombies.models.Kill;
import se.experis.humans_vs_zombies.models.Mission;
import se.experis.humans_vs_zombies.repositories.GameRepository;
import se.experis.humans_vs_zombies.repositories.MissionRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/game/{gameId}/mission")
public class MissionController {

    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private GameRepository gameRepository;

    @GetMapping
    public ResponseEntity<List<Mission>> getAllMissions(@PathVariable Long gameId) {
        List<Mission> returnMissions = missionRepository.getByGameId(gameId);
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(returnMissions, status);
    }

    @GetMapping("/{missionId}")
    public ResponseEntity<Mission> getSpecificMission(@PathVariable Long gameId, @PathVariable Long missionId) {
        Mission returnMission = new Mission();
        HttpStatus status;

        if (!missionRepository.existsById(missionId)) {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(returnMission, status);
        }

        returnMission = missionRepository.findById(missionId).get();

        if (returnMission.getGame().getId() != gameId) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnMission, status);
        }

        status = HttpStatus.OK;
        return new ResponseEntity<>(returnMission, status);
    }

    @PostMapping
    public ResponseEntity<Mission> addMission(@PathVariable Long gameId, @RequestBody Mission mission) {
        Mission returnMission = new Mission();
        HttpStatus status;

        if (!gameRepository.existsById(gameId) || mission.getGame().getId() != gameId) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnMission, status);
        }

        returnMission = missionRepository.save(mission);
        status = HttpStatus.OK;
        return new ResponseEntity<>(returnMission, status);
    }

    @PutMapping("/{missionId}")
    public ResponseEntity<Mission> updateMission(@RequestBody Mission mission, @PathVariable Long gameId, @PathVariable Long missionId) {
        Mission returnMission = new Mission();
        HttpStatus status;

        if (missionId != mission.getId() || (missionRepository.findById(missionId).get().getGame().getId() != gameId) || mission.getGame()==null) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnMission, status);
        }

        returnMission = missionRepository.save(mission);
        status = HttpStatus.OK;
        return new ResponseEntity<>(returnMission, status);
    }

    @DeleteMapping("/{missionId}")
    public ResponseEntity<Mission> deleteMission(@PathVariable Long gameId, @PathVariable Long missionId) {
        HttpStatus status = null;

        if (!missionRepository.existsById(missionId) || (missionRepository.getById(missionId).getGame().getId() != gameId)) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(status);
        }

        status = HttpStatus.OK;
        missionRepository.deleteById(missionId);
        return new ResponseEntity<>(status);
    }
}
