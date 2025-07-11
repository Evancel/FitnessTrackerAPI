package fitnesstracker.model.dto;

import fitnesstracker.model.entity.Application;

import java.util.List;

public class DeveloperResponse {
    private long id;
    private String email;
    List<Application> applications;

    public DeveloperResponse() {
    }

    public DeveloperResponse(long id, String email, List<Application> applicationList) {
        this.id = id;
        this.email = email;
        this.applications = applicationList;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Application> getApplications() {
        return this.applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }
}
