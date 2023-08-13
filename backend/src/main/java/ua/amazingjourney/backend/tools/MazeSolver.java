package ua.amazingjourney.backend.tools;

import ua.amazingjourney.backend.exception.ShortestPathCalculationException;
import ua.amazingjourney.backend.model.Cell;
import ua.amazingjourney.backend.model.Maze;

import java.util.LinkedList;

/**
 * A class, that has static methods, which solve maze
 */
public class MazeSolver {

    /**
     * Solves maze using BFS algorithm
     * @param maze maze to solve
     * @param goalPosition position to get to
     * @param startingPosition position to start from
     * @return the list of cells. Starting from beginning to goal position
     */
    public static LinkedList<Cell> solveMazeBFS(Maze maze, Cell goalPosition, Cell startingPosition) {

        //Checking if starting and goal positions are accessible
        if (startingPosition.getICoordinate() < 0 || startingPosition.getICoordinate() >= maze.getSize() * 2 - 1
                || startingPosition.getJCoordinate() < 0 || startingPosition.getJCoordinate() >= maze.getSize() * 2 - 1) {
            throw new ShortestPathCalculationException("The starting position is not in the maze");
        }
        if (goalPosition.getICoordinate() < 0 || goalPosition.getICoordinate() >= maze.getSize() * 2 - 1
                || goalPosition.getJCoordinate() < 0 || goalPosition.getJCoordinate() >= maze.getSize() * 2 - 1) {
            throw new ShortestPathCalculationException("The goal position is not in the maze");
        }
        if (maze.getGrid()[startingPosition.getICoordinate()][startingPosition.getJCoordinate()]) {
            throw new ShortestPathCalculationException("The starting position is inaccessible");
        }
        if (maze.getGrid()[goalPosition.getICoordinate()][goalPosition.getJCoordinate()]) {
            throw new ShortestPathCalculationException("The goal position is inaccessible");
        }


        //Calculating actual size of a maze array
        int width = maze.getSize() * 2 - 1;
        int height = maze.getSize() * 2 - 1;

        //Maze in progress of solving. It is integer, so that we can mark distance from each point to beginning
        //And later we can go back to the start
        int[][] solvingMaze = new int[height][width];

        //Filling integer array
        fillNewMaze(solvingMaze, maze.getGrid());

        //Filling array with distances to beginning until we reach the goal
        goToGoalPosition(goalPosition, startingPosition, solvingMaze);

        //Now we make the list that is the way to the goal
        LinkedList<Cell> pathToTheGoal = findPathToTheGoal(goalPosition, startingPosition, solvingMaze);

        return pathToTheGoal;
    }

    /**
     * Finds the shortest path to the goal, using array with distances to beginning
     * @param goalPosition cell with goal coordinates
     * @param startingPosition cell with starting coordinates
     * @param solvingMaze array with filled distances to the start
     * @return linked list of cells in order from start to goal - the shortest path
     */
    private static LinkedList<Cell> findPathToTheGoal(Cell goalPosition, Cell startingPosition, int[][] solvingMaze) {
        //Creating the path
        LinkedList<Cell> pathToTheGoal = new LinkedList<>();

        //Now we start from the goal cell
        pathToTheGoal.addFirst(new Cell(goalPosition));

        //While we don't get to start
        while (!pathToTheGoal.getFirst().equals(startingPosition)) {

            Cell currentCell = pathToTheGoal.getFirst();

            //If we got to start
            if (currentCell.equals(startingPosition)) {
                break;
            }

            //Choosing the next cell to go to
            int way = chooseWayToGo(solvingMaze, currentCell);

            switch (way) {
                case 1 -> //Go up
                        pathToTheGoal.addFirst(new Cell(currentCell.getICoordinate() - 1, currentCell.getJCoordinate()));
                case 2 -> //Go right
                        pathToTheGoal.addFirst(new Cell(currentCell.getICoordinate(), currentCell.getJCoordinate() + 1));
                case 3 -> //Go down
                        pathToTheGoal.addFirst(new Cell(currentCell.getICoordinate() + 1, currentCell.getJCoordinate()));
                case 4 -> //Go left
                        pathToTheGoal.addFirst(new Cell(currentCell.getICoordinate(), currentCell.getJCoordinate() - 1));
            }

        }

        return pathToTheGoal;
    }

