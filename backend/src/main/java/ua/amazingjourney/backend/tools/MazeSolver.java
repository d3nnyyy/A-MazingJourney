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
        int[][] solvingMaze = new int[maze.getSize()][maze.getSize()];

        boolean[][] mazeOld = new boolean[maze.getSize()][maze.getSize()];

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
                if (cell == goalPosition) {
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


        //Now we make the list that is the way to the goal

        LinkedList<Cell> pathToTheGoal = new LinkedList<>();
        pathToTheGoal.addFirst(new Cell(goalPosition));
        while (!pathToTheGoal.getFirst().equals(startingPosition)) {
            Cell currentCell = pathToTheGoal.getFirst();
            if (currentCell.equals(startingPosition)) {
                break;
            }
            //Go left
            if (currentCell.getJCoordinate() != 0
                    && solvingMaze[currentCell.getICoordinate()][currentCell.getJCoordinate() - 1] > 0
                    && solvingMaze[currentCell.getICoordinate()][currentCell.getJCoordinate() - 1] < solvingMaze[currentCell.getICoordinate()][currentCell.getJCoordinate()]) {
                pathToTheGoal.addFirst(new Cell(currentCell.getICoordinate(), currentCell.getJCoordinate() - 1));
            }
            //Go down
            else if (currentCell.getICoordinate() != solvingMaze[0].length - 1
                    && solvingMaze[currentCell.getICoordinate() + 1][currentCell.getJCoordinate()] > 0
                    && solvingMaze[currentCell.getICoordinate() + 1][currentCell.getJCoordinate()] < solvingMaze[currentCell.getICoordinate()][currentCell.getJCoordinate()]) {
                pathToTheGoal.addFirst(new Cell(currentCell.getICoordinate() + 1, currentCell.getJCoordinate()));
            }
            //Go right
            else if (currentCell.getJCoordinate() != solvingMaze[0].length - 1
                    && solvingMaze[currentCell.getICoordinate()][currentCell.getJCoordinate() + 1] > 0
                    && solvingMaze[currentCell.getICoordinate()][currentCell.getJCoordinate() + 1] < solvingMaze[currentCell.getICoordinate()][currentCell.getJCoordinate()]) {
                pathToTheGoal.addFirst(new Cell(currentCell.getICoordinate(), currentCell.getJCoordinate() + 1));
            }
            //Go up
            else if (currentCell.getICoordinate() != 0
                    && solvingMaze[currentCell.getICoordinate() - 1][currentCell.getJCoordinate()] > 0
                    && solvingMaze[currentCell.getICoordinate() - 1][currentCell.getJCoordinate()] < solvingMaze[currentCell.getICoordinate()][currentCell.getJCoordinate()]) {
                pathToTheGoal.addFirst(new Cell(currentCell.getICoordinate() - 1, currentCell.getJCoordinate()));
            }
        }


        return pathToTheGoal;
    }

}
