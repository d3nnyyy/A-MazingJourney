package ua.amazingjourney.backend.model;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The character. Can move
 */
@Data
@AllArgsConstructor
public class Character {
    //The row coordinate of a character
    private int yCoordinate;

    //The column coordinate of a character
    private int xCoordinate;

    /**
     * Moves character on 1 cell to the right
     */
    public void moveRight() {
        xCoordinate++;
    }

    /**
     * Moves character on 1 cell to the left
     */
    public void moveLeft() {
        xCoordinate--;
    }

    /**
     * Moves character on 1 cell up
     */
    public void moveUp() {
        yCoordinate--;
    }

    /**
     * Moves character on 1 cell down
     */
    public void moveDown() {
        yCoordinate++;
    }
}
