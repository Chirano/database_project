package pt.org.upskill.domain;

public class Vaccine {
    private int id;
    private String name;

    public Vaccine(String name) {
        //this.id = ;
        this.name = name;
    }

    public String name() {
        return name;
    }
}
