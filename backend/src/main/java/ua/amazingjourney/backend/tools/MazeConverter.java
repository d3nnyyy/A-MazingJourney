package ua.amazingjourney.backend.tools;

import java.util.Arrays;

public class MazeConverter {

    public static void main(String[] args) {

    }

    public static String convertToMazeString(boolean[][] mazeArray) {
        StringBuilder mazeString = new StringBuilder();

        for (int i = 0; i < mazeArray.length; i++) {
            for (int j = 0; j < mazeArray[i].length; j++) {
                if (!mazeArray[i][j]) {
                    mazeString.append(" "); // Open path
                } else {
                    mazeString.append("#"); // Wall
                }
            }
            mazeString.append("\n");
        }

        return mazeString.toString();
    }
}

