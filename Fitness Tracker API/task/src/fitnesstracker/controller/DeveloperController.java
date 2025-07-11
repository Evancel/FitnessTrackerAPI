package fitnesstracker.controller;

import fitnesstracker.model.dto.DeveloperRequest;
import fitnesstracker.model.dto.DeveloperResponse;
import fitnesstracker.model.entity.Developer;
import fitnesstracker.service.DeveloperService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * REST controller for developer account management.
 *
 * Handles developer registration and secure profile access using Spring Security.
 */
@RestController
@RequestMapping("/api/developers")
public class DeveloperController {

    private final DeveloperService developerService;

    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    /**
     * POST /api/developers/signup
     * Registers a new developer account.
     *
     * - Validates the incoming request using Jakarta Bean Validation.
     * - Persists a new developer with encoded password.
     * - Returns 201 Created with the URI of the newly registered resource.
     *
     * @param request the developer registration data
     * @return 201 Created with Location header pointing to the new developer resource
     */
    @PostMapping("/signup")
    public ResponseEntity<Void> register(@Valid @RequestBody DeveloperRequest request) {
        Developer savedDeveloper = developerService.register(request);

        // Create a URI for the newly registered developer
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedDeveloper.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * GET /api/developers/{id}
     * Returns the profile of the authenticated developer.
     *
     * - Access is allowed only if the requested ID matches the authenticated user's ID.
     * - Returns developer details along with associated applications.
     *
     * @param details authenticated Spring Security user
     * @param id      path variable representing developer ID
     * @return Developer profile data or 403 if access is denied
     */
    @GetMapping("/{id}")
    public ResponseEntity<DeveloperResponse> getById(@AuthenticationPrincipal UserDetails details,
                                                     @PathVariable Long id) {
        return ResponseEntity.ok(developerService.getOwnProfileById(details.getUsername(), id));
    }
}
