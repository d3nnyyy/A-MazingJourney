package ua.amazingjourney.backend.model;

import lombok.Data;

@Data
public class Maze {

    private int width;
    private int height;

    private boolean[][] grid;

    public Maze(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new boolean[height][width];
    }

    public boolean isWall(int x, int y) {
        return grid[y][x];
    }

    public void generateMaze() {
        //TODO:
    }

}
