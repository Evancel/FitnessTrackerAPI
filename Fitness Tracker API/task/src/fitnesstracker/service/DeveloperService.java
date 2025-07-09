package fitnesstracker.service;

import fitnesstracker.model.entity.Developer;

import java.util.Optional;

public interface DeveloperService {
    Developer register(Developer developer);
    Optional<Developer> findById(Long id);
    Optional<Developer> findByEmail(String email);
}
