package ua.amazingjourney.backend.model;

import lombok.Data;

public class Game {
    private Maze maze;
    private Character character;
    private GoalPosition goalPosition;
    //Size of a maze. The value must be odd
    private int size;

    private static final int COUNT_OF_CIRCLES = 5;
    private static final int DEFAULT_CHARACTER_COORDINATE = 0;

    @Data
    private class GoalPosition {
        private int x;
        private int y;
    }

    public void startNewGame() {
        maze = new Maze(size, size);
        maze.generateMaze(COUNT_OF_CIRCLES);
        character = new Character(DEFAULT_CHARACTER_COORDINATE, DEFAULT_CHARACTER_COORDINATE);
        //TODO: Do the rest
    }

    public void restartCurrentGame() {
        character = new Character(DEFAULT_CHARACTER_COORDINATE, DEFAULT_CHARACTER_COORDINATE);
        //TODO: do the rest
    }
}
