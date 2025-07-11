package fitnesstracker.utility;

public final class ErrorMessages {
    private ErrorMessages() {
        // prevent instantiation
    }

    public static final String APPLICATION_ALREADY_EXISTS = "Application with the name '%s' already exists.";
    public static final String DEVELOPER_NOT_FOUND = "Developer with email '%s' not found.";
    public static final String EMAIL_ALREADY_EXISTS = "Email '%s' already exists.";
    public static final String EMAIL_NOT_FOUND = "Email '%s' not found.";
    public static final String UNAUTHORIZED_ACCESS = "User is not authenticated.";
    public static final String FORBIDDEN_ACCESS = "Forbidden: You do not have permission to access this resource.";
    public static final String TOO_MANY_REQUESTS = "Forbidden: too many requests.";
    public static final String INVALID_CATEGORY = "Invalid application category: '%s'.";
}
