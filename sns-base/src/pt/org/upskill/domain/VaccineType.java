package pt.org.upskill.domain;

public class VaccineType {
    private String code;
    private String shortDescription;
    private VaccineTech vaccineTech;

    public VaccineType(String code, String shortDescription, VaccineTech vaccineTech) {
        this.code = code;
        this.shortDescription = shortDescription;
        this.vaccineTech = vaccineTech;
    }

    public String code() {
        return code;
    }

    public String shortDescription() {
        return shortDescription;
    }
    public VaccineTech vaccineTech() {
        return vaccineTech;
    }
}
