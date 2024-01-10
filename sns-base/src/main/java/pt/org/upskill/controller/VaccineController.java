package pt.org.upskill.controller;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.domain.Brand;
import pt.org.upskill.domain.Vaccine;
import pt.org.upskill.domain.VaccineType;
import pt.org.upskill.dto.VaccineDTO;
import pt.org.upskill.repository.BrandRepository;
import pt.org.upskill.repository.Repositories;
import pt.org.upskill.repository.VaccineRepository;
import pt.org.upskill.repository.VaccineTypeRepository;

import java.util.ArrayList;
import java.util.List;

public class VaccineController {
    VaccineRepository vaccineRepository = Repositories.getInstance().vaccineRepository();
    VaccineTypeRepository vaccineTypeRepository = Repositories.getInstance().vaccineTypeRepository();
    BrandRepository brandRepository = Repositories.getInstance().brandRepository();

    private Vaccine vaccine;

    public void createVaccine(String vaccineTypeCode, String brandName, String vaccineName) {
        VaccineType vaccineType = vaccineTypeRepository.getByCode(vaccineTypeCode);
        Brand band = brandRepository.getByName(brandName);
        vaccine = vaccineRepository.createVaccine(vaccineName, vaccineType, band);
    }

    public List<VaccineDTO> vaccineList() {
        List<Vaccine> vaccineList = vaccineRepository.vaccineList();
        List<VaccineDTO> vaccineDTOList = new ArrayList<>();
        VaccineDTO vaccineDTO = new VaccineDTO();
        for (Vaccine vaccine : vaccineList) {
            vaccineDTO.name = vaccine.name();
            vaccineDTO.vaccineTypeDTO.code = vaccine.vaccineType().code();
            vaccineDTO.vaccineTypeDTO.shortDescription = vaccine.vaccineType().shortDescription();
            vaccineDTO.vaccineTypeDTO.vaccineTechDTO.id = vaccine.vaccineType().vaccineTech().id();
            vaccineDTO.vaccineTypeDTO.vaccineTechDTO.name = vaccine.vaccineType().vaccineTech().name();
            vaccineDTO.vaccineTypeDTO.vaccineTechDTO.description = vaccine.vaccineType().vaccineTech().description();
            vaccineDTOList.add(vaccineDTO);
        }
        return vaccineDTOList;
    }
/*
    public String getVaccineTechName(int id) {
        return vaccineTechRepository.getById(id).name();
    }
*/
    public boolean confirm() {
        vaccineRepository.save(vaccine);
        return true;
    }
}
