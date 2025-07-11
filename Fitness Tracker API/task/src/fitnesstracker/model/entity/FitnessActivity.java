package fitnesstracker.model.entity;

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
    private Long id;
    private String username;
    private String activity;
    private int duration;
    private int calories;
    private LocalDateTime creationDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;


    public FitnessActivity() {
        this.creationDate = LocalDateTime.now();
    }


    @JsonProperty("application")
    public String getApplicationName() {
        return application != null ? application.getName() : null;
    }

    public Long getId() {
        return this.id;
    }

    public String getUsername() {
        return username;
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
        return this.creationDate;
    }

    public Application getApplication() {
        return this.application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}
