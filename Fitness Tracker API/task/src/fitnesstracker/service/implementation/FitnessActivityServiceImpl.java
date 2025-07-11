package fitnesstracker.service.implementation;

import fitnesstracker.exception.TooManyRequestsException;
import fitnesstracker.exception.UnauthenticatedAccessException;
import fitnesstracker.model.AppCategory;
import fitnesstracker.model.dto.FitnessActivityRequest;
import fitnesstracker.model.dto.FitnessActivityResponse;
import fitnesstracker.model.entity.Application;
import fitnesstracker.model.entity.FitnessActivity;
import fitnesstracker.model.mapper.FitnessActivityMapper;
import fitnesstracker.repository.FitnessActivityRepository;
import fitnesstracker.service.ApplicationService;
import fitnesstracker.service.FitnessActivityService;
import fitnesstracker.service.TokenBucketService;
import fitnesstracker.utility.ErrorMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class FitnessActivityServiceImpl implements FitnessActivityService {

    private static final Logger log = LoggerFactory.getLogger(FitnessActivityServiceImpl.class);
    private final ApplicationService applicationService;
    private final TokenBucketService tokenService;
    private final FitnessActivityRepository activityRepository;

    public FitnessActivityServiceImpl(ApplicationService applicationService,
                                      TokenBucketService tokenService,
                                      FitnessActivityRepository activityRepository) {
        this.applicationService = applicationService;
        this.tokenService = tokenService;
        this.activityRepository = activityRepository;
    }

    @Override
    public void register(String apiKey, FitnessActivityRequest request) {
        Application application = authenticateAndRateLimit(apiKey);


        log.info("Registering fitness activity '{}' for application '{}'.",
                request.getActivity(), application.getName());
        FitnessActivity activity = FitnessActivityMapper.toFitnessActivity(request);
        activity.setApplication(application);
        activityRepository.save(activity);
    }

    @Override
    public Page<FitnessActivityResponse> getAll(String apiKey, int page, int size) {
        Application application = authenticateAndRateLimit(apiKey);

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "creationDate"));
        Page<FitnessActivity> activities = activityRepository.findByApplication(application, pageable);
        return activities.map(FitnessActivityMapper::toResponse);
    }

    private Application authenticateAndRateLimit(String apiKey) {
        Application application = applicationService.findByApikey(apiKey)
                .orElseThrow(() -> new UnauthenticatedAccessException(ErrorMessages.UNAUTHORIZED_ACCESS));
        if (application.getCategory().equals(AppCategory.BASIC) &&
                !tokenService.consumeToken(application.getId())) {
            log.error("Too many requests from the BASIC application '{}'", application.getName());
            throw new TooManyRequestsException(ErrorMessages.TOO_MANY_REQUESTS);
        }
        return application;
    }
}
