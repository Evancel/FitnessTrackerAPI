package fitnesstracker.service.implementation;

import fitnesstracker.model.entity.Developer;
import fitnesstracker.repository.DeveloperRepository;
import fitnesstracker.service.DeveloperService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeveloperServiceImpl implements DeveloperService {

    private final DeveloperRepository developerRepository;

    public DeveloperServiceImpl(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @Override
    public Developer register(Developer developer) {
        developerRepository.save(developer);
        return developer;
    }

    @Override
    public Optional<Developer> findById(Long id) {
        return developerRepository.findById(id);
    }

    @Override
    public Optional<Developer> findByEmail(String email) {
        return developerRepository.findByEmail(email);
    }
}
