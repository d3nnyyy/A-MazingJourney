package ua.amazingjourney.backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import static ua.amazingjourney.backend.tools.MazeGenerator.generateMaze;

/**
 * A maze. Has its grid and size
 */
@Data
@NoArgsConstructor
public class Maze {

    /*
     * Example of a maze:
     * 010001 0
     * 010101 1
     * 010100 2
     * 010111 3
     * 000100 4
     * 111100 5
     * 012345
     * Where 1 is a wall
     * As we can see, there are walls on odd rows and columns
     * while blank spaces on even rows and columns
     **/

    /**
     * A grid. An array of true and false values, where true is a wall and false is a space for walking
     */
    private boolean[][] grid;

    /**
     * A really walkable length of side of a maze
     * the true size of a maze is this size * 2 - 1
     * it will be the length of a side of a maze array
     */
    private int size;

    /**
     * Default constructor. Creates a maze
     * @param mazeInitializer an initializer, which has maze's data
     */
    public Maze(MazeInitializer mazeInitializer) {
        this.grid = generateMaze(mazeInitializer);
        this.size = mazeInitializer.getSize();
    }


}
