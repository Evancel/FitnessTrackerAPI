package fitnesstracker.model.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public class ApplicationRequest {
    @NotBlank
    @Column(unique = true)
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String category;

    public ApplicationRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
