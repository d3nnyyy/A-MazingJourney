package ua.amazingjourney.backend.exception;

/**
 * Exception that is thrown when the maze cannot be generated.
 */
public class MazeGenerationException extends RuntimeException{
    public MazeGenerationException(String message, Exception e) {
        super(message, e);
    }

    public MazeGenerationException(String message) {
        super(message);
    }
}
