package ua.amazingjourney.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ua.amazingjourney.backend.model.MazeInitializer;
import ua.amazingjourney.backend.model.MazeResponse;
import ua.amazingjourney.backend.model.TraveledPathRequest;
import ua.amazingjourney.backend.service.MazeService;

import java.util.LinkedList;

/**
 * Controller for maze-related requests.
 * Contains methods for generating a maze,
 * getting the shortest path in the maze
 * and calculating the percentage of the traveled path's length
 * compared to the shortest path's length.
 */

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/maze")
@RequiredArgsConstructor
public class MazeController {

    /**
     * Injected MazeService instance.
     */
    private final MazeService mazeService;

    /**
     * Generates a maze and returns the generated maze along with the shortest path.
     *
     * @param mazeInitializer The maze initialization parameters.
     * @return MazeResponse containing the generated maze and its shortest path.
     */
    @PostMapping("/generate")
    public MazeResponse generateMaze(@RequestBody MazeInitializer mazeInitializer) {
        boolean[][] generatedMaze = mazeService.generateMaze(mazeInitializer);
        LinkedList<LinkedList<Integer>> shortestPath = mazeService.getShortestPath();

        return new MazeResponse(generatedMaze, shortestPath);
    }

    /**
     * Calculates and returns the percentage of the traveled path's length compared to the shortest path's length.
     *
     * @param traveledPathRequest The request containing the traveled path.
     * @return The percentage of traveled path's length compared to the shortest path's length.
     */
    @PostMapping("/stats")
    public Double getStats(@RequestBody TraveledPathRequest traveledPathRequest) {
        return mazeService.getStats(traveledPathRequest);
    }
}
