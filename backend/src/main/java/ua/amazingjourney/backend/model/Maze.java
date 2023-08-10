package ua.amazingjourney.backend.model;

import lombok.Data;
import ua.amazingjourney.backend.tools.MazeGenerator;

@Data
public class Maze {

    private int width;
    private int height;


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
     * and blank spaces on even rows and columns
     */

    private boolean[][] grid;

    public Maze(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new boolean[height][width];
    }

    public boolean isWall(int x, int y) {
        return grid[y][x];
    }

    public void generateMaze(int countOfCircles) {
        grid = (new MazeGenerator(width, height)).generateMaze(countOfCircles);
    }

}
