package ua.amazingjourney.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.amazingjourney.backend.exception.MazeGenerationException;
import ua.amazingjourney.backend.exception.ShortestPathCalculationException;
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

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/maze")
@RequiredArgsConstructor
@Slf4j
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
    @Operation(summary = "Generate a maze")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Maze generated"),
            @ApiResponse(responseCode = "500", description = "Error generating maze")
    })
    @PostMapping("/generate")
    public ResponseEntity<?> generateMaze(@RequestBody MazeInitializer mazeInitializer) {

        try {
            boolean[][] generatedMaze = mazeService.generateMaze(mazeInitializer);
            LinkedList<LinkedList<Integer>> shortestPath = mazeService.getShortestPath();

            return ResponseEntity.ok(new MazeResponse(generatedMaze, shortestPath));

        } catch (MazeGenerationException e) {
            log.error("Error generating maze", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (ShortestPathCalculationException e) {
            log.error("Error calculating shortest path", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (RuntimeException e) {
            log.error("Error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    /**
     * Calculates and returns the percentage of the traveled path's length compared to the shortest path's length.
     *
     * @param traveledPathRequest The request containing the traveled path.
     * @return The percentage of traveled path's length compared to the shortest path's length.
     */
    @Operation(summary = "Calculate the percentage of the traveled path's length compared to the shortest path's length")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Percentage calculated"),
            @ApiResponse(responseCode = "500", description = "Error calculating percentage")
    })
    @PostMapping("/stats")
    public ResponseEntity<?> getStats(@RequestBody TraveledPathRequest traveledPathRequest) {

        try {

            return ResponseEntity.ok(mazeService.getStats(traveledPathRequest));

        } catch (ShortestPathCalculationException e) {
            log.error("Error calculating shortest path", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (RuntimeException e) {
            log.error("Error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
