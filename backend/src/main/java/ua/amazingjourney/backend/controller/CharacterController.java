package ua.amazingjourney.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.amazingjourney.backend.model.TraveledPathRequest;
import ua.amazingjourney.backend.service.CharacterService;
import ua.amazingjourney.backend.tools.Cell;

import java.util.LinkedList;

@RequestMapping("/api/character")
@RestController
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService characterService;

    @PostMapping("/traveled-path")
    public LinkedList<Cell> getTraveledPath(@RequestBody TraveledPathRequest traveledPathRequest) {
        return characterService.getTraveledPath(traveledPathRequest);
    }

}
