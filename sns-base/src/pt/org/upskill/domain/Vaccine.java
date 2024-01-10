package pt.org.upskill.domain;

public class Vaccine {
    private int id;
    private String name;
    private VaccineType vaccineType;
    private Brand brand;

    public Vaccine(String name, VaccineType vaccineType, Brand brand) {
        //this.id = ;
        this.name = name;
        this.vaccineType = vaccineType;
        this.brand = brand;
    }

    public String name() {
        return name;
    }
}
