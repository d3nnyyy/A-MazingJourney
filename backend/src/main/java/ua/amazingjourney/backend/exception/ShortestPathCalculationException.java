package ua.amazingjourney.backend.exception;

/**
 * Exception that is thrown when the shortest path in the maze cannot be calculated.
 */
public class ShortestPathCalculationException extends RuntimeException {
    public ShortestPathCalculationException(String message, Exception e) {
        super(message, e);
    }

    public ShortestPathCalculationException(String message) {
        super(message);
    }
}
