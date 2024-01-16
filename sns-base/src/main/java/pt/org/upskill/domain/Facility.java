package pt.org.upskill.domain;

import pt.org.upskill.auth.Email;
import pt.org.upskill.dto.BrandDTO;
import pt.org.upskill.dto.FacilityDTO;
import pt.org.upskill.dto.DTOable;
import pt.org.upskill.repository.FacilityRepository;

import java.time.LocalTime;

public class Facility implements DTOable {
    private int id;
    private String name;
    private Address address;
    private Phone phone;
    private Email email;
    private Phone fax;
    private Website website;
    private LocalTime openingHour;
    private LocalTime closingHour;
    private int maxVaccinesPerHour;

    public Facility(String name, Address address, Phone phone, Email email, Phone fax, Website website, LocalTime openingHour, LocalTime closingHour, int maxVaccinesPerHour) {
        FacilityRepository facilityRepository = new FacilityRepository();
        this.id = facilityRepository.nextId();
        //
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.fax = fax;
        this.website = website;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
        this.maxVaccinesPerHour = maxVaccinesPerHour;
    }

    public int id() {
        return this.id;
    }

    public String name() {
        return this.name;
    }

    @Override
    public FacilityDTO toDTO() {
        FacilityDTO dto = new FacilityDTO();
        dto.id = id();
        dto.name = name();
        return dto;
    }

}
