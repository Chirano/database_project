package pt.org.upskill.controller;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.domain.VaccineTech;
import pt.org.upskill.domain.VaccineType;

import pt.org.upskill.dto.DTO;
import pt.org.upskill.dto.DTOlists;
import pt.org.upskill.dto.KeyValueDTO;
import pt.org.upskill.dto.VaccineTypeDTO;
import pt.org.upskill.repository.Repositories;
import pt.org.upskill.repository.VaccineTechRepository;
import pt.org.upskill.repository.VaccineTypeRepository;

import java.util.List;

public class VaccineTypeController implements UIable {
    VaccineTypeRepository vaccineTypeRepository = Repositories.getInstance().vaccineTypeRepository();
    private VaccineType vaccineType;

    public void register(DTO dto) {
        vaccineType = vaccineTypeRepository.createVaccineType(dto);
    }

    public boolean confirm() {
        return vaccineTypeRepository.save(vaccineType);
    }

    public List<KeyValueDTO<String>> dtoList() {
        return new DTOlists().vaccineTypeDTOList();
    }
}
