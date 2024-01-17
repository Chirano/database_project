package pt.org.upskill.controller;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.dto.KeyValueDTO;
import pt.org.upskill.repository.FacilityRepository;
import pt.org.upskill.repository.Repositories;
import pt.org.upskill.repository.VaccineRepository;

import java.util.List;

public class FacilityController {
    FacilityRepository facilityRepository = Repositories.getInstance().facilityRepository();

    public List<KeyValueDTO> keyValueDTOList() {
        return facilityRepository.keyValueDTOList();
    }
}
