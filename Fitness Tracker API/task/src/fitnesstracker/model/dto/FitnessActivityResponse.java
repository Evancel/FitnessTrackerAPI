package fitnesstracker.model.dto;

import java.time.LocalDateTime;

public class FitnessActivityResponse {
    private Long id;
    private String username;
    private String activity;
    private int duration;
    private int calories;
    private LocalDateTime creationDate;

    public FitnessActivityResponse() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getActivity() {
        return this.activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCalories() {
        return this.calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
