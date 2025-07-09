package fitnesstracker.controller;

import fitnesstracker.model.dto.ApplicationRequest;
import fitnesstracker.model.dto.ApplicationResponse;
import fitnesstracker.exception.UnauthenticatedAccessException;
import fitnesstracker.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing user applications within the fitness tracker.
 */
@RestController
@RequestMapping("/api/applications")
public class ApplicationsController {
    private final ApplicationService applicationService;

    public ApplicationsController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    /**
     * Registers a new application under the authenticated user's account.
     *
     * @param details authenticated user details
     * @param request the application data to register
     * @return response containing application info or error status
     */
    @PostMapping("/register")
    public ResponseEntity<ApplicationResponse> register(@AuthenticationPrincipal UserDetails details,
                                                        @Valid @RequestBody ApplicationRequest request) {
        if (details == null) {
            throw new UnauthenticatedAccessException("User is not authenticated");
        }

        ApplicationResponse response = applicationService.register(details.getUsername(), request);

        return ResponseEntity.status(201).body(response);
    }
}
