package fitnesstracker.service;

import fitnesstracker.model.dto.ApplicationRequest;
import fitnesstracker.model.dto.ApplicationResponse;
import fitnesstracker.model.entity.Application;

import java.util.Optional;

public interface ApplicationService {
    boolean existsByName(String name);

    Optional<Application> findByApikey(String apikey);

    ApplicationResponse register(String email, ApplicationRequest request);
}