    /**
     * Finds the way to go from the current cell
     * @param solvingMaze maze to find the way in
     * @param currentCell cell from which we will find the next cell
     * @return 1 is up, 2 is right, 3 is down, 4 is left
     */
    private static int chooseWayToGo(int[][] solvingMaze, Cell currentCell) {
        //We are finding the shortest distance from current cell to the start
        int lowest = solvingMaze[currentCell.getICoordinate()][currentCell.getJCoordinate()];
        int way = 0;

        //Check up
        if (currentCell.getICoordinate() != 0
                && solvingMaze[currentCell.getICoordinate() - 1][currentCell.getJCoordinate()] > 0
                && solvingMaze[currentCell.getICoordinate() - 1][currentCell.getJCoordinate()] < lowest) {
            lowest = solvingMaze[currentCell.getICoordinate() - 1][currentCell.getJCoordinate()];
            way = 1;
        }
        //Check right
        if (currentCell.getJCoordinate() != solvingMaze[0].length - 1
                && solvingMaze[currentCell.getICoordinate()][currentCell.getJCoordinate() + 1] > 0
                && solvingMaze[currentCell.getICoordinate()][currentCell.getJCoordinate() + 1] < lowest) {
            lowest = solvingMaze[currentCell.getICoordinate()][currentCell.getJCoordinate() + 1];
            way = 2;
        }
        //Check down
        if (currentCell.getICoordinate() != solvingMaze[0].length - 1
                && solvingMaze[currentCell.getICoordinate() + 1][currentCell.getJCoordinate()] > 0
                && solvingMaze[currentCell.getICoordinate() + 1][currentCell.getJCoordinate()] < lowest) {
            lowest = solvingMaze[currentCell.getICoordinate() + 1][currentCell.getJCoordinate()];
            way = 3;
        }
        //Check left
        if (currentCell.getJCoordinate() != 0
                && solvingMaze[currentCell.getICoordinate()][currentCell.getJCoordinate() - 1] > 0
                && solvingMaze[currentCell.getICoordinate()][currentCell.getJCoordinate() - 1] < lowest) {
            lowest = solvingMaze[currentCell.getICoordinate()][currentCell.getJCoordinate() - 1];
            way = 4;
        }

        return way;
    }

    /**
     * Fills an array with distances from each cell to the start until we reach the goal cell
     * @param goalPosition cell to reach
     * @param startingPosition cell to start from
     * @param solvingMaze maze to fill
     */
    private static void goToGoalPosition(Cell goalPosition, Cell startingPosition, int[][] solvingMaze) {
        LinkedList<Cell> queueOfLastCells = new LinkedList<>();

        //We start from first cell - starting position
        solvingMaze[startingPosition.getICoordinate()][startingPosition.getJCoordinate()] = 1;
        queueOfLastCells.add(new Cell(startingPosition));

        boolean reachedGoal = false;
        //Then we go through all the elements in a queue until we reach the goal position
        while (!reachedGoal) {

            //We save current last cells so that we won't delete them from this list in the loop
            LinkedList<Cell> savedLastCells = new LinkedList<>(queueOfLastCells);

            //We are going through all the current cells
            for (Cell cell: savedLastCells) {

                //We check if we got to the goal
                if (cell.equals(goalPosition)) {
                    reachedGoal = true;
                    break;
                }

                //Go top if we can
                if (cell.getICoordinate() != 0 && solvingMaze[cell.getICoordinate() - 1][cell.getJCoordinate()] == 0) {
                    queueOfLastCells.add(new Cell(cell.getICoordinate() - 1, cell.getJCoordinate()));
                    solvingMaze[cell.getICoordinate() - 1][cell.getJCoordinate()] = solvingMaze[cell.getICoordinate()][cell.getJCoordinate()] + 1;
                }
                //Go right if we can
                if (cell.getJCoordinate() != solvingMaze[0].length - 1 && solvingMaze[cell.getICoordinate()][cell.getJCoordinate() + 1] == 0) {
                    queueOfLastCells.add(new Cell(cell.getICoordinate(), cell.getJCoordinate() + 1));
                    solvingMaze[cell.getICoordinate()][cell.getJCoordinate() + 1] = solvingMaze[cell.getICoordinate()][cell.getJCoordinate()] + 1;
                }
                //Go down if we can
                if (cell.getICoordinate() != solvingMaze.length - 1 && solvingMaze[cell.getICoordinate() + 1][cell.getJCoordinate()] == 0) {
                    queueOfLastCells.add(new Cell(cell.getICoordinate() + 1, cell.getJCoordinate()));
                    solvingMaze[cell.getICoordinate() + 1][cell.getJCoordinate()] = solvingMaze[cell.getICoordinate()][cell.getJCoordinate()] + 1;
                }
                //Go left if we can
                if (cell.getJCoordinate() != 0 && solvingMaze[cell.getICoordinate()][cell.getJCoordinate() - 1] == 0) {
                    queueOfLastCells.add(new Cell(cell.getICoordinate(), cell.getJCoordinate() - 1));
                    solvingMaze[cell.getICoordinate()][cell.getJCoordinate() - 1] = solvingMaze[cell.getICoordinate()][cell.getJCoordinate()] + 1;
                }

                //At the end we delete this cell from the queue
                queueOfLastCells.remove(cell);
            }
        }
    }

    /**
     * Fills the integer array depending on the boolean array, where 0 is blank space and -1 is a wall
     * @param solvingMaze integer array to fill
     * @param mazeOld boolean array to depend on
     */
    private static void fillNewMaze(int[][] solvingMaze, boolean[][] mazeOld) {
        // -1 is a wall, 0 is a blank thing (at first)
        for (int i = 0; i < solvingMaze.length; i++) {
            for (int j = 0; j < solvingMaze[0].length; j++) {
                if (!mazeOld[i][j]) {
                    solvingMaze[i][j] = 0;
                }
                else {
                    solvingMaze[i][j] = -1;
                }
            }
        }
    }

}
