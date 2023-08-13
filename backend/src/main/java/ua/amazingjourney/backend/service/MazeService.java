package ua.amazingjourney.backend.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import ua.amazingjourney.backend.exception.MazeGenerationException;
import ua.amazingjourney.backend.exception.ShortestPathCalculationException;
import ua.amazingjourney.backend.exception.StatsCalculationException;
import ua.amazingjourney.backend.model.Cell;
import ua.amazingjourney.backend.model.Maze;
import ua.amazingjourney.backend.model.MazeInitializer;
import ua.amazingjourney.backend.model.TraveledPathRequest;
import ua.amazingjourney.backend.tools.MazeGenerator;
import ua.amazingjourney.backend.tools.MazeSolver;

import java.util.LinkedList;

/**
 * Service for maze-related operations.
 * Contains methods for generating a maze,
 * getting the shortest path in the maze
 * and calculating the percentage of the traveled path's length
 * compared to the shortest path's length.
 */

@Service
@Getter
public class MazeService {

    /**
     * The maze instance.
     */
    private final Maze maze;

    /**
     * Constructor for MazeService class.
     * Initializes the maze instance.
     */
    public MazeService() {
        this.maze = new Maze();
    }

    /**
     * Generates a maze based on the provided maze initialization parameters.
     *
     * @param mazeInitializer The maze initialization parameters.
     * @return The generated maze.
     */
    public boolean[][] generateMaze(MazeInitializer mazeInitializer) {

        try {
            maze.setGrid(MazeGenerator.generateMaze(mazeInitializer));
            maze.setSize(mazeInitializer.getSize());
            return maze.getGrid();
        } catch (Exception e) {
            throw new MazeGenerationException("Error generating maze", e);
        }

    }

    /**
     * Gets the shortest path in the maze using BFS algorithm.
     *
     * @return A list of coordinates representing the shortest path.
     */
    public LinkedList<LinkedList<Integer>> getShortestPath() {

        try {

            LinkedList<Cell> solvedLinkedListOfCells = MazeSolver.solveMazeBFS(
                    maze,
                    new Cell(0, 0),
                    new Cell(maze.getSize() * 2 - 2, maze.getSize() * 2 - 2));
            LinkedList<LinkedList<Integer>> solvedLinkedListOfLinkedLists = new LinkedList<>();

            for (Cell cell : solvedLinkedListOfCells) {
                LinkedList<Integer> solvedLinkedList = new LinkedList<>();
                solvedLinkedList.add(cell.getICoordinate());
                solvedLinkedList.add(cell.getJCoordinate());
                solvedLinkedListOfLinkedLists.add(solvedLinkedList);
            }
            return solvedLinkedListOfLinkedLists;

        } catch (Exception e) {
            throw new ShortestPathCalculationException("Error calculating shortest path", e);
        }
    }

    /**
     * Calculates and returns the percentage of the traveled path's length compared to the shortest path's length.
     *
     * @param traveledPathRequest The request containing the traveled path.
     * @return The percentage of traveled path's length compared to the shortest path's length.
     */
    public Double getStats(TraveledPathRequest traveledPathRequest) {

        try {

            int traveledPathLength = traveledPathRequest.getVisitedCells().size();
            int shortestPathLength = getShortestPath().size();

            return ((double) traveledPathLength / shortestPathLength) * 100;

        } catch (StatsCalculationException e) {
            throw new StatsCalculationException("Error calculating stats", e);
        }

    }
}
