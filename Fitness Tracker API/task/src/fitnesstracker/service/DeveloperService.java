package fitnesstracker.service;

import fitnesstracker.model.dto.DeveloperRequest;
import fitnesstracker.model.dto.DeveloperResponse;
import fitnesstracker.model.entity.Developer;

import java.util.Optional;

public interface DeveloperService {
    Developer register(DeveloperRequest request);

    DeveloperResponse getOwnProfileById(String username, Long id);

    Optional<Developer> findByEmail(String email);
}
