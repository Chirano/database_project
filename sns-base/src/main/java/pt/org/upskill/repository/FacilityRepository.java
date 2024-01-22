package pt.org.upskill.repository;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.auth.Email;
import pt.org.upskill.domain.*;
import pt.org.upskill.dto.*;

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

    private Boolean validateSave(Facility facility) {
        return true;
    }

    private Boolean validateDelete(Facility facility) {
        return true;
    }

    public Facility createFacility(DTO dto) throws Exception {
        FacilityDTO facilityDTO = (FacilityDTO) dto;
        return new Facility.Builder()
                .withName(facilityDTO.name())
                .withAddress(new Address(
                        facilityDTO.addressDTO().streetName(),
                        facilityDTO.addressDTO().postalCode(),
                        facilityDTO.addressDTO().cityName()))
                .withPhone(new Phone(facilityDTO.phoneDTO().phoneNumber()))
                .withEmail(new Email(facilityDTO.emailDTO().address()))
                .withFax(new Phone(facilityDTO.faxDTO().phoneNumber()))
                .withWebsite(new Website(facilityDTO.websiteDTO().address()))
                .withOpeningHour(facilityDTO.openingHour())
                .withClosingingHour(facilityDTO.closingHour())
                .withMaxVaccinesPerHour(facilityDTO.maxVaccinesPerHour())
                .build();
    }

    @Override
    public boolean save(Object object) {
        Facility facility = (Facility) object;
        if (validateSave(facility)) {
            facilityList.add(facility);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Object object) {
        Facility facility = (Facility) object;
        if (validateDelete(facility)) {
            facilityList.remove(facility);
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
            dtoList.add(new KeyValueDTO(dto.id().toString(), dto.name()));
        }
        return dtoList;
    }
}