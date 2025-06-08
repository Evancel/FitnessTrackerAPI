package fitnesstracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@JsonPropertyOrder({
        "id",
        "username",
        "activity",
        "duration",
        "calories",
        "application",
})
public class FitnessActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;
    private String username;
    private String activity;
    private int duration;
    private int calories;
    private LocalDateTime creationDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;


    public FitnessActivity(){
        this.creationDate = LocalDateTime.now();
    }

    public FitnessActivity(String username,
                           String activity,
                           int duration,
                           int calories,
                           Application application) {
        this.username = username;
        this.activity = activity;
        this.duration = duration;
        this.calories = calories;
        this.creationDate = LocalDateTime.now();
        this.application = application;
    }

    @JsonProperty("application")
    public String getApplicationName() {
        return application != null ? application.getName() : null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}
