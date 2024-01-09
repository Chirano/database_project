package pt.org.upskill.repository;

import pt.org.upskill.domain.VaccineTech;

import java.util.ArrayList;
import java.util.List;

public class VaccineTechRepository implements Persistable {

    public VaccineTechRepository() {}

    private List<VaccineTech> vaccineTechList = new ArrayList<VaccineTech>();

    public int nextId() {
        int maxId = 0;
        for (VaccineTech vaccineTech : vaccineTechList) {
            if (vaccineTech.id() > maxId) {
                maxId = vaccineTech.id();
            };
        }
        return maxId+1;
    }

    public VaccineTech getById(int id) {
        for (VaccineTech vaccineTech : vaccineTechList) {
            if (vaccineTech.id() == id) {
                return vaccineTech;
            };
        }
        return null;
    }

    private Boolean validateSave(Object object) {
        return true;
    }

    private Boolean validateDelete(Object object) {
        return true;
    }

    public VaccineTech createVaccineTech(String name, String description) {
        return new VaccineTech(name, description);
    }

    @Override
    public boolean save(Object object) {
        if (validateSave(object)) {
            vaccineTechList.add((VaccineTech) object);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Object object) {
        if (validateDelete(object)) {
            vaccineTechList.remove(object);
            return true;
        }return false;
    }

    public List<VaccineTech> vaccineTechList() {
        return vaccineTechList;
    }
}