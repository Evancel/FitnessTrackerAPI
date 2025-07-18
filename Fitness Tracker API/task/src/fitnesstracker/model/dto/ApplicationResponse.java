package fitnesstracker.model.dto;

public class ApplicationResponse {
    private Long id;
    private String name;
    private String apikey;
    public String category;

    public ApplicationResponse() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApikey() {
        return this.apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
