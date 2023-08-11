package ua.amazingjourney.backend.tools;

public class Main {
    public static void main(String[] args) {

        MazeGenerator mazeGenerator = new MazeGenerator(9,9);

        var maze = mazeGenerator.generateMaze(0);

        System.out.println(MazeConverter.convertToMazeString(maze));

    }
}
