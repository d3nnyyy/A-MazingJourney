package ua.amazingjourney.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.amazingjourney.backend.model.MazeInitializer;
import ua.amazingjourney.backend.service.MazeService;

@RestController
@RequestMapping("/api/maze")
@RequiredArgsConstructor
public class MazeController {

    private final MazeService mazeService;

    @PostMapping("/generate")
    public boolean[][] getGeneratedMaze(@RequestBody MazeInitializer mazeInitializer) {
        return mazeService.generateMaze(mazeInitializer);
    }

}
