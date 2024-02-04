package pt.org.upskill.domain;

import pt.org.upskill.auth.Email;
import pt.org.upskill.dto.*;

import java.time.LocalTime;

public class Facility implements DTOable<FacilityDTO> {
    private Integer id;
    private String name;
    private Address address;
    private Phone phone;
    private Email email;
    private Phone fax;
    private Website website;
    private LocalTime openingHour;
    private LocalTime closingHour;
    private int maxVaccinesPerHour;

    public Integer id() {
        return this.id;
    }
    public String name() {
        return this.name;
    }
    public Address address() {
        return this.address;
    }
    public Phone phone() {
        return this.phone;
    }
    public Email email() {
        return this.email;
    }
    public Phone fax() {
        return this.fax;
    }
    public Website website() {
        return this.website;
    }
    public LocalTime openingHour() {
        return this.openingHour;
    }
    public LocalTime closingHour() {
        return this.closingHour;
    }
    public Integer maxVaccinesPerHour() {
        return this.maxVaccinesPerHour;
    }

    public Facility(final Facility.Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.address = builder.address;
        this.phone = builder.phone;
        this.email = builder.email;
        this.fax = builder.fax;
        this.website = builder.website;
        this.openingHour = builder.openingHour;
        this.closingHour = builder.closingHour;
        this.maxVaccinesPerHour = builder.maxVaccinesPerHour;
    }

    public Facility(Integer id, String name){
        this.id = id;
        this.name = name;
    }
    public Facility(Integer id){
        this.id = id;
    }

    public static class Builder {
        private Integer id;
        private String name;
        private Address address;
        private Phone phone;
        private Email email;
        private Phone fax;
        private Website website;
        private LocalTime openingHour;
        private LocalTime closingHour;
        private int maxVaccinesPerHour;

        public Facility.Builder withId(final Integer id) {
            this.id = id;
            return this;
        }
        public Facility.Builder withName(final String name) {
            this.name = name;
            return this;
        }
        public Facility.Builder withAddress(final Address address) {
            this.address = address;
            return this;
        }
        public Facility.Builder withPhone(final Phone phone) {
            this.phone = phone;
            return this;
        }
        public Facility.Builder withEmail(final Email email) {
            this.email = email;
            return this;
        }
        public Facility.Builder withFax(final Phone fax) {
            this.fax = fax;
            return this;
        }
        public Facility.Builder withWebsite(final Website website) {
            this.website = website;
            return this;
        }
        public Facility.Builder withOpeningHour(final LocalTime openingHour) {
            this.openingHour = openingHour;
            return this;
        }
        public Facility.Builder withClosingingHour(final LocalTime closingHour) {
            this.closingHour = closingHour;
            return this;
        }
        public Facility.Builder withMaxVaccinesPerHour(final Integer maxVaccinesPerHour) {
            this.maxVaccinesPerHour = maxVaccinesPerHour;
            return this;
        }

        public Facility build() {
            return new Facility(this);
        }
    }

    @Override
    public FacilityDTO toDTO() {
        FacilityDTO.Builder builder = new FacilityDTO.Builder();
        if (id() != null) {
            builder.withId(id());
        }
        if (name() != null) {
            builder.withName(name());
        }
        if (address() != null) {
            builder.withAddressDTO((AddressDTO) address().toDTO());
        }
        if (phone() != null) {
            builder.withPhoneDTO((PhoneDTO) phone().toDTO());
        }
        if (email() != null) {
            builder.withEmailDTO((EmailDTO) email().toDTO());
        }
        if (fax() != null) {
            builder.withFaxDTO((PhoneDTO) fax().toDTO());
        }
        if (website() != null) {
            builder.withWebsiteDTO((WebsiteDTO) website().toDTO());
        }
        if (openingHour() != null) {
            builder.withOpeningHour(openingHour());
        }
        if (closingHour() != null) {
            builder.withClosingingHour(closingHour());
        }
        if (maxVaccinesPerHour() != null) {
            builder.withMaxVaccinesPerHour(maxVaccinesPerHour());
        }
        return builder.build();
    }
}
