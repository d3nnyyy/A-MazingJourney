package ua.amazingjourney.backend.model;

import lombok.Data;

import java.util.LinkedList;

/**
 * Request for traveled path.
 * Contains a list of visited cells.
 * Used to get the data from front-end
 * and being converted to a list of visited cells.
 */

@Data
public class TraveledPathRequest {

    /**
     * A list of visited cells.
     * Will be converted to a LinkedList of Cells.
     */
    private LinkedList<LinkedList<Integer>> visitedCells;

}
