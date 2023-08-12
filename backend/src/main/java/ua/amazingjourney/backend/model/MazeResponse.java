package ua.amazingjourney.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedList;

/**
 * Response for maze-related operations.
 * Contains maze grid and shortest path in the maze.
 * Used for convenient transfer of data to the front-end.
 */

@Data
@AllArgsConstructor
public class MazeResponse {

    /**
     * The maze grid.
     */
    private boolean[][] maze;

    /**
     * The shortest path in the maze.
     */
    private LinkedList<LinkedList<Integer>> shortestPath;

}
