package ua.amazingjourney.backend.service;

import org.springframework.stereotype.Service;
import ua.amazingjourney.backend.model.MazeInitializer;
import ua.amazingjourney.backend.tools.MazeGenerator;

@Service
public class MazeService {

    public boolean[][] generateMaze(MazeInitializer mazeInitializer) {
        return MazeGenerator.generateMaze(mazeInitializer);
    }

}
