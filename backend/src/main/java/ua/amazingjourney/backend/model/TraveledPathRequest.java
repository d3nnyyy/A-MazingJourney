package ua.amazingjourney.backend.model;

import lombok.Data;

import java.util.LinkedList;

@Data
public class TraveledPathRequest {

    private LinkedList<LinkedList<Integer>> visitedCells;

}
