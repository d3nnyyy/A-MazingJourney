package ua.amazingjourney.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.amazingjourney.backend.model.TraveledPathRequest;
import ua.amazingjourney.backend.service.CharacterService;
import ua.amazingjourney.backend.model.Cell;

import java.util.LinkedList;

@RequestMapping("/api/character")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class CharacterController {

    private final CharacterService characterService;

    @PostMapping("/traveled-path")
    public LinkedList<Cell> getTraveledPath(@RequestBody TraveledPathRequest traveledPathRequest) {
        return characterService.getTraveledPath(traveledPathRequest);
    }

}
