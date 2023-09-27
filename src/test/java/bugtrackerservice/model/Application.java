package bugtrackerservice.model;

public class Application {
    private String description;
    private Integer id;
    private String name;
    private String owner;

    public Application() {
    }

    public Application(String description, Integer id, String name, String owner) {
        setDescription(description);
        setId(id);
        setName(name);
        setOwner(owner);
    }

    public Application(String description, String name, String owner) {
        setDescription(description);
        setName(name);
        setOwner(owner);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "{"
                + "\"description\" : \"" + description
                + "\", \"name\" : \"" + name
                + "\", \"owner\" : \"" + owner
                + "\"}";
    }
}
