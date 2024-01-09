package pt.org.upskill.controller;

import pt.org.upskill.domain.VaccineTech;
import pt.org.upskill.repository.Repositories;
import pt.org.upskill.repository.VaccineTechRepository;

import java.util.List;

public class VaccineTechController {
    VaccineTechRepository vaccineTechRepository = Repositories.getInstance().vaccineTechRepository();

    private VaccineTech vaccineTech;

    public void createVaccineTech(String name, String description) {
        vaccineTech = vaccineTechRepository.createVaccineTech(name, description);
    }

    public List<VaccineTech> vaccineTechList() {
        return vaccineTechRepository.vaccineTechList();
    }

    public String getVaccineTechName(int id) {
        return vaccineTechRepository.getById(id).name();
    }

    public boolean confirm() {
        vaccineTechRepository.save(vaccineTech);
        return true;
    }
}
