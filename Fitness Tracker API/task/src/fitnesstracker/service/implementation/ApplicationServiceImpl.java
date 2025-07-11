package fitnesstracker.service.implementation;

import fitnesstracker.exception.ApplicationAlreadyExists;
import fitnesstracker.exception.DeveloperNotFoundException;
import fitnesstracker.model.dto.ApplicationRequest;
import fitnesstracker.model.dto.ApplicationResponse;
import fitnesstracker.model.entity.Application;
import fitnesstracker.model.entity.Developer;
import fitnesstracker.model.mapper.ApplicationMapper;
import fitnesstracker.repository.ApplicationRepository;
import fitnesstracker.service.ApplicationService;
import fitnesstracker.service.DeveloperService;
import fitnesstracker.service.TokenBucketService;
import fitnesstracker.utility.ErrorMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    private static final Logger log = LoggerFactory.getLogger(ApplicationServiceImpl.class);

    private final ApplicationRepository applicationRepository;
    private final DeveloperService developerService;
    private final TokenBucketService tokenService;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository,
                                  DeveloperService developerService,
                                  TokenBucketService tokenService) {
        this.applicationRepository = applicationRepository;
        this.developerService = developerService;
        this.tokenService = tokenService;
    }

    @Override
    public boolean existsByName(String name) {
        return applicationRepository.existsByNameIgnoreCase(name);
    }

    @Override
    public Optional<Application> findByApikey(String apikey) {
        return applicationRepository.findByApikey(apikey);
    }

    @Override
    public ApplicationResponse register(String email, ApplicationRequest request) {
        log.info("Registering application '{}' for developer '{}'", request.getName(), email);
        if (existsByName(request.getName())) {
            log.warn("Application with name '{}' already exists", request.getName());
            throw new ApplicationAlreadyExists(ErrorMessages.APPLICATION_ALREADY_EXISTS.formatted(request.getName()));
        }

        Developer developer = developerService.findByEmail(email)
                .orElseThrow(() -> {
                    log.error("Developer not found for email '{}'", email);
                    return new DeveloperNotFoundException(ErrorMessages.DEVELOPER_NOT_FOUND.formatted(email));
                });

        Application application = Application.createFromRequest(request, developer); // a factory method in Application
        application = applicationRepository.save(application);
        tokenService.registerApp(application.getId());
        log.info("Application '{}' successfully saved with API key '{}'",
                application.getName(), application.getApikey());

        return ApplicationMapper.toResponse(application);
    }
}
