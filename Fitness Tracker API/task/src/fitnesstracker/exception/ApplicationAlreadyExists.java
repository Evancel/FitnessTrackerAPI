package fitnesstracker.exception;

public class ApplicationAlreadyExists extends RuntimeException {
    public ApplicationAlreadyExists(String message) {
        super(message);
    }
}
