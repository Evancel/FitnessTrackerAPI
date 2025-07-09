package fitnesstracker.service.implementation;

import fitnesstracker.model.entity.FitnessActivity;
import fitnesstracker.repository.FitnessActivityRepository;
import fitnesstracker.service.FitnessActivityService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class FitnessActivityServiceImpl implements FitnessActivityService {
    private final FitnessActivityRepository activityRepository;

    public FitnessActivityServiceImpl(FitnessActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public void register(FitnessActivity activity) {
        if (activity != null) {
            activityRepository.save(activity);
        }
    }

    @Override
    public Iterable<FitnessActivity> getAll() {
        return activityRepository.findAll(Sort.by(Sort.Direction.DESC, "creationDate"));
    }
}
