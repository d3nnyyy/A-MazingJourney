package ua.amazingjourney.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedList;

@Data
@AllArgsConstructor
public class MazeResponse {

    private boolean[][] maze;
    private LinkedList<LinkedList<Integer>> shortestPath;

}
