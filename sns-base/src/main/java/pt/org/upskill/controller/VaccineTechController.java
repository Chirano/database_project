package pt.org.upskill.controller;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.domain.VaccineTech;
import pt.org.upskill.dto.*;
import pt.org.upskill.repository.Repositories;
import pt.org.upskill.repository.VaccineTechRepository;

import java.util.List;

public class VaccineTechController implements UIable{
    VaccineTechRepository vaccineTechRepository = Repositories.getInstance().vaccineTechRepository();

    private VaccineTech vaccineTech;

    public void register(DTO dto) {
        vaccineTech = vaccineTechRepository.createVaccineTech(dto);
    }

    public boolean save() {
        return vaccineTechRepository.save(vaccineTech);
    }

    public List<KeyValueDTO> keyValueDTOList() { return vaccineTechRepository.keyValueDTOList(); }
}
