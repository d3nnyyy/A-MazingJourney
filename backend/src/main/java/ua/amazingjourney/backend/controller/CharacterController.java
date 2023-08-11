package ua.amazingjourney.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.amazingjourney.backend.model.CharacterMoveRequest;
import ua.amazingjourney.backend.service.CharacterService;

@RequestMapping("/api/character")
@RestController
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService characterService;

    @PostMapping("/move")
    public String moveCharacter(@RequestBody CharacterMoveRequest request) {
        return characterService.moveCharacter(request.getCharacter(), request.getMoveRequest());
    }

}
