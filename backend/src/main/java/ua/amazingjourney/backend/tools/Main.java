package ua.amazingjourney.backend.tools;

public class Main {
    public static void main(String[] args) {

        MazeGenerator mazeGenerator = new MazeGenerator(41,41);

        var maze = mazeGenerator.generateMaze(5);

        System.out.println(MazeConverter.convertToMazeString(maze));

    }
}
