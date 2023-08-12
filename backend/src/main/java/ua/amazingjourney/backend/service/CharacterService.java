package ua.amazingjourney.backend.service;

import org.springframework.stereotype.Service;
import ua.amazingjourney.backend.model.Character;
import ua.amazingjourney.backend.tools.Cell;

import java.util.LinkedList;

@Service
public class CharacterService {

    public LinkedList<Cell> getTraveledPath(Character character) {
        return character.getVisitedCells();
    }

}
