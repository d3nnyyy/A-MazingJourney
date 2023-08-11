package ua.amazingjourney.backend.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class MazeGenerator {
    private int width;
    private int height;

    private boolean[][] maze;

    public MazeGenerator(int width, int height) {
        //WIDTH AND HEIGHT MUST BE ODD NUMBERS
        this.width = width;
        this.height = height;
        maze = new boolean[height][width];
        for (int i = 0; i < maze.length; i++) {
            Arrays.fill(maze[i], true);
        }
    }

    public boolean[][] generateMaze(int circlesInMaze) {
        createTypicalMaze();
        createCirclesInMaze(circlesInMaze);

        return maze;
    }

    private void createCirclesInMaze(int countOfCircles) {
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

    private void createTypicalMaze() {
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
            ArrayList<Integer> possibleWays = getPossibleWays(currentCell);

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
            }
            else {
                visitedCells.pollFirst();
            }
        }
    }

    private ArrayList<Integer> getPossibleWays(Cell currentCell) {
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
