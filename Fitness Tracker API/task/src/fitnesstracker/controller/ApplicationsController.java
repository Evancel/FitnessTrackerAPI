package fitnesstracker.controller;

import fitnesstracker.entity.AppCategory;
import fitnesstracker.entity.Application;
import fitnesstracker.entity.Developer;
import fitnesstracker.entity.dto.ApplicationRequest;
import fitnesstracker.entity.dto.ApplicationResponse;
import fitnesstracker.service.ApplicationService;
import fitnesstracker.service.DeveloperService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/applications")
public class ApplicationsController {
    private final ApplicationService applicationService;
    private final DeveloperService developerService;

    public ApplicationsController(ApplicationService applicationService,
                                  DeveloperService developerService) {
        this.applicationService = applicationService;
        this.developerService = developerService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@AuthenticationPrincipal UserDetails details,
                                      @Valid @RequestBody ApplicationRequest request) {
        if (request == null || applicationService.exists(request.getName())) {
            return ResponseEntity.badRequest().build();
        }

        if (details == null) {
            return ResponseEntity.status(401).build();
        }

        Optional<Developer> optDeveloper = developerService.findByEmail(details.getUsername());
        if (optDeveloper.isEmpty()) {
            return ResponseEntity.status(401).build();
        }

        Application application = new Application();
        application.setName(request.getName());
        application.setDescription(request.getDescription());
        application.setCategory(AppCategory.convertToEnum(request.getCategory()));
        application.setDeveloper(optDeveloper.get());

        Application savedApplication = applicationService.save(application);
        ApplicationResponse response = new ApplicationResponse();
        response.setName(savedApplication.getName());
        response.setApikey(savedApplication.getApikey());
        response.setCategory(savedApplication.getCategory().toString());
        return ResponseEntity.status(201).body(response);
    }

}
