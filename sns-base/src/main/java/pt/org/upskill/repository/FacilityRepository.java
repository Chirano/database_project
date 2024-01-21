package pt.org.upskill.repository;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.auth.Email;
import pt.org.upskill.domain.*;
import pt.org.upskill.dto.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FacilityRepository implements Persistable {

    public FacilityRepository() {}

    private List<Facility> facilityList = new ArrayList<Facility>();

    public int nextId() {
        int maxId = 0;
        for (Facility facility : facilityList) {
            if (facility.id() > maxId) {
                maxId = facility.id();
            };
        }
        return maxId+1;
    }

    public Facility getById(int id) {
        for (Facility facility : facilityList) {
            if (facility.id() == id) {
                return facility;
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

    public Facility createFacility(DTO dto) throws Exception {
        FacilityDTO facilityDTO = (FacilityDTO) dto;
        return new Facility(facilityDTO.name,
                new Address(facilityDTO.addressDTO.streetName, facilityDTO.addressDTO.postalCode, facilityDTO.addressDTO.cityName),
                new Phone(facilityDTO.phoneDTO.phoneNumber),
                new Email(facilityDTO.emailDTO.address),
                new Phone(facilityDTO.faxDTO.phoneNumber),
                new Website(facilityDTO.websiteDTO.address),
                facilityDTO.openingHour, facilityDTO.closingHour, facilityDTO.maxVaccinesPerHour);
    }

    @Override
    public boolean save(Object object) {
        if (validateSave(object)) {
            facilityList.add((Facility) object);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Object object) {
        if (validateDelete(object)) {
            facilityList.remove(object);
            return true;
        }
        return false;
    }

    public List<Facility> facilityList() {
        return facilityList;
    }

    public List<KeyValueDTO> keyValueDTOList() {
        List<KeyValueDTO> dtoList = new ArrayList<>();
        for (Facility item : facilityList()) {
            FacilityDTO dto = item.toDTO();
            dtoList.add(new KeyValueDTO(dto.id.toString(), dto.name));
        }
        return dtoList;
    }
}