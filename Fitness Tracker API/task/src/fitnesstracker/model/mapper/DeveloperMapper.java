package fitnesstracker.model.mapper;

import fitnesstracker.model.dto.DeveloperRequest;
import fitnesstracker.model.dto.DeveloperResponse;
import fitnesstracker.model.entity.Application;
import fitnesstracker.model.entity.Developer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeveloperMapper {
    private DeveloperMapper() {
    }

    public static Developer toDeveloper(DeveloperRequest request) {
        Developer developer = new Developer();
        developer.setEmail(request.getEmail());
        return developer;
    }

    public static DeveloperResponse toResponse(Developer developer) {
        List<Application> applicationList = new ArrayList<>(developer.getApplications());
        Collections.reverse(applicationList);

        DeveloperResponse response = new DeveloperResponse();
        response.setId(developer.getId());
        response.setEmail(developer.getEmail());
        response.setApplications(applicationList);
        return response;
    }
}
