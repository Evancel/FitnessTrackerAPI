package fitnesstracker.model.mapper;

import fitnesstracker.model.dto.FitnessActivityRequest;
import fitnesstracker.model.dto.FitnessActivityResponse;
import fitnesstracker.model.entity.FitnessActivity;

import java.util.ArrayList;
import java.util.List;

public class FitnessActivityMapper {
    private FitnessActivityMapper() {
    }

    public static FitnessActivity toFitnessActivity(FitnessActivityRequest request) {
        FitnessActivity activity = new FitnessActivity();
        activity.setUsername(request.getUsername());
        activity.setActivity(request.getActivity());
        activity.setDuration(request.getDuration());
        activity.setCalories(request.getCalories());
        return activity;
    }

    public static FitnessActivityResponse toResponse(FitnessActivity activity) {
        FitnessActivityResponse response = new FitnessActivityResponse();
        response.setId(activity.getId());
        response.setUsername(activity.getUsername());
        response.setActivity(activity.getActivity());
        response.setDuration(activity.getDuration());
        response.setCalories(activity.getCalories());
        response.setCreationDate(activity.getCreationDate());
        return response;
    }

    public static List<FitnessActivityResponse> toResponses(List<FitnessActivity> activities) {
        List<FitnessActivityResponse> responses = new ArrayList<>();
        for (FitnessActivity activity : activities) {
            responses.add(toResponse(activity));
        }
        return responses;
    }
}
