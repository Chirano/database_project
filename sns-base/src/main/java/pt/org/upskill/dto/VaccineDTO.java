package pt.org.upskill.dto;

public class VaccineDTO {
    public String name;
    public VaccineTypeDTO vaccineTypeDTO;

    public VaccineDTO() {
        this.vaccineTypeDTO = new VaccineTypeDTO();
    }
}
