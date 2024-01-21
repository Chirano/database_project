package pt.org.upskill.dto;

import java.time.LocalTime;

public class FacilityDTO implements DTO {
    public Integer id;
    public String name;
    public AddressDTO addressDTO;
    public PhoneDTO phoneDTO;
    public EmailDTO emailDTO;
    public PhoneDTO faxDTO;
    public WebsiteDTO websiteDTO;
    public LocalTime openingHour;
    public LocalTime closingHour;
    public int maxVaccinesPerHour;
}
