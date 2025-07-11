package fitnesstracker.model.dto;

import fitnesstracker.utility.StrongPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class DeveloperRequest {
    @NotBlank
    @Email(regexp = ".+@.+\\..+", message = "Email must be valid and contain a domain")
    private String email;
    @StrongPassword
    private String password;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
