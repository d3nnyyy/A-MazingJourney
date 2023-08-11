package ua.amazingjourney.backend.tools;

import ua.amazingjourney.backend.model.Character;
import ua.amazingjourney.backend.model.Maze;

import java.util.LinkedList;

public class MazeSolver {

    public LinkedList<Cell> solveMazeBFS(Maze maze, Cell goalPosition, Character character) {
        return solveMazeBFS(maze, goalPosition, new Cell(character.getYCoordinate(), character.getXCoordinate()));
    }

    public LinkedList<Cell> solveMazeBFS(Maze maze, Cell goalPosition, Cell startingPosition) {
        //Maze in progress of solving. It is integer, so that we can mark distance from each point to beginning
        //And later we can go back to the start
        int[][] solvingMaze;

        boolean[][] mazes = new boolean[maze.][];
    }

}
