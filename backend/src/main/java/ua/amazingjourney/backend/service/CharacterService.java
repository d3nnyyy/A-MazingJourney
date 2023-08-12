package ua.amazingjourney.backend.service;

import org.springframework.stereotype.Service;
import ua.amazingjourney.backend.model.TraveledPathRequest;
import ua.amazingjourney.backend.model.Cell;

import java.util.LinkedList;

@Service
public class CharacterService {

    public LinkedList<Cell> getTraveledPath(TraveledPathRequest traveledPathRequest) {

        LinkedList<Cell> visitedCells = new LinkedList<>();
        for (LinkedList<Integer> cell : traveledPathRequest.getVisitedCells()) {
            visitedCells.add(new Cell(cell.get(0), cell.get(1)));
        }

        return visitedCells;
    }

}
