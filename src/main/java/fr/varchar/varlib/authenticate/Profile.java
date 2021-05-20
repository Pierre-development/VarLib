package fr.varchar.varlib.authenticate;

public class Profile {
    private String name;
    private String id;

    public Profile(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
