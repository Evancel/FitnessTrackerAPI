package fitnesstracker.model.mapper;

import fitnesstracker.model.dto.ApplicationResponse;
import fitnesstracker.model.entity.Application;

public class ApplicationMapper {
    public static ApplicationResponse toResponse(Application application) {
        ApplicationResponse response = new ApplicationResponse();
        response.setName(application.getName());
        response.setApikey(application.getApikey());
        response.setCategory(application.getCategory().toString());
        return response;
    }
}