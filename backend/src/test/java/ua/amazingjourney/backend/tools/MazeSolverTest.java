package ua.amazingjourney.backend.tools;

import org.junit.jupiter.api.Test;
import ua.amazingjourney.backend.exception.ShortestPathCalculationException;
import ua.amazingjourney.backend.model.Cell;
import ua.amazingjourney.backend.model.Maze;
import ua.amazingjourney.backend.model.MazeInitializer;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class MazeSolverTest {

    @Test
    public void testSolveMazeBFS() {

        int size = 10;
        int difficulty = 1;

        int start = 0;
        int finish = 2 * size - 2;

        MazeInitializer mazeInitializer = new MazeInitializer(size, difficulty);
        Maze maze = new Maze(mazeInitializer);

        Cell goalPosition = new Cell(finish, finish);
        Cell startingPosition = new Cell(start, start);

        LinkedList<Cell> path = MazeSolver.solveMazeBFS(maze, goalPosition, startingPosition);

        assertNotNull(path);
        assertTrue(path.size() > 0);
        assertEquals(startingPosition, path.getFirst());
        assertEquals(goalPosition, path.getLast());
    }

    @Test
    public void testSolveMazeSmallest() {

        int size = 1;
        int difficulty = 1;

        int start = 0;
        int finish = 0;

        MazeInitializer mazeInitializer = new MazeInitializer(size, difficulty);
        Maze maze = new Maze(mazeInitializer);

        Cell goalPosition = new Cell(finish, finish);
        Cell startingPosition = new Cell(start, start);

        LinkedList<Cell> path = MazeSolver.solveMazeBFS(maze, goalPosition, startingPosition);

        assertNotNull(path);
        assertTrue(path.size() > 0);
        assertEquals(startingPosition, path.getFirst());
        assertEquals(goalPosition, path.getLast());
    }

    @Test
    public void testSolveMazeIncorrectPositions() {

        int size = 10;
        int difficulty = 1;

        MazeInitializer mazeInitializer = new MazeInitializer(size, difficulty);
        Maze maze = new Maze(mazeInitializer);

        Cell goalPosition = new Cell(1, 1);
        Cell startingPosition = new Cell(3, 3);

        assertThrows(ShortestPathCalculationException.class, () -> MazeSolver.solveMazeBFS(maze, goalPosition, startingPosition));

    }

}