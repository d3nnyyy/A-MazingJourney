package ua.amazingjourney.backend.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import ua.amazingjourney.backend.model.Maze;
import ua.amazingjourney.backend.model.MazeInitializer;
import ua.amazingjourney.backend.tools.Cell;
import ua.amazingjourney.backend.tools.MazeGenerator;
import ua.amazingjourney.backend.tools.MazeSolver;

import java.util.LinkedList;

@Service
@Getter
public class MazeService {

    private Maze maze;

    public MazeService() {
        this.maze = new Maze();
    }

    public boolean[][] generateMaze(MazeInitializer mazeInitializer) {
        maze.setGrid(MazeGenerator.generateMaze(mazeInitializer));
        maze.setSize(mazeInitializer.getSize());
        return MazeGenerator.generateMaze(mazeInitializer);
    }

    public LinkedList<LinkedList<Integer>> getShortestPath() {

        LinkedList<Cell> solvedLinkedListOfCells = MazeSolver.solveMazeBFS(maze, new Cell(0, 0), new Cell(maze.getSize() - 1, maze.getSize() - 1));
        LinkedList<LinkedList<Integer>> solvedLinkedListOfLinkedLists = new LinkedList<>();

        for (Cell cell : solvedLinkedListOfCells) {
            LinkedList<Integer> solvedLinkedList = new LinkedList<>();
            solvedLinkedList.add(cell.getICoordinate());
            solvedLinkedList.add(cell.getJCoordinate());
            solvedLinkedListOfLinkedLists.add(solvedLinkedList);
        }
        return solvedLinkedListOfLinkedLists;
    }
}
