package ua.amazingjourney.backend.tools;

import ua.amazingjourney.backend.model.Cell;
import ua.amazingjourney.backend.model.Maze;
import ua.amazingjourney.backend.model.MazeInitializer;

public class MazeConverter {

    public static void main(String[] args) {

        Maze maze = new Maze( new MazeInitializer(5, 10));
        System.out.println(convertToMazeString(maze.getGrid()));

        System.out.println(MazeSolver.solveMazeBFS(maze, new Cell(8,8), new Cell(0, 0)));
    }

    public static String convertToMazeString(boolean[][] mazeArray) {
        StringBuilder mazeString = new StringBuilder();

        for (int i = 0; i < mazeArray.length; i++) {
            for (int j = 0; j < mazeArray[i].length; j++) {
                if (!mazeArray[i][j]) {
                    mazeString.append(" "); // Open path
                } else {
                    mazeString.append("#"); // Wall
                }
            }
            mazeString.append("\n");
        }

        return mazeString.toString();
    }
}

