package fitnesstracker.utility;

public final class ErrorMessages {
    private ErrorMessages() {
        // prevent instantiation
    }

    public static final String APPLICATION_ALREADY_EXISTS = "Application with the name '%s' already exists.";
    public static final String DEVELOPER_NOT_FOUND = "Developer with email '%s' not found.";
    public static final String UNAUTHORIZED_ACCESS = "User is not authenticated.";
    public static final String INVALID_CATEGORY = "Invalid application category: '%s'.";
}
