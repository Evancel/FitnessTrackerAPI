package fitnesstracker.controller;

import fitnesstracker.model.dto.ApplicationRequest;
import fitnesstracker.model.dto.ApplicationResponse;
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
 * REST controller for managing applications registered by developers.
 *
 * Handles application registration under authenticated developer accounts.
 * Enforces validation and delegates business logic to the ApplicationService.
 */
@RestController
@RequestMapping("/api/applications")
public class ApplicationController {
    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    /**
     * POST /api/applications/register
     * Registers a new application under the authenticated developer.
     *
     * - Requires valid Spring Security authentication (UserDetails injected).
     * - Validates application request payload.
     * - Returns generated API key and registered app info upon success.
     *
     * @param details authenticated user details (injected by Spring Security)
     * @param request the application registration data
     * @return 201 Created with ApplicationResponse (API key, name, etc.)
     */
    @PostMapping("/register")
    public ResponseEntity<ApplicationResponse> register(@AuthenticationPrincipal UserDetails details,
                                                        @Valid @RequestBody ApplicationRequest request) {
        return ResponseEntity
                .status(201)
                .body(applicationService.register(details.getUsername(), request));
    }
}
