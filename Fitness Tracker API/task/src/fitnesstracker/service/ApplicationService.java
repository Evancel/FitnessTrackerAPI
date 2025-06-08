package fitnesstracker.service;

import fitnesstracker.entity.Application;
import fitnesstracker.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    public ApplicationService(ApplicationRepository applicationRepository){
        this.applicationRepository = applicationRepository;
    }

    public boolean exists(String name){
        return  applicationRepository.existsByName(name);
    }

    public Optional<Application> findByApikey(String apikey){
        return applicationRepository.findByApikey(apikey);
    }

    public Application save(Application application){
        application.setApikey(application.getId(), application.getName());
        applicationRepository.save(application);
        return application;
    }
}
