package ua.amazingjourney.backend.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
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
        maze.setGrid(MazeGenerator.generateMaze(mazeInitializer));
        maze.setSize(mazeInitializer.getSize());
        return MazeGenerator.generateMaze(mazeInitializer);
    }

    /**
     * Gets the shortest path in the maze using BFS algorithm.
     *
     * @return A list of coordinates representing the shortest path.
     */
    public LinkedList<LinkedList<Integer>> getShortestPath() {

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
    }

    /**
     * Calculates and returns the percentage of the traveled path's length compared to the shortest path's length.
     *
     * @param traveledPathRequest The request containing the traveled path.
     * @return The percentage of traveled path's length compared to the shortest path's length.
     */
    public Double getStats(TraveledPathRequest traveledPathRequest) {

        LinkedList<LinkedList<Integer>> traveledPath = traveledPathRequest.getVisitedCells();

        int traveledPathLength = traveledPath.size();
        int shortestPathLength = getShortestPath().size();

        return (double) (traveledPathLength / shortestPathLength * 100);
    }
}
