package fitnesstracker.utility;

import fitnesstracker.exception.UnauthenticatedAccessException;
import org.springframework.security.core.Authentication;

public class AuthUtils {
    private AuthUtils() {
    }

    public static String extractApiKey(Authentication auth) {
        if (auth == null || auth.getCredentials() == null) {
            throw new UnauthenticatedAccessException(ErrorMessages.UNAUTHORIZED_ACCESS);
        }
        return auth.getCredentials().toString();
    }
}
