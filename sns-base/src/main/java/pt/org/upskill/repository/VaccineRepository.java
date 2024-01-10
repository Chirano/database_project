package pt.org.upskill.repository;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.domain.Brand;
import pt.org.upskill.domain.Vaccine;
import pt.org.upskill.domain.VaccineType;

import java.util.ArrayList;
import java.util.List;

public class VaccineRepository implements Persistable {

    public VaccineRepository() {}

    private List<Vaccine> vaccineList = new ArrayList<Vaccine>();
/*
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
*/
    public Vaccine createVaccine(String name, VaccineType vaccineType, Brand brand) {
        return new Vaccine(name, vaccineType, brand);
    }

    @Override
    public boolean save(Object object) {
        /*
        if (validateSave(object)) {
            vaccineTechList.add((VaccineTech) object);
            return true;
        }

         */
        return false;
    }

    @Override
    public boolean delete(Object object) {
        /*
        if (validateDelete(object)) {
            vaccineTechList.remove(object);
            return true;
        }
        */
        return false;

    }
/*
    public List<VaccineTech> vaccineTechList() {
        return vaccineTechList;
    }

 */
}