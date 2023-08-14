package ua.amazingjourney.backend.tools;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ua.amazingjourney.backend.exception.MazeGenerationException;
import ua.amazingjourney.backend.model.MazeInitializer;

import static org.junit.jupiter.api.Assertions.*;

class MazeGeneratorTest {

    @Test
    public void testGenerateMaze() {
        MazeInitializer mazeInitializer = new MazeInitializer(5,2);

        boolean[][] maze = MazeGenerator.generateMaze(mazeInitializer);

        assertNotNull(maze);
        assertEquals(9, maze.length);
        assertEquals(9, maze[0].length);

        System.out.println(MazeConverter.convertToMazeString(maze));
    }

    @Test
    public void testNegativeSize() {
        MazeInitializer mazeInitializer = new MazeInitializer(-10,2);

        assertThrows(MazeGenerationException.class, () -> MazeGenerator.generateMaze(mazeInitializer));
    }

    @Test
    public void testNegativeDifficulty() {
        MazeInitializer mazeInitializer = new MazeInitializer(10,-2);

        assertThrows(MazeGenerationException.class, () -> MazeGenerator.generateMaze(mazeInitializer));
    }

    @Test
    public void testSmallestMazeSmallestDifficulty() {
        MazeInitializer mazeInitializer = new MazeInitializer(1,1);

        boolean[][] maze = MazeGenerator.generateMaze(mazeInitializer);

        assertNotNull(maze);
        assertEquals(1, maze.length);
        assertEquals(1, maze[0].length);

        System.out.println(MazeConverter.convertToMazeString(maze));
    }

    @Test
    public void testSmallestMazeBiggestDifficulty() {
        MazeInitializer mazeInitializer = new MazeInitializer(1,10);

        boolean[][] maze = MazeGenerator.generateMaze(mazeInitializer);

        assertNotNull(maze);
        assertEquals(1, maze.length);
        assertEquals(1, maze[0].length);

        System.out.println(MazeConverter.convertToMazeString(maze));
    }

    @Test
    public void testNormalMazeBiggestDifficulty() {
        MazeInitializer mazeInitializer = new MazeInitializer(10,10);

        boolean[][] maze = MazeGenerator.generateMaze(mazeInitializer);

        assertNotNull(maze);
        assertEquals(19, maze.length);
        assertEquals(19, maze[0].length);

        System.out.println(MazeConverter.convertToMazeString(maze));
    }

    @Test
    public void testNormalMazeSmallestDifficulty() {
        MazeInitializer mazeInitializer = new MazeInitializer(10,1);

        boolean[][] maze = MazeGenerator.generateMaze(mazeInitializer);

        assertNotNull(maze);
        assertEquals(19, maze.length);
        assertEquals(19, maze[0].length);

        System.out.println(MazeConverter.convertToMazeString(maze));
    }

    @Test
    public void testSmallMazeSmallestDifficulty() {
        MazeInitializer mazeInitializer = new MazeInitializer(2,1);

        boolean[][] maze = MazeGenerator.generateMaze(mazeInitializer);

        assertNotNull(maze);
        assertEquals(3, maze.length);
        assertEquals(3, maze[0].length);

        System.out.println(MazeConverter.convertToMazeString(maze));
    }

    @Test
    public void testSmallMazeBiggestDifficulty() {
        MazeInitializer mazeInitializer = new MazeInitializer(2,10);

        boolean[][] maze = MazeGenerator.generateMaze(mazeInitializer);

        assertNotNull(maze);
        assertEquals(3, maze.length);
        assertEquals(3, maze[0].length);

        System.out.println(MazeConverter.convertToMazeString(maze));
    }

    @AfterEach
    public void breakBetweenMazes() {
        System.out.println("Next maze");
        System.out.println();
    }

}