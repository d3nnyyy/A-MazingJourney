package ua.amazingjourney.backend.model;

import ua.amazingjourney.backend.tools.*;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedList;

/**
 * The character. Can move
 */
@Data
public class Character {
    //The row coordinate of a character
    private int yCoordinate;

    //The column coordinate of a character
    private int xCoordinate;

    //The list of cells, that were visited by character
    private LinkedList<Cell> visitedCells;

    public Character(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        visitedCells = new LinkedList<>();
        visitedCells.addFirst(new Cell(yCoordinate, xCoordinate));
    }

    /**
     * Moves character on 1 cell to the right
     */
    public void moveRight() {
        xCoordinate += 2;
        if (!visitedCells.contains(new Cell(yCoordinate, xCoordinate))) {
            visitedCells.addFirst(new Cell(yCoordinate, xCoordinate));
        }
    }

    /**
     * Moves character on 1 cell to the left
     */
    public void moveLeft() {
        xCoordinate += 2;
        if (!visitedCells.contains(new Cell(yCoordinate, xCoordinate))) {
            visitedCells.addFirst(new Cell(yCoordinate, xCoordinate));
        }
    }

    /**
     * Moves character on 1 cell up
     */
    public void moveUp() {
        yCoordinate += 2;
        if (!visitedCells.contains(new Cell(yCoordinate, xCoordinate))) {
            visitedCells.addFirst(new Cell(yCoordinate, xCoordinate));
        }
    }

    /**
     * Moves character on 1 cell down
     */
    public void moveDown() {
        yCoordinate += 2;
        if (!visitedCells.contains(new Cell(yCoordinate, xCoordinate))) {
            visitedCells.addFirst(new Cell(yCoordinate, xCoordinate));
        }
    }
}
