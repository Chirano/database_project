package pt.org.upskill.controller;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.domain.Brand;
import pt.org.upskill.domain.Vaccine;
import pt.org.upskill.domain.VaccineType;
import pt.org.upskill.repository.BrandRepository;
import pt.org.upskill.repository.Repositories;
import pt.org.upskill.repository.VaccineRepository;
import pt.org.upskill.repository.VaccineTypeRepository;

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
/*
    public List<VaccineTech> vaccineTechList() {
        return vaccineTechRepository.vaccineTechList();
    }

    public String getVaccineTechName(int id) {
        return vaccineTechRepository.getById(id).name();
    }
*/
    public boolean confirm() {
        vaccineRepository.save(vaccine);
        return true;
    }


}
