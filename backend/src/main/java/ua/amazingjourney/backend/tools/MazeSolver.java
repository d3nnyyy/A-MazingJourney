package ua.amazingjourney.backend.tools;

import ua.amazingjourney.backend.model.Maze;

import java.util.LinkedList;

public class MazeSolver {

    /**
     * Solves maze using BFS algorithm
     * @param maze maze to solve
     * @param goalPosition position to get to
     * @param startingPosition position to start from
     * @return the list of cells. Starting from beginning to goal position
     */
    public static LinkedList<Cell> solveMazeBFS(Maze maze, Cell goalPosition, Cell startingPosition) {

        int width = maze.getSize() * 2 - 1;
        int height = maze.getSize() * 2 - 1;

        //Maze in progress of solving. It is integer, so that we can mark distance from each point to beginning
        //And later we can go back to the start
        int[][] solvingMaze = new int[height][width];

        //boolean[][] mazeOld = new boolean[maze.getSize()][maze.getSize()];

        fillNewMaze(solvingMaze, maze.getGrid());
        goToGoalPosition(goalPosition, startingPosition, solvingMaze);

        //Now we make the list that is the way to the goal
        LinkedList<Cell> pathToTheGoal = findPathToTheGoal(goalPosition, startingPosition, solvingMaze);

        return pathToTheGoal;
    }

    private static LinkedList<Cell> findPathToTheGoal(Cell goalPosition, Cell startingPosition, int[][] solvingMaze) {
        LinkedList<Cell> pathToTheGoal = new LinkedList<>();
        pathToTheGoal.addFirst(new Cell(goalPosition));
        while (!pathToTheGoal.getFirst().equals(startingPosition)) {
            Cell currentCell = pathToTheGoal.getFirst();
            if (currentCell.equals(startingPosition)) {
                break;
            }

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

    private static int chooseWayToGo(int[][] solvingMaze, Cell currentCell) {
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

    private static void goToGoalPosition(Cell goalPosition, Cell startingPosition, int[][] solvingMaze) {
        LinkedList<Cell> queueOfLastCells = new LinkedList<>();

        //We start from first cell - starting position
        solvingMaze[startingPosition.getICoordinate()][startingPosition.getJCoordinate()] = 1;
        queueOfLastCells.add(new Cell(startingPosition));

        boolean reachedGoal = false;
        //Then we go through all the elements in a queue until we reach the goal position
        while (!reachedGoal) {

            LinkedList<Cell> savedLastCells = new LinkedList<>(queueOfLastCells);

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
