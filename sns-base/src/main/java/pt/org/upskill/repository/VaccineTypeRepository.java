package pt.org.upskill.repository;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.domain.VaccineType;
import pt.org.upskill.dto.DTO;
import pt.org.upskill.dto.VaccineTypeDTO;

import java.util.ArrayList;
import java.util.List;

public class VaccineTypeRepository implements Persistable {

    private List<VaccineType> vaccineTypeList = new ArrayList<VaccineType>();

    public VaccineType createVaccineType(DTO dto) {
        VaccineTypeDTO vaccineTypeDTO = (VaccineTypeDTO) dto;
        VaccineTechRepository vaccineTechRepository = Repositories.getInstance().vaccineTechRepository();
        return new VaccineType(vaccineTypeDTO.code, vaccineTypeDTO.shortDescription, vaccineTechRepository.getById(vaccineTypeDTO.vaccineTechId));
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

    public VaccineType getByCode(String code) {
        for (VaccineType vaccineType : vaccineTypeList) {
            if (vaccineType.code().equals(code)) {
                return vaccineType;
            }
        }
        return null;
    }
}
