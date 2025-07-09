package fitnesstracker.service;

import fitnesstracker.model.entity.FitnessActivity;

public interface FitnessActivityService {
    void register(FitnessActivity activity);
    Iterable<FitnessActivity> getAll();
}
