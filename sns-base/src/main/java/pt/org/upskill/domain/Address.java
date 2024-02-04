package pt.org.upskill.domain;

import pt.org.upskill.dto.AddressDTO;
import pt.org.upskill.dto.DTOable;

public class Address implements DTOable<AddressDTO> {
    private String streetName;
    private String postalCode;
    private String cityName;

    public String streetName() {
        return this.streetName;
    }
    public String postalCode() {
        return this.postalCode;
    }
    public String cityName() {
        return this.cityName;
    }
    public Address(String streetName, String cityName, String postalCode){
        this.streetName = streetName;
        this.cityName = cityName;
        this.postalCode = postalCode;
    }

    public Address (String address){
        this.streetName = address;
    }

    public Address(final Builder builder) {
        this.cityName = builder.cityname;
        this.postalCode = builder.postalcode;
        this.streetName = builder.streetnanme;
    }

    @Override
    public AddressDTO toDTO() {
        AddressDTO.Builder builder = new AddressDTO.Builder();
        if (streetName() != null) {
            builder.withStreetName(streetName());
        }
        if (postalCode() != null) {
            builder.withPostalCode(postalCode());
        }
        if (cityName() != null) {
            builder.withCityName(cityName());
        }
        return builder.build();
    }

    public static class Builder {

        private String cityname;
        private String streetnanme;
        private String postalcode;

        public Address.Builder withCityName(final String cityname) {
            this.cityname = cityname;
            return this;
        }
        public Address.Builder withStreetName(final String streetname) {
            this.streetnanme = streetname;
            return this;
        }
        public Address.Builder withPostalCode(final String postalCode) {
            this.postalcode = postalCode;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }
}
