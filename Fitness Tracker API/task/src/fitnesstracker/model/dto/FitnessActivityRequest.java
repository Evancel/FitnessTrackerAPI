package fitnesstracker.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FitnessActivityRequest {
    @NotBlank(message = "Username can not be blank")
    private String username;
    @NotBlank(message = "Activity can not be blank")
    private String activity;
    @NotNull
    private int duration;
    @NotNull
    private int calories;

    public FitnessActivityRequest() {
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
}
