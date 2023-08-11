package ua.amazingjourney.backend.model;

import lombok.Data;

import static ua.amazingjourney.backend.tools.MazeGenerator.generateMaze;

@Data
public class Maze {

    /**
     * Example of a maze:
     * 01000100000 0
     * 01010100000 1
     * 01010001000 2
     * 01011111000 3
     * 00010000000 4
     * 11110000000 5
     * 01234567
     * Where 1 is a wall
     * As we can see, there are walls on odd rows and columns
     * while blank spaces on even rows and columns
     **/

    private boolean[][] grid;

    public Maze(MazeInitializer mazeInitializer) {
        this.grid = generateMaze(mazeInitializer);
    }

    public boolean isWall(int x, int y) {
        return grid[y][x];
    }

}
