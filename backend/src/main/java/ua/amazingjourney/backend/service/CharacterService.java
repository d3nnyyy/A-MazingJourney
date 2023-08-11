package ua.amazingjourney.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.amazingjourney.backend.model.Character;
import ua.amazingjourney.backend.model.Maze;
import ua.amazingjourney.backend.model.MoveRequest;

@Service
public class CharacterService {

    private Maze maze;
    private Character character;

    private final MazeService mazeService;

    @Autowired
    public CharacterService(MazeService mazeService) {
        this.mazeService = mazeService;
        this.maze = mazeService.getMaze();
    }

    public String moveCharacter(Character character, MoveRequest moveRequest) {

        int newX = character.getXCoordinate();
        int newY = character.getYCoordinate();

        switch (moveRequest.getDirection()) {
            case UP -> newY -= 2;
            case DOWN -> newY += 2;
            case LEFT -> newX -= 2;
            case RIGHT -> newX += 2;
        }

        if (isValidPosition(newX, newY)) {
            character.setXCoordinate(newX);
            character.setYCoordinate(newY);
        }

        this.character = character;
        return "Character moved";
    }

    private boolean isValidPosition(int x, int y) {
        if (x < 0 || y < 0 || x >= maze.getSize() || y >= maze.getSize()) {
            return false;
        }
        return !maze.isWall(x, y);
    }


    public Character getCharacter() {
        return character;
    }
}
