package ua.amazingjourney.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.amazingjourney.backend.model.MazeInitializer;
import ua.amazingjourney.backend.model.MazeResponse;
import ua.amazingjourney.backend.service.MazeService;

import java.util.LinkedList;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/maze")
@RequiredArgsConstructor
public class MazeController {

    private final MazeService mazeService;

//    @PostMapping("/generate")
//    public boolean[][] getGeneratedMaze(@RequestBody MazeInitializer mazeInitializer) {
//        return mazeService.generateMaze(mazeInitializer);
//    }
//
//    @GetMapping("/shortest-path")
//    public LinkedList<LinkedList<Integer>> getShortestPath() {
//        return mazeService.getShortestPath();
//    }

    @PostMapping("/generate")
    public MazeResponse generateMaze(@RequestBody MazeInitializer mazeInitializer) {
        boolean[][] generatedMaze = mazeService.generateMaze(mazeInitializer);
        LinkedList<LinkedList<Integer>> shortestPath = mazeService.getShortestPath();

        return new MazeResponse(generatedMaze, shortestPath);
    }
}
