package pt.org.upskill.domain;

import pt.org.upskill.dto.VaccineTechDTO;
import pt.org.upskill.dto.VaccineTypeDTO;
import pt.org.upskill.dto.DTOable;

public class VaccineType implements DTOable {
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

    @Override
    public VaccineTypeDTO toDTO() {
        VaccineTypeDTO dto = new VaccineTypeDTO.Builder()
                .withCode(code())
                .withShortDescription(shortDescription())
                .withVaccineTechDTO(new VaccineTechDTO.Builder()
                        .withId(vaccineTech().id())
                        .withName(vaccineTech().name())
                        .withDescription(vaccineTech().description())
                        .build())
                .build();
        return dto;
    }

}
