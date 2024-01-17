package pt.org.upskill.domain;

import pt.org.upskill.dto.BrandDTO;
import pt.org.upskill.dto.DTOable;
import pt.org.upskill.dto.VaccineTechDTO;
import pt.org.upskill.dto.VaccineTypeDTO;
import pt.org.upskill.repository.Repositories;
import pt.org.upskill.repository.VaccineTechRepository;

public class VaccineTech implements DTOable {
    private Integer id;
    private String name;
    private String description;

    public VaccineTech(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int id() {
        return id;
    }
    public String name() {
        return name;
    }
    public String description() { return description; }

    @Override
    public VaccineTechDTO toDTO() {
        VaccineTechDTO dto = new VaccineTechDTO();
        dto.id = id();
        dto.name = name();
        dto.description = description();
        return dto;
    }

}