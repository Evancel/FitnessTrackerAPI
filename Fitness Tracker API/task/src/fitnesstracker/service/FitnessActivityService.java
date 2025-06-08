package fitnesstracker.service;

import fitnesstracker.entity.FitnessActivity;
import fitnesstracker.repository.FitnessActivityRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class FitnessActivityService {
    private final FitnessActivityRepository activityRepository;

    public FitnessActivityService(FitnessActivityRepository activityRepository){
        this.activityRepository = activityRepository;
    }

    public void save(FitnessActivity activity){
        if(activity != null){
            activityRepository.save(activity);
        }
    }

    public Iterable<FitnessActivity> getAll(){
        return activityRepository.findAll(Sort.by(Sort.Direction.DESC, "creationDate"));
    }
}
