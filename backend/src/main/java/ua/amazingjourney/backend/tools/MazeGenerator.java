package ua.amazingjourney.backend.tools;

import ua.amazingjourney.backend.model.MazeInitializer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class MazeGenerator {

    public static boolean[][] generateMaze(MazeInitializer mazeInitializer) {

        int width = mazeInitializer.getSize() * 2 - 1;
        int height = mazeInitializer.getSize() * 2 - 1;


        int maxDifficulty = 10;
        int numberOfCircles = (maxDifficulty + 1 - mazeInitializer.getDifficulty()) * (mazeInitializer.getSize() / 4);

        boolean[][] maze = new boolean[height][width];

        for (int i = 0; i < maze.length; i++) {
            Arrays.fill(maze[i], true);
        }

        createTypicalMaze(maze);
        createCirclesInMaze(numberOfCircles, width, height, maze);

        return maze;
    }

    private static void createCirclesInMaze(int countOfCircles, int width, int height, boolean[][] maze) {
        int alreadyErased = 0;
        Random randomGenerator = new Random();

        while (alreadyErased < countOfCircles) {
            int randomICoordinate = randomGenerator.nextInt((height - 1) / 2) * 2 + 1;
            int randomJCoordinate = randomGenerator.nextInt((width - 1) / 2) * 2 + 1;
            if (maze[randomICoordinate][randomJCoordinate]) {
                alreadyErased++;
                maze[randomICoordinate][randomJCoordinate] = false;
            }
        }

    }

    private static void createTypicalMaze(boolean[][] maze) {
        LinkedList<Cell> visitedCells = new LinkedList<>();
        Random randomGenerator = new Random();
        //We start from top-left corner
        visitedCells.addFirst(new Cell(0, 0));
        maze[0][0] = false;

        while (!visitedCells.isEmpty()) {
            //Choosing random way
            //1 - top, 2 - right, 3 - down, 4 - left

            Cell currentCell = visitedCells.getFirst();

            //Defining accessible ways to pick from
            ArrayList<Integer> possibleWays = getPossibleWays(currentCell, maze);

            if (!possibleWays.isEmpty()) {

                int way = possibleWays.get(randomGenerator.nextInt(possibleWays.size()));

                switch (way) {
                    case 1 -> { //Go up
                        maze[currentCell.iCoordinate - 1][currentCell.jCoordinate] = false;
                        maze[currentCell.iCoordinate - 2][currentCell.jCoordinate] = false;
                        visitedCells.addFirst(new Cell(currentCell.iCoordinate - 2, currentCell.jCoordinate));
                    }
                    case 2 -> { //Go right
                        maze[currentCell.iCoordinate][currentCell.jCoordinate + 1] = false;
                        maze[currentCell.iCoordinate][currentCell.jCoordinate + 2] = false;
                        visitedCells.addFirst(new Cell(currentCell.iCoordinate, currentCell.jCoordinate + 2));
                    }
                    case 3 -> { //Go down
                        maze[currentCell.iCoordinate + 1][currentCell.jCoordinate] = false;
                        maze[currentCell.iCoordinate + 2][currentCell.jCoordinate] = false;
                        visitedCells.addFirst(new Cell(currentCell.iCoordinate + 2, currentCell.jCoordinate));
                    }
                    case 4 -> { //Go left
                        maze[currentCell.iCoordinate][currentCell.jCoordinate - 1] = false;
                        maze[currentCell.iCoordinate][currentCell.jCoordinate - 2] = false;
                        visitedCells.addFirst(new Cell(currentCell.iCoordinate, currentCell.jCoordinate - 2));
                    }
                }
            } else {
                visitedCells.pollFirst();
            }
        }
    }

    private static ArrayList<Integer> getPossibleWays(Cell currentCell, boolean[][] maze) {
        ArrayList<Integer> possibleWays = new ArrayList<>();
        if (currentCell.iCoordinate != 0
                && maze[currentCell.iCoordinate - 2][currentCell.jCoordinate]) {
            possibleWays.add(1);
        }
        if (currentCell.jCoordinate != (maze[0].length - 1)
                && maze[currentCell.iCoordinate][currentCell.jCoordinate + 2]) {
            possibleWays.add(2);
        }
        if (currentCell.iCoordinate != (maze.length - 1)
                && maze[currentCell.iCoordinate + 2][currentCell.jCoordinate]) {
            possibleWays.add(3);
        }
        if (currentCell.jCoordinate != 0
                && maze[currentCell.iCoordinate][currentCell.jCoordinate - 2]) {
            possibleWays.add(4);
        }
        return possibleWays;
    }
}
