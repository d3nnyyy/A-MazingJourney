package ua.amazingjourney.backend.model;

import lombok.Data;

public class Game {
    private Maze maze;
    private Character character;
    private GoalPosition goalPosition;

    @Data
    private class GoalPosition {
        private int x;
        private int y;
    }

    public void startNewGame() {
        //TODO:
    }

    public void restartCurrentGame() {
        //TODO:
    }
}
