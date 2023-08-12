package ua.amazingjourney.backend.model;

import lombok.NoArgsConstructor;
import ua.amazingjourney.backend.tools.*;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedList;

/**
 * The character. Can move
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Character {

    //The list of cells, that were visited by character
    private LinkedList<Cell> visitedCells;
}
