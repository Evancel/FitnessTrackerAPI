package fitnesstracker.service;

import fitnesstracker.entity.Developer;
import fitnesstracker.repository.DeveloperRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeveloperService {

    private final DeveloperRepository developerRepository;

    public DeveloperService(DeveloperRepository developerRepository){
        this.developerRepository = developerRepository;
    }

    public Developer save(Developer developer){
        developerRepository.save(developer);
        return developer;
    }

    public Optional<Developer> findById(Long id){
        return developerRepository.findById(id);
    }

    public Optional<Developer> findByEmail(String email){
        return developerRepository.findByEmail(email);
    }
}
