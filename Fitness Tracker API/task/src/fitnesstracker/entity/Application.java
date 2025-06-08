package fitnesstracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.keygen.KeyGenerators;

import java.math.BigInteger;

@Entity
@JsonPropertyOrder({
        "id",
        "name",
        "description",
        "apikey",
        "category",
})
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;
    @NotBlank
    @Column(unique = true)
    private String name;
    @NotNull
    private String description;
    private String apikey;
    @NotNull
    @Enumerated(EnumType.STRING)
    private AppCategory category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "developer_id")
    @JsonIgnore
    private Developer developer;

    public Application() {
    }

    public Application(String name) {
        this.name = name;
    }

    public Application(String name, String description, AppCategory category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getApikey() {
        return apikey;
    }

    public void setApikey(long id, String name) {
        if(this.apikey != null){
            return;
        }
        this.apikey = createApikey(id, name);
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public AppCategory getCategory() {
        return category;
    }

    public void setCategory(AppCategory category) {
        this.category = category;
    }

    private String createApikey(long id, String name) {
        byte[] array = KeyGenerators.secureRandom(10).generateKey();
        return new BigInteger(1, array).toString(16);
    }
}
