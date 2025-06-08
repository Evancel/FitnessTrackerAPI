package fitnesstracker;

import fitnesstracker.entity.Application;

import java.util.List;

public class DeveloperProfileDto {
    private long id;
    private String email;
    List<Application> applications;

    public DeveloperProfileDto(){}

    public DeveloperProfileDto(long id, String email, List<Application> applicationList){
        this.id = id;
        this.email = email;
        this.applications = applicationList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }
}
