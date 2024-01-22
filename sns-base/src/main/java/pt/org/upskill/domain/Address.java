package pt.org.upskill.domain;

import pt.org.upskill.dto.AddressDTO;
import pt.org.upskill.dto.BrandDTO;
import pt.org.upskill.dto.DTOable;

public class Address implements DTOable {
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

    public Address(String streetName, String postalCode, String cityName) {
        this.streetName = streetName;
        this.postalCode = postalCode;
        this.cityName = cityName;
    }

    @Override
    public Object toDTO() {
        AddressDTO dto = new AddressDTO.Builder()
                .withStreetName(streetName())
                .withPostalCode(postalCode())
                .withCityName(cityName())
                .build();
        return dto;
    }
}
