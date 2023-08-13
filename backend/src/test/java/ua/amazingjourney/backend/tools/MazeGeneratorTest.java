package ua.amazingjourney.backend.tools;

import org.junit.jupiter.api.Test;
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
    }

}