package fitnesstracker.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@JsonPropertyOrder({
        "id",
        "email",
})
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;
    @NotBlank
    @Email(regexp = ".+@.+\\..+", message = "Email must be valid and contain a domain")
    @Column(unique = true)
    private String email;
    @NotBlank
    private String password;
    private String authority;
    @OneToMany(mappedBy = "developer")
    private Collection<Application> applications;

    public Developer() {
        this.authority = "ROLE_ADMIN";
        applications = new ArrayList<>();
    }

    public Developer(String email, String password) {
        this.email = email;
        this.password = password;
        this.authority = "ROLE_ADMIN";
        applications = new ArrayList<>();
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

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public void setApplications(Collection<Application> applicationCollection) {
        applications.addAll(applicationCollection);
    }

    public Collection<Application> getApplications() {
        return this.applications;
    }
}