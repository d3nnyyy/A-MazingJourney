package ua.amazingjourney.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Initializer of maze. Used to pass as parameter in maze constructor.
 */
@Data
@AllArgsConstructor
public class MazeInitializer {

    /**
     * Size of a maze. Not a real size of an array in maze.
     * The real size is this size * 2 - 1
     */
    private int size;

    /**
     * Difficulty.
     * The count of possible routes from start to finish depends on this variable.
     * The less the difficulty, the less there are ways to pass the labyrinth.
     */
    private int difficulty;

}
