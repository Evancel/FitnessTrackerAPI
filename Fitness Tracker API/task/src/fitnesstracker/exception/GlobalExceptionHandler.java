package fitnesstracker.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(DeveloperNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleDeveloperNotFoundException(DeveloperNotFoundException exception) {
        log.warn("Developer not found {}", exception.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Developer not found");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ApplicationAlreadyExists.class)
    public ResponseEntity<Map<String, String>> handleApplicationAlreadyExistsException(ApplicationAlreadyExists exception) {
        log.warn("Application already exists {}", exception.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Application already exists");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(UnauthenticatedAccessException.class)
    public ResponseEntity<Void> handleUnauthenticated(UnauthenticatedAccessException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}

