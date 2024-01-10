package pt.org.upskill.dto;

public class VaccineTypeDTO {
    public String code;
    public String shortDescription;
    public VaccineTechDTO vaccineTechDTO;

    public VaccineTypeDTO() {
        this.vaccineTechDTO = new VaccineTechDTO();
    }
}
