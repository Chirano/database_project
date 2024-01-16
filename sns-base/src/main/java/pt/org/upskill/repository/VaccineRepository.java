package pt.org.upskill.repository;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.domain.Brand;
import pt.org.upskill.domain.Vaccine;
import pt.org.upskill.domain.VaccineType;
import pt.org.upskill.dto.DTO;
import pt.org.upskill.dto.VaccineDTO;

import java.util.ArrayList;
import java.util.List;

public class VaccineRepository implements Persistable {

    public VaccineRepository() {}

    private List<Vaccine> vaccineList = new ArrayList<Vaccine>();

    public int nextId() {
        int maxId = 0;
        for (Vaccine vaccine : vaccineList) {
            if (vaccine.id() > maxId) {
                maxId = vaccine.id();
            };
        }
        return maxId+1;
    }

    public Vaccine getById(int id) {
        for (Vaccine vaccine : vaccineList) {
            if (vaccine.id() == id) {
                return vaccine;
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

    public Vaccine createVaccine(DTO dto) {
        VaccineDTO vaccineDTO = (VaccineDTO) dto;
        VaccineTypeRepository vaccineTypeRepository = Repositories.getInstance().vaccineTypeRepository();
        BrandRepository brandRepository = Repositories.getInstance().brandRepository();
        return new Vaccine.Builder()
                .name(vaccineDTO.name)
                .vaccineType(vaccineTypeRepository.getByCode(vaccineDTO.vaccineTypeCode))
                .brand(brandRepository.getByName(vaccineDTO.brandName))
                .build();
    }

    @Override
    public boolean save(Object object) {
        if (validateSave(object)) {
            vaccineList.add((Vaccine) object);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Object object) {
        if (validateDelete(object)) {
            vaccineList.remove(object);
            return true;
        }
        return false;
    }

    public List<Vaccine> vaccineList() {
        return vaccineList;
    }

}