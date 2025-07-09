package fitnesstracker.exception;

public class UnauthenticatedAccessException extends RuntimeException {
    public UnauthenticatedAccessException(String message) {
        super(message);
    }
}
