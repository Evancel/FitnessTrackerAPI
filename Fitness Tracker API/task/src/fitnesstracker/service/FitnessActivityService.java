package fitnesstracker.service;

import fitnesstracker.model.dto.FitnessActivityRequest;
import fitnesstracker.model.dto.FitnessActivityResponse;
import org.springframework.data.domain.Page;

public interface FitnessActivityService {
    void register(String apiKey, FitnessActivityRequest request);

    Page<FitnessActivityResponse> getAll(String apiKey, int page, int size);
}
