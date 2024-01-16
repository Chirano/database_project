package pt.org.upskill.dto;

import pt.org.upskill.domain.*;
import pt.org.upskill.repository.Repositories;

import java.util.ArrayList;
import java.util.List;

public class DTOlists {

    public List<KeyValueDTO<Integer>> facilityDTOList() {
        List<Facility> list = Repositories.getInstance().facilityRepository().facilityList();
        List<KeyValueDTO<Integer>> dtoList = new ArrayList<>();
        for (Facility item : list) {
            FacilityDTO dto = item.toDTO();
            dtoList.add(new KeyValueDTO<>(dto.id, dto.name));
        }
        return dtoList;
    }

    public List<KeyValueDTO<String>> brandDTOList() {
        List<Brand> list = Repositories.getInstance().brandRepository().brandList();
        List<KeyValueDTO<String>> dtoList = new ArrayList<>();
        for (Brand item : list) {
            BrandDTO dto = item.toDTO();
            dtoList.add(new KeyValueDTO<>(dto.name, dto.name));
        }
        return dtoList;
    }

    public List<KeyValueDTO<Integer>> vaccineTechDTOList() {
        List<VaccineTech> list = Repositories.getInstance().vaccineTechRepository().vaccineTechList();
        List<KeyValueDTO<Integer>> dtoList = new ArrayList<>();
        for (VaccineTech item : list) {
            VaccineTechDTO dto = item.toDTO();
            dtoList.add(new KeyValueDTO<>(dto.id, dto.name));
        }
        return dtoList;
    }

    public List<KeyValueDTO<String>> vaccineTypeDTOList() {
        List<VaccineType> list = Repositories.getInstance().vaccineTypeRepository().vaccineTypeList();
        List<KeyValueDTO<String>> dtoList = new ArrayList<>();
        for (VaccineType item : list) {
            VaccineTypeDTO dto = item.toDTO();
            dtoList.add(new KeyValueDTO<>(dto.code, dto.shortDescription + " - Technology: " + dto.vaccineTechId));
        }
        return dtoList;
    }

    public List<KeyValueDTO<Integer>> vaccineDTOList() {
        List<Vaccine> list = Repositories.getInstance().vaccineRepository().vaccineList();
        List<KeyValueDTO<Integer>> dtoList = new ArrayList<>();
        for (Vaccine item : list) {
            VaccineDTO dto = item.toDTO();
            dtoList.add(new KeyValueDTO<>(dto.id, dto.name + " -  Type: " + dto.vaccineTypeCode));
        }
        return dtoList;
    }
}
