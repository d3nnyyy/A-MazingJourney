package ua.amazingjourney.backend.exception;

public class StatsCalculationException extends RuntimeException {
    public StatsCalculationException(String message, Exception e) {
        super(message, e);
    }
}
