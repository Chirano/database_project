package pt.org.upskill.domain;

public class Role {

    private String name;

    public Role(String name) {
        this.name = name;
    }

    protected Role() {
    }

    public String name() {
        return this.name;
    }
}
