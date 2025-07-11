package fitnesstracker.service.implementation;

import fitnesstracker.exception.EmailAlreadyExists;
import fitnesstracker.exception.EmailNotFoundException;
import fitnesstracker.model.dto.DeveloperRequest;
import fitnesstracker.model.dto.DeveloperResponse;
import fitnesstracker.model.entity.Developer;
import fitnesstracker.model.mapper.DeveloperMapper;
import fitnesstracker.repository.DeveloperRepository;
import fitnesstracker.service.DeveloperService;
import fitnesstracker.utility.ErrorMessages;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeveloperServiceImpl implements DeveloperService {


    private final PasswordEncoder passwordEncoder;
    private final DeveloperRepository developerRepository;

    public DeveloperServiceImpl(PasswordEncoder passwordEncoder, DeveloperRepository developerRepository) {
        this.passwordEncoder = passwordEncoder;
        this.developerRepository = developerRepository;
    }

    @Override
    public Developer register(DeveloperRequest request) {
        if (findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExists(ErrorMessages.EMAIL_ALREADY_EXISTS.formatted(request.getEmail()));
        }

        Developer developer = DeveloperMapper.toDeveloper(request);
        developer.setPassword(passwordEncoder.encode(request.getPassword()));
        developer = developerRepository.save(developer);
        return developer;
    }

    @Override
    public DeveloperResponse getOwnProfileById(String username, Long id) {
        Developer developer = findByEmail(username)
                .orElseThrow(() -> new EmailNotFoundException(ErrorMessages.EMAIL_NOT_FOUND.formatted(username)));

        if (!id.equals(developer.getId())) {
            throw new AccessDeniedException(ErrorMessages.FORBIDDEN_ACCESS);
        }

        return DeveloperMapper.toResponse(developer);
    }

    @Override
    public Optional<Developer> findByEmail(String email) {
        return developerRepository.findByEmail(email);
    }
}
