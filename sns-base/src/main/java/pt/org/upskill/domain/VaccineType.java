package pt.org.upskill.domain;

import pt.org.upskill.dto.VaccineTechDTO;
import pt.org.upskill.dto.VaccineTypeDTO;
import pt.org.upskill.dto.DTOable;

public class VaccineType implements DTOable<VaccineTypeDTO> {
    private String code;
    private String shortDescription;
    private VaccineTech vaccineTech;
    public VaccineType(String code){
        this.code = code;
    }

    public VaccineType(String code, String shortDescription, VaccineTech vaccineTech) {
        this.code = code;
        this.shortDescription = shortDescription;
        this.vaccineTech = vaccineTech;
    }

    public VaccineType(Builder builder){
        this.code = builder.code;
        this.shortDescription = builder.shortDescription;
        this.vaccineTech = builder.vaccineTech;

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
                //.withVaccineTechDTO(vaccineTech().toDTO())
                .build();
        return dto;
    }

    public static class Builder {

        private String code;
        private String shortDescription;
        private VaccineTech vaccineTech;

        public VaccineType.Builder withCode(final String code) {
            this.code = code;
            return this;
        }
        public VaccineType.Builder withShortDescription(final String shortDescription) {
            this.shortDescription = shortDescription;
            return this;
        }
        public VaccineType.Builder withVaccineTech(final VaccineTech vaccineTech) {
            this.vaccineTech = vaccineTech;
            return this;
        }

        public VaccineType build() {
            return new VaccineType(this);
        }
    }

}
