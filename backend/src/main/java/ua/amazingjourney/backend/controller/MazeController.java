package ua.amazingjourney.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.amazingjourney.backend.model.MazeInitializer;
import ua.amazingjourney.backend.model.MazeResponse;
import ua.amazingjourney.backend.model.TraveledPathRequest;
import ua.amazingjourney.backend.service.MazeService;

import java.util.LinkedList;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/maze")
@RequiredArgsConstructor
public class MazeController {

    private final MazeService mazeService;

    @PostMapping("/generate")
    public MazeResponse generateMaze(@RequestBody MazeInitializer mazeInitializer) {
        boolean[][] generatedMaze = mazeService.generateMaze(mazeInitializer);
        LinkedList<LinkedList<Integer>> shortestPath = mazeService.getShortestPath();

        return new MazeResponse(generatedMaze, shortestPath);
    }

    @PostMapping("/stats")
    public Double getStats(@RequestBody TraveledPathRequest traveledPathRequest) {
        return mazeService.getStats(traveledPathRequest);
    }
}
