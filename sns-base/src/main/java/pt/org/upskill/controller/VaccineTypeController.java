package pt.org.upskill.controller;

import pt.org.upskill.domain.VaccineTech;
import pt.org.upskill.domain.VaccineType;

import pt.org.upskill.repository.Repositories;
import pt.org.upskill.repository.VaccineTechRepository;
import pt.org.upskill.repository.VaccineTypeRepository;

import java.util.List;

public class VaccineTypeController {
    VaccineTypeRepository vaccineTypeRepository = Repositories.getInstance().vaccineTypeRepository();
    private VaccineType vaccineType;

    public void createVaccineType(String code, String shortDescription, int vaccineTechId) {
        VaccineTechRepository vaccineTechRepository = Repositories.getInstance().vaccineTechRepository();
        VaccineTech vaccineTech = vaccineTechRepository.getById(vaccineTechId);

        this.vaccineType = vaccineTypeRepository.createVaccineType(code, shortDescription, vaccineTech);
    }

    public List<VaccineType> vaccineTypeList() {
        return vaccineTypeRepository.vaccineTypeList();
    }

    public boolean confirm() {
        vaccineTypeRepository.save(vaccineType);
        return true;
    }
}
