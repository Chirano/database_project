package pt.org.upskill.repository;

import pt.org.upskill.domain.VaccineTech;
import pt.org.upskill.domain.VaccineType;

import java.util.ArrayList;
import java.util.List;

public class VaccineTypeRepository implements Persistable {

    private List<VaccineType> vaccineTypeList = new ArrayList<VaccineType>();

    public VaccineType createVaccineType(String code, String shortDescription, VaccineTech vaccineTech) {
        return new VaccineType(code, shortDescription, vaccineTech);
    }

    @Override
    public boolean save(Object object) {
        vaccineTypeList.add((VaccineType) object);
        return true;
    }

    @Override
    public boolean delete(Object object) {
        vaccineTypeList.remove(object);
        return true;
    }

    public List<VaccineType> vaccineTypeList() {
        return vaccineTypeList;
    }
}
