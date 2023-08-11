package ua.amazingjourney.backend.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import ua.amazingjourney.backend.model.Maze;
import ua.amazingjourney.backend.model.MazeInitializer;
import ua.amazingjourney.backend.tools.MazeGenerator;

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

}
