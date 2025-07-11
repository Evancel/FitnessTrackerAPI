package fitnesstracker.controller;

import fitnesstracker.model.dto.FitnessActivityRequest;
import fitnesstracker.model.dto.FitnessActivityResponse;
import fitnesstracker.service.FitnessActivityService;
import fitnesstracker.utility.AuthUtils;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing fitness activity records submitted by applications.
 *
 * Each request must be authenticated with a valid API key associated with a registered application.
 * The controller supports activity registration and paginated retrieval of stored activity records.
 */
@RestController
@RequestMapping("/api/tracker")
public class FitnessActivityController {
    private final FitnessActivityService activityService;

    public FitnessActivityController(FitnessActivityService activityService) {
        this.activityService = activityService;
    }

    /**
     * POST /api/tracker
     * Submits a new fitness activity to be stored.
     *
     * - Requires a valid API key from an authenticated application.
     * - Only authenticated and rate-limited apps (based on category) may store data.
     * - Returns 201 Created on success.
     *
     * @param authentication Spring Security authentication object containing API key credentials
     * @param request the fitness activity data submitted by the application
     * @return 201 Created if successfully registered
     */
    @PostMapping
    public ResponseEntity<Void> postFitnessActivity(Authentication authentication,
                                                    @Valid @RequestBody FitnessActivityRequest request) {
        String apiKey = AuthUtils.extractApiKey(authentication);
        activityService.register(apiKey, request);
        return ResponseEntity.status(201).build();
    }

    /**
     * GET /api/tracker
     * Retrieves all fitness activities submitted by the authenticated application.
     *
     * - Supports pagination via 'page' and 'size' request parameters.
     * - Results are sorted by creation date in descending order.
     * - Accessible only to authenticated applications.
     *
     * @param authentication Spring Security authentication object containing API key credentials
     * @param page page number (0-based index)
     * @param size number of records per page
     * @return paginated list of fitness activity responses
     */
    @GetMapping
    public ResponseEntity<Page<FitnessActivityResponse>> getAll(
            Authentication authentication,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) int size) {
        String apiKey = AuthUtils.extractApiKey(authentication);
        return ResponseEntity.ok(activityService.getAll(apiKey, page, size));
    }
}
